package vn.edu.iuh.fit.convert;

public class AddToCartForm {
    private String productID;
    private String quantity;
    private String cusID;
    private String ordID;
    private String note;

    public AddToCartForm() {
    }

    public AddToCartForm(String productID, String quantity, String cusID, String ordID, String note) {
        this.productID = productID;
        this.quantity = quantity;
        this.cusID = cusID;
        this.ordID = ordID;
        this.note = note;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getOrdID() {
        return ordID;
    }

    public void setOrdID(String ordID) {
        this.ordID = ordID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "addToCartForm{" +
                "productID='" + productID + '\'' +
                ", quantity='" + quantity + '\'' +
                ", cusID='" + cusID + '\'' +
                ", ordID='" + ordID + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

