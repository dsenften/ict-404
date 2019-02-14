package generics;

public class UseMyGenericCache {

    public static void main(String[] args) {

        { // Ohne generische Klassen

            CacheString cacheString = new CacheString();
            CacheShirt cacheShirt = new CacheShirt();
        }

        { // Mit einer generischen Klasse

            Cache<String> cacheString = new Cache<>();
            Cache<Shirt> cacheShirt = new Cache<>();
        }
    }
}
