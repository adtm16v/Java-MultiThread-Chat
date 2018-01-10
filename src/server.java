import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class server  {
    public final int port = 1234;
    private static ServerSocket serverSocket;
private static ArrayList<User> db = new ArrayList<User>();
    public static void main(String[] args) {
        start();
        handle();
        end();
    }
    class User {
        String name,password;
        User(){

        }
        User(String name, String password){
            this.name = name;
            this.password = password;
        }


    }

    public static void setDb(){
        try {
            Scanner sc = new Scanner(new File("accounts.txt"));
            while (sc.hasNext()){
                User buff = null;
                buff.name = sc.next();
                buff.password = sc.next();
                db.add(buff);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void start() {
        try {
            int port = 1234;
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running on port:"+port);//creating server
        } catch (IOException e) {

        }
    }
        public static void handle(){
        while (true){
            try {
                Socket client = serverSocket.accept();
                new ClientHandler(client);//обработка клиента
            } catch (IOException e) {

            }
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }
    public static void end() {
        try {
            serverSocket.close();
        } catch (IOException e) {

        }
    }
}