package vn.edu.iuh.fit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.convert.ProductPricePath;
import vn.edu.iuh.fit.dao.OrderDao;
import vn.edu.iuh.fit.dao.ProductDao;
import vn.edu.iuh.fit.entity.OrderDetail;

import java.util.List;

@Controller
public class ProductControll {
    private final ProductDao productDao;
    private final OrderDao orderDao;
    @Autowired
    public ProductControll(ProductDao productDao, OrderDao orderDao) {
        this.productDao = productDao;
        this.orderDao = orderDao;
    }

    @GetMapping("/")
    public String showAllProduct(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "4") int size,
                              Model model) {
        Page<ProductPricePath> productPricePaths = productDao.getAll(page, size);
        model.addAttribute("productPricePaths", productPricePaths.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", productPricePaths.getTotalPages());

        return "index";
    }
    @GetMapping("/{cusID}")
    public String showAllProductForCus(@PathVariable Long cusID,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "4") int size,
                              Model model) {
        List<OrderDetail> ods=orderDao.getLastOrder(cusID);
        Page<ProductPricePath> productPricePaths = productDao.getAll(page, size);
        model.addAttribute("productPricePaths", productPricePaths.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", productPricePaths.getTotalPages());
        model.addAttribute("ods", ods);

        return "index";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @GetMapping("/admin-login")
    public String showAdminLoginForm() {
        return "admin-login";
    }

}
