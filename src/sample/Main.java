package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/**
 * The Inventory Management is an application for managing
 * an inventory of parts and products consisting of associated parts.
 *
 * @author Joseph Veal-Briscoe
 *
 */
public class Main extends Application {


    /**
     * Loads MainPageController.
     *
     * @param primaryStage Passed from parent method.
     * @throws IOException From FXMLLoader.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The main method is the starting point of the application.
     *
     * Creates sample data and launches the application.
     *
     * @param args
     */
    public static void main(String[] args) {


        InHouse part1 = new InHouse(1, "Brakes", 15.99, 23, 10, 30, 4) ;
        InHouse part2 = new InHouse(2, "Wheel", 16.99, 11, 10, 30, 5);
        Outsourced part3 = new Outsourced (3, "Seat", 10.99, 15, 10, 30, "Emerson");
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);


        Product product1 = new Product(1000, "Giant Bike", 299.99, 13, 10, 15);
        Product product2 = new Product(1001, "Tricycle", 99.99, 5, 1, 10);
        Product product3 = new Product(1002, "Car", 399.99, 10, 5, 20);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);


        launch(args);
    }
}
