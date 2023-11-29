package vn.edu.iuh.fit.convert;

public class EmployeeForm {
    private String empID;
    private String fullName;
    private String address;
    private String email;
    private String phone;
    private String dob;
    private String status;

    public EmployeeForm() {
    }

    public EmployeeForm(String empID, String fullName, String address, String email, String phone, String dob, String status) {
        this.empID = empID;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.status = status;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeeForm{" +
                "empID='" + empID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob='" + dob + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
