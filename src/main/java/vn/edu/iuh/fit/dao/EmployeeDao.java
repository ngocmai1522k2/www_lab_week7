package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDao {
    private final EntityManager manager;
    @Autowired
    public EmployeeDao(EntityManager manager) {
        this.manager = manager;
    }
    @Transactional
    public boolean add(Employee emp)
    {
        try {
            manager.persist(emp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public boolean update(Employee emp )
    {
        try {
            manager.merge(emp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public Employee get(long id)
    {
        try {
            return manager.find(Employee.class,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Transactional
    public List<Employee> getAllEmpList()
    {
        try {
            return manager.createQuery("select c from Employee c", Employee.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public boolean detele(long id)
    {
        try {
            Employee emp=manager.find(Employee.class,id);
            emp.setStatus(0);
            manager.merge(emp);;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public boolean activeEmp(long id)
    {
        try {
            Employee emp=manager.find(Employee.class,id);
            emp.setStatus(1);
            manager.merge(emp);;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public Page<Employee> getAll(int page, int size) {
        try {
            List<Employee> employees = manager.createQuery("select e from Employee e", Employee.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();

            long totalEmps = (long) manager.createQuery("select count(e) from Employee e").getSingleResult();

            return new PageImpl<>(employees, PageRequest.of(page, size), totalEmps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Transactional
    public boolean logIn(long empID, String pass) {
        try {
            String sql="SELECT * FROM employee where EmployeeID=?";
            Query query = manager.createNativeQuery(sql, Employee.class);
            query.setParameter(1, empID);
            Employee employee= (Employee) query.getSingleResult();
            //pass la so dien thoai
            if (employee != null && employee.getPhone().equalsIgnoreCase(pass)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
