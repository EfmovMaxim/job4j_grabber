package ru.job4j.html;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

public class HabrComParse implements Parse{
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz");
          //  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    public Post detail(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        /*Elements row = doc.select(".vacancy-header__date");
        Element e = row.first();
        LocalDateTime createdDate =  LocalDateTime.parse(e.child(0).child(0).attr("datetime"), dateFormat);
        */
        Elements row = doc.select(".style-ugc");
        Element e = row.first();

        return new Post(link, e.text());
    }

    public List<Post> list(String link) throws IOException {
        Document doc;
        Elements row;
        Element href;
        Post currentPost;
        List<Post> rsl = new LinkedList<>();

        List<String> urlList = new ArrayList<>();
        urlList.add(link);
        for (int i = 2; i < 3; i++) {
            urlList.add(urlList.get(0) + "?page=" + i);
        }

        for (String url : urlList) {
            doc = Jsoup.connect(url).get();
            row = doc.select(".vacancy-card__inner");

            for (Element td : row) {
                href = td.child(1);
                currentPost = detail(href.attr("abs:href"));

                currentPost.setCreated(LocalDateTime.parse(td.child(0).child(0).attr("datetime"), dateFormat));
                currentPost.setTitle(td.child(2).child(1).child(0).text());
                rsl.add(currentPost);

            }
        }
        return rsl;
    }


    public static void main(String[] args) throws Exception {

        HabrComParse habr = new HabrComParse();
        List<Post> rsl = habr.list("https://career.habr.com/vacancies/java_developer");
        rsl.forEach(System.out::println);
    }
}
