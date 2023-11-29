package vn.edu.iuh.fit.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @Column(name="OrderID")
    private long order_id;
    @Column(name="OrderDate")
 //   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") =>nguy hiem
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name="EmployeeID")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name="CustomerID")
    private Customer customer;

    public Order() {
    }

    public Order(long order_id) {
        this.order_id = order_id;
    }

    public Order(long order_id, LocalDateTime orderDate, Employee employee, Customer customer) {
        this.order_id = order_id;
        this.orderDate = orderDate;
        this.employee = employee;
        this.customer = customer;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", orderDate=" + orderDate +
                ", employee=" + employee +
                ", customer=" + customer +
                '}';
    }
}
