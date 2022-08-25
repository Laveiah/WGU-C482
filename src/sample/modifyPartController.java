package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that displays modify part screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class modifyPartController implements Initializable {
    Stage stage;
    Parent scene;


    /**
     * The part Inventory text field.
     */
    @FXML
    private TextField ModifyPartInventoryField;


    /**
     * The cancel button for parts.
     */
    @FXML
    private Button modifyCancelB;


    /**
     * The machine ID/company text field for the part.
     */
    @FXML
    private TextField modifyPartChangeableTextField;


    /**
     * The machine ID/company name label for the part.
     */
    @FXML
    private Label modifyPartChangeableTextLabel;


    /**
     * The part ID text field.
     */
    @FXML
    private TextField modifyPartIDField;


    /**
     * The in-house radio button.
     */
    @FXML
    private RadioButton modifyPartInHouse;


    /**
     * The part Maximum text field.
     */
    @FXML
    private TextField modifyPartMaxField;


    /**
     * The part Minimum text field.
     */
    @FXML
    private TextField modifyPartMinField;


    /**
     * The part name text field.
     */
    @FXML
    private TextField modifyPartNameField;


    /**
     * The outsourced radio button.
     */
    @FXML
    private RadioButton modifyPartOutsourced;


    /**
     * The part Price text field.
     */
    @FXML
    private TextField modifyPartPriceField;


    /**
     * The part save button for the part.
     */
    @FXML
    private Button modifyPartSaveB;


    /**
     * The toggle group for the radio buttons.
     */
    @FXML
    private ToggleGroup modifyPartToggle;


    /**
     * Loads MainPageController.
     *
     * @param actionEvent Cancel button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onModifyCancelB(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }





    /**
     * Sets machine ID/company name label to "Machine ID".
     *
     * @param event In-house radio button action.
     */
    @FXML
    void onModifyPartInHouse(ActionEvent event) {

        modifyPartChangeableTextLabel.setText("Machine ID");
    }


    /**
     * Sets machine ID/company name label to "Company Name".
     *
     * @param event Outsourced radio button.
     */
    @FXML
    void onModifyPartOutsourced(ActionEvent event) {

        modifyPartChangeableTextLabel.setText("Company Name");
    }

    /**
     * The part object selected in the MainPageController.
     */
    private Part selectedPart;


    /**
     * Replaces part in inventory and loads MainPageController.
     *
     * Text fields are validated with error messages displayed preventing empty and/or
     * invalid values.
     *
     * @param actionEvent Save button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    public void onModifyPartSaveB(ActionEvent actionEvent) throws IOException {


        boolean inHouse;
        int id = selectedPart.getId();
        String name = modifyPartNameField.getText();
        Double price = Double.parseDouble(modifyPartPriceField.getText());
        int stock = Integer.parseInt(ModifyPartInventoryField.getText());
        int min = Integer.parseInt(modifyPartMinField.getText());
        int max = Integer.parseInt(modifyPartMaxField.getText());
        int machineId;
        String companyName;
        boolean partAddSuccessful = false;


        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name Empty");
            alert.setContentText("Name cannot be empty.");
            alert.showAndWait();

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        if (min < 0 && max >= min) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value Incorrect ");
            alert.setContentText("Please make sure Max is greater than Min. ");
            alert.showAndWait();
        }
        if (stock > max || stock < min){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value Incorrect ");
            alert.setContentText("Also make sure Inventory is in between Max and Min and Min is less than Max. ");
            alert.showAndWait();
        }

         else {
                if (modifyPartInHouse.isSelected()) {

                    machineId = Integer.parseInt(modifyPartIDField.getText());
                    InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                    Inventory.addPart(newInHousePart);
                    partAddSuccessful = true;

                }
                if (modifyPartOutsourced.isSelected()) {
                    companyName = modifyPartNameField.getText();
                    Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max,
                            companyName);
                    Inventory.addPart(newOutsourcedPart);
                    partAddSuccessful = true;
                }


                if (partAddSuccessful) {
                    Inventory.deletePart(selectedPart);

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Value Incorrect ");
                    alert.setContentText("Please make sure in-house or outsourced is selected");
                    alert.showAndWait();

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();

                }
            }

    }


    /**
     * Initializes controller and populates text fields with part selected in MainPageController.
     *
     * @param location The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedPart = mainPageController.getPartToModify();

        if (selectedPart instanceof InHouse) {
            modifyPartInHouse.setSelected(true);
            modifyPartChangeableTextLabel.setText("Machine ID");
            modifyPartChangeableTextField.setText(String.valueOf(((InHouse) selectedPart).getMachineID()));
        }

        if (selectedPart instanceof Outsourced){
            modifyPartOutsourced.setSelected(true);
            modifyPartChangeableTextLabel.setText("Company Name");
            modifyPartChangeableTextField.setText(((Outsourced) selectedPart).getCompanyName());
        }

        modifyPartIDField.setText(String.valueOf(selectedPart.getId()));
        modifyPartNameField.setText(selectedPart.getName());
        ModifyPartInventoryField.setText(String.valueOf(selectedPart.getStock()));
        modifyPartPriceField.setText(String.valueOf(selectedPart.getPrice()));
        modifyPartMaxField.setText(String.valueOf(selectedPart.getMax()));
        modifyPartMinField.setText(String.valueOf(selectedPart.getMin()));
    }
}



//11-15 add part - save

//16 - modify part - save

