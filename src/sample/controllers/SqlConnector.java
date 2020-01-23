package sample.controllers;

import java.sql.*;

public class SqlConnector {
    private Connection conn;
    private ResultSet response;
    private Statement statement;
    public int lastId;
    public int inserted;

    /**
     * funckja odpowiedzalna za połaczenie z bazą danych
     * @param connectionName
     */
    public void getConnection(String connectionName) {
        System.out.println(connectionName);
        switch (connectionName)
        {
            case "localhost":
                String dbName="aplikacja_ocr";
                String user="root";
                String password="";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName+"?serverTimezone=UTC", user , password);
                    statement = conn.createStatement();

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("brak polaczenia");
                break;

        }
    }

    /**
     * funkcja wstawiająca dane do bazy przesłane w zapytaniu
     * @param query
     */
    public void insertData(String query)
    {
        try {
            this.inserted = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                this.lastId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * funkcja zwracająca dane z bazy na podstawie zapytania
     * @param query
     * @return
     */
    public ResultSet getData(String query)
    {
        System.out.println(response);
        System.out.println(statement);
        try
        {
            response = statement.executeQuery(query);
            System.out.println("records from database");

        } catch (Exception ex)
        {
            System.out.println(ex);
        }

        return response;
    }


}
