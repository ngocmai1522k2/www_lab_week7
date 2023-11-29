# www_lab_week7
# Lab7 Spring Boot Project Shopping online 
Họ tên: [Lê Thị Ngọc Mai]

MSSV: [20005501]
## Mô tả

Dự án Spring Boot với diagram 
![image](https://github.com/ngocmai1522k2/www_lab_week7/assets/144517477/3b701c85-f2c1-40e1-b793-d84ef19f5e22)


## Yêu cầu

Đảm bảo bạn đã cài đặt đầy đủ các thành phần sau:
- Java Development Kit (JDK)
- Maven
- MariaDB

## Công nghệ sử dụng

- Spring Boot: Framework để xây dựng ứng dụng doanh nghiệp dựa trên Java.
- MariaDB: Hệ thống quản lý cơ sở dữ liệu quan hệ.
## Cấu hình application.properties:

```properties
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://localhost:3306/lab7
spring.datasource.username=root
spring.datasource.password=20005501
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8089
spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```
## Các Package

### 1. Package "entity"
   - Chứa các đối tượng thực thể như Customer, Employee, Order, OrderDetail, Product, ProductImage, ProductPrice.

### 2. Package "dao"
   Chứa các lớp Data Access Object (DAO) đảm nhiệm truy cập và thao tác dữ liệu trong cơ sở dữ liệu. Các lớp DAO bao gồm 
   - CustomerDao (chứa hàm logIn),
   - PostDao (chứa các hàm logIn, add, update, delete, activeEmp, get, getAllEmpList, getAll),
   - OrderDao (chứa hàm add, getOrderByDate, getOrderByMonth, getOrderByEmployeeForMonth, getLastOrder),
   - OrderDetailDao (chứa các hàm calcTotalPrice, getOrderDetailByOrderID),
   - ProductDao (chứa các hàm add, update, delete, activeProduct, get, getAllProductList, getProductPricePathsOfProduct, getProductPricePaths, getAll).

### 3. Package "controllers"
   - Chứa các lớp controller như CustomerController, EmployeeController, OrderController, ProductController.

### 4. Package "convert"
   - Chứa các lớp đảm nhiệm chuyển đổi dữ liệu như AddToCartForm, CartDTO, EmployeeForm, ProductPricePath




## Chạy Ứng dụng

- Chạy class `WwwLabWeek7Application` để khởi động ứng dụng Spring Boot. 
- Sau đó, truy cập http://localhost:8089 để xem kết quả.

## Lưu ý

- Trước khi chạy ứng dụng, đảm bảo rằng đã cấu hình đúng kết nối database trong file `application.properties`.

## Thư viện và Công nghệ sử dụng

- Spring Boot
- MariaDB
- Thymeleaf (được sử dụng trong thư mục "templates")

Tôi hoan nghênh sự đóng góp từ cộng đồng để cải thiện dự án này.

## Chạy ứng dụng
1. **Trang chủ**
![image](https://github.com/ngocmai1522k2/www_lab_week7/assets/144517477/0da8e692-ec30-49a8-86fa-9f919b8d50c9)
2. **Trang đăng ký**
![image](https://github.com/ngocmai1522k2/www_lab_week7/assets/144517477/47c5c2ef-2bb3-43fa-957b-fe494783f6ee)
3. **Trang đăng nhập**

![image](https://github.com/ngocmai1522k2/www_lab_week7/assets/144517477/e835ef20-b6d5-456d-ba48-7de052c43b66)

4. **Trang thêm sản phẩm**
   ![image](https://github.com/ngocmai1522k2/www_lab_week7/assets/144517477/bf4ff275-4b4a-4fb9-b09f-dc6bbb81b0aa)
   
6. **Trang thông tin nhân viên**
   ![image](https://github.com/ngocmai1522k2/www_lab_week7/assets/144517477/e8047659-d3b3-4e71-b7e5-b4e05bf6a7dc)
   
7. **Trang cập nhật nhân viên**
   ![image](https://github.com/ngocmai1522k2/www_lab_week7/assets/144517477/34b76d28-ead3-4750-bc36-253b83b15107)
   



## Tài liệu liên quan

- [Java EE Documentation](https://javaee.github.io/javaee-spec/)
- [Java Database Connectivity (JDBC) Documentation](https://docs.oracle.com/en/java/javase/16/docs/api/java.sql/java/sql/package-summary.html)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/index.html)
- [Git Version Control](https://git-scm.com/book/en/v2)


## Đóng góp

Nếu bạn muốn đóng góp vào dự án hoặc báo cáo lỗi, vui lòng tạo issue hoặc gửi pull request vào repository GitHub của dự án.

- GitHub Repository: [www_lab_week7](https://github.com/ngocmai1522k2/www_lab_week7)
- Tạo issue mới: [Tạo issue](https://github.com/ngocmai1522k2/www_lab_week7/issues/new)
- Gửi pull request: [Gửi pull request](https://github.com/ngocmai1522k2/www_lab_week7/compare)

Chúng tôi rất hoan nghênh mọi đóng góp từ cộng đồng!

---




