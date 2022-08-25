package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;


import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that displays the add part screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */

public class addPartController implements Initializable {


    Stage stage;
    Parent scene;

    /**
     * The save radio button.
     */
    @FXML
    private Button cancelB;

    /**
     * The machine ID/company name label for the part.
     */
    @FXML
    private Label changeableText;

    /**
     * The part ID text field.
     */
    @FXML
    private TextField idField;

    /**
     * The in-house radio button.
     */
    @FXML
    private RadioButton inHouseB;

    /**
     * The part inventory level text field.
     */
    @FXML
    private TextField inventoryField;

    /**
     * The machine ID/company name text field for the part.
     */
    @FXML
    private TextField machineIDField;

    /**
     * The part maximum level text field.
     */
    @FXML
    private TextField maxField;

    /**
     * The part minimum level text field.
     */
    @FXML
    private TextField minField;

    /**
     * The part name text field.
     */
    @FXML
    private TextField nameField;

    /**
     * The outsourced radio button.
     */
    @FXML
    private RadioButton outsourcedB;

    /**
     * The toggle group for the radio buttons.
     */
    @FXML
    private ToggleGroup partToggle;

    /**
     * The part price text field.
     */
    @FXML
    private TextField priceField;

    /**
     * The save radio button.
     */
    @FXML
    private Button saveB;


    /**
     * Displays confirmation dialog and loads MainPageController.
     *
     * @param actionEvent Cancel button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onCancelB(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    private boolean isOutsourced = true;


    /**
     * Sets machine ID/company name label to "Machine ID".
     *
     * @param actionEvent In-house radio button action.
     */
    public void onInHouse(javafx.event.ActionEvent actionEvent) {

        changeableText.setText("Machine ID");
    }


    /**
     * Sets machine ID/company name label to "Company Name".
     *
     * @param actionEvent Outsourced radio button.
     */
    public void onOutsourced(javafx.event.ActionEvent actionEvent) {

        changeableText.setText("Company Name");
    }


    /**
     * Displays confirmation dialog and loads MainScreenController.
     * Text fields are validated with error messages displayed preventing empty and/or invalid values.
     *
     * @param actionEvent Cancel button action.
     * @throws IOException From FXMLLoader.
     */
    public void onSaveB(ActionEvent actionEvent) throws IOException {

        boolean inHouse;

        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            Double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(inventoryField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            int machineId;


            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
            } else if (min < 0 || max < min || max < 0 || (stock > max || stock < min)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Value Incorrect ");
                alert.setContentText("Please make sure Max is greater than Min or Min/Max is not a negative number. " +
                        "Make sure Inventory is in between Max and Min and Min");
                alert.showAndWait();
            } else {

                if (inHouseB.isSelected()) {
                    int machineID = Integer.parseInt(machineIDField.getText());
                    InHouse part = new InHouse(id, name, price, stock, min, max, machineID);
                    Inventory.addPart(part);

                } else if (outsourcedB.isSelected()) {
                    String companyName = machineIDField.getText();
                    Outsourced part = new Outsourced(id, name, price, stock, min, max, companyName);
                    Inventory.addPart(part);

                }
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value Incorrect ");
            alert.setContentText("Please enter the Characters/Numbers in the correct field");
            alert.showAndWait();
        }

}
    /**
     * Initializes controller and generates new ID by 1.
     *
     * @param location The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idField.setText(String.valueOf(Inventory.getallParts().size()+1));
    }
}



