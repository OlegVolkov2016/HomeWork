package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Олег Волков on 12.02.2016.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<String, Connection>();

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket)
        {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME) {
                    String userName = answer.getData();
                    if ((userName != null) && (!userName.isEmpty()) && (!connectionMap.containsKey(userName))) {
                        connectionMap.put(userName, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return userName;
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                if (!entry.getKey().equals(userName)) {
                    Message message = new Message(MessageType.USER_ADDED, entry.getKey());
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message answer = connection.receive();
                if (answer.getType() == MessageType.TEXT) {
                    Message message = new Message(MessageType.TEXT, userName + ": " + answer.getData());
                    sendBroadcastMessage(message);
                }
                else {
                    ConsoleHelper.writeMessage("Принятое сообщение не является текстом");
                }
            }
        }

        @Override
        public void run()
        {
            SocketAddress remoteAddress = socket.getRemoteSocketAddress();
            String userName = "";
            try (Connection connection = new Connection(socket)) {
                ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом: " + remoteAddress.toString());
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            }
            catch (IOException | ClassNotFoundException e)
            {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            }
            if (!userName.isEmpty()) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Закрыто соединение с удаленным адресом");
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка отправки сообщения");
            }
        }
    }

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Введите порт сервера");
        int serverPort = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage(e.getMessage());
        }

    }
}
