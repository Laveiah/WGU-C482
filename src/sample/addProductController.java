package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that displays the add product screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */

public class addProductController implements Initializable {

    Stage stage;
    Parent scene;


    /**
     * The Cancel product button.
     */
    @FXML
    private Button addProductCancelB;


    /**
     * The Add Product button.
     */
    @FXML
    private Button addProductFormB;


    /**
     * The product ID level text field.
     */
    @FXML
    private TextField addProductIDField;


    /**
     * The product inventory level text field.
     */
    @FXML
    private TextField addProductInventoryField;


    /**
     * The product Maximum level text field.
     */
    @FXML
    private TextField addProductMaxField;


    /**
     * The product Minimum level text field.
     */
    @FXML
    private TextField addProductMinField;

    /**
     * The product name text field.
     */
    @FXML
    private TextField addProductNameField;


    /**
     * The part Inventory Level column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> addProductInventoryCol;


    /**
     * The part ID column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> addProductPartIDCol;


    /**
     * The part name column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, String> addProductPartNameCol;


    /**
     * The price column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Double> addProductPriceCol;


    /**
     * The Text field for the associated parts table.
     */
    @FXML
    private TextField addProductPriceField;


    /**
     * The remove product button.
     */
    @FXML
    private Button addProductRemoveB;


    /**
     * The product remove column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> addProductRemoveInventoryCol;


    /**
     * The part ID column for the removable parts table.
     */
    @FXML
    private TableColumn<Part, Integer> addProductRemovePartIDCol;


    /**
     * The part name column for the removable parts table.
     */
    @FXML
    private TableColumn<Part, String> addProductRemovePartNameCol;


    /**
     * The part price column for the removable parts table.
     */
    @FXML
    private TableColumn<Part, Double> addProductRemovePriceCol;


    /**
     * The add product button.
     */
    @FXML
    private Button addProductSaveB;


    /**
     * The product search text field.
     */
    @FXML
    private TextField addProductSearchField;


    /**
     * The product table view text.
     */
    @FXML
    private TableView<Part> addProductTableView;


    /**
     * The removable product view text field.
     */
    @FXML
    private TableView<Part> addProductRemoveTableView;



    /**
     * Initiates a search based on value in parts search text field and refreshes the parts
     * table view with search results.
     *
     * Parts can be searched for by ID or name.
     *
     * @param event Part search button action.
     */
    @FXML
    void onAddProductSearchField(KeyEvent event) {
        ObservableList<Part> allParts = Inventory.getallParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = addProductSearchField.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        addProductTableView.setItems(partsFound);
    }

    /**
     * Loads MainPageController.
     *
     * @param actionEvent Cancel button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onAddProductCancelB(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Adds part object selected in the all parts table to the associated parts table.
     *
     * @param event Add button action.
     */
    @FXML
    void onAddProductFormB(ActionEvent event) {
        Part selectedPart = addProductTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value Missing ");
            alert.setContentText("Please select part.");
            alert.showAndWait();

        } else {
            assocParts.add(selectedPart);
            addProductRemoveTableView.setItems(assocParts);
        }
    }



    /**
     * Displays confirmation dialog and removes selected part from associated parts table.
     *
     * @param event Remove button action.
     */
    @FXML
    void onAddProductRemoveB(ActionEvent event) {
        Part selectedPart = addProductRemoveTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value Missing ");
            alert.setContentText("Please add part.");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove this part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                assocParts.remove(selectedPart);

            }
        }
    }


    /**
     * Adds new product to inventory and loads MainPageController.
     *
     * Text fields are validated with error messages displayed preventing empty and/or
     * invalid values.
     *
     * @param actionEvent Save button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onAddProductSaveB(ActionEvent actionEvent) throws IOException{
//      price
    try {
        int id = 0;
        String name = addProductNameField.getText();
        Double price = Double.parseDouble(addProductPriceField.getText());
        int stock = Integer.parseInt(addProductInventoryField.getText());
        int min = Integer.parseInt(addProductMinField.getText());
        int max = Integer.parseInt(addProductMaxField.getText());


        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name Empty");
            alert.setContentText("Name cannot be empty.");
            alert.showAndWait();

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else if (min < 0 || max < min || max < 0 || (stock > max || stock < min)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value Incorrect ");
            alert.setContentText("Please make sure Max is greater than Min. Also make sure Inventory is in between Max and Min");
            alert.showAndWait();

            } else {

            Product newProduct = new Product(id, name, price, stock, min, max);
            for (Part part : assocParts) {
                newProduct.addAssociatedParts(part);
            }
            newProduct.setId(Inventory.newProductID());
            Inventory.addProduct(newProduct);
        /**
         * Loads MainPageController.
         *
         * @param event Passed from parent method.
         * @throws IOException From FXMLLoader.
         */
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

            }
        } catch (Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Value Incorrect ");
        alert.setContentText("Please enter the Characters/Numbers in the correct field");
        alert.showAndWait();
    }
    }



    /**
     * A list containing parts associated with the product.
     */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    /**
     * Initializes controller and populates table views.
     *
     * @param location The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addProductPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProductTableView.setItems(Inventory.getallParts());

        addProductRemovePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductRemovePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductRemovePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProductRemoveTableView.setItems(assocParts);

    }
}

