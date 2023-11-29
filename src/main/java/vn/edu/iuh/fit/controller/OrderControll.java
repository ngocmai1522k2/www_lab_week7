package vn.edu.iuh.fit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.convert.AddToCartForm;
import vn.edu.iuh.fit.convert.CartDTO;
import vn.edu.iuh.fit.convert.ProductPricePath;
import vn.edu.iuh.fit.dao.OrderDao;
import vn.edu.iuh.fit.dao.OrderDetailDao;
import vn.edu.iuh.fit.dao.ProductDao;
import vn.edu.iuh.fit.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OrderControll {
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final OrderDetailDao orderDetailDao;
    @Autowired
    public OrderControll(OrderDao orderDao, ProductDao productDao, OrderDetailDao orderDetailDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.orderDetailDao = orderDetailDao;
    }


    @GetMapping("/addToCart/{cusID}/{productID}")
    public String showAddToCartForm(@PathVariable Long cusID, @PathVariable Long productID , Model model) {
        Product product=productDao.get(productID);
        model.addAttribute("product", product);
        model.addAttribute("cusID", cusID);
        return "add-to-cart";
    }
    @PostMapping("/addToCart")
    public String addToCart(@ModelAttribute AddToCartForm addToCartForm) {
        long cusID=Long.parseLong(addToCartForm.getCusID());
        long productID=Long.parseLong(addToCartForm.getProductID());
        long ordID=Long.parseLong(addToCartForm.getOrdID());
        int quantity=Integer.parseInt(addToCartForm.getQuantity());
        System.out.println(orderDao.add(new Order(ordID, LocalDateTime.now(), new Employee(9), new Customer(cusID)),
                new OrderDetail(new Product(productID), addToCartForm.getNote(), quantity)));

        return "redirect:/mycart/"+cusID;
    }
    @GetMapping("/mycart/{cusID}")
    public String showMyCart(@PathVariable Long cusID, Model model) {
        List<OrderDetail> orderDetails=orderDao.getLastOrder(cusID);
        if (orderDetails!=null&&!orderDetails.isEmpty()){
            List<CartDTO> cart=new ArrayList<>();
            for (OrderDetail od:orderDetails) {
                ProductPricePath productPricePath=productDao.getProductPricePathsOfProduct(od.getProduct().getProduct_id());
                cart.add(new CartDTO(od.getProduct().getProduct_id(), productPricePath.getName(), productPricePath.getPrice(),
                        productPricePath.getPath(), od.getQuantity(), od.getNote()));
            }
            double total= orderDetailDao.calcTotalPrice(orderDetails.get(0).getOrder().getOrder_id());
            model.addAttribute("cart", cart);
            model.addAttribute("total", total);
            model.addAttribute("cusID", cusID);
            return "mycart";

        }else {
            return "empty-cart";
        }
    }
    @GetMapping("/ordByDate")
    public String showOrderToDate(Model model) {
        Map<LocalDateTime,Long> map=orderDao.getOrderByDate();
        model.addAttribute("map", map);
        return "order-by-date";
    }
    @GetMapping("/ordByEmpInMonth")
    public String showOrderToEmployeeInMonthForm() {
        return "chose-month-year";
    }
    @PostMapping ("/ordByEmpInMonth")
    public String OrderToEmployeeInMonth(@RequestParam String selectedYear, @RequestParam String selectedMonth, Model model) {
        Map<String, Long> map=orderDao.getOrderByEmployeeForMonth(Integer.parseInt(selectedYear),
                                Integer.parseInt(selectedMonth));
        if (map==null||map.isEmpty()){
            // Đăng nhập thất bại, gửi thông báo lỗi
            model.addAttribute("error", "Không có đơn hàng nào trong tháng bạn yêu cầu");
            return "chose-month-year";
        }else {
            return "redirect:/ordByEmpInMonth/" + selectedMonth + '/' + selectedYear;
        }
    }
    @GetMapping("/ordByEmpInMonth/{month}/{year}")
    public String showOrderToEmployeeInMonth(@PathVariable int month, @PathVariable int year, Model model) {
        Map<String, Long> map=orderDao.getOrderByEmployeeForMonth(year, month);
        model.addAttribute("map", map);
        return "order-by-emp";
    }

}
