package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Models an inventory of Parts and Products.
 *
 * The class provides persistent data for the application.
 *
 * @author Joseph Veal-Briscoe
 */

public class Inventory {



    /**
     * A list of all parts in inventory.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();



    /**
     * A list of all products in inventory.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();



    /**
     * Gets a list of all parts in inventory.
     *
     * @return A list of part objects.
     */
    public static ObservableList<Part> getallParts() {
        return allParts;
    }



    /**
     * Gets a list of all products in inventory.
     *
     * @return A list of product objects.
     */    public static ObservableList<Product> getallProducts() {

        return allProducts;
    }



    /**
     * Adds a part to the inventory.
     *
     * @param newPart The part object to add.
     */
    public static void addPart(Part newPart) {

        allParts.add(newPart);
    }


    /**
     * Adds a product to the inventory.
     *
     * @param newProduct The product object to add.
     */
    public static void addProduct(Product newProduct) {

        allProducts.add(newProduct);
    }


    /**
     * Removes a part from the list of parts.
     *
     * @param selectedPart The part to be removed.
     * @return A boolean indicating status of part removal.
     */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Removes a product from the list of parts.
     *
     * @param selectedProduct The product to be removed.
     * @return A boolean indicating status of product removal.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Searches the list of parts by ID.
     *
     * @param partId The part ID.
     * @return The part object if found, null if not found.
     */
    public static Part lookupPart(int partId) {
        Part partReceived = null;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                partReceived = part;
            }
        }
        return partReceived;

    }

    /**
     * Searches the list of parts by name.
     *
     * @param partName The part name.
     * @return A list of parts found.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsReceived = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().equals(partName)) {
                partsReceived.add(part);
            }
        }
        return partsReceived;

        //product
    }

    /**
     * Searches the list of products by ID.
     *
     * @param productId The product ID.
     * @return The product object if found, null if not found.
     */
    public static Product lookupProduct(int productId) {
        Product productReceived = null;

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                productReceived = product;
            }
        }

        return productReceived;
    }


    /**
     * Searches the list of products by name.
     *
     * @param productName The product name.
     * @return A list of products found.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsReceived = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                productsReceived.add(product);
            }
        }

        return productsReceived;
    }


    /**
     * Replaces a part in the list of parts.
     *
     * @param index Index of the part to be replaced.
     * @param selectedPart The part used for replacement.
     */
    public static void updatePart (int index, Part selectedPart) {

        allParts.set(index, selectedPart);
    }


    /**
     * Replaces a product in the list of products.
     *
     * @param index Index of the product to be replaced.
     * @param selectedProduct The product used for replacement.
     */
    public static void updateProduct (int index, Product selectedProduct) {

        allProducts.set(index, selectedProduct);
    }


    /**
     * Generates a new product ID.
     *
     * @return A unique product ID.
     */
    public static int newProductID() {
        return ++productIdField;
    }

    /**
     * An ID for a product. Variable used for unique product IDs.
     */
    private static int productIdField = 1002;
}


