package C482.Model;

public class InHouse extends Parts {
    private int machineID;

    public InHouse(int partID, int stock, int min, int max, String name, double partCost, int machineID) {
        super(partID, stock, min, max, name, partCost);

        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
