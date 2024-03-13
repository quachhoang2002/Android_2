package food.app.activity;

    public class CartItem {
        private int imageResId;
        private String productName;
        private double price;
        private int quantity;

        public CartItem(int imageResId, String productName, double price, int quantity) {
            this.imageResId = imageResId;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        public int getImageResId() {
            return imageResId;
        }

        public String getProductName() {
            return productName;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

