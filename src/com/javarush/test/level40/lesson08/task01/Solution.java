package com.javarush.test.level40.lesson08.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
        getSite(new URL("http://javarush.ru/social.html?lang=uk"));
    }

    public static void getSite(URL url) {
        try {
            Socket clientSocket = new Socket(url.getHost(), url.getDefaultPort());

            String request = url.getQuery();
            if (request != null)
            {
                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(request.getBytes());
                outputStream.flush();
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (bufferedReader.ready()) {
                System.out.println(bufferedReader.readLine());
            }
            bufferedReader.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}