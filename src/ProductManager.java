import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    public ArrayList<Product> findProductsByName(String name) {
        return (ArrayList<Product>) products.stream().filter(product -> product.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }
    public void removeProduct(Product product) throws IOException {
        products.remove(product);
        FileManager.saveProducts(getProducts());
        if (product instanceof Drinks drinks) {
            System.out.println(FontManager.primaryCombo + drinks.getName() + "- " + drinks.getSize() + " Successfully Deleted..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - (drinks.getName() + "- " + drinks.getSize() + " Successfully Deleted...").length()));
        } else {
            System.out.println(FontManager.primaryCombo + product.getName() + " Successfully Deleted..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - (product.getName() + " Successfully Deleted...").length()));
        }

    }

    public void updateProduct(Product product, String name, double price) {
        String tempName = (name.isBlank()) ? product.getName() : name;
        double tempPrice = (String.valueOf(price).isBlank()) ? product.getPrice() : price;

        product.setName(tempName);
        if (product instanceof Drinks drinks) {
            drinks.setPriceForSize(drinks.getSize(), tempPrice);
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
                System.out.println(FontManager.primaryCombo + "| " + Global.spacerString(57, drinks.getName()) + " | " + Global.spacerString(22, drinks.getSize().toString()) + " | " + Global.spacerString(23, (String.format("%.2f PHP", drinks.getPrice()))) + " |" + FontManager.RESET);
            }
        }
        Global.putHorizontalLine(FontManager.primaryCombo, MainClass.horizontalLineLength);
    }

    public <T extends Product> Product findProduct(String name) {
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
    public void addDrink(String name, Drinks.Size size) throws IOException {
        Scanner input =new Scanner(System.in);
        do {
            try {
                System.out.print(FontManager.responseCombo + "What Is The Price Of " + size + " Size Of " + name + ": " + FontManager.RESET);
                double price = input.nextDouble();
                input.nextLine();
                addProduct(new Drinks(name, price, size));
                FileManager.saveProducts(getProducts());
                break;
            } catch (InputMismatchException e) {
                System.out.println(FontManager.warningCombo + "WARNING! Invalid Input. Please Enter A Valid Amount.");
                input.nextLine();
            }
        } while (true);
    }
}
