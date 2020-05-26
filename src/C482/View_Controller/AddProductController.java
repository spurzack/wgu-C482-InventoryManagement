package C482.View_Controller;

import C482.Model.Inventory;
import C482.Model.Parts;
import C482.Model.Products;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddProductController implements Initializable {

    private Stage stage;
    private Object scene;

    //Un Associated Parts Table
    @FXML private TableView<Parts> PartsTableView;
    @FXML private TableColumn<Parts, Integer> PartsIDColumn;
    @FXML private TableColumn<Parts, String> PartsNameColumn;
    @FXML private TableColumn<Parts, Integer> PartsInventoryColumn;
    @FXML private TableColumn<Parts, Double> PartsCostColumn;

    //Associated Parts Table
    @FXML private TableView<Parts> AssociatedPartsTableView;
    @FXML private TableColumn<Products, Integer> AssociatedPartsIDColumn;
    @FXML private TableColumn<Products, String> AssociatedPartsNameColumn;
    @FXML private TableColumn<Products, Integer> AssociatedPartsInventoryColumn;
    @FXML private TableColumn<Products, Double> AssociatedPartsCostColumn;

    //Add Fields
    @FXML private TextField APRName;
    @FXML private TextField APRInventory;
    @FXML private TextField APRPrice;
    @FXML private TextField APRMaximum;
    @FXML private TextField APRMinimum;

    //Other Buttons/Fields
    @FXML private TextField SearchField;
    private ObservableList<Parts> associatedParts = FXCollections.observableArrayList();
    private ObservableList<Parts> originalParts = FXCollections.observableArrayList();

    @FXML public void searchPartButton(ActionEvent event) {
        String term = SearchField.getText();
        ObservableList foundParts = Inventory.lookupPart(term);
        if(foundParts.isEmpty()) {
            MainWindowController.confirmDialog("No Match", "Unable to locate a Part name with: " + term);
        } else {
            PartsTableView.setItems(foundParts);
        }
    }

    @FXML void addPartToProduct(ActionEvent event) {
        Parts selectedPart = PartsTableView.getSelectionModel().getSelectedItem();

        if(selectedPart != null) {
            associatedParts.add(selectedPart);
            updatePartTable();
            updateAssociatedPartTable();
        }

        else {
            MainWindowController.infoDialog("Select a part","Select a part", "Select a part to add to the Product");
        }
    }

    @FXML
    void deletePartFromProduct(ActionEvent event) {
        Parts selectedPart = AssociatedPartsTableView.getSelectionModel().getSelectedItem();

        if(selectedPart != null) {
            MainWindowController.confirmDialog("Deleting Parts","Are you sure you want to delete " + selectedPart.getName() + " from the Product?");
            associatedParts.remove(selectedPart);
            updatePartTable();
            updateAssociatedPartTable();
            }

        else {
            MainWindowController.infoDialog("No Selection","No Selection", "Please choose something to remove");
        }
    }

    @FXML public void cancelAddProduct(ActionEvent event) throws IOException {
        if (MainWindowController.confirmDialog("Cancel?", "Are you sure?")) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    @FXML void saveProduct(ActionEvent event) throws IOException {

        //Test if valid product
        if (associatedParts.isEmpty()){
            MainWindowController.infoDialog("Input Error", "Please add one ore more parts", "A product must have one or more parts associated with it.");
            return;}

        if (APRName.getText().isEmpty() || APRMinimum.getText().isEmpty() || APRMaximum.getText().isEmpty() || APRMaximum.getText().isEmpty() || APRPrice.getText().isEmpty()) {
            MainWindowController.infoDialog("Input Error", "Cannot have blank fields", "Check all the fields.");
            return;
        }

        Integer inv = Integer.parseInt(this.APRInventory.getText());
        Integer min = Integer.parseInt(this.APRMinimum.getText());
        Integer max = Integer.parseInt(this.APRMaximum.getText());

        if (max < min) {
            MainWindowController.infoDialog("Input Error", "Error in min and max field", "Check Min and Max value.");
            return;
        }

        if (inv < min || inv > max) {
            MainWindowController.infoDialog("Input Error", "Error in inventory field", "Inventory must be between Minimum and Maximum");
            return;
        }

        //Add Product
        if (MainWindowController.confirmDialog("Save?", "Would you like to save this part?")) {
            Products product = new Products();
            product.setProductID(getNewID());
            product.setName(this.APRName.getText());
            product.setStock(Integer.parseInt(this.APRInventory.getText()));
            product.setMin(Integer.parseInt(this.APRMinimum.getText()));
            product.setMax(Integer.parseInt(this.APRMaximum.getText()));
            product.setProductPrice(Double.parseDouble(this.APRPrice.getText()));
            product.addAssociatedPart(associatedParts);
            Inventory.addProduct(product);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }

    }


    private int getNewID(){
        int newID = 1;
        for (int i = 0; i < Inventory.getAllProducts().size(); i++) {
            if (Inventory.getAllProducts().get(i).getProductID() == newID) {
                newID++;
            }
        }
        return newID;
    }

    public void updatePartTable() {
        PartsTableView.setItems(Inventory.getAllParts());
    }

    private void updateAssociatedPartTable() {
        AssociatedPartsTableView.setItems(associatedParts);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        originalParts = Inventory.getAllParts();

        //Columns and Table for un-associated parts.
        PartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        PartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("partCost"));
        PartsTableView.setItems(originalParts);

        //Columns and Table for associated parts
        AssociatedPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        AssociatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("partCost"));
        AssociatedPartsTableView.setItems(associatedParts);

        updatePartTable();
        updateAssociatedPartTable();
    }
}
