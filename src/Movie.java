import java.io.IOException;
import java.net.UnknownHostException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;


public class Movie {

    private String name;
    private String details;

    public Movie(String name, String details) {
        setName(name);
        setDetails(details);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class MovieAPI {

    private static final String apiKey = "e84c1206";
    private static final String apiUrl = "http://www.omdbapi.com/";

    public static Movie fetchMovie(String movieName) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = apiUrl + "?t=" + movieName + "&apikey=" + apiKey;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code " + response);
            }

            String responseData = response.body().string();
            JSONObject object = new JSONObject(responseData);

            if (object.getString("Response").equals("True")) {
                String title = object.getString("Title");
                String plot = object.getString("Plot");

                return new Movie(title, plot);
            } else {
                return null;
            }
        } catch (UnknownHostException e) {
            System.out.println(FontManager.TEXT_RED_BRIGHT + "ERROR! No Internet Connection. Please Try Again Later...");
            return null;
        }
    }
}
