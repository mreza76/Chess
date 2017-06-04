package netmork;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Client extends NetworkConnection {
    private String ip;
    private int port;

    public Client() {
    }

    @Override
    public boolean isServer() {
        return false;
    }

    @Override
    public String getIP() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }
}
