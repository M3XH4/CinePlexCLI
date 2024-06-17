import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.Paper;
import java.awt.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PrintManager {
    public static int printer = 0;
    public static PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

    private static final double inch = 72;
    private static final double width = 3 * inch;
    private static final double height = 2 * inch;

    public static void displayPrinters() {
        for (int i = 0; i < printServices.length; i++) {
            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "     " + (i + 1) + ". " + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + printServices[i].getName() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - ("     " + (i + 1) + ". " + printServices[i].getName()).length()));
        }
    }
    public static void displayCurrentPrinter() {
        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "    Current Printer: " + FontManager.BOLD + printServices[printer].getName() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - ("Current Printer: " + FontManager.BOLD + printServices[printer].getName()).length()));
    }

    public static void executePrint(Printable printable, double paperHeight) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        if (printServices.length > 0) {
            try {
                printerJob.setPrintService(printServices[printer]);
            } catch (PrinterException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Error!!! No Printer Found.");
            return;
        }

        PageFormat pageFormat = new PageFormat();
        Paper paper = new Paper();
        double tempHeight = (paperHeight != 0) ? paperHeight : pageFormat.getImageableHeight();
        paper.setSize(width, tempHeight);
        paper.setImageableArea(0, 0, width, tempHeight);
        pageFormat.setPaper(paper);
        pageFormat.setOrientation(PageFormat.PORTRAIT);

        Book book = new Book();
        book.append(printable, pageFormat);
        printerJob.setPageable(book);

        try {
            printerJob.print();
            System.out.println("Receipt printed successfully...");
        } catch (PrinterException e) {
            System.out.println("Error Printing: " + e.getMessage());
        }
    }
    public static void print(String movie, String cinemaCode, String seat) {
        Printable printable = (graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) return Printable.NO_SUCH_PAGE;
            Graphics2D g2D = (Graphics2D) graphics;
            g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            ReceiptTemplate.receiptSeats(g2D, movie, cinemaCode, seat);
            return Printable.PAGE_EXISTS;
        };
        executePrint(printable, height);
    }
    public static void print(ArrayList<Product> shoppingCart, ArrayList<Integer> quantities, ArrayList<Drinks.Size> sizes, double totalCost, double cash, double change) {
        Printable printable = (graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) return Printable.NO_SUCH_PAGE;
            Graphics2D g2D = (Graphics2D) graphics;
            g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            ReceiptTemplate.receiptShop(g2D, shoppingCart, quantities, sizes, totalCost, cash, change);
            return Printable.PAGE_EXISTS;
        };
        executePrint(printable, 0);
    }
}

class ReceiptTemplate {
    public static int inch = 72;
    public static int width = 3 * inch;
    public static int centerX = width / 2;

    public static void receiptSeats(Graphics2D g2D, String movie, String cinemaCode, String seat) {
        String purchaseDate = getCurrentDateTime();
        int y = 20;

        g2D.setFont(new Font("Bebas Neue", Font.PLAIN, 16));
        g2D.drawString("CINEPLEX", centerX - g2D.getFontMetrics().stringWidth("CINEPLEX") / 2, y);
        g2D.setFont(new Font("Bebas Neue", Font.PLAIN, 9));
        y += g2D.getFontMetrics().getHeight();
        g2D.drawString("Purchase Date: " + purchaseDate, centerX - g2D.getFontMetrics().stringWidth("Purchase Date: " + purchaseDate) / 2, y);
        g2D.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
        y += g2D.getFontMetrics().getHeight();
        g2D.drawString(seat, centerX - g2D.getFontMetrics().stringWidth(seat) / 2, y - 12);
        g2D.setFont(new Font("Bebas Neue", Font.PLAIN, 11));
        y += g2D.getFontMetrics().getHeight();
        g2D.drawString("Cinema " + cinemaCode, centerX - g2D.getFontMetrics().stringWidth("Cinema " + cinemaCode) / 2, y - 14);
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD));
        y += g2D.getFontMetrics().getHeight() - 10;
        g2D.drawString(movie, centerX - g2D.getFontMetrics().stringWidth(movie) / 2, y + 4);
        g2D.setFont(new Font("Bebas Neue", Font.PLAIN, 9));
        g2D.setFont(g2D.getFont().deriveFont(Font.ITALIC));
        y += 2 * g2D.getFontMetrics().getHeight() - 10;
        g2D.drawString( "THANK YOU AND WE HOPE YOU HAVE A GREAT TIME!", centerX - g2D.getFontMetrics().stringWidth( "THANK YOU AND WE HOPE YOU HAVE A GREAT TIME!") / 2, y);
    }

    public static void receiptShop(Graphics2D g2D, ArrayList<Product> shoppingCart, ArrayList<Integer> quantities, ArrayList<Drinks.Size> sizes, double totalCost, double cash, double change) {
        int leftMargin = 20;
        int topMargin = 30;
        int lineStartMargin = 15;
        int lineEndMargin = 180;
        g2D.setFont(new Font("Bebas Neue", Font.PLAIN, 26));
        g2D.drawString("CINEPLEX", centerX - g2D.getFontMetrics().stringWidth("CINEPLEX") / 2, topMargin);

        g2D.setFont(new Font("Bebas Neue", Font.PLAIN, 14));
        int dividerY = topMargin + 5;
        g2D.drawLine(lineStartMargin, dividerY, lineStartMargin + lineEndMargin, dividerY);

        int productHeaderY = dividerY + 15;
        g2D.drawString("PRODUCT", leftMargin + 5, productHeaderY);
        g2D.drawString("QTY", leftMargin + 80, productHeaderY);
        g2D.drawString("PRICE", leftMargin + 140, productHeaderY);

        int tableLineY = productHeaderY + 5;
        g2D.drawLine(lineStartMargin, tableLineY, lineStartMargin + lineEndMargin, tableLineY);

        int productDetailY = tableLineY + 15;
        int index = 0;

        for (Product product : shoppingCart) {
            if (product instanceof Drinks drinks) {
                g2D.drawString(drinks.getName() + " (" + sizes.get(index).toString().toUpperCase() + ")", leftMargin + 5, productDetailY);
                g2D.drawString(String.format("%.2f PHP", drinks.getPrices().get(sizes.get(index))), leftMargin + 130, productDetailY);
            } else {
                g2D.drawString(product.getName(), leftMargin + 5, productDetailY);
                g2D.drawString(String.format("%.2f PHP", product.getPrice()) , leftMargin + 130, productDetailY);
            }
            g2D.drawString(String.valueOf(quantities.get(index)), leftMargin + 85, productDetailY);
            productDetailY += 15;
            index++;
        }
        int endTableLineY = productDetailY + 5;
        g2D.drawLine(lineStartMargin, endTableLineY, lineStartMargin + lineEndMargin, endTableLineY);

        int totalCostY = endTableLineY + 25;
        g2D.drawString(String.format("TOTAL COST:           %.2f PHP", totalCost), leftMargin ,totalCostY);
        int cashY = totalCostY + 15;
        g2D.drawString(String.format("CASH:                       %.2f PHP", cash), leftMargin, cashY);
        int changeY = cashY + 15;
        g2D.drawString(String.format("CHANGE:                   %.2f PHP", change), leftMargin, changeY);

        int dividerY2 = changeY + 10;
        g2D.drawLine(lineStartMargin, dividerY2, lineStartMargin + lineEndMargin, dividerY2);

        int purchaseDateY = dividerY2 + 15;
        g2D.drawString("PURCHASE DATE: " + getCurrentDateTime(), centerX - g2D.getFontMetrics().stringWidth(("PURCHASE DATE: " + getCurrentDateTime())) / 2, purchaseDateY);

        int messageY = purchaseDateY + 15;
        g2D.drawString("THANK YOU FOR PURCHASING!", centerX - g2D.getFontMetrics().stringWidth("THANK YOU FOR PURCHASING!") / 2, messageY);
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        return formatter.format(new Date());
    }
}