package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.*;

import java.io.*;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery
{
    private Path logDir;

    private List<LogRecord> logRecords;

    public LogParser(Path logDir)
    {
        this.logDir = logDir;
        logRecords = new ArrayList<>();
        for (File file : getFileTree(logDir)) {
            logRecords.addAll(getLogRecords(file));
        }
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
        for (LogRecord logRecord : logRecords)
        {
            if (isDateInInterval(logRecord.date, after, before))
            {
                uniqueIPsSet.add(logRecord.ip);
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
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (user.equals(logRecord.userName)))
                {
                    IPsForUser.add(logRecord.ip);
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
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (event.equals(logRecord.event)))
                {
                    IPsForEvent.add(logRecord.ip);
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
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (status.equals(logRecord.status)))
                {
                    IPsForStatus.add(logRecord.ip);
                }
            }
        }
        return IPsForStatus;
    }


    @Override
    public Set<String> getAllUsers()
    {
        Set<String> allUsers = new HashSet<>();
        for (LogRecord logRecord : logRecords)
        {
            allUsers.add(logRecord.userName);
        }
        return allUsers;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before)
    {
        Set<String> allUsers = new HashSet<>();
        for (LogRecord logRecord : logRecords) {
            if (isDateInInterval(logRecord.date, after, before)) {
                allUsers.add(logRecord.userName);
            }
        }
        return allUsers.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before)
    {
        int count = 0;
        if (user != null)
        {
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (user.equals(logRecord.userName)))
                {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before)
    {
        Set<String> usersForIP = new HashSet<>();
        if (ip != null)
        {
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (ip.equals(logRecord.ip)))
                {
                    usersForIP.add(logRecord.userName);
                }
            }
        }
        return usersForIP;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before)
    {
        Set<String> loggedUsers = new HashSet<>();
        for (LogRecord logRecord : logRecords)
        {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.LOGIN)
                    && (logRecord.status == Status.OK))
            {
                loggedUsers.add(logRecord.userName);
            }
        }
        return loggedUsers;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before)
    {
        Set<String> downloadedPluginUsers = new HashSet<>();
        for (LogRecord logRecord : logRecords)
        {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.DOWNLOAD_PLUGIN)
                    && (logRecord.status == Status.OK))
            {
                downloadedPluginUsers.add(logRecord.userName);
            }
        }
        return downloadedPluginUsers;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before)
    {
        Set<String> wroteMessageUsers = new HashSet<>();
        for (LogRecord logRecord : logRecords)
        {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.WRITE_MESSAGE)
                    && (logRecord.status == Status.OK))
            {
                wroteMessageUsers.add(logRecord.userName);
            }
        }
        return wroteMessageUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before)
    {
        Set<String> solvedTaskUsers = new HashSet<>();
        for (LogRecord logRecord : logRecords)
        {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.SOLVE_TASK))
            {
                solvedTaskUsers.add(logRecord.userName);
            }
        }
        return solvedTaskUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
    {
        Set<String> solvedTaskUsers = new HashSet<>();
        for (LogRecord logRecord : logRecords)
        {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.SOLVE_TASK)
                    && (logRecord.number == task))
            {
                solvedTaskUsers.add(logRecord.userName);
            }
        }
        return solvedTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)
    {
        Set<String> doneTaskUsers = new HashSet<>();
        for (LogRecord logRecord : logRecords)
        {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.DONE_TASK))
            {
                doneTaskUsers.add(logRecord.userName);
            }
        }
        return doneTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task)
    {
        Set<String> doneTaskUsers = new HashSet<>();
        for (LogRecord logRecord : logRecords)
        {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.DONE_TASK)
                    && (logRecord.number == task))
            {
                doneTaskUsers.add(logRecord.userName);
            }
        }
        return doneTaskUsers;
    }


    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before)
    {
        Set<Date> datesForUserAndEvent = new HashSet<>();
        if ((user != null) && (event != null))
        {
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (user.equals(logRecord.userName))
                        && (event == logRecord.event))
                {
                    datesForUserAndEvent.add(logRecord.date);
                }
            }
        }
        return datesForUserAndEvent;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before)
    {
        Set<Date> datesWhenSomethingFailed = new HashSet<>();
        for (LogRecord logRecord : logRecords)
        {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.status == Status.FAILED))
            {
                datesWhenSomethingFailed.add(logRecord.date);
            }
        }
        return datesWhenSomethingFailed;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before)
    {
        Set<Date> datesWhenErrorHappened = new HashSet<>();
        for (LogRecord logRecord : logRecords)
        {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.status == Status.ERROR))
            {
                datesWhenErrorHappened.add(logRecord.date);
            }
        }
        return datesWhenErrorHappened;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before)
    {
        Set<Date> datesWhenUserLoggedFirstTime = new TreeSet<>();
        if (user != null)
        {
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (user.equals(logRecord.userName))
                        && (logRecord.event == Event.LOGIN) && (logRecord.status == Status.OK))
                {
                    datesWhenUserLoggedFirstTime.add(logRecord.date);
                }
            }
        }

        return datesWhenUserLoggedFirstTime.size() > 0 ? (Date) datesWhenUserLoggedFirstTime.toArray()[0] : null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before)
    {
        Set<Date> datesWhenUserSolvedTask = new TreeSet<>();
        if (user != null)
        {
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (user.equals(logRecord.userName))
                        && (logRecord.event == Event.SOLVE_TASK) && (logRecord.number == task))
                {
                    datesWhenUserSolvedTask.add(logRecord.date);
                }
            }
        }

        return datesWhenUserSolvedTask.size() > 0 ? (Date) datesWhenUserSolvedTask.toArray()[0] : null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before)
    {
        Set<Date> datesWhenUserDoneTask = new TreeSet<>();
        if (user != null)
        {
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (user.equals(logRecord.userName))
                        && (logRecord.event == Event.DONE_TASK) && (logRecord.number == task))
                {
                    datesWhenUserDoneTask.add(logRecord.date);
                }
            }
        }

        return datesWhenUserDoneTask.size() > 0 ? (Date) datesWhenUserDoneTask.toArray()[0] : null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before)
    {
        Set<Date> datesWhenUserWroteMessage = new HashSet<>();
        if (user != null)
        {
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (user.equals(logRecord.userName))
                        && (logRecord.event == Event.WRITE_MESSAGE))
                {
                    datesWhenUserWroteMessage.add(logRecord.date);
                }
            }
        }

        return datesWhenUserWroteMessage;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before)
    {
        Set<Date> datesWhenUserDownloadedPlugin = new HashSet<>();
        if (user != null)
        {
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (user.equals(logRecord.userName))
                        && (logRecord.event == Event.DOWNLOAD_PLUGIN))
                {
                    datesWhenUserDownloadedPlugin.add(logRecord.date);
                }
            }
        }

        return datesWhenUserDownloadedPlugin;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before)
    {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before)
    {
        Set<Event> allEvents = new HashSet<>();
        for (LogRecord logRecord : logRecords) {
            if (isDateInInterval(logRecord.date, after, before)) {
                allEvents.add(logRecord.event);
            }
        }
        return allEvents;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before)
    {
        Set<Event> eventsForIP = new HashSet<>();
        if (ip != null)
        {
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (ip.equals(logRecord.ip)))
                {
                    eventsForIP.add(logRecord.event);
                }
            }
        }
        return eventsForIP;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before)
    {
        Set<Event> eventsForUser = new HashSet<>();
        if (user != null)
        {
            for (LogRecord logRecord : logRecords)
            {
                if (isDateInInterval(logRecord.date, after, before) && (user.equals(logRecord.userName)))
                {
                    eventsForUser.add(logRecord.event);
                }
            }
        }
        return eventsForUser;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before)
    {
        Set<Event> failedEvents = new HashSet<>();
        for (LogRecord logRecord : logRecords) {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.status == Status.FAILED)) {
                failedEvents.add(logRecord.event);
            }
        }
        return failedEvents;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before)
    {
        Set<Event> errorEvents = new HashSet<>();
        for (LogRecord logRecord : logRecords) {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.status == Status.ERROR)) {
                errorEvents.add(logRecord.event);
            }
        }
        return errorEvents;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before)
    {
        int count = 0;
        for (LogRecord logRecord : logRecords) {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.SOLVE_TASK)
                    && (logRecord.number == task)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before)
    {
        int count = 0;
        for (LogRecord logRecord : logRecords) {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.DONE_TASK)
                    && (logRecord.number == task)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before)
    {
        Map<Integer, Integer> solvedTasksAndTheirNumber = new HashMap<>();
        for (LogRecord logRecord : logRecords) {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.SOLVE_TASK)) {
                if (solvedTasksAndTheirNumber.containsKey(logRecord.number)) {
                    solvedTasksAndTheirNumber.put(logRecord.number, solvedTasksAndTheirNumber.get(logRecord.number) + 1);
                } else {
                    solvedTasksAndTheirNumber.put(logRecord.number, 1);
                }
            }
        }
        return solvedTasksAndTheirNumber;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before)
    {
        Map<Integer, Integer> doneTasksAndTheirNumber = new HashMap<>();
        for (LogRecord logRecord : logRecords) {
            if (isDateInInterval(logRecord.date, after, before) && (logRecord.event == Event.DONE_TASK)) {
                if (doneTasksAndTheirNumber.containsKey(logRecord.number)) {
                    doneTasksAndTheirNumber.put(logRecord.number, doneTasksAndTheirNumber.get(logRecord.number) + 1);
                } else {
                    doneTasksAndTheirNumber.put(logRecord.number, 1);
                }
            }
        }
        return doneTasksAndTheirNumber;
    }


    @Override
    public Set<Object> execute(String query)
    {
        Set<Object> result = new HashSet<>();
        LogQuery logQuery = parseQuery(query);
        if (logQuery != null) {
            switch (logQuery.command) {
                case "get": {
                    switch (logQuery.field) {
                        case "ip": {
                            for (LogRecord logRecord : logRecords) {
                                if (!logQuery.parameter.isEmpty() && !logRecordMatch(logRecord, logQuery.parameter, logQuery.value)) {
                                    continue;
                                }
                                if (!logQuery.after.isEmpty() && !logRecordMatchDate(logRecord, logQuery.after, logQuery.before)) {
                                    continue;
                                }
                                result.add(logRecord.ip);
                            }
                            break;
                        }
                        case "user": {
                            for (LogRecord logRecord : logRecords) {
                                if (!logQuery.parameter.isEmpty() && !logRecordMatch(logRecord, logQuery.parameter, logQuery.value)) {
                                    continue;
                                }
                                if (!logQuery.after.isEmpty() && !logRecordMatchDate(logRecord, logQuery.after, logQuery.before)) {
                                    continue;
                                }
                                result.add(logRecord.userName);
                            }
                            break;
                        }
                        case "date": {
                            for (LogRecord logRecord : logRecords) {
                                if (!logQuery.parameter.isEmpty() && !logRecordMatch(logRecord, logQuery.parameter, logQuery.value)) {
                                    continue;
                                }
                                if (!logQuery.after.isEmpty() && !logRecordMatchDate(logRecord, logQuery.after, logQuery.before)) {
                                    continue;
                                }
                                result.add(logRecord.date);
                            }
                            break;
                        }
                        case "event": {
                            for (LogRecord logRecord : logRecords) {
                                if (!logQuery.parameter.isEmpty() && !logRecordMatch(logRecord, logQuery.parameter, logQuery.value)) {
                                    continue;
                                }
                                if (!logQuery.after.isEmpty() && !logRecordMatchDate(logRecord, logQuery.after, logQuery.before)) {
                                    continue;
                                }
                                result.add(logRecord.event);
                            }
                            break;
                        }
                        case "status": {
                            for (LogRecord logRecord : logRecords) {
                                if (!logQuery.parameter.isEmpty() && !logRecordMatch(logRecord, logQuery.parameter, logQuery.value)) {
                                    continue;
                                }
                                if (!logQuery.after.isEmpty() && !logRecordMatchDate(logRecord, logQuery.after, logQuery.before)) {
                                    continue;
                                }
                                result.add(logRecord.status);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return result;
    }

    private boolean logRecordMatch (LogRecord logRecord, String parameter, String value) {
        if ((parameter != null) && (value != null)) {
            switch (parameter) {
                case "ip" : {
                    return value.equals(logRecord.ip);
                }
                case "user" : {
                    return value.equals(logRecord.userName);
                }
                case "date" : {
                    DateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy HH:mm:ss");
                    Date date = new Date();
                    try {
                        date = dateFormat.parse(value);
                    }
                    catch (ParseException e) {
                    }
                    return (date.equals(logRecord.date));
                }
                case "event" : {
                    return (Event.valueOf(value) == logRecord.event);
                }
                case "status" : {
                    return (Status.valueOf(value) == logRecord.status);
                }
            }
        }
        return false;
    }

    private boolean logRecordMatchDate(LogRecord logRecord, String after, String before) {
        if ((after != null) && (before != null)) {
            DateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy HH:mm:ss");
            Date dateAfter = new Date();
            Date dateBefore = new Date();
            try {
                dateAfter = dateFormat.parse(after);
                dateBefore = dateFormat.parse(before);
            }
            catch (ParseException e) {
            }
            return isDateInInterval(logRecord.date, dateAfter, dateBefore);
        }
        return false;
    }

    private class LogQuery {
        private String command;
        private String field;
        private String parameter;
        private String value;
        private String after;
        private String before;

        public LogQuery(String command, String field)
        {
            this.command = command;
            this.field = field;
            this.parameter = "";
            this.value = "";
            this.after = "";
            this.before = "";
        }

        public LogQuery(String command, String field, String parameter, String value)
        {
            this.command = command;
            this.field = field;
            this.parameter = parameter;
            this.value = value;
            this.after = "";
            this.before = "";
        }

        public LogQuery(String command, String field, String parameter, String value, String after, String before)
        {
            this.command = command;
            this.field = field;
            this.parameter = parameter;
            this.value = value;
            this.after = after;
            this.before = before;
        }
    }

    private LogQuery parseQuery(String query) {
        String command;
        String field;
        String parameter;
        String value;
        String after;
        String before;
        if (query != null) {
            String[] args = query.split(" ");
            if (args.length >= 2) {
                command = args[0];
                field = args[1];
                if (args.length >= 6) {
                    parameter = args[3];
                    String[] vals = query.split("\"");
                    value = vals[1];
                    if (vals.length >= 4) {
                        after = vals[3];
                        before = vals[5];
                        return new LogQuery(command, field, parameter, value, after, before);
                    } else
                    {
                        return new LogQuery(command, field, parameter, value);
                    }
                } else {
                    return new LogQuery(command, field);
                }
            }
        }
        return null;
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
}
