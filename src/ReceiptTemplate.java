import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiptTemplate {
    public static void receiptSeats(Graphics2D g2D, String movie, String cinemaCode, String seat) {
        /*int startX = 50;
        int startY = 50;
        int lineHeight = 20;
        String purchaseDate = getCurrentDateTime();

        g2D.drawString("Purchase Date: " + purchaseDate, startX, startY);
        g2D.drawString("MOVIE: " + movie, startX, startY + lineHeight * 2);
        g2D.drawString("Cinema " + cinemaCode, startX, startY + lineHeight * 4);
        g2D.drawString(seat, startX, startY + lineHeight * 6);
        g2D.drawString("CINEPLEX", startX, startY + lineHeight * 8);
        g2D.drawString("THANK YOU AND WE HOPE YOU HAVE A GREAT TIME!", startX, startY + lineHeight * 10);*/

        int inch = 72;
        int width = 3 * inch;
        int height = 2 * inch;

        int centerX = width / 2;
        int startY = 20;
        int y = startY;
        g2D.setFont(new Font("Bebas Neue", Font.PLAIN, 12));
        String purchaseDate = getCurrentDateTime();
        g2D.drawString("CINEPLEX", centerX - g2D.getFontMetrics().stringWidth("CINEPLEX") / 2, y);

        y += g2D.getFontMetrics().getHeight();
        g2D.drawString("Purchase Date: " + purchaseDate, centerX - g2D.getFontMetrics().stringWidth("Purchase Date: " + purchaseDate) / 2, y);

        y += g2D.getFontMetrics().getHeight();
        g2D.drawString("Cinema " + cinemaCode, centerX - g2D.getFontMetrics().stringWidth("Cinema " + cinemaCode) / 2, y);

        y += 20;
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD));
        g2D.drawString(movie, centerX - g2D.getFontMetrics().stringWidth(movie) / 2, y);

        y += 2 * g2D.getFontMetrics().getHeight();
        g2D.setFont(g2D.getFont().deriveFont(Font.ITALIC));
        g2D.drawString( "THANK YOU AND WE HOPE YOU HAVE A GREAT TIME!", centerX - g2D.getFontMetrics().stringWidth( "THANK YOU AND WE HOPE YOU HAVE A GREAT TIME!") / 2, y);

    }
    public static void receiptShop(Graphics2D g2D) {

    }

    private static String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        return formatter.format(new Date());
    }
}
