package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import net.sf.jasperreports.engine.JRException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DrzavaController implements Initializable {
    public ComboBox<String> drzava;
    private ObservableList<String> listaDrzava = FXCollections.observableArrayList();
    ArrayList<Drzava> drzave;

    public DrzavaController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        drzave = GeografijaDAO.getInstance().drzave();
        System.out.println(drzave);
        for (Drzava d: drzave) {
            listaDrzava.add(d.getNaziv());
        }
        drzava.setItems(listaDrzava);
    }


    public void izvjestaj(ActionEvent actionEvent) {
        GradoviReport gradoviReport = new GradoviReport();
        try {
            gradoviReport.showReportDrzava(GeografijaDAO.getConnection(), drzava.getValue());
        }
        catch (JRException ex) {
            ex.printStackTrace();
        }
    }
}
