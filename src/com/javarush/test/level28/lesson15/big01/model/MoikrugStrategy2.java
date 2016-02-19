package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.model.Strategy;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олег Волков on 09.02.2016.
 */
public class MoikrugStrategy2 implements Strategy {
        private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
//    private static final String URL_FORMAT = "http://javarush.ru/vacancies?page=%2$d&q=java+%1$s";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        int page = 1;
        List<Vacancy> listVacancy = new ArrayList<>();
        while (true) {
            try {
                Document document = getDocument(searchString, page);
                Elements elements = document.select("div#jobs_list div.inner");
                if (document.select("div#jobs_list div.inner").isEmpty()) {
                    break;
                }
                for (Element loop : elements) {
                    Vacancy newVacancy = new Vacancy();
                    newVacancy.setTitle(loop.select("div.title").text());
                    if (!loop.select("div.salary").isEmpty()) {
                        newVacancy.setSalary(loop.select("div.salary").text());
                    } else {
                        newVacancy.setSalary("");
                    }
                    newVacancy.setCity(loop.select("span.location").text());
                    newVacancy.setCompanyName(loop.select("div.company_name a").text());
                    newVacancy.setSiteName(document.title());
                    newVacancy.setUrl("https://moikrug.ru" + loop.select("div.title a").attr("href"));
                    listVacancy.add(newVacancy);
                }
                page++;
            } catch (IOException e) {
            }
        }
        return listVacancy;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("none")
                .get();

        return document;
    }
}
