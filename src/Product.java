import java.io.Serial;
import java.io.Serializable;

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
        SMALL(15.00),
        MEDIUM(25.00),
        LARGE(30.00);

        private double price;

        Size(double price) {
            setPrice(price);
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
    private double[] prices = new double[Size.values().length];
    private Size size;

    public Drinks(String name, Size size) {
        super(name, size.getPrice());
        setSize(size);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

}
