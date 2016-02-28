package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.util.Date;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
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
    }
}
