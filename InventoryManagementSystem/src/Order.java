// Order.java: Builder Pattern for Orders
class Order {
    private String product;
    private int quantity;
    private String shipping;

    private Order(OrderBuilder builder) {
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.shipping = builder.shipping;
    }

    public static class OrderBuilder {
        private String product;
        private int quantity;
        private String shipping;

        public OrderBuilder setProduct(String product) {
            this.product = product;
            return this;
        }

        public OrderBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderBuilder setShipping(String shipping) {
            this.shipping = shipping;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public String toString() {
        return "Order [product=" + product + ", quantity=" + quantity + ", shipping=" + shipping + "]";
    }
} // End of Order