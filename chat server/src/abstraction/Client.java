package abstraction;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream oos ;
    private ObjectInputStream ois;

    public Client(Socket socket){
        this.socket = socket;
    }

    public Client(Socket socket , ObjectOutputStream oos , ObjectInputStream ois ){
        this.socket = socket;
        this.oos = oos;
        this.ois = ois;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getThisObjectOutputStream() {
        return oos;
    }

    public ObjectInputStream getThisObjectInputStream() {
        return ois;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }
}
