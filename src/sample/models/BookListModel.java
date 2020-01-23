package sample.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookListModel {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty pages;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }


    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPages() {
        return pages.get();
    }

    public SimpleIntegerProperty pagesProperty() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages.set(pages);
    }

    public BookListModel(Integer id, String name, Integer pages) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.pages = new SimpleIntegerProperty(pages);
    }
}
