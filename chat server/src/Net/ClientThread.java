package Net;

import abstraction.Client;
import Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import static Net.Server.getUserList;

public class ClientThread extends Thread {
    private Socket socket ;
    private boolean flag = true;
    private Message c;
    private Message out;
    private ObjectInputStream deserializer ;
    private ObjectOutputStream serializer ;


    public ClientThread( Socket socket ){
        this.socket = socket;
        start();
    }

    public void run(){
        try {
            int i = 0;
            deserializer = new ObjectInputStream(socket.getInputStream());
            serializer = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("0");
            c = (Message)deserializer.readObject();
            System.out.println("1");
            final String login = c.getLogin();
            getUserList().addUser( login , socket , serializer , deserializer );

            if (!c.getMessage().equals(" ")) {
                System.out.println("[" +c.getLogin() +"]: " +c.getMessage() );
            }
            String arr1 = getUserList().getUsers();

            c.setOnlineUsers(arr1);
            broadcast( getUserList().getClientsList() , c );


            while(true) {
                c = (Message)deserializer.readObject();

                if (!c.getMessage().equals(" ")) {
                    System.out.println("[" +c.getLogin() +"]: " +c.getMessage() );
                }
                String arr = getUserList().getUsers();
                //System.out.println(arr);
                c.setOnlineUsers(arr);

                if (!c.getMessage().equals(" ") && !c.getMessage().equals("")){
                    System.out.println("Send broadcast!"  +" Message\"" +c.getMessage() +"\"");
                    broadcast( getUserList().getClientsList() , c );
                } else {
                    System.out.println("Send msg!" +" Message\"" +c.getMessage() +"\"");
                    serializer.writeObject(c);
                }

                //getUserList().getClientsList().get(0).getThisObjectOutputStream().writeObject(c);
                //serializer.writeObject(c);
            }

        } catch (SocketException e) {
            System.out.println( c.getLogin() +" disconnected!" );
            getUserList().delUser(c.getLogin());
            //timer.stop();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private void broadcast( ArrayList<Client> clientsArrayList , Message msg ) {
        try {
            //System.out.println("clientListArray: " +clientsArrayList);
            for (Client cl : clientsArrayList ) {
                //System.out.println("In Broadcast!");
                //System.out.println(cl.getSocket());
                cl.getThisObjectOutputStream().writeObject(msg);
            }
        } catch (SocketException e) {
            System.out.println( c.getLogin() +" disconnected!" );
            getUserList().delUser(c.getLogin());
            //timer.stop();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}