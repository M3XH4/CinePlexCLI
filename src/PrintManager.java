import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.image.BufferedImage;
import java.awt.print.Paper;
import java.awt.*;
import java.awt.print.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class PrintManager {
    public static int printer = 5;
    public static PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
    private static final String horizontalLine = "================================================================================================================";

    private static final double inch = 72;
    private static final double width = 3 * inch;
    private static final double height = 2 * inch;

    public static void displayPrinters() {
        for (int i = 0; i < printServices.length; i++) {
            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "     " + (i + 1) + ". " + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + printServices[i].getName() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length() - ("     " + (i + 1) + ". " + printServices[i].getName()).length()));
        }
    }
    public static void displayCurrentPrinter() {
        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "    Current Printer: " + FontManager.BOLD + printServices[printer].getName() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length() - ("Current Printer: " + FontManager.BOLD + printServices[printer].getName()).length()));
    }
    public static void print(String movie, String cinemaCode, String seat) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

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

        Printable printable = new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2D = (Graphics2D) graphics;
                g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());


                ReceiptTemplate.receiptSeats(g2D, movie, cinemaCode, seat);

                return Printable.PAGE_EXISTS;
            }
        };

        PageFormat pageFormat = new PageFormat();
        Paper paper = new Paper();

        paper.setSize(width, height);
        paper.setImageableArea(0,0, width, height);
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
}
