/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressbuch;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author Nasser
 */
public class AdressbuchViewController implements Initializable{ 

    private Adressbuch adressbuch;

    private ObservableList<Kontakt> tableContent;

    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<Kontakt, String> name;
    @FXML
    private TableColumn<Kontakt, String> phone;
    @FXML
    private TableColumn<Kontakt, String> email;
    @FXML
    private TableView<Kontakt> tableView;
    @FXML
    private TextField nameField; 
   @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private Button addButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adressbuch = new Adressbuch();
        tableContent = FXCollections.observableArrayList();

        configureTable();

        showKontakte(adressbuch.getAlleKontakte());
        searchField.textProperty().addListener((e) -> filterList());
        addButton.setOnAction((ActionEvent e) -> addKontakt());
    }

    private void configureTable() {
        name.setCellValueFactory(new PropertyValueFactory<Kontakt, String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<Kontakt, String>("telefon"));
        email.setCellValueFactory(new PropertyValueFactory<Kontakt, String>("email"));

        tableView.setItems(tableContent);

        tableView.setEditable(true);

        name.setCellFactory(TextFieldTableCell.<Kontakt>forTableColumn());
        phone.setCellFactory(TextFieldTableCell.<Kontakt>forTableColumn());
        email.setCellFactory(TextFieldTableCell.<Kontakt>forTableColumn());
        name.setOnEditCommit((e) -> updateKontakt(e));
        phone.setOnEditCommit((e) -> updateKontakt(e));
        email.setOnEditCommit((e) -> updateKontakt(e));
    }

    private void showKontakte(Kontakt[] kontakte) {
        tableContent.clear();
        for (Kontakt x : kontakte) {
            tableContent.add(x);;
        }
    }

    private void filterList() {
        showKontakte(adressbuch.getKontakte(searchField.getText()));
        System.out.println(searchField.getText());
    }

    private String getKey(Kontakt alterKontakt) {
        if (alterKontakt.getName() == null) {
            return alterKontakt.getEmail();
        } else {
            return alterKontakt.getName();
        }
    }

    private void addKontakt() {
        if (nameField.getText().isEmpty() && phoneField.getText().isEmpty() && emailField.getText().isEmpty()) {
            return;
        }
        try {
            adressbuch.addKontakt(new Kontakt(nameField.getText(),
                    phoneField.getText(), emailField.getText()));
            nameField.clear();
            phoneField.clear();
            emailField.clear();
            filterList();
        } catch (IllegalStateException ise) {
            ViewHelper.showError(ise.getMessage());
        } catch (UngueltigerSchluesselException e) {
            ViewHelper.showError(e.toString());
        }
    }

    private void updateKontakt(TableColumn.CellEditEvent<Kontakt, String> e) {
        String alt = e.getOldValue();
        String neu = e.getNewValue();
        int index = e.getTablePosition().getRow();
        int col = e.getTablePosition().getColumn();

        if (alt.equals(neu)) {
            return;
        }
        try {

            Kontakt k = tableView.getItems().get(index);
            Kontakt kn = new Kontakt(k.getName(), k.getTelefon(), k.getEmail());

            if (col == 0) {
                kn.setName(neu);
            } else if (col == 1) {
                kn.setTelefon(neu);
            } else {
                kn.setEmail(neu);
            }

            adressbuch.updateKontakt(getKey(k), kn);

        } catch (IllegalStateException ise) {
            ViewHelper.showError(ise.getMessage());
        } catch (UngueltigerSchluesselException use) {
            ViewHelper.showError(use.toString());
        }

        filterList();
    }
}
