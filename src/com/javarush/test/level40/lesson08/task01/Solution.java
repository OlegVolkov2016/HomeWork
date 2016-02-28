package com.javarush.test.level40.lesson08.task01;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/* Отправка GET-запроса через сокет
Перепиши реализацию метода getSite, он должен явно создавать и использовать сокетное соединение Socket с сервером.
Адрес сервера и параметры для GET-запроса получи из параметра url.
Порт используй дефолтный для http.
Классы HttpURLConnection, HttpClient и т.д. не использовать.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket clientSocket = new Socket(url.getHost(), url.getDefaultPort());

            String request = "GET " + url.getFile() + " HTTP/1.1\r\n";
            request += "Accept: text/plain, text/html, text/*\r\n";
            request += "\r\n";
            OutputStream outputStream = clientSocket.getOutputStream();

            PrintWriter printWriter = new PrintWriter(outputStream, false);
            printWriter.print(request);
            printWriter.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (bufferedReader.ready()) {
                System.out.println(bufferedReader.readLine());
            }
            outputStream.close();
            bufferedReader.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}