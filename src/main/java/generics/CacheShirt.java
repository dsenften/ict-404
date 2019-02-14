package generics;

public class CacheShirt {
    private Shirt shirt;

    public void addText(Shirt shirt) {
        this.shirt = shirt;
    }

    public Shirt getShirt() {
        return this.shirt;
    }
}
