package ru.job4j.html;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class HabrComParse {

    public static void main(String[] args) throws Exception {
        List<String> urlList = new ArrayList<>();
        urlList.add("https://career.habr.com/vacancies/java_developer");

        for (int i = 2; i < 6; i++) {
            urlList.add(urlList.get(0) + "?page=" + i);
        }

        for (String url : urlList) {
            Document doc = Jsoup.connect(url).get();
            Elements row = doc.select(".vacancy-card__inner");


            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

            for (Element td : row) {
                Element href = td.child(1);
                Element date = td.child(0).child(0);
                Element text = td.child(2).child(1).child(0);

                System.out.println(dateFormat.parse(date.attr("datetime")));
                System.out.println(href.attr("abs:href"));
                System.out.println(text.text());
            }
        }
    }
}
