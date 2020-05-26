package C482.Model;

public abstract class Parts {
    private int partID, stock, min, max;
    private String name;
    private double partCost;

    public Parts(int partID, int stock, int min, int max, String name, double partCost) {
        this.partID = partID;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.name = name;
        this.partCost = partCost;
    }

    //Getters
    public int getPartID() {
        return partID;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getName() {
        return name;
    }

    public double getPartCost() {
        return partCost;
    }


    //Setters
    public void setPartID(int partID) {
        this.partID = partID;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPartCost(double partCost) {
        this.partCost = partCost;
    }
}
