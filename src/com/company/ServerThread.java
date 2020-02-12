package com.company;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerThread extends Thread{

    private Socket socket;
    private String user;
    private List<Socket> clients;

    public ServerThread(Socket socket, List<Socket> clients){
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try{
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String text;

            do{
                text = reader.readLine();
                user = text.split(":")[0];

                for(Socket s: clients){ //Отправляем всем клиентам
                    System.out.println(s);
                    OutputStream outputStream = s.getOutputStream();
                    PrintWriter printWriter = new PrintWriter(outputStream, true);
                    System.err.println(text);
                    printWriter.println(text);
                }
            } while (!text.equals("quit"));
        }
        catch (IOException e){
            System.err.println(user+" "+e.getMessage());
        }
    }
}
