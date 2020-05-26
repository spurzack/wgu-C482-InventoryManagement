package C482.View_Controller;

import C482.Model.InHouse;
import C482.Model.Inventory;
import C482.Model.OutSourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import C482.Model.Inventory.*;

import static C482.Model.Inventory.getAllParts;

public class AddPartController implements Initializable {
    @FXML private RadioButton outsourced;
    @FXML private Label inhouseoroutsourced;
    @FXML private TextField Name;
    @FXML private TextField Inventory;
    @FXML private TextField Price;
    @FXML private TextField Maximum;
    @FXML private TextField Minimum;
    @FXML private TextField companyORmachineID;
    private Stage stage;
    private Object scene;


    public void radioadd()
    {
        if (outsourced.isSelected())
            this.inhouseoroutsourced.setText("Company Name");
        else
            this.inhouseoroutsourced.setText("Machine ID");
    }
    
    @FXML public void onActionCancel(ActionEvent event) throws IOException {
        if(MainWindowController.confirmDialog("Cancel?", "Are you sure?")) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }
    //save part
    @FXML public void onActionSave(ActionEvent event) {
        try {
            int partInventory = Integer.parseInt(Inventory.getText());
            int partMin = Integer.parseInt(Minimum.getText());
            int partMax = Integer.parseInt(Maximum.getText());
            if(MainWindowController.confirmDialog("Save?", "Would you like to save this part?"))
            if(partMax < partMin) {
                MainWindowController.infoDialog("Input Error", "Error in min and max field", "Check Min and Max value." );
            }
            else if(partInventory < partMin || partInventory> partMax) {
                MainWindowController.infoDialog("Input Error", "Error in inventory field", "Inventory must be between Minimum and Maximum" );
            }
            else{
                int newID = getNewID();
                String name = Name.getText();
                int stock = partInventory;
                double price = Double.parseDouble(Price.getText());
                int min = partMin;
                int max = partMax;
                if (outsourced.isSelected()) {
                    String companyName = companyORmachineID.getText();
                    OutSourced temp = new OutSourced(newID, stock, min, max, name, price, companyName);
                    C482.Model.Inventory.addPart(temp);
                } else {
                    int machineID = Integer.parseInt(companyORmachineID.getText());
                    InHouse temp = new InHouse(newID, stock, min, max, name, price, machineID);
                    C482.Model.Inventory.addPart(temp);
                }
                stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                stage.setTitle("Inventory Management System");
                stage.setScene(new Scene((Parent) scene));
                stage.show();
            }
        }
        catch (Exception e){
            MainWindowController.infoDialog("Input Error", "Error in adding part", "Check fields for correct input" );
        }
    }

    public static int getNewID(){
        int newID = 1;
        for (int i = 0; i < getAllParts().size(); i++) {
                newID++;
            }
        return newID;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

