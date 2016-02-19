package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        if (args.length > 1) {
            switch (args[0]) {
                case "-c" : {
                    if (args.length >= 4) {
                        try {
                            String name = args[1];
                            Sex sex = ("м".equals(args[2]) ? Sex.MALE : Sex.FEMALE);
                            Date bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);
                            if (sex == Sex.MALE) {
                                allPeople.add(Person.createMale(name, bd));
                            } else {
                                allPeople.add(Person.createFemale(name, bd));
                            }
                            System.out.println(allPeople.size() - 1);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case "-u" : {
                    if (args.length >= 5) {
                        try {
                            int id = Integer.parseInt(args[1]);
                            String name = args[2];
                            Sex sex = ("м".equals(args[3]) ? Sex.MALE : Sex.FEMALE);
                            Date bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4]);
                            if ((id >= 0) && (id < allPeople.size())) {
                                Person person = allPeople.get(id);
                                person.setName(name);
                                person.setSex(sex);
                                person.setBirthDay(bd);
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case "-d" : {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if ((id >= 0) && (id < allPeople.size())) {
                            Person person = allPeople.get(id);
                            person.setName(null);
                            person.setSex(null);
                            person.setBirthDay(null);
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "-i" : {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if ((id >= 0) && (id < allPeople.size()))
                        {
                            Person person = allPeople.get(id);
                            System.out.println(person.getName() + " " + (person.getSex() == Sex.MALE ? "м" : "ж")
                                    + " " + new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(person.getBirthDay()));
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
