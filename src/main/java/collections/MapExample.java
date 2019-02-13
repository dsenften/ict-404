package collections;

import generics.Shirt;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapExample {

    public static void main(String[] args) {

        Map<String, Shirt> parts = new TreeMap<>();

        parts.put("S001", new Shirt("Blue Polo Shirt"));
        parts.put("S002", new Shirt("Black Shirt"));
        
        Set<String> keys = parts.keySet();
        for (String key: keys) {
            System.out.println("Part #" + key + ", " + parts.get(key));
        }
    }
}
