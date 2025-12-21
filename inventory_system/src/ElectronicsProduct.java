public class ElectronicsProduct extends Product {

    private final String brand;
    private final int warrantyPeriod;

    private ElectronicsProduct(ElectronicsBuilder builder) {
        super(builder);
        this.brand = builder.brand;
        this.warrantyPeriod = builder.warrantyPeriod;
    }

    public String getBrand() { return brand; }
    public int getWarrantyPeriod() { return warrantyPeriod; }

    public static class ElectronicsBuilder extends Product.Builder<ElectronicsBuilder> {

        private final String brand;
        private int warrantyPeriod = 12;

        public ElectronicsBuilder(String sku, String name, double price, String brand) {
            super(sku, name, price, ProductCategory.ELECTRONICS);
            this.brand = brand;
        }

        public ElectronicsBuilder warrantyPeriod(int months) {
            this.warrantyPeriod = months;
            return this;
        }

        @Override protected ElectronicsBuilder self() { return this; }

        @Override public ElectronicsProduct build() { return new ElectronicsProduct(this); }
    }
}