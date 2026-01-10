public class Main {
    public static void main(String[] args) {

        CacheStorage<String, String> cache =
                new InMemoryCacheStorage<>(5);

        DBStorage<String, String> db =
                new SimpleDBStorage<>();

        Cache<String, String> system =
                new Cache<>(cache, db,
                        new WriteThroughPolicy<>(),
                        new LRUEvictionAlgorithm<>(),
                        4);

        system.updateData("A", "Apple").join();
        system.updateData("B", "Banana").join();
        system.updateData("C", "Cherry").join();
        system.updateData("D", "Durian").join();
        system.updateData("E", "Elderberry").join();
        system.updateData("F", "Fig").join();

        System.out.println(system.accessData("F").join());
        system.shutdown();
    }
}