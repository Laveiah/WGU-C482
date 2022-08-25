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
import javafx.scene.input.InputMethodEvent;
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
 * Controller class that displays the main screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class mainPageController implements Initializable {

    /**
     * The exit button for application.
     */
    @FXML
    private Button exitB;


    /**
     * The text field for the parts search.
     */
    @FXML
    private TextField partSearch;


    /**
     * The add button for parts.
     */
    @FXML
    private Button partsAddB;


    /**
     * The delete button for parts.
     */
    @FXML
    private Button partsDeleteB;


    /**
     * The ID column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partsIDColumn;


    /**
     * The Inventory column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partsInventoryColumn;


    /**
     * The modify button for parts.
     */
    @FXML
    private Button partsModifyB;


    /**
     * The Name column for the parts table.
     */
    @FXML
    private TableColumn<Part, String> partsNameColumn;


    /**
     * The Price column for the parts table.
     */
    @FXML
    private TableColumn<Part, Double> partsPriceColumn;


    /**
     * The Tableview for the parts table.
     */
    @FXML
    private TableView<Part> partsTable;


    /**
     * The delete button for products.
     */
    @FXML
    private Button productDeleteB;


    /**
     * The ID column for the product table.
     */
    @FXML
    private TableColumn<Product, Integer> productIDColumn;


    /**
     * The inventory column for the product table.
     */
    @FXML
    private TableColumn<Product, Inventory> productInventoryColumn;


    /**
     * The name column for the product table.
     */
    @FXML
    private TableColumn<Product, String> productNameColumn;


    /**
     * The price column for the product table.
     */
    @FXML
    private TableColumn<Product, Double> productPriceColumn;


    /**
     * The text field for the parts search.
     */
    @FXML
    private TextField productSearch;


    /**
     * The Tableview for the product table.
     */
    @FXML
    private TableView<Product> productTable;


    /**
     * The add button for products.
     */
    @FXML
    private Button productsAddB;


    /**
     * The modify product button for products.
     */
    @FXML
    private Button productsModifyB;




    /**
     * The product object selected in the table view.
     */
    private static Product productToModify;

    /**
     * The part object selected in the table view.
     */
    private static Part partToModify;


    Stage stage;
    Parent scene;


    /**
     * Gets the part object selected by the user in the part table.
     *
     * @return A part object, null if no part selected.
     */
    public static Part getPartToModify() {
        return partToModify;
    }


    /**
     * Gets the product object selected by the user in the product table.
     *
     * @return A product object, null if no product selected.
     */
    public static Product getProductToModify(){
        return productToModify;
    }


    /**
     * Loads the AddPartController.
     *
     * @param actionEvent Add button action.
     * @throws IOException From FXMLLoader.
     */
    public void onPartsAddB(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Loads the ModifyPartController.
     *
     * The method displays an error message if no part is selected.
     *
     * @param actionEvent Part modify button action.
     * @throws IOException From FXMLLoader.
     */
    public void onPartsModifyB(ActionEvent actionEvent) throws IOException {

        partToModify = partsTable.getSelectionModel().getSelectedItem();

        if (partToModify == null) {

            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Part not selected");
            alertError.showAndWait();

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }  else {

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    /**
     * Deletes the part selected by the user in the part table.
     *
     * Displays an error message if no part is selected and a confirmation
     * message before deleting the selected part.
     *
     * @param actionEvent Part delete button action.
     */
    public void onPartsDeleteB(ActionEvent actionEvent) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }


    /**
     * Loads AddProductController.
     *
     * @param actionEvent Add product button action.
     * @throws IOException From FXMLLoader.
     */
    public void onProductAddB(ActionEvent actionEvent) throws IOException  {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Loads the ModifyProductController.
     *
     * The method displays an error message if no product is selected.
     *
     * @param actionEvent Product modify button action.
     * @throws IOException From FXMLLoader.
     */
    public void onProductModifyB(ActionEvent actionEvent) throws IOException {

        productToModify = productTable.getSelectionModel().getSelectedItem();


        if (productToModify == null) {

            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Product not selected");
            alertError.showAndWait();

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("modifyProduct.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }  else {

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("modifyProduct.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    /**
     * Deletes the product selected by the user in the product table.
     *
     * Displays an error message if no product is selected and a confirmation
     * message before deleting the selected product. Also prevents user from deleting
     * a product with one or more associated parts.
     *
     * @param actionEvent Product delete button action.
     */
    public void onProductDeleteB(ActionEvent actionEvent) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Product not selected");
            alertError.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<Part> assocParts = selectedProduct.getAssociatedParts();

                if (assocParts.size() >= 1) {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("Error");
                    alertError.setHeaderText("Parts Associated");
                    alertError.setContentText("Remove all parts from product before deletion");
                    alertError.showAndWait();
                } else {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    }


    /**
     * Exits the program.
     *
     * @param actionEvent Exit button action.
     */
    public void onExitB(ActionEvent actionEvent) {

        System.exit(0);
    }


    /**
     * Initiates a search based on value in parts search text field and refreshes the parts
     * table view with search results.
     *
     * Parts can be searched for by ID.
     *
     * @param event Part search button action.
     */
    @FXML
    void onPartSearch(KeyEvent event) {

        ObservableList<Part> allParts = Inventory.getallParts();
        ObservableList<Part> partsReceived = FXCollections.observableArrayList();
        String searchString = partSearch.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsReceived.add(part);
            }
        }
        partsTable.setItems(partsReceived);
    }


    /**
     * Initiates a search based on value in products search text field and refreshes the products
     * table view with search results.
     *
     * Products can be searched for by ID.
     *
     * @param event Part search button action.
     */
    public void onProductSearch(KeyEvent event) {

        ObservableList<Product> allProducts = Inventory.getallProducts();
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String searchString = productSearch.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) ||
                    product.getName().contains(searchString)) {
                productsFound.add(product);
            }
        }

        productTable.setItems(productsFound);
    }


    /**
     * Initializes controller and populates table views.
     *
     * @param location The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partsTable.setItems(Inventory.getallParts());
        partsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        productTable.setItems(Inventory.getallProducts());
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
