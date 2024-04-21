package food.app.activity.Models;

public class DeleteCartModel {
    private int productId;
    private int userId;

    public DeleteCartModel(int productId, int userId) {
        this.productId = productId;
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
