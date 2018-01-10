import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler  extends  Thread{
    private final Socket client;
    String user;


    public ClientHandler(Socket client) {
        System.out.println("There is a new client...");
        this.client = client;
        start();
    }
    @Override
    public void run(){
     try {
         DataInputStream dis = new DataInputStream(client.getInputStream());
         DataOutputStream dos = new DataOutputStream(client.getOutputStream());
         String nickname, password;
         nickname = dis.readUTF();
         this.user = nickname;
     } catch (IOException e) {
         e.printStackTrace();
     }
        while (true) {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if (dis.available() > 0) { // обработка сообщений
                String message = dis.readUTF();
                System.out.println(user+':'+message);
                try {
                    File chatlog = new File("chatlog.txt");
                    FileWriter writer = new FileWriter(chatlog,true);
                    writer.write(user + ':' + message + '\n');
                    writer.close();
                    Scanner sc= new Scanner(chatlog);
                    String messageToClient = "";
                    while (sc.hasNextLine()){
                        messageToClient+=sc.nextLine();
                        messageToClient+='\n';

                    }

                    DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                    dos.writeUTF(messageToClient);
                    chatlog.delete();
                    writer.close();
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                //server.end();
            }
        } catch (IOException e) {
            }
            try {
            Thread.sleep(10);
            } catch (InterruptedException e) {

            }
        }
    }
}
