package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олег Волков on 09.02.2016.
 */
public class LinkedInStrategy implements Strategy {
//    private static final String URL_FORMAT = "http://www.linkedin.com/vsearch/j?keywords=java+%s";
    private static final String URL_FORMAT = "http://www.linkedin.com/vsearch/j?keywords=java+%s";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        return new ArrayList<>();
    }
}
