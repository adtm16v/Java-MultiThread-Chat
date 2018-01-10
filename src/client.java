

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client {
   private static Socket socket;
   private static String nickname, password;
   public static void main(String[] args) {
        connect();
        handle();
        end();
    }
    public static void connect() {
            try {
                socket = new Socket("localhost",1234);
            } catch (IOException e) {

            }
    }
    public static void handle() {
            try {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                System.out.print("Enter your nickname: ");
                Scanner sc = new Scanner(System.in);
                nickname =sc.nextLine();
                dos.writeUTF(nickname);
                String messageToS;
                while(true) {
                    System.out.print(nickname + ":");
                    messageToS = sc.nextLine();
                    dos.writeUTF(messageToS);
                    for (int i = 0 ; i < 10 ; i++){
                        System.out.println('\b');
                    }
                    String chatlog = dis.readUTF();
                    System.out.println(chatlog);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public static void end() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
