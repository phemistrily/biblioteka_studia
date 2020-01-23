package sample.controllers;

public class ConfigClass implements SqlInterface {
    public String mainTitle="Aplikacja";

    public ConfigClass (String connectionName){
        switch (connectionName) {
            case "localhost":
                SqlConnector sqlConnector = new SqlConnector();
                sqlConnector.getConnection("localhost");
                break;
            default:

                break;
        }
    }

    @Override
    public SqlConnector getLocalhostConnection() {
        SqlConnector sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
        return sqlConnector;
    }
}
