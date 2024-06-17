import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    }
    private Map<Size, Double> prices;
    public Drinks(String name, double smallPrice, double mediumPrice, double largePrice) {
        super(name, 0.0);
        setPrices(new HashMap<>());
        getPrices().put(Size.SMALL, smallPrice);
        getPrices().put(Size.MEDIUM, mediumPrice);
        getPrices().put(Size.LARGE, largePrice);
    }

    public Map<Size, Double> getPrices() {
        return prices;
    }

    public void setPrices(Map<Size, Double> prices) {
        this.prices = prices;
    }

    public Size getSize(double price) {
        for (Drinks.Size size : Drinks.Size.values()) {
            if (price == getPrices().get(size)) {
                return size;
            }
        }
        return null;
    }
}
