package food.app.activity.Models;

public class OrderHistoryItem {
    private int id;
    private String customerName;
    private String shippingAddress;
    private String customerPhone;
    private String email_receive;

    private int status;
    private double total_price;

    public OrderHistoryItem(int id, String customerName, String shippingAddress, String customerPhone, String email_receive, double total_price,int status) {
        this.id = id;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.customerPhone = customerPhone;
        this.email_receive = email_receive;
        this.total_price = total_price;
        this.status = status;
    }

    public double getTotal_price() {
        return total_price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getEmail_receive() {
        return email_receive;
    }

    public void setEmail_receive(String email_receive) {
        this.email_receive = email_receive;
    }
}
