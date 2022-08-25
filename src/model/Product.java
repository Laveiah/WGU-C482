package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Models a product that can contain associated parts.
 *
 * @author Joseph Veal-Briscoe
 *
 */
public class Product {

    /**
     * A list of associated parts for the product
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor for a new instance of a product
     *
     * @param id the ID for the product
     * @param name the name of the product
     * @param price the price of the product
     * @param stock the inventory level of the product
     * @param min the minimum level for the product
     * @param max the maximum level for the product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Adds a part to the associated parts list for the product.
     *
     * @param part The part to add
     */
    public void  addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * The getter for the id
     *
     * @return id of the product
     */
    public int getId() {
        return id;
    }


    /**
     * The setter for the id
     *
     * @param id The id of the product
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * The getter for the name
     *
     * @return name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * The setter for the name
     *
     * @param name The name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The getter for the price
     * @return price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * The setter for the price
     *
     * @param price The price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * The getter for the stock
     *
     * @return the stock of the product
     */
    public int getStock() {
        return stock;
    }

    /**
     * The setter for the stock
     *
     * @param stock The inventory level of the product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * The getter for the min
     *
     * @return the min of the product
     */
    public int getMin() {
        return min;
    }

    /**
     * The setter for the min
     *
     * @param min The minimum level for the product
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * The getter for max
     *
     * @return the max of the product
     */
    public int getMax() {
        return max;
    }

    /**
     * The setter for the max
     *
     * @param max The maximum level of the product
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds a part to the associated parts list for the product.
     *
     * @param part The part to add
     */
    public void addAssociatedParts(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes a part from the associated parts list for the product.
     *
     * @param selectedAssociatePart The part to delete
     */
    public void deleteAssociatedPart(Part selectedAssociatePart) {
        associatedParts.remove(selectedAssociatePart);
    }

    /**
     * Gets list of associated parts for the product.
     *
     * @return a list of parts
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
         }


    }


