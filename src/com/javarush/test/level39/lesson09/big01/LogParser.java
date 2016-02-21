package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.IPQuery;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery {
    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    private List<File> getFileTree(String root) {
        List<File> fileList = new ArrayList<>();
        File rootDir = new File(root);
        File[] subDirs = rootDir.listFiles();
        for (File file: subDirs){
            if (file.isFile() && file.getName().endsWith(".log")) {
                fileList.add(file);
            }
        }
        return fileList;
    }
    private boolean after(Date date1, Date date2) {
        return date1.getTime() >= date2.getTime();
    }
    private boolean before(Date date1, Date date2) {
        return date1.getTime() <= date2.getTime();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        Set<String> uniqueIPsMap = new HashSet<>();
        for (File loop : getFileTree(logDir.toString())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(loop))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String re1 = "((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])";    // IPv4 IP Address 1
                    String re2 = ".*?";    // Non-greedy match on filler
                    String re3 = "((?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])";    // DDMMYYYY 1
                    String re4 = ".*?";    // Non-greedy match on filler
                    String re5 = "((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";    // HourMinuteSec 1
                    Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                    Matcher m = p.matcher(line);
                    SimpleDateFormat convertToDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    if (m.find()) {
                        String ipaddress1 = m.group(1);
                        String ddmmyyyy1 = m.group(2);
                        String time1 = m.group(3);
                        Date date = convertToDate.parse(ddmmyyyy1 + " " + time1);
                        if (after != null && before != null) {
                            if (after(date, after) && before(date, before)) {
                                uniqueIPsMap.add(ipaddress1);
                            }
                        }
                        if (after == null && before != null) {
                            if (before(date, before)) {
                                uniqueIPsMap.add(ipaddress1);
                            }
                        }
                        if ((after != null && before == null)) {
                            if (after(date, after)) {
                                uniqueIPsMap.add(ipaddress1);
                            }
                        }
                        if (after == null && before == null) {
                            uniqueIPsMap.add(ipaddress1);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return uniqueIPsMap.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        Set<String> uniqueIPs = new HashSet<>();
        for (File loop : getFileTree(logDir.toString())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(loop))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String re1 = "((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])";    // IPv4 IP Address 1
                    String re2 = ".*?";    // Non-greedy match on filler
                    String re3 = "((?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])";    // DDMMYYYY 1
                    String re4 = ".*?";    // Non-greedy match on filler
                    String re5 = "((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";    // HourMinuteSec 1
                    Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                    Matcher m = p.matcher(line);
                    SimpleDateFormat convertToDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    if (m.find()) {
                        String ipaddress1 = m.group(1);
                        String ddmmyyyy1 = m.group(2);
                        String time1 = m.group(3);
                        Date date = convertToDate.parse(ddmmyyyy1 + " " + time1);
                        if (after != null && before != null) {
                            if (after(date, after) && before(date, before)) {
                                uniqueIPs.add(ipaddress1);
                            }
                        }
                        if (after == null && before != null) {
                            if (before(date, before)) {
                                uniqueIPs.add(ipaddress1);
                            }
                        }
                        if ((after != null && before == null)) {
                            if (after(date, after)) {
                                uniqueIPs.add(ipaddress1);
                            }
                        }
                        if (after == null && before == null) {
                            uniqueIPs.add(ipaddress1);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return uniqueIPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        Set<String> uniqueIPsForUser = new HashSet<>();
        for (File loop : getFileTree(logDir.toString())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(loop))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String re1 = "((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])";    // IPv4 IP Address 1
                    String re2 = ".*?";    // Non-greedy match on filler
                    String re3 = "((?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])";    // DDMMYYYY 1
                    String re4 = ".*?";    // Non-greedy match on filler
                    String re5 = "((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";    // HourMinuteSec 1
                    Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                    Matcher m = p.matcher(line);
                    String[] strings = line.split("(\\h)");
                    StringBuilder userName = new StringBuilder();
                    for (String str : strings) {
                        if (str.matches("((?:[a-zA-Z][a-z]+))")) {
                            userName.append(str + " ");
                        }
                    }
                    SimpleDateFormat convertToDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    if (m.find()) {
                        String ipaddress1 = m.group(1);
                        String ddmmyyyy1 = m.group(2);
                        String time1 = m.group(3);
                        Date date = convertToDate.parse(ddmmyyyy1 + " " + time1);
                        if (user.contains(userName.toString().trim())) {
                            if (after != null && before != null) {
                                if (after(date, after) && before(date, before)) {
                                    uniqueIPsForUser.add(ipaddress1);
                                }
                            }
                            if (after == null && before != null) {
                                if (before(date, before)) {
                                    uniqueIPsForUser.add(ipaddress1);
                                }
                            }
                            if ((after != null && before == null)) {
                                if (after(date, after)) {
                                    uniqueIPsForUser.add(ipaddress1);
                                }
                            }
                            if (after == null && before == null) {
                                uniqueIPsForUser.add(ipaddress1);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return uniqueIPsForUser;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        Set<String> uniqueIPsForEvent = new HashSet<>();
        for (File loop : getFileTree(logDir.toString())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(loop))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String re1 = "((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])";    // IPv4 IP Address 1
                    String re2 = ".*?";    // Non-greedy match on filler
                    String re3 = "((?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])";    // DDMMYYYY 1
                    String re4 = ".*?";    // Non-greedy match on filler
                    String re5 = "((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";    // HourMinuteSec 1
                    String re6 = ".*?";
                    String re7 = "((?:[A-Z_]{2,}[A-Z]?))";
                    Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                    Matcher m = p.matcher(line);
                    SimpleDateFormat convertToDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    if (m.find()) {
                        String ipaddress1 = m.group(1);
                        String ddmmyyyy1 = m.group(2);
                        String time1 = m.group(3);
                        String ev = m.group(4);
                        Date date = convertToDate.parse(ddmmyyyy1 + " " + time1);
                        if (event.name().contains(ev)) {
                            if (after != null && before != null) {
                                if (after(date, after) && before(date, before)) {
                                    uniqueIPsForEvent.add(ipaddress1);
                                }
                            }
                            if (after == null && before != null) {
                                if (before(date, before)) {
                                    uniqueIPsForEvent.add(ipaddress1);
                                }
                            }
                            if ((after != null && before == null)) {
                                if (after(date, after)) {
                                    uniqueIPsForEvent.add(ipaddress1);
                                }
                            }
                            if (after == null && before == null) {
                                uniqueIPsForEvent.add(ipaddress1);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return uniqueIPsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        Set<String> uniqueIPsForEvent = new HashSet<>();
        for (File loop : getFileTree(logDir.toString())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(loop))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String re1 = "((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])";    // IPv4 IP Address 1
                    String re2 = ".*?";    // Non-greedy match on filler
                    String re3 = "((?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])";    // DDMMYYYY 1
                    String re4 = ".*?";    // Non-greedy match on filler
                    String re5 = "((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";    // HourMinuteSec 1
                    Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                    Matcher m = p.matcher(line);
                    String[] strings = line.split("(\\h)");
                    String stat = "";
                    if (strings[strings.length - 1].matches("((?:[A-Z]{2,}))")) {
                        stat = strings[strings.length - 1];
                    }
                    SimpleDateFormat convertToDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    if (m.find()) {
                        String ipaddress1 = m.group(1);
                        String ddmmyyyy1 = m.group(2);
                        String time1 = m.group(3);
                        Date date = convertToDate.parse(ddmmyyyy1 + " " + time1);
                        if (status.name().contains(stat)) {
                            if (after != null && before != null) {
                                if (after(date, after) && before(date, before)) {
                                    uniqueIPsForEvent.add(ipaddress1);
                                }
                            }
                            if (after == null && before != null) {
                                if (before(date, before)) {
                                    uniqueIPsForEvent.add(ipaddress1);
                                }
                            }
                            if ((after != null && before == null)) {
                                if (after(date, after)) {
                                    uniqueIPsForEvent.add(ipaddress1);
                                }
                            }
                            if (after == null && before == null) {
                                uniqueIPsForEvent.add(ipaddress1);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return uniqueIPsForEvent;
    }
}
