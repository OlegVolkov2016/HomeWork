package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));

        // Task 1
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println();
        Set<String> uniqueIPs = logParser.getUniqueIPs(null, null);
        for (String uniqueIP : uniqueIPs)
        {
            System.out.println(uniqueIP);
        }
        System.out.println();
        Set<String> IPsForUser = logParser.getIPsForUser("Eduard Petrovich Morozko", null, new Date());
        for (String IPForUser : IPsForUser)
        {
            System.out.println(IPForUser);
        }
        System.out.println();
        Set<String> IPsForEvent = logParser.getIPsForEvent(Event.LOGIN, null, new Date());
        for (String IPForEvent : IPsForEvent)
        {
            System.out.println(IPForEvent);
        }
        System.out.println();
        Set<String> IPsForStatus = logParser.getIPsForStatus(Status.FAILED, null, new Date());
        for (String IPForStatus : IPsForStatus)
        {
            System.out.println(IPForStatus);
        }
        System.out.println();

        // Task 2
        Set<String> allUsers = logParser.getAllUsers();
        for (String user : allUsers)
        {
            System.out.println(user);
        }
        System.out.println();
        System.out.println(logParser.getNumberOfUsers(null, new Date()));
        System.out.println();
        System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", null, new Date()));
        System.out.println();
        Set<String> usersForIP = logParser.getUsersForIP("127.0.0.1", null, new Date());
        for (String user : usersForIP)
        {
            System.out.println(user);
        }
        System.out.println();
        Set<String> loggedUsers = logParser.getLoggedUsers(null, new Date());
        for (String user : loggedUsers)
        {
            System.out.println(user);
        }
        System.out.println();
        Set<String> downloadedPluginUsers = logParser.getDownloadedPluginUsers(null, new Date());
        for (String user : downloadedPluginUsers)
        {
            System.out.println(user);
        }
        System.out.println();
        Set<String> wroteMessageUsers = logParser.getWroteMessageUsers(null, new Date());
        for (String user : wroteMessageUsers)
        {
            System.out.println(user);
        }
        System.out.println();
        Set<String> solvedTaskUsers = logParser.getSolvedTaskUsers(null, new Date());
        for (String user : solvedTaskUsers)
        {
            System.out.println(user);
        }
        System.out.println();
        solvedTaskUsers = logParser.getSolvedTaskUsers(null, new Date(), 18);
        for (String user : solvedTaskUsers)
        {
            System.out.println(user);
        }
        System.out.println();
        Set<String> doneTaskUsers = logParser.getDoneTaskUsers(null, new Date());
        for (String user : doneTaskUsers)
        {
            System.out.println(user);
        }
        System.out.println();
        doneTaskUsers = logParser.getDoneTaskUsers(null, new Date(), 15);
        for (String user : doneTaskUsers)
        {
            System.out.println(user);
        }
        System.out.println();

        // Task 3
        Set<Date> datesForUserAndEvent = logParser.getDatesForUserAndEvent("Eduard Petrovich Morozko", Event.WRITE_MESSAGE, null, new Date());
        for (Date date : datesForUserAndEvent)
        {
            System.out.println(date);
        }
        System.out.println();
        Set<Date> datesWhenSomethingFailed = logParser.getDatesWhenSomethingFailed(null, new Date());
        for (Date date : datesWhenSomethingFailed)
        {
            System.out.println(date);
        }
        System.out.println();
        Set<Date> datesWhenErrorHappened = logParser.getDatesWhenErrorHappened(null, new Date());
        for (Date date : datesWhenErrorHappened)
        {
            System.out.println(date);
        }
        System.out.println();
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, new Date()));
        System.out.println();
        System.out.println(logParser.getDateWhenUserSolvedTask("Vasya Pupkin", 18, null, new Date()));
        System.out.println();
        System.out.println(logParser.getDateWhenUserDoneTask("Vasya Pupkin", 18, null, new Date()));
        System.out.println();
        Set<Date> datesWhenUserWroteMessage = logParser.getDatesWhenUserWroteMessage("Eduard Petrovich Morozko", null, new Date());
        for (Date date : datesWhenUserWroteMessage)
        {
            System.out.println(date);
        }
        System.out.println();
        Set<Date> datesWhenUserDownloadedPlugin = logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, new Date());
        for (Date date : datesWhenUserDownloadedPlugin)
        {
            System.out.println(date);
        }
        System.out.println();

        // Task 4
        System.out.println(logParser.getNumberOfAllEvents(null, new Date()));
        System.out.println();
        Set<Event> allEvents = logParser.getAllEvents(null, new Date());
        for (Event event : allEvents)
        {
            System.out.println(event);
        }
        System.out.println();
        Set<Event> eventsForIP = logParser.getEventsForIP("146.34.15.5", null, new Date());
        for (Event event : eventsForIP)
        {
            System.out.println(event);
        }
        System.out.println();
        Set<Event> failedEvents = logParser.getFailedEvents(null, new Date());
        for (Event event : failedEvents)
        {
            System.out.println(event);
        }
        System.out.println();
        Set<Event> errorEvents = logParser.getErrorEvents(null, new Date());
        for (Event event : errorEvents)
        {
            System.out.println(event);
        }
        System.out.println();
        System.out.println(logParser.getNumberOfAttemptToSolveTask(18, null, new Date()));
        System.out.println();
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(15, null, new Date()));
        System.out.println();
        Map<Integer, Integer> solvedTasksAndTheirNumber = logParser.getAllSolvedTasksAndTheirNumber(null, new Date());
        for (Map.Entry<Integer, Integer> entry : solvedTasksAndTheirNumber.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
        Map<Integer, Integer> doneTasksAndTheirNumber = logParser.getAllDoneTasksAndTheirNumber(null, new Date());
        for (Map.Entry<Integer, Integer> entry : doneTasksAndTheirNumber.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();

        // Task 5
        Set<Object> result;
        result = logParser.execute("get ip");
        for (Object object : result) {
            System.out.println(object.toString());
        }
        System.out.println();
        result = logParser.execute("get user");
        for (Object object : result) {
            System.out.println(object.toString());
        }
        System.out.println();
        result = logParser.execute("get date");
        for (Object object : result) {
            System.out.println(object.toString());
        }
        System.out.println();
        result = logParser.execute("get event");
        for (Object object : result) {
            System.out.println(object.toString());
        }
        System.out.println();
        result = logParser.execute("get status");
        for (Object object : result) {
            System.out.println(object.toString());
        }
        System.out.println();

        // Task 6
        result = logParser.execute("get ip for user = \"Vasya Pupkin\"");
        for (Object object : result) {
            System.out.println(object.toString());
        }
        System.out.println();
        result = logParser.execute("get user for status = \"OK\"");
        for (Object object : result) {
            System.out.println(object.toString());
        }
        System.out.println();
        result = logParser.execute("get date for ip = \"127.0.0.1\"");
        for (Object object : result) {
            System.out.println(object.toString());
        }
        System.out.println();
        result = logParser.execute("get event for date = \"30.01.2014 12:56:22\"");
        for (Object object : result) {
            System.out.println(object.toString());
        }
        System.out.println();
        result = logParser.execute("get status for event = \"WRITE_MESSAGE\"");
        for (Object object : result) {
            System.out.println(object.toString());
        }
        System.out.println();

        // Task 7
        result = logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between "
                + "\"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"");
        for (Object object : result) {
            System.out.println(object.toString());
        }
    }
}
