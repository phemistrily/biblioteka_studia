package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.entities.BookEntity;
import java.io.IOException;
import java.sql.SQLException;

public class AddBookController {
    @FXML
    private TextField bookName;
    @FXML
    private TextField source;
    @FXML
    private AnchorPane primaryStage;

    @FXML
    public void addBook(ActionEvent actionEvent) throws IOException, SQLException {
        String bookNameData = bookName.getText();
        String sourceData = source.getText();
        BookEntity book = new BookEntity();
        Integer bookId = book.addBook(bookNameData,sourceData);
        if(bookId > 0)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxmlData/addFiles.fxml"));
            AnchorPane pane = loader.load();

            AddFilesController addFilesController = loader.getController();
            addFilesController.initData(bookId);
            primaryStage.getChildren().setAll(pane);
        }
        else
        {
            System.out.println("Blad aplikacji do obslugi");
        }
    }
}
