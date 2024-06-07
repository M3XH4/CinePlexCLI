import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class MovieAPI {

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
                System.out.println("Movie Not Found");
                return null;
            }
        }
    }
}
