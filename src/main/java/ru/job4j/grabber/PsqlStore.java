package ru.job4j.grabber;

import ru.job4j.html.HabrComParse;

import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    @Override
    public void save(Post post) {
        String query;
        if (findByLink(post.getLink()) == null) {
            query = "INSERT INTO post (title, description, created, link) VALUES (?, ?, ?, ?)";
        } else {
            query = "UPDATE post SET title = ?, description = ?, created = ? WHERE link = ?";
        }

        try(PreparedStatement statement
                = cnn.prepareStatement(query)) {
        statement.setString(1, post.getTitle());
        statement.setString(2, post.getDescription());
        statement.setTimestamp(3, Timestamp.valueOf(post.getCreated()));
        statement.setString(4, post.getLink());
        statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<Post> executeStatement(String query) {
        List<Post> rsl = new LinkedList<>();

        try (Statement statement
                     = cnn.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                rsl.add(new Post(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getTimestamp(5).toLocalDateTime()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rsl;
    }


    @Override
    public List<Post> getAll() {
        return executeStatement("SELECT post_id, title, link, description, created  FROM post");
    }

    @Override
    public Post findById(int id) {
        return executeStatement(
                String.format("SELECT post_id, title, link, description, created FROM post WHERE post_id = %d", id))
                .stream().findFirst().orElse(null);
    }

    @Override
    public Post findByLink(String link) {
        return executeStatement(
                String.format("SELECT post_id, title, link, description, created FROM post WHERE link = '%s'",link))
                .stream().findFirst().orElse(null);
    }

    public static void main(String[] args) {
        Properties cfg = new Properties();
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("habrcomparse.properties")) {
            cfg.load(in);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        Store store = new PsqlStore(cfg);
        List<Post> rsl = new LinkedList<>();



        HabrComParse habr = new HabrComParse();
        try {
            rsl = habr.list("https://career.habr.com/vacancies/java_developer");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        int count = 0;
        for (Post post : rsl) {
            try {
                store.save(post);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
                count++;
            if (count == 5) {
                break;
            }

        }

        store.getAll().forEach(System.out::println);
    }
}