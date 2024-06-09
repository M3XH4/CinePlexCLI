import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public abstract class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private double price;

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Snack extends Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Snack(String name, double price) {
        super(name, price);
    }
}

class Drinks extends Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public enum Size {
        SMALL,
        MEDIUM,
        LARGE;

        private double price;  // Each size has a price.

        public double getPrice() {
            return this.price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
    private Size size;

    public Drinks(String name, double price, Size size) {
        super(name, price);
        setSize(size);
        setPriceForSize(size, price);
    }
    // Get the price for a specific size
    public double getPriceForSize(Size size) {
        return size.getPrice();
    }

    // Set the price for a specific size
    public void setPriceForSize(Size size, double price) {
        size.setPrice(price);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }


}
