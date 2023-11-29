package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.entity.Order;
import vn.edu.iuh.fit.entity.OrderDetail;
import vn.edu.iuh.fit.entity.ProductPrice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDao {
    private final EntityManager manager;
    @Autowired
    public OrderDao(EntityManager manager) {
        this.manager = manager;
    }
    @Transactional
    public boolean add(Order order, OrderDetail orderDetail)
    {
        ProductPrice pp = new ProductPrice();
        double price=0;
        try {
            //thêm order
            manager.merge(order);
            //lấy thông tin giá của product để gán cho price của orderdetail
            String nativeQuery = "SELECT *\n" +
                    "FROM productprice \n" +
                    "WHERE productprice.ProductID =? \n" +
                    "AND productprice.PriceDate = (\n" +
                    "    SELECT MAX(productprice.PriceDate)\n" +
                    "    FROM productprice\n" +
                    "    WHERE productprice.ProductID =? \n" +
                    ");";
            Query query = manager.createNativeQuery(nativeQuery, ProductPrice.class);
            query.setParameter(1, orderDetail.getProduct().getProduct_id());
            query.setParameter(2, orderDetail.getProduct().getProduct_id());
            pp= (ProductPrice) query.getSingleResult();
            price= (double) pp.getPrice();
            OrderDetail ord=new OrderDetail(order, orderDetail.getProduct(),
                    orderDetail.getNote(),price, orderDetail.getQuantity());
            manager.merge(ord);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public Map<LocalDateTime,Long> getOrderByDate(){
        Map<LocalDateTime,Long> map= new HashMap<>();
        try {
            String nativeQuery= "SELECT DATE(OrderDate) AS OrderDay, COUNT(*) AS TotalOrders\n" +
                    "FROM orders\n" +
                    "GROUP BY DATE(OrderDate)\n" +
                    "ORDER BY OrderDay;";
            List<Object[]> list= manager.createNativeQuery(nativeQuery, Object[].class).getResultList();
            for (Object[] objects : list) {
                map.put(((java.sql.Date) objects[0]).toLocalDate().atStartOfDay(), (long)objects[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @Transactional
    public Map<String,Long> getOrderByMonth(){
        Map<String,Long> map= new HashMap<>();
        try {
            String nativeQuery= "SELECT DATE_FORMAT(OrderDate, '%Y-%m') AS OrderMonth, COUNT(*) AS TotalOrders\n" +
                    "FROM orders\n" +
                    "GROUP BY OrderMonth\n" +
                    "ORDER BY OrderMonth";
            List<Object[]> list= manager.createNativeQuery(nativeQuery, Object[].class).getResultList();
            for (Object[] objects : list) {
                map.put((String) objects[0], (Long) objects[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }
    @Transactional
    public Map<String,Long> getOrderByEmployeeForMonth(int year, int month){
        Map<String,Long> map= new HashMap<>();
        try {
            String nativeQuery= "SELECT \n" +
                    "    employee.EmployeeID, \n" +
                    "    employee.FullName, \n" +
                    "    COUNT(orders.OrderID) AS TotalOrders\n" +
                    "FROM \n" +
                    "    employee \n" +
                    "INNER JOIN \n" +
                    "    orders ON employee.EmployeeID = orders.EmployeeID\n" +
                    "WHERE \n" +
                    "    YEAR(orders.OrderDate) = ? -- Lọc theo năm hiện tại\n" +
                    "    AND MONTH(orders.OrderDate) = ? -- Lọc theo tháng hiện tại\n" +
                    "GROUP BY \n" +
                    "    employee.EmployeeID, \n" +
                    "    employee.FullName\n" +
                    "ORDER BY \n" +
                    "    TotalOrders DESC;";
            Query query= manager.createNativeQuery(nativeQuery, Object[].class);
            query.setParameter(1, year);
            query.setParameter(2, month);
            List<Object[]> list=query.getResultList();
            if (list==null||list.isEmpty()) return null;
            for (Object[] objects : list) {
                map.put((String) objects[1],(Long) objects[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @Transactional
    public List<OrderDetail> getLastOrder(long cusID){
        List<OrderDetail> list= new ArrayList<>();
        try {
            String nativeQuery= "SELECT * FROM orderdetail WHERE OrderID=(\n" +
                    "SELECT MAX(OrderID) FROM orders WHERE CustomerID=?\n" +
                    ")\n";
            Query query= manager.createNativeQuery(nativeQuery, OrderDetail.class);
            query.setParameter(1, cusID);
            list=query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
