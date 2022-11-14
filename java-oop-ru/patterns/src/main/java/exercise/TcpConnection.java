package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection implements Connection {
    private String ip;
    private int port;
    private Connection connection;

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public void connect() {
        connection.connect();
    }

    public String getCurrentState() {
        return connection.getCurrentState();
    }

    public void write(String data) {
        connection.write(data);
    }

    public void disconnect() {
        connection.disconnect();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

// END
