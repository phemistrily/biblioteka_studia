package sample.entities;

import sample.controllers.SqlConnector;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PageEntity {

    SqlConnector sqlConnector;

    private String fileName;
    private String pageNumber;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public PageEntity() { this.sqlConnector = getLocalhostConnection(); }

    public SqlConnector getLocalhostConnection() {
        SqlConnector sqlConnector;
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
        return sqlConnector;
    }

    public ResultSet getPages(Integer bookId) {
        String query = "SELECT p.id, p.book_id as bookId, p.page_number as pageNumber, p.file_name as fileName from pages p WHERE p.book_id = " + bookId;
        ResultSet pagesData = sqlConnector.getData(query);
        return pagesData;
    }

    public Integer lastPage(Integer bookId) throws SQLException {
        String query = "SELECT p.page_number as pageNumber FROM pages p WHERE p.book_id = " + bookId + " ORDER BY p.page_number DESC LIMIT 1";
        ResultSet lastPageData = sqlConnector.getData(query);
        System.out.println(lastPageData);
        Integer lastPage = null;
        while(lastPageData.next()) {
            lastPage = lastPageData.getInt("pageNumber");
        }
        if (lastPage == null)
        {
            lastPage = 0;
        }
        System.out.println(lastPage);
        return lastPage;
    }

    public void createNewPage(Integer bookId, Integer pageId, File file) {
        String filename = String.valueOf(file);
        filename = filename.replace("\\", "\\\\");
        String query = "INSERT INTO pages (book_id, page_number, file_name) VALUES (" + bookId + "," + pageId + ",'" + filename + "')";
        sqlConnector.insertData(query);
    }

    public ResultSet getPageList(Integer bookId) {
        String query = "SELECT file_name FROM pages WHERE book_id = " + bookId + " ORDER BY page_number ASC";
        ResultSet pageList = sqlConnector.getData(query);
        return pageList;
    }
}
