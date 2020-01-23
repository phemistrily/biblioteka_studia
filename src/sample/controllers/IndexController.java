package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import sample.entities.BookEntity;
import sample.models.BookListModel;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    @FXML
    private AnchorPane primaryStage;
    @FXML
    private TableView<BookListModel> bookList;
    @FXML
    private TableColumn<BookListModel, String> name;
    @FXML
    private TableColumn<BookListModel, Integer> pages;

    private ObservableList<BookListModel> bookListModel = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initializeFactory();
        try {
            this.getBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bookList.setItems(bookListModel);
    }

    private void initializeFactory() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        pages.setCellValueFactory(new PropertyValueFactory<>("pages"));
    }

    private void getBooks() throws SQLException {
        BookEntity books = new BookEntity();
        ResultSet booksData = books.getBooks();
        this.putBooksToBookListModel(booksData);
    }

    private void putBooksToBookListModel(ResultSet booksData) throws SQLException {
        System.out.println("model");
        System.out.println(booksData);
        while (booksData.next()){
            bookListModel.add(new BookListModel(booksData.getInt("id"),booksData.getString("name"), booksData.getInt("pages")));
        }
    }

    public void addBookView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/addBookView.fxml"));
        AnchorPane pane = loader.load();

        primaryStage.getChildren().setAll(pane);
    }

    public void chooseBook(ActionEvent actionEvent) throws IOException, SQLException {
        BookListModel book = bookList.getSelectionModel().getSelectedItem();
        System.out.println(book.getId());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/addFiles.fxml"));
        AnchorPane pane = loader.load();

        AddFilesController addFilesController = loader.getController();
        addFilesController.initData(book.getId());
        primaryStage.getChildren().setAll(pane);
    }
}
