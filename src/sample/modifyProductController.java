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
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that displays the modify product screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class modifyProductController implements Initializable {
    Stage stage;
    Parent scene;



    /**
     * The add product button for the modify product.
     */
    @FXML
    private Button modifyAddProductFormB;


    /**
     * The part for the add product table view.
     */
    @FXML
    private TableView<Part> modifyAddProductTableView;


    /**
     * The cancel product button for the modify product.
     */
    @FXML
    private Button modifyProductCancelB;


    /**
     * The Product ID for the add product field.
     */
    @FXML
    private TextField modifyProductIDField;


    /**
     * The Inventory for the add product field.
     */
    @FXML
    private TextField modifyProductInventoryField;


    /**
     * The Maximum for the add product field.
     */
    @FXML
    private TextField modifyProductMaxField;


    /**
     * The Minimum for the add product field.
     */
    @FXML
    private TextField modifyProductMinField;


    /**
     * The name for the add product field.
     */
    @FXML
    private TextField modifyProductNameField;


    /**
     * The Inventory column for the add product table.
     */
    @FXML
    private TableColumn<Part, Integer> modifyProductInventoryCol;


    /**
     * The part ID for the add product table.
     */
    @FXML
    private TableColumn<Part, Integer> modifyProductPartIDCol;


    /**
     * The name column for the add product table.
     */
    @FXML
    private TableColumn<Part, String> modifyProductPartNameCol;


    /**
     * The price column for the add product table.
     */
    @FXML
    private TableColumn<Part, Double> modifyProductPriceCol;


    /**
     * The price Text Field for the add product table.
     */
    @FXML
    private TextField modifyProductPriceField;


    /**
     * The remove product button for the modify product.
     */
    @FXML
    private Button modifyProductRemoveB;


    /**
     * The table view column for the removable table.
     */
    @FXML
    private TableView<Part> modifyRemoveProductTableView;


    /**
     * The inventory level column for the removable table.
     */
    @FXML
    private TableColumn<Part, Integer> modifyProductRemoveInventoryCol;


    /**
     * The part ID column for the removable table.
     */
    @FXML
    private TableColumn<Part, Integer> modifyProductRemovePartIDCol;


    /**
     * The Part Name column for the removable table.
     */
    @FXML
    private TableColumn<Part, String> modifyProductRemovePartNameCol;


    /**
     * The Price column for the removable table.
     */
    @FXML
    private TableColumn<Part, Double> modifyProductRemovePriceCol;


    /**
     * The save product button for the modify product.
     */
    @FXML
    private Button modifyProductSaveB;


    /**
     * The search text field for the modify product.
     */
    @FXML
    private TextField modifyProductSearchField;


    /**
     * Initiates a search based on value in parts search text field and refreshes the parts
     * table view with search results.
     *
     * Parts can be searched for by ID.
     *
     * @param event Part search button action.
     */
    @FXML
    void onModifyPartIDSearch(KeyEvent event) {
        ObservableList<Part> allParts = Inventory.getallParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = modifyProductSearchField.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        modifyAddProductTableView.setItems(partsFound);
    }


    /**
     * Displays confirmation dialog and loads MainPageController.
     *
     * @param actionEvent Cancel button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onAddProductCancelB(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    @FXML
    void onAddProductFormB(ActionEvent event) {
            Part selectedPart = modifyAddProductTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value Missing ");
            alert.setContentText("Please select part.");
            alert.showAndWait();

        } else {
            assocParts.add(selectedPart);
            modifyRemoveProductTableView.setItems(assocParts);
        }
    }



    /**
     * Displays confirmation message and removes selected part from associated parts table.
     *
     * Displays error message if no part is selected.
     *
     * @param event Remove button action.
     */
    @FXML
    void onAddProductRemoveB(ActionEvent event) {
        Part selectedPart = modifyRemoveProductTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value Missing ");
            alert.setContentText("Please add part.");
            alert.showAndWait();

        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove this part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);

                assocParts.remove(selectedPart);
                modifyRemoveProductTableView.setItems(assocParts);

            }
        }
    }


    /**
     * The product object selected in the MainPageController.
     */
    Product selectedProduct;


    /**
     * Replaces product in inventory and loads MainPageController.
     *
     * Text fields are validated with error messages displayed preventing empty and/or
     * invalid values.
     *
     * @param actionEvent Save button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onAddProductSaveB(ActionEvent actionEvent) throws IOException{


            int id = selectedProduct.getId();
            String name = modifyProductNameField.getText();
            Double price = Double.parseDouble(modifyProductPriceField.getText());
            int stock = Integer.parseInt(modifyProductInventoryField.getText());
            int min = Integer.parseInt(modifyProductMinField.getText());
            int max = Integer.parseInt(modifyProductMaxField.getText());

            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
            }
            else if (min < 0 || max < min || max < 0 || (stock > max || stock < min)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Value Incorrect ");
                    alert.setContentText("Please make sure Max is greater than Min or Min/Max is not a negative number. " +
                            "Make sure Inventory is in between Max and Min and Min");
                    alert.showAndWait();

                }  else  {
                    Product newProduct = new Product(id, name, price, stock, min, max);
                    for (Part part : assocParts) {
                        newProduct.addAssociatedPart(part);
                    }
                    Inventory.addProduct(newProduct);
                    Inventory.deleteProduct(selectedProduct);

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }


    /**
     * Initializes controller and populates text fields with product selected in MainScreenController.
     *
     * @param location The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
            @Override
            public void initialize (URL location, ResourceBundle resources){

                selectedProduct = mainPageController.getProductToModify();
                assocParts = selectedProduct.getAssociatedParts();

                modifyProductRemovePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                modifyProductRemovePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                modifyProductRemoveInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                modifyProductRemovePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
                modifyAddProductTableView.setItems(Inventory.getallParts());

                modifyProductPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                modifyProductPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                modifyProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                modifyProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
                modifyRemoveProductTableView.setItems(assocParts);

                modifyProductIDField.setText(String.valueOf(selectedProduct.getId()));
                modifyProductNameField.setText(selectedProduct.getName());
                modifyProductInventoryField.setText(String.valueOf(selectedProduct.getStock()));
                modifyProductPriceField.setText(String.valueOf(selectedProduct.getPrice()));
                modifyProductMaxField.setText(String.valueOf(selectedProduct.getMax()));
                modifyProductMinField.setText(String.valueOf(selectedProduct.getMin()));
            }
        }