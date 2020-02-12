package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Socket> clients = new ArrayList<Socket>();
    private static int port = 3000;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Сервер запущен на порту "+port);

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Клиент соединился с сервером");
                clients.add(socket);
                new ServerThread(socket, clients).start();

            }

        }catch (IOException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
