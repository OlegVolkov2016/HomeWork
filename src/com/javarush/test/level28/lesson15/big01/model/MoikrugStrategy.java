package com.javarush.test.level28.lesson15.big01.model;

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
public class MoikrugStrategy implements Strategy {
        private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
//    private static final String URL_FORMAT = "http://javarush.ru/vacancies?page=%2$d&q=java+%1$s";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        try
        {
            Document document;
            int pageCounter = 1;

            while(true)
            {
                document = getDocument(searchString, pageCounter++);
                if(document == null) break;

                Elements elements = document.getElementsByClass("job");
                if(elements.size() == 0) break;

                for(Element element : elements)
                {
                    Vacancy vacancy = new Vacancy();

                    vacancy.setUrl( element.getElementsByClass("title").first().child(0).attr("abs:href") );
                    vacancy.setTitle( element.getElementsByAttributeValue("class", "title").text() );
                    vacancy.setCity( element.getElementsByAttributeValue("class", "location").text() );
                    vacancy.setSalary( element.getElementsByAttributeValue("class", "salary").text() );
                    vacancy.setSiteName("https://moikrug.ru/");
                    vacancy.setCompanyName( element.getElementsByAttributeValue("class", "company_name").first().child(0).text() );

                    vacancies.add(vacancy);
                }
            }
        }
        catch (Exception e)
        {

        }

        return vacancies;
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
