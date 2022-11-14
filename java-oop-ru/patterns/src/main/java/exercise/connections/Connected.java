package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already connected");
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void write(String data) {
        System.out.println("The data was sent");
    }

    @Override
    public void disconnect() {
        connection.setConnection(new Disconnected(connection));
        System.out.println("disconnected");
    }
}
// END
