package sample.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PageListModel {
    private SimpleIntegerProperty id;
    private SimpleStringProperty fileName;
    private SimpleIntegerProperty pageNumber;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public int getPageNumber() {
        return pageNumber.get();
    }

    public SimpleIntegerProperty pageNumberProperty() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber.set(pageNumber);
    }

    public PageListModel(Integer id, String fileName, Integer pageNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.fileName = new SimpleStringProperty(fileName);
        this.pageNumber = new SimpleIntegerProperty(pageNumber);
    }
}
