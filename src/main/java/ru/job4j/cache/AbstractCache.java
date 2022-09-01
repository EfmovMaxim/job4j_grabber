package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    public V get(K key) {
        Boolean reloadCache = false;

        V value = cache.getOrDefault(key, new SoftReference<V>(null)).get();

        if (value == null) {
            System.out.println("Значение в кеше отсутствует");
            value = this.load(key);
            put(key, value);
        }

        return value;
    }

    protected abstract V load(K key);

}
