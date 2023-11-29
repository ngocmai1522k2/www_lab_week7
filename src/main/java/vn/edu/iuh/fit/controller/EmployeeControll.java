package vn.edu.iuh.fit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.convert.EmployeeForm;
import vn.edu.iuh.fit.dao.EmployeeDao;
import vn.edu.iuh.fit.entity.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class EmployeeControll {
    private final EmployeeDao employeeDao;
    @Autowired
    public EmployeeControll(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    @PostMapping("/admin-login")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        long empID=Long.parseLong(username);
        // Thực hiện kiểm tra đăng nhập ở đây, ví dụ:
        if (employeeDao.logIn(empID, password)) {
            model.addAttribute("empID", empID);
            return "redirect:/employee/" + empID;
        } else {
            // Đăng nhập thất bại, gửi thông báo lỗi
            model.addAttribute("error", "Thông tin đăng nhập không đúng");
            return "admin-login";
        }
    }
    @GetMapping("/employee/{empID}")
    public String showAllProductForCus(@PathVariable Long empID,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "3") int size,
                                       Model model) {
        Page<Employee> employees = employeeDao.getAll(page, size);
        model.addAttribute("employees", employees.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", employees.getTotalPages());

        return "employee";
    }
    @GetMapping("/deleteEmp/{empDelID}/{empID}")
    public String deleteEmp(@PathVariable Long empID, @PathVariable Long empDelID) {
        employeeDao.detele(empDelID);
        return "redirect:/employee/"+empID;
    }
    @GetMapping("/updateEmp/{empUpID}/{empID}")
    public String showUpdateEmpForm(@PathVariable Long empID,@PathVariable Long empUpID ,Model model) {
        Employee employee=employeeDao.get(empUpID);
        model.addAttribute("employee", employee);
        model.addAttribute("empID", empID);
        return "update-employee";
    }
    @PostMapping("/updateEmp/{empID}")
    public String updateEmp(@ModelAttribute EmployeeForm employeeForm, @PathVariable Long empID) {
        long empUpID=Long.parseLong(employeeForm.getEmpID());
        int status=Integer.parseInt(employeeForm.getStatus());
        // Định dạng chuỗi ngày sinh
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển chuỗi ngày sinh thành đối tượng LocalDateTime
        LocalDateTime dob = LocalDate.parse(employeeForm.getDob(), formatter).atStartOfDay();
        employeeDao.update(new Employee(empUpID, employeeForm.getAddress(), dob, employeeForm.getEmail(), employeeForm.getFullName(), employeeForm.getPhone(), status));
        return "redirect:/employee/"+empID;
    }
    @GetMapping("/addEmp/{empID}")
    public String showAddEmpForm(@PathVariable Long empID, Model model) {
        model.addAttribute("empID", empID);
        return "add-employee";
    }
    @PostMapping("/addEmp/{empID}")
    public String addEmp(@PathVariable Long empID, @ModelAttribute EmployeeForm employeeForm) {
        long empAddID=Long.parseLong(employeeForm.getEmpID());
        int status=Integer.parseInt(employeeForm.getStatus());
        // Định dạng chuỗi ngày sinh
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển chuỗi ngày sinh thành đối tượng LocalDateTime
        LocalDateTime dob = LocalDate.parse(employeeForm.getDob(), formatter).atStartOfDay();

        employeeDao.add(new Employee(empAddID, employeeForm.getAddress(), dob, employeeForm.getEmail(), employeeForm.getFullName(), employeeForm.getPhone(), status));
        return "redirect:/employee/"+empID;
    }

}
