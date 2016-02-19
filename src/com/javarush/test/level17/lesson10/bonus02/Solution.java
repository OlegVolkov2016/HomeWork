package com.javarush.test.level17.lesson10.bonus02;

import com.javarush.test.level17.lesson10.bonus01.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
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
                    if ((args.length - 1) % 3 == 0) {
                        try {
                            for (int i = 1; i < args.length; i+=3) {
                                String name = args[i];
                                Sex sex = ("м".equals(args[i+1]) ? Sex.MALE : Sex.FEMALE);
                                Date bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i+2]);
                                synchronized (allPeople) {
                                    if (sex == Sex.MALE) {
                                        allPeople.add(Person.createMale(name, bd));
                                    } else {
                                        allPeople.add(Person.createFemale(name, bd));
                                    }
                                    System.out.println(allPeople.size() - 1);
                                }
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case "-u" : {
                    if ((args.length - 1) % 4 == 0) {
                        try {
                            for (int i = 1; i < args.length; i+=4) {
                                int id = Integer.parseInt(args[i]);
                                String name = args[i+1];
                                Sex sex = ("м".equals(args[i+2]) ? Sex.MALE : Sex.FEMALE);
                                Date bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i+3]);
                                synchronized (allPeople) {
                                    if ((id >= 0) && (id < allPeople.size())) {
                                        Person person = allPeople.get(id);
                                        person.setName(name);
                                        person.setSex(sex);
                                        person.setBirthDay(bd);
                                    }
                                }
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
                        for (int i = 1; i < args.length; i++) {
                            int id = Integer.parseInt(args[i]);
                            synchronized (allPeople) {
                                if ((id >= 0) && (id < allPeople.size())) {
                                    Person person = allPeople.get(id);
                                    person.setName(null);
                                    person.setSex(null);
                                    person.setBirthDay(null);
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "-i" : {
                    try {
                        for (int i = 1; i < args.length; i++) {
                            int id = Integer.parseInt(args[i]);
                            if ((id >= 0) && (id < allPeople.size()))
                            {
                                Person person = allPeople.get(id);
                                System.out.println(person.getName() + " " + (person.getSex() == Sex.MALE ? "м" : "ж")
                                        + " " + new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(person.getBirthDay()));
                            }
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
