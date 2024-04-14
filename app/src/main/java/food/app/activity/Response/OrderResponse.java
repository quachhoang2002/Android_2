package food.app.activity.Response;

public class OrderResponse {
    private int id;
    private String customerName;
    private String shippingAddress;
    private String customerPhone;
    private String emailReceive;
    private Double totalPrice;
    private Integer employeeID; // Use object types for fields that can be null
    private int status;

    // Default constructor
    public OrderResponse() {
    }

    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for customerName
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter and setter for shippingAddress
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    // Getter and setter for customerPhone
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    // Getter and setter for emailReceive
    public String getEmailReceive() {
        return emailReceive;
    }

    public void setEmailReceive(String emailReceive) {
        this.emailReceive = emailReceive;
    }

    // Getter and setter for totalPrice
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Getter and setter for employeeID
    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    // Getter and setter for status
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

