package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String rsl = "";

        try {
            rsl = Files.readString(Path.of(cachingDir, key));
            put(key, rsl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rsl;
    }

    public static void main(String[] args) {
        DirFileCache cache = new DirFileCache("./src" + File.separator + "data" + File.separator);
        System.out.println(cache.get("Names.txt"));
        cache.cache.forEach((k, v) -> System.out.println(k + " : " + v.get()));
    }
}
