package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class GUIController implements Initializable {
    public TableView lista;
    public TableColumn id;
    public TableColumn naziv;
    public TableColumn brojStanovnika;
    public TableColumn drzava;
    //public Button ispisiBtn;
    private GeografijaDAO baza;
    public ObservableList<Grad> gradovi;
    public TextField gradZaDodavanje;
    public TextField drzavaZaBrisanje;
    public Button dugmeBrisiDrzavu;
    public Label opcija;
    public BorderPane mainPane;
    private ResourceBundle bundle;

    public GUIController(GeografijaDAO baza) {
        this.baza = baza;
    }

    /*public void ispisi(ActionEvent actionEvent) {
        gradovi = FXCollections.observableArrayList(baza.gradovi());
        lista.itemsProperty().setValue(gradovi);
    }*/

    public void save(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("DOCX Files", "*.docx"),
                new FileChooser.ExtensionFilter("XSLX Files", "*.xslx")
        );
        File file = fileChooser.showSaveDialog(stage);
    }

    private void izaberi(Locale locale) {
        Stage primaryStage = (Stage)mainPane.getScene().getWindow();
        Locale.setDefault(locale);
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gui.fxml"), bundle);
        loader.setController(new GUIController(baza));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
    }

    public void setBos(ActionEvent actionEvent) {
        izaberi(new Locale("bs","BIH"));
    }

    public void setEng(ActionEvent actionEvent) {
        izaberi(new Locale("en","US"));
    }

    public void setNJem(ActionEvent actionEvent) {
        izaberi(new Locale("de","DE"));
    }

    public void setFran(ActionEvent actionEvent) {
        izaberi(new Locale("fr","FR"));
    }

    public void pozivGradoviD(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            fxmlLoader.setResources(bundle);
            fxmlLoader.setLocation(getClass().getResource("/fxml/drzava.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            Stage stage = new Stage();
            stage.setTitle("Izbor");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ispis(ActionEvent actionEvent) {
        baza = GeografijaDAO.getInstance();
        GradoviReport report = new GradoviReport();
        try {
            report.showReport(GeografijaDAO.getConnection());
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        ObservableList<Grad> listaGradova = FXCollections.observableArrayList();
        ArrayList<Grad> gradovi = GeografijaDAO.getInstance().gradovi();
        naziv.setCellValueFactory(new PropertyValueFactory("naziv"));
        brojStanovnika.setCellValueFactory(new PropertyValueFactory("brojStanovnika"));
        drzava.setCellValueFactory(new PropertyValueFactory("drzava"));
        for (Grad g : gradovi) {
            listaGradova.add(g);
        }
        lista.setItems(listaGradova);
    }

    public void izadji(ActionEvent actionEvent) {
        Platform.exit();
    }
}
