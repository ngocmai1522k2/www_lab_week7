package vn.edu.iuh.fit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import vn.edu.iuh.fit.dao.EmployeeDao;
import vn.edu.iuh.fit.dao.OrderDao;
import vn.edu.iuh.fit.dao.OrderDetailDao;
import vn.edu.iuh.fit.dao.ProductDao;

@SpringBootApplication
public class WwwLabWeek7Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context=SpringApplication.run(WwwLabWeek7Application.class, args);
        EmployeeDao employeeDao =context.getBean(EmployeeDao.class);
        OrderDao orderDao =context.getBean(OrderDao.class);
        OrderDetailDao orderDetailDao =context.getBean(OrderDetailDao.class);
        ProductDao productDao =context.getBean(ProductDao.class);

//        System.out.println(employeeDao.get(2));
//        employeeDao.getAllEmpList().forEach(e->System.out.println(e.toString()));
//        System.out.println(employeeDao.detele(50));
//               System.out.println(orderDao.add(new Order(200, LocalDateTime.now(), new Employee(1), new Customer(2)),
//                new OrderDetail(new Product(4), "", 2)));
        /* orderDao.getOrderByDate().entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "\nSo luong: " + entry.getValue());
        });*/
        /*orderDao.getOrderByMonth().entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "\nSo luong: " + entry.getValue());
        });*/
        /*orderDao.getOrderByEmployeeForMonth().entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "\nSo luong: " + entry.getValue());
        });*/
//        orderDetailDao.getOrderDetailByOrderID(402).forEach(od->System.out.println(od.toString()));
//        System.out.println(orderDetailDao.calcTotalPrice(402));
//        System.out.println(productDao.detele(6));
        productDao.getProductPricePaths().forEach(pp->System.out.println(pp.toString()));
    }

}
