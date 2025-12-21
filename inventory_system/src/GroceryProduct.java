import java.util.Date;

public class GroceryProduct extends Product {

    private final Date expiryDate;
    private final boolean refrigerated;

    private GroceryProduct(Builder builder) {
        super(builder);
        this.expiryDate = builder.expiryDate;
        this.refrigerated = builder.refrigerated;
    }

    public Date getExpiryDate() { return expiryDate; }
    public boolean isRefrigerated() { return refrigerated; }

    public static class Builder extends Product.Builder<Builder> {

        private Date expiryDate;
        private boolean refrigerated;

        public Builder(String sku, String name, double price) {
            super(sku, name, price, ProductCategory.GROCERY);
        }

        public Builder expiryDate(Date date) { this.expiryDate = date; return this; }
        public Builder refrigerated(boolean r) { this.refrigerated = r; return this; }

        @Override protected Builder self() { return this; }

        @Override public GroceryProduct build() { return new GroceryProduct(this); }
    }
}