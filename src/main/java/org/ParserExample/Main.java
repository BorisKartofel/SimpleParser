package org.ParserExample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.ConnectException;

public class Main {
    public static void main(String[] args) throws IOException {

        Document doc = null;
        try {
            doc = Jsoup.connect("https://ria.ru/lenta/").get();

            Elements elements = doc.getElementsByAttributeValue("itemprop", "itemListElement");

            for (Element element :
                    elements) {
                String content = element.getElementsByAttributeValue("itemprop", "name").attr("content");
                String reference = element.getElementsByAttributeValue("itemprop", "url").attr("href");

                System.out.printf("%s | %s%n", content, reference);
            }

        } catch (ConnectException e) {
            System.out.println("Не получилось соединиться с сайтом");
        }
    }
}