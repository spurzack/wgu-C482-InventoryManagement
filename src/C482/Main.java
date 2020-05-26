package C482;

//Zack Spurlock
//Student ID: 000742176


import C482.Model.InHouse;
import C482.Model.Inventory;
import C482.Model.OutSourced;
import C482.Model.Products;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Add Parts InHouse
        Inventory.addPart(new InHouse(1, 20, 1, 50, "Animal Crossing - New Horizons", 59.99, 19));
        Inventory.addPart(new InHouse(2, 25, 1, 50, "Legend of Zelda - Breath of the Wild", 59.99, 12));
        Inventory.addPart(new InHouse(3, 15, 1, 50, "Pokemon Shield", 49.99, 82));
        Inventory.addPart(new InHouse(4, 10, 1, 50, "Super Mario Odyssey", 29.99, 99));

        //Add Parts OutSourced
        Inventory.addPart(new OutSourced(5, 10, 1, 50, "Octopath Traveler", 39.99, "Square Enix"));
        Inventory.addPart(new OutSourced(6, 5, 1, 50, "Baba is You", 9.99, "Arvi Teikari, Hempuli Oy, MP2 Games"));
        Inventory.addPart(new OutSourced(7, 20, 1, 50, "Fortnite", 0.00, "Epic"));
        Inventory.addPart(new OutSourced(8, 25, 1, 50, "Xenoblade Chronicles 2", 44.99, "Monlith Soft"));
        Inventory.addPart(new OutSourced(9, 5, 1, 50, "Final Fantasy X|X-2 HD Remaster", 35.99, "Square Enix"));
        Inventory.addPart(new OutSourced(10, 20, 1, 50, "Dragon Quest Builders 2", 49.99, "Square Enix"));
        Inventory.addPart(new OutSourced(11, 25, 1, 50, "Trials of Mana", 49.99, "Square Enix"));

        //Add Products
        Inventory.addProduct(new Products(1, 10, 1, 50, "Nintendo Bundle", 249.99));
        Inventory.addProduct(new Products(2, 8, 1, 50, "Square Enix Bundle", 159.99));
        Inventory.addProduct(new Products(3, 8, 1, 50, "Assorted Publishers Bundle", 159.99));



        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/MainWindow.fxml"));
        Scene scene = new Scene(root, 1280, 580);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
