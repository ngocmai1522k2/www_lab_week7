package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDetailDao {
    private final EntityManager manager;
    @Autowired
    public OrderDetailDao(EntityManager manager) {
        this.manager = manager;
    }
    @Transactional
    public double calcTotalPrice(long orderID){
        double totalPrice=0;
        List<OrderDetail> list= new ArrayList<>();
        try {
            String nativeQuery= "SELECT * \n" +
                    "FROM orderdetail\n" +
                    "WHERE OrderID=?";
            Query query = manager.createNativeQuery(nativeQuery, OrderDetail.class);
            query.setParameter(1, orderID);
            list=query.getResultList();
            for (OrderDetail orderDetail:list) {
                totalPrice+=orderDetail.getPrice()*orderDetail.getQuantity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;

    }
    @Transactional
    public List<OrderDetail> getOrderDetailByOrderID(long orderID){
        List<OrderDetail> list= new ArrayList<>();
        try {
            String nativeQuery= "SELECT * \n" +
                    "FROM orderdetail\n" +
                    "WHERE OrderID=?";
            Query query = manager.createNativeQuery(nativeQuery, OrderDetail.class);
            query.setParameter(1, orderID);
            list=query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
