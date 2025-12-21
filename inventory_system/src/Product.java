public abstract class Product {
    private final String sku;
    private final String name;
    private final double price;
    private int quantity;
    private final int threshold;
    private final ProductCategory category;

    protected Product(Builder<?> builder) {
        this.sku = builder.sku;
        this.name = builder.name;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.threshold = builder.threshold;
        this.category = builder.category;
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getThreshold() { return threshold; }
    public ProductCategory getCategory() { return category; }

    public static abstract class Builder<T extends Builder<T>> {
        private final String sku;
        private final String name;
        private final double price;
        private final ProductCategory category;

        private int quantity = 0;
        private int threshold = 10;

        public Builder(String sku, String name, double price, ProductCategory category) {
            this.sku = sku;
            this.name = name;
            this.price = price;
            this.category = category;
        }

        public T quantity(int quantity) { this.quantity = quantity; return self(); }
        public T threshold(int threshold) { this.threshold = threshold; return self(); }

        protected abstract T self();
        public abstract Product build();
    }
}