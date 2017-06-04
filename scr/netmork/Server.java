package netmork;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Server extends NetworkConnection {
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

    public Server() {
    }
}
