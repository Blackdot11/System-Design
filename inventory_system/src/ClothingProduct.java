public class ClothingProduct extends Product {

    private final String size;
    private final String color;

    private ClothingProduct(Builder builder) {
        super(builder);
        this.size = builder.size;
        this.color = builder.color;
    }

    public String getSize() { return size; }
    public String getColor() { return color; }

    public static class Builder extends Product.Builder<Builder> {
        private String size;
        private String color;

        public Builder(String sku, String name, double price) {
            super(sku, name, price, ProductCategory.CLOTHING);
        }

        public Builder size(String size) { this.size = size; return this; }
        public Builder color(String color) { this.color = color; return this; }

        @Override protected Builder self() { return this; }

        @Override public ClothingProduct build() { return new ClothingProduct(this); }
    }
}