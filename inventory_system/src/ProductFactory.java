public class ProductFactory {

    public Product createProduct(ProductCategory category,
                                 String sku, String name,
                                 double price, int quantity, int threshold) {

        switch (category) {
            case ELECTRONICS:
                return new ElectronicsProduct.ElectronicsBuilder(sku, name, price, "GenericBrand")
                        .quantity(quantity)
                        .threshold(threshold)
                        .build();

            case CLOTHING:
                return new ClothingProduct.Builder(sku, name, price)
                        .quantity(quantity)
                        .threshold(threshold)
                        .build();

            case GROCERY:
                return new GroceryProduct.Builder(sku, name, price)
                        .quantity(quantity)
                        .threshold(threshold)
                        .build();

            default:
                throw new IllegalArgumentException("Unsupported category");
        }
    }
}