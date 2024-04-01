package food.app.activity.Request;

public class PaymentRequest {
    private int orderID;
    private String orderInfo;

    public PaymentRequest() {
    }

    //setters and getters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }


}
