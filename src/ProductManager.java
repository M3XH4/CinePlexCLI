import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private ArrayList<Product> products;
    private ArrayList<Product> shoppingCart;
    private ArrayList<Integer> quantities;
    private ArrayList<Drinks.Size> sizes;

    public ProductManager() {
        setProducts(new ArrayList<>());
        setShoppingCart(new ArrayList<>());
        setQuantities(new ArrayList<>());
        setSizes(new ArrayList<>());
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(ArrayList<Integer> quantities) {
        this.quantities = quantities;
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ArrayList<Drinks.Size> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<Drinks.Size> sizes) {
        this.sizes = sizes;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public ArrayList<Product> findProductsByName(String name) {
        return (ArrayList<Product>) products.stream().filter(product -> product.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public void removeProduct(Product product) throws IOException {
        products.remove(product);
        FileManager.saveProducts(getProducts());
        if (product instanceof Drinks drinks) {
            System.out.println(FontManager.primaryCombo + drinks.getName() + " (" + drinks.getSize(drinks.getPrice()) + ") Successfully Deleted..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - (drinks.getName() + " (" + drinks.getSize(drinks.getPrice()) + ") Successfully Deleted...").length()));
        } else {
            System.out.println(FontManager.primaryCombo + product.getName() + " Successfully Deleted..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - (product.getName() + " Successfully Deleted...").length()));
        }

    }

    public void updateProduct(Product product, String newName, double oldPrice, double newPrice) {
        String tempName = (newName.isBlank()) ? product.getName() : newName;
        double tempPrice = (String.valueOf(newPrice).isBlank()) ? oldPrice : newPrice;

        product.setName(tempName);
        if (product instanceof Drinks drinks) {
            drinks.getPrices().replace(drinks.getSize(oldPrice), tempPrice);
        } else {
            product.setPrice(tempPrice);
        }
    }

    public void displaySnacks() {
        Global.putHorizontalLine(FontManager.tertiaryCombo, MainClass.horizontalLineLength);
        System.out.println(FontManager.primaryCombo + "|" + Global.putSpaces(51) + "SNACKS" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - ("SNACKS".length() + 53)) + FontManager.primaryCombo + "|" + FontManager.RESET);
        Global.putHorizontalLine(FontManager.tertiaryCombo, MainClass.horizontalLineLength);
        Global.putHorizontalLine(FontManager.primaryCombo, MainClass.horizontalLineLength);
        System.out.println(FontManager.primaryCombo + "|" + Global.putSpaces(40) + "NAME" + Global.putSpaces(40) + "|" + Global.putSpaces(10) + "PRICE" + Global.putSpaces(10) + "|" + FontManager.RESET);
        Global.putHorizontalLine(FontManager.primaryCombo, MainClass.horizontalLineLength);
        for (Product product : products) {
            if (product instanceof Snack) {
                System.out.println(FontManager.primaryCombo + "| " + Global.spacerString(82, product.getName()) + " | " + Global.spacerString(23, (String.format("%.2f PHP", product.getPrice()))) + " |" + FontManager.RESET);
            }
        }
        Global.putHorizontalLine(FontManager.primaryCombo, MainClass.horizontalLineLength);
    }
    public void displayDrinks() {
        Global.putHorizontalLine(FontManager.tertiaryCombo, MainClass.horizontalLineLength);
        System.out.println(FontManager.primaryCombo + "|" + Global.putSpaces(51) + "DRINKS" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - ("DRINKS".length() + 53)) + FontManager.primaryCombo + "|" + FontManager.RESET);
        Global.putHorizontalLine(FontManager.tertiaryCombo, MainClass.horizontalLineLength);
        Global.putHorizontalLine(FontManager.primaryCombo, MainClass.horizontalLineLength);
        System.out.println(FontManager.primaryCombo + "|" + Global.putSpaces(28) + "NAME" + Global.putSpaces(27) + "|" + Global.putSpaces(10) + "SIZE" + Global.putSpaces(10) + "|" + Global.putSpaces(10) + "PRICE" + Global.putSpaces(10) + "|" + FontManager.RESET);
        Global.putHorizontalLine(FontManager.primaryCombo, MainClass.horizontalLineLength);
        for (Product product : products) {
            if (product instanceof Drinks drinks) {
                for (Drinks.Size size : Drinks.Size.values()) {
                    System.out.println(FontManager.primaryCombo + "| " + Global.spacerString(57, drinks.getName()) + " | " + Global.spacerString(22, size.toString()) + " | " + Global.spacerString(23, String.format("%.2f PHP", drinks.getPrices().get(size))) + " |" + FontManager.RESET);
                }
            }
        }
        Global.putHorizontalLine(FontManager.primaryCombo, MainClass.horizontalLineLength);
    }

    public Product findProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                if (product instanceof Drinks drinks) {
                    return drinks;
                } else {
                    return product;
                }
            }
        }
        return null;
    }

    public Drinks.Size getDrinksSize(Product product) {
        Scanner input = new Scanner(System.in);
        if (product instanceof Drinks drinks) {
            do {
                try {
                    System.out.print(FontManager.responseCombo + "Select The Size of " + drinks.getName() + " (SMALL,MEDIUM,LARGE): " + FontManager.RESET);
                    return Drinks.Size.valueOf(input.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println(FontManager.warningCombo + "WARNING! Please Enter The Correct Size. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - "WARNING! Please Enter The Correct Size. Please Try Again.".length()));
                }
            } while (true);
        }
        return null;
    }

}
