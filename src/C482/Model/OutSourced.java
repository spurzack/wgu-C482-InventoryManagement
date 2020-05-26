package C482.Model;

public class OutSourced extends Parts {
    private String companyName;

    public OutSourced(int partID, int stock, int min, int max, String name, double partCost, String companyName) {
        super(partID, stock, min, max, name, partCost);

        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
