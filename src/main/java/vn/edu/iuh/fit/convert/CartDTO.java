package vn.edu.iuh.fit.convert;

public class CartDTO {
    private long productID;
    private String productName;
    private double price;
    private String path;
    private double quantity;
    private String note;

    public CartDTO() {
    }

    public CartDTO(long productID, String productName, double price, String path, double quantity, String note) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.path = path;
        this.quantity = quantity;
        this.note = note;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", path='" + path + '\'' +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                '}';
    }
}
