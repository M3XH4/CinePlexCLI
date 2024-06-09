import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private ArrayList<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    public void updateProduct(Product product, String name, double price) {
        product.setName(name);
        product.setPrice(price);
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
                System.out.println(FontManager.primaryCombo + "| " + Global.spacerString(57, drinks.getName()) + " | " + Global.spacerString(22, drinks.getSize().toString()) + " | " + Global.spacerString(23, (String.format("%.2f PHP", drinks.getPrice()))) + " |" + FontManager.RESET);
            }
        }
        Global.putHorizontalLine(FontManager.primaryCombo, MainClass.horizontalLineLength);
    }

    public <T extends Product> Product findProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                if (product instanceof Drinks) {
                    return (Drinks) product;
                } else {
                    return product;
                }
            }
        }
        return null;
    }

    public Drinks.Size getDrinksSize(Product product) {
        Scanner input = new Scanner(System.in);
        if (product instanceof Drinks) {
            Drinks drinks = (Drinks) product;
            do {
                try {
                    System.out.print(FontManager.responseCombo + "Select The Size of " + drinks.getName() + " (SMALL,MEDIUM,LARGE): " + FontManager.RESET);
                    return Drinks.Size.valueOf(input.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println(FontManager.warningCombo + "WARNING! Please Enter The Correct Size. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - "WARNING! Please Enter The Correct Size. Please Try Again.".length()));
                }
            } while (true);
        }
        return null; // Not a drink, so no size selection
    }
}
