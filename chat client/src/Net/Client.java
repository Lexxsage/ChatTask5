package Net;

import GUI.ChatFrame;
import Message.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

    private static String message = null;
    //private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Timer timer;
    private static Message mgsFromServer;
    private static String text = "";
    private static String login;
    private static String mess;
    private static ChatFrame frm;
    private static ObjectOutputStream serializer;
    private static ObjectInputStream deserializer;

    public static void main(String[] args) {

        String message = null;

        Socket mySocket = null;
        try {
            mySocket = new Socket("127.0.0.1", 8206);

            //System.out.println("Enter Login: ");

            login = JOptionPane.showInputDialog("Login:");//in.readLine();
            if (login.equals("")) {
                login = JOptionPane.showInputDialog("Uncorrect login!Login must be longer than 0 symbols!  \n Try again!");
                if (login.equals("")) {
                    System.out.println("Uncorrect login!");
                    System.exit(-1);
                }
            }

            frm = new ChatFrame(login);
            System.out.println("0");
            serializer = new ObjectOutputStream(mySocket.getOutputStream());
            System.out.println("011111");
            deserializer = new ObjectInputStream(mySocket.getInputStream());
            System.out.println("1");
            sendMsg(" "); //send registration message
            System.out.println("2");
            Message mgs = recvMsg();
            addToMessagesPane(mgs);
            updOnlineClients(mgs);
            System.out.println("3");
            frm.getTextField().setText(null);

            frm.getSendButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {

                    System.out.println("ActionListener");
                    sendMsg(frm.getText());
                    //Message mgs = recvMsg();
                    //addToMessagesPane(mgs);
                    //updOnlineClients(mgs);

                    frm.getTextField().setText(null);

                }
            });

//            timer = new Timer(1000, new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
            //sendMsg(" ");
            while (true) {
                Message mess = recvMsg();
                addToMessagesPane(mess);
                updOnlineClients(mess);
            }
//                }
//            });
//            timer.start();

        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ConnectException e) {
            System.out.println("Can't connect to server!");
        } catch (SocketException e) {
            System.out.println("Lost connection to server!");
            timer.stop();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static void sendMsg(String message) {
        //message = frm.getText();//in.readLine();

        if (!message.equals("")) {
            Message c = new Message(1, login, message);
            //message = null;
            try {
                serializer.writeObject(c);

            } catch (SocketException e) {
                System.out.println("Lost connection to server!");
                timer.stop();
                System.exit(-1);
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }

    private static Message recvMsg() {
        //message = frm.getText();//in.readLine();

        Message b = null;
        try {

            b = (Message) deserializer.readObject();

        } catch (SocketException e) {
            System.out.println("Lost connection to server!");
            timer.stop();
            System.exit(-1);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return b;
    }

    private static void addToMessagesPane(Message msg) {
        if (!msg.getMessage().equals(" ") && !msg.getMessage().equals("")) {
            mess = "[" + msg.getLogin() + "]: " + msg.getMessage() + "\n";
            text += mess; //frm.getMessagesPane().getText();
            frm.getMessagesPane().setText(text);
        }
    }

    private static void updOnlineClients(Message msg) {
        //msg.getUsers().toString();
        frm.getOnlineUsersList().setText(msg.getUsers());
    }
}
