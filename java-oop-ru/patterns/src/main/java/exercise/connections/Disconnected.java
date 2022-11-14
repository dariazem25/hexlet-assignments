package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private TcpConnection connection;

    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        connection.setConnection(new Connected(connection));
        System.out.println("connected");
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void write(String data) {
        System.out.println("Error! Connection was disconnected");
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection has already been disconnected");
    }
}
// END
