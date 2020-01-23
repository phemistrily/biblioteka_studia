package sample.controllers;

import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.entities.PageEntity;
import sample.models.PageListModel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddFilesController {
    private Integer bookId;
    @FXML
    private AnchorPane primaryStage;
    @FXML
    private TableView<PageListModel> pagesList;
    @FXML
    private TableColumn<PageListModel, String> fileName;
    @FXML
    private TableColumn<PageListModel, Integer> pageNumber;

    private ObservableList<PageListModel> pageListModel = FXCollections.observableArrayList();

    public void initData(Integer bookId) throws SQLException {
        this.bookId = bookId;
        this.initializeFactory();
        this.getPages();
        pagesList.setItems(pageListModel);
    }

    private void initializeFactory() {
        fileName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        pageNumber.setCellValueFactory(new PropertyValueFactory<>("pageNumber"));
    }

    private void getPages() throws SQLException {
        PageEntity pages = new PageEntity();
        ResultSet pagesData = pages.getPages(this.bookId);
        System.out.println("pagesData");
        System.out.println(pagesData);
        this.putPagesToPageListModel(pagesData);
    }

    private void putPagesToPageListModel(ResultSet pagesData) throws SQLException {
        System.out.println("model");
        System.out.println(pagesData);
        pageListModel.clear();
        while (pagesData.next()){
            pageListModel.add(new PageListModel(pagesData.getInt("id"),pagesData.getString("fileName"), pagesData.getInt("pageNumber")));
        }
    }

    public void returnToIndex(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/index.fxml"));
        AnchorPane pane = loader.load();
        primaryStage.getChildren().setAll(pane);
    }

    public void addFiles(ActionEvent actionEvent) throws SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Dodaj pliki książki");
        Stage stage = (Stage)primaryStage.getScene().getWindow();
        PageEntity pages = new PageEntity();
        Integer lastPage = pages.lastPage(this.bookId);
        lastPage += 1;

        Integer counter = 0;
        List<File> list = fileChooser.showOpenMultipleDialog(stage);
        System.out.println("last page: " + lastPage);
        Integer fileCount = list.size();
        Runnable[] files = new Runnable[fileCount];
        Thread[] threads = new Thread[fileCount];

        for (File file : list)
        {
            Desktop desktop = Desktop.getDesktop();
            //desktop.open(file);
            files[counter] = new SendFile(this.bookId, lastPage, file);
            lastPage +=1;
            threads[counter] = new Thread(files[counter]);
            threads[counter].start();
            counter++;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.getPages();
        pagesList.setItems(pageListModel);
    }

    public void generateBook(ActionEvent actionEvent) throws IOException, DocumentException {
        try {
            ImagesToPdf pdf = new ImagesToPdf(this.bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
