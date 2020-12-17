package Net;

import abstraction.UsersList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    private static UsersList list = new UsersList();

    public static void main(String[] args) {
        try {
            ServerSocket listenerSocket = new ServerSocket(8206);
            //listenerSocket.setSoTimeout();
            while (true) {
                Socket client = null;
                while (client == null){
                    client = listenerSocket.accept();
                    // брать первое сообщение
                }
                new ClientThread(client);
                //new ServerBroadcastThread();

            }
//            ObjectInputStream deserializer = new ObjectInputStream(client.getInputStream());
//            ObjectOutputStream serializer = new ObjectOutputStream(client.getOutputStream());

//            while(true) {
//                Message c = (Message)deserializer.readObject();
//                //c.message = "none";
//                System.out.println("Login: " +c.login +"\n" +"Message: " +c.message );
//
//                serializer.writeObject(c);
//            }

        } catch (SocketException e){

        } catch (IOException e) {
            //To change body of catch statement use File | Settings | File Templates.
        }
        //serializer.flush();

    }

    public synchronized static UsersList getUserList() {
        return list;
    }


}
