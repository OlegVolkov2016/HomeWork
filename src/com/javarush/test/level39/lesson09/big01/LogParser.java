package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.IPQuery;

import java.io.*;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {
    private Path logDir;

    public LogParser(Path logDir)
    {
        this.logDir = logDir;
    }

    private List<File> getFileTree(Path logDir) {
        List<File> fileList = new ArrayList<>();
        File rootDir = new File(logDir.toString());
        File[] subDirs = rootDir.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if (pathname.isFile() && pathname.getName().endsWith(".log")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        fileList.addAll(Arrays.asList(subDirs));
        return fileList;
    }
    private boolean isDateInInterval(Date date, Date after, Date before) {
        Date afterDate = (after == null) ? new Date(0) : after;
        Date beforeDate = (before == null) ? new Date(Long.MAX_VALUE) : before;
        return ((date.getTime() >= afterDate.getTime()) && (date.getTime() <= beforeDate.getTime()));
    }

    private class LogRecord {
        private String ip;
        private String userName;
        private Date date;
        private Event event;
        int number;
        private Status status;

        public LogRecord(String ip, String userName, Date date, Event event, int number, Status status)
        {
            this.ip = ip;
            this.userName = userName;
            this.date = date;
            this.event = event;
            this.number = number;
            this.status = status;
        }

        @Override
        public String toString()
        {
            return "LogRecord{" +
                    "ip='" + ip + '\'' +
                    ", userName='" + userName + '\'' +
                    ", date=" + date +
                    ", event=" + event +
                    ", number=" + number +
                    ", status=" + status +
                    '}';
        }
    }

    private List<LogRecord> getLogRecords (File file) {
        DateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy HH:mm:ss");
        List<LogRecord> logRecordList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\t");
                if (args.length >= 5)
                {
                    String ip = args[0];
                    String userName = args[1];
                    Date date = new Date();
                    try
                    {
                       date = dateFormat.parse(args[2]);
                    }
                    catch (ParseException e) {
                    }
                    String[] eventData = args[3].split(" ");
                    Event event = Event.valueOf(eventData[0]);
                    int number = 0;
                    if (eventData.length >= 2) {
                        try
                        {
                            number = Integer.parseInt(eventData[1]);
                        } catch (NumberFormatException e) {
                        }
                    }
                    Status status = Status.valueOf(args[4]);
                    LogRecord logRecord =  new LogRecord(ip, userName, date, event, number, status);
                    logRecordList.add(logRecord);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return logRecordList;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        Set<String> uniqueIPsSet = new HashSet<>();
        for (File file : getFileTree(logDir)) {
            List<LogRecord> logRecords = getLogRecords(file);
            for (LogRecord logRecord : logRecords) {
                if (isDateInInterval(logRecord.date, after, before)) {
                    uniqueIPsSet.add(logRecord.ip);
                }
            }
        }
        return uniqueIPsSet;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        Set<String> IPsForUser = new HashSet<>();
        if (user != null)
        {
            for (File file : getFileTree(logDir))
            {
                List<LogRecord> logRecords = getLogRecords(file);
                for (LogRecord logRecord : logRecords)
                {
                    if (isDateInInterval(logRecord.date, after, before) && (user.equals(logRecord.userName)))
                    {
                        IPsForUser.add(logRecord.ip);
                    }
                }
            }
        }
        return IPsForUser;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        Set<String> IPsForEvent = new HashSet<>();
        if (event != null)
        {
            for (File file : getFileTree(logDir))
            {
                List<LogRecord> logRecords = getLogRecords(file);
                for (LogRecord logRecord : logRecords)
                {
                    if (isDateInInterval(logRecord.date, after, before) && (event.equals(logRecord.event)))
                    {
                        IPsForEvent.add(logRecord.ip);
                    }
                }
            }
        }
        return IPsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        Set<String> IPsForStatus = new HashSet<>();
        if (status != null)
        {
            for (File file : getFileTree(logDir))
            {
                List<LogRecord> logRecords = getLogRecords(file);
                for (LogRecord logRecord : logRecords)
                {
                    if (isDateInInterval(logRecord.date, after, before) && (status.equals(logRecord.status)))
                    {
                        IPsForStatus.add(logRecord.ip);
                    }
                }
            }
        }
        return IPsForStatus;
    }
}
