package C482.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Parts> allParts = FXCollections.observableArrayList();
    private static ObservableList<Products> allProducts = FXCollections.observableArrayList();

    //+ addPart(newPart:Part):void
    public static void addPart(Parts newPart){
        allParts.add(newPart);
    }

    //+ getAllParts():ObservableList<Part>
    public static ObservableList<Parts> getAllParts() {
        return allParts;
    }

    //+ addProduct(newProduct:Product):void
    public static void addProduct(Products newProduct){
        allProducts.add(newProduct);
    }

    //+ getAllProducts():ObservableList<Product>
    public static ObservableList<Products> getAllProducts() {
        return allProducts;
    }

    //+ lookupPart(partId:int):Part
    public static Parts lookupPartID(int partID) {
        Parts temp = null;
        for (Parts parts : allParts){
            if (partID == parts.getPartID()){
                temp = parts;
            }
        }
        return temp;
    }

    //+ lookupProduct(productId:int):Product
    public static Products lookupProductID(int productID) {
        Products temp = null;
        for (Products products : allProducts){
            if (productID == products.getProductID()){
                temp = products;
            }
        }
        return temp;
    }

    //+ lookupPart(partName:String):ObservableList<Part>
    public static ObservableList lookupPart(String searchPartName){
        ObservableList<Parts> foundParts = FXCollections.observableArrayList();

        if(searchPartName.length() == 0) {
            foundParts = allParts;
        }
        else {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getName().toLowerCase().contains(searchPartName.toLowerCase())) {
                    foundParts.add(allParts.get(i));
                }
            }
        }

        return foundParts;
    }

    //+ lookupProduct(productName:String):ObservableList<Product>
    public static ObservableList lookupProduct(String searchProductName){
        ObservableList<Products> foundProducts = FXCollections.observableArrayList();

        if(searchProductName.length() == 0) {
            foundProducts = allProducts;
        }
        else {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getName().toLowerCase().contains(searchProductName.toLowerCase())) {
                    foundProducts.add(allProducts.get(i));
                }
            }
        }

        return foundProducts;
    }

    //+ updatePart(index:int, selectedPart:Part):void
    public static void updatePart(int index, Parts selectedPart){
        allParts.set(index, selectedPart);
    }

    //+ updateProduct(index:int, newProduct:Product):void
    public static void updateProduct(int index, Products newProduct){
        allProducts.set(index, newProduct);
    }

    //+ deletePart(selectedPart:Part):boolean
    public static boolean deletePart(Parts selectedPart){
        return allParts.remove(selectedPart);
    }

    //+ deleteProduct(selectedProduct:Product):boolean
    public static boolean deleteProduct(Products selectedProduct){
        return allProducts.remove(selectedProduct);
    }
}
