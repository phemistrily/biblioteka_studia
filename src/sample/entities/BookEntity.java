package sample.entities;

import sample.controllers.SqlConnector;

import java.sql.ResultSet;

public class BookEntity {

    SqlConnector sqlConnector;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private String name;
    private String source;

    public BookEntity() { this.sqlConnector = getLocalhostConnection(); }

    public SqlConnector getLocalhostConnection() {
        SqlConnector sqlConnector;
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
        return sqlConnector;
    }

    public ResultSet getBooks() {
        String query = "SELECT books.id,books.name, count(p.id) as pages FROM books LEFT JOIN pages p ON books.id = p.book_id GROUP BY books.id";
        System.out.println(query);
        ResultSet bookData = sqlConnector.getData(query);
        return bookData;
    }

    public Integer addBook(String bookNameData, String sourceData) {
        this.setName(bookNameData);
        this.setSource(sourceData);
        System.out.println("book data:");
        System.out.println(this.getSource());
        System.out.println(this.getName());
        String addBookQuery = "INSERT INTO books (name, source) VALUES ('" + this.getName() + "','" + this.getSource() + "')";
        sqlConnector.insertData(addBookQuery);
        return sqlConnector.lastId;
    }
}
