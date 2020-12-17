package abstraction;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UsersList {
    private String users = null;
    private ArrayList<Client> clientsList = null;// = new ArrayList<Client>();
    private HashMap<String, Client> onlineUsers = new HashMap<String, Client>();
    private Set<Map.Entry<String, Client>> onlineClientsSet;// = word_and_count_storage.entrySet();

    public void addUser(String login, Socket socket) {
        System.out.println(login + " connected");
        if (!onlineUsers.containsKey(login)) {
            onlineUsers.put(login, new Client(socket));
            //System.out.println("if:"+the_word);
            //System.out.println( "1" );
        } else {
            int i = 1;
            while (onlineUsers.containsKey(login)) {
                login = login + "i";
                i++;
            }
            onlineUsers.put(login, new Client(socket));
        }
    }

    public void addUser(String login, Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {
        System.out.println(login + " connected");
        if (!onlineUsers.containsKey(login)) {
            onlineUsers.put(login, new Client(socket, oos, ois));
            //System.out.println("if:"+the_word);
            //System.out.println( "1" );
        } else {
            int i = 1;
            while (onlineUsers.containsKey(login)) {
                login = login + "i";
                i++;
            }
            onlineUsers.put(login, new Client(socket, oos, ois));
        }
    }

    public void delUser(String login) {
        onlineUsers.remove(login);
    }

    public String getUsers() {
        //int j = 0;
        //onlineClientsSet = onlineUsers.entrySet();
        ///String[] users = new String[onlineUsers.size()];
        users = "Admin";
        for (String m : onlineUsers.keySet()) // Проходим по всем Key мапа.
        {
            users = users + "\n" + m;
            //j++;
        }
        //System.out.println(users);
        return users;
    }

    public ArrayList<Client> getClientsList() {
        onlineClientsSet = onlineUsers.entrySet();
        int cap = onlineClientsSet.size();
        //System.out.println("onlineClientsSet.size: " +onlineClientsSet.size());
        clientsList = new ArrayList<Client>(cap);
        int i = 0;
        for (Map.Entry<String, Client> m : onlineClientsSet) // Проходим по всем элементам word_and_count_storage_set
        {
            //System.out.println("In getClientsList!" +i);
            i++;
            clientsList.add(m.getValue());
        }
        System.out.println("clientsList.size: " + clientsList.size());
        return clientsList;
    }
}
