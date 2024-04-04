package food.app.activity.Response;

import java.util.List;

import food.app.activity.Models.CategoryModel;

public class PaymentResponse {
    private String status;
    private String message;

    private Data data;

    //DEFINE SUB CLASS
    public class Data {
        private String transactionID;
        private String hashID;
        private String paymentURL;

        public Data() {
        }

        public Data(String transactionID, String hashID, String paymentURL) {
            this.transactionID = transactionID;
            this.hashID = hashID;
            this.paymentURL = paymentURL;
        }

        public String getTransactionID() {
            return transactionID;
        }

        public void setTransactionID(String transactionID) {
            this.transactionID = transactionID;
        }

        public String getHashID() {
            return hashID;
        }

        public void setHashID(String hashID) {
            this.hashID = hashID;
        }

        public String getPaymentURL() {
            return paymentURL;
        }

        public void setPaymentURL(String paymentURL) {
            this.paymentURL = paymentURL;
        }
    }

    public PaymentResponse() {
    }


    public PaymentResponse(String status, String message, Data data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
