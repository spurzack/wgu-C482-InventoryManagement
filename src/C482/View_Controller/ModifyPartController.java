package C482.View_Controller;

import C482.Model.InHouse;
import C482.Model.Inventory;
import C482.Model.OutSourced;
import C482.Model.Parts;
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

public class ModifyPartController implements Initializable {

    @FXML private RadioButton outsourced;
    @FXML private RadioButton inHouse;
    @FXML private Label inhouseoroutsourced;
    @FXML private TextField ID;
    @FXML private TextField Name;
    @FXML private TextField Inventory;
    @FXML private TextField Price;
    @FXML private TextField Maximum;
    @FXML private TextField Minimum;
    @FXML private TextField companyORmachineID;

    private Stage stage;
    private Object scene;
    public Parts selectedPart;
    private int partID;

    public void radioadd()
    {
        if (outsourced.isSelected())
            this.inhouseoroutsourced.setText("Company Name");
        else
            this.inhouseoroutsourced.setText("Machine ID");
    }

    public void setParts(Parts selectedPart) {
        this.selectedPart = selectedPart;
        partID = C482.Model.Inventory.getAllParts().indexOf(selectedPart);
        ID.setText(Integer.toString(selectedPart.getPartID()));
        Name.setText(selectedPart.getName());
        Inventory.setText(Integer.toString(selectedPart.getStock()));
        Price.setText(Double.toString(selectedPart.getPartCost()));
        Maximum.setText(Integer.toString(selectedPart.getMax()));
        Minimum.setText(Integer.toString(selectedPart.getMin()));
        if(selectedPart instanceof InHouse){
            InHouse ih = (InHouse) selectedPart;
            inHouse.setSelected(true);
            this.inhouseoroutsourced.setText("Machine ID");
            companyORmachineID.setText(Integer.toString(ih.getMachineID()));
        }
        else{
           OutSourced os = (OutSourced) selectedPart;
            outsourced.setSelected(true);
            this.inhouseoroutsourced.setText("Company Name");
            companyORmachineID.setText(os.getCompanyName());
        }
    }

    @FXML void onActionSave(ActionEvent event) throws IOException {
        int partInventory = Integer.parseInt(Inventory.getText());
        int partMin = Integer.parseInt(Minimum.getText());
        int partMax = Integer.parseInt(Maximum.getText());
        if (MainWindowController.confirmDialog("Save?", "Would you like to save this part?"))
            if (partMax < partMin) {
                MainWindowController.infoDialog("Input Error", "Error in min and max field", "Check Min and Max value.");
            } else if (partInventory < partMin || partInventory > partMax) {
                MainWindowController.infoDialog("Input Error", "Error in inventory field", "Inventory must be between Minimum and Maximum");
            } else {
                int id = Integer.parseInt(ID.getText());
                String name = Name.getText();
                double price = Double.parseDouble(Price.getText());
                int stock = Integer.parseInt(Inventory.getText());
                int min = Integer.parseInt(Minimum.getText());
                int max = Integer.parseInt(Maximum.getText());
                if (inHouse.isSelected()) {
                    try {
                        int machineID = Integer.parseInt(companyORmachineID.getText());
                        InHouse temp = new InHouse(id, stock, min, max, name, price, machineID);
                        C482.Model.Inventory.updatePart(partID, temp);
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                        stage.setTitle("Inventory Management System");
                        stage.setScene(new Scene((Parent) scene));
                        stage.show();
                }
                    catch (NumberFormatException e){
                        MainWindowController.infoDialog("Input Error", "Check Machine ID ", "Machine ID can only contain numbers 0-9");
                    }
                }
                else {
                    String companyName = companyORmachineID.getText();
                    OutSourced temp = new OutSourced(id, stock, min, max, name, price, companyName);
                    C482.Model.Inventory.updatePart(partID, temp);
                    stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                    stage.setTitle("Inventory Management System");
                    stage.setScene(new Scene((Parent) scene));
                    stage.show();
                }
            }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
