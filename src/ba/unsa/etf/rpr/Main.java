package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class Main extends Application {
    private static GeografijaDAO baza;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*baza = GeografijaDAO.getInstance();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gui.fxml"));
        loader.setController(new GUIController(baza));*/
        Locale.setDefault(new Locale("bs","BIH"));
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/gui.fxml"), bundle);
        loader.setController(new GUIController(baza));
        Parent root = loader.load();
        primaryStage.setTitle("Gradovi");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
    }

    public static void main(String[] args) {
        /*System.out.println("Gradovi su:\n" + ispisiGradove());
        glavniGrad();*/
        launch(args);
    }

    public static void glavniGrad() {
        GeografijaDAO baza = GeografijaDAO.getInstance();
        String drzava;
        System.out.print("Unesite naziv drzave: ");
        Scanner tok = new Scanner(System.in);
        drzava = tok.nextLine();
        Grad grad = baza.glavniGrad(drzava);
        System.out.println(grad.getNaziv());
    }

    public static String ispisiGradove() {
        baza = GeografijaDAO.getInstance();
        ArrayList<Grad> gradovi = baza.gradovi();
        StringBuilder res = new StringBuilder();
        for (Grad x : gradovi) {
            res.append(x.getNaziv()).append(" (").append(x.getDrzava().getNaziv()).append(") - ").append(x.getBrojStanovnika()).append("\n");
        }
        return res.toString();
    }
}
