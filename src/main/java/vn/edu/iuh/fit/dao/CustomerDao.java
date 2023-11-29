package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.entity.Customer;

@Repository
public class CustomerDao {
    private final EntityManager manager;

    public CustomerDao(EntityManager manager) {
        this.manager = manager;
    }
    @Transactional
    public boolean logIn(long cusID, String pass) {
        try {
            String sql="SELECT * FROM customer where CustomerID=?";
            Query query = manager.createNativeQuery(sql, Customer.class);
            query.setParameter(1, cusID);
            Customer customer= (Customer) query.getSingleResult();
            //pass la so dien thoai
            if (customer != null && customer.getPhone().equalsIgnoreCase(pass)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
