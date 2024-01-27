// File: WeatherApiService.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

public class WeatherApiService {
    public static String getWeatherData(String cityName, String APIId) throws IOException {
        StringBuilder response = new StringBuilder();

        URL url = null;
        try {
            URI uri = new URI("https", "api.openweathermap.org", "/data/2.5/weather", "q=" + cityName + "&APPID=" + APIId, null);
            url = uri.toURL();

        } catch (URISyntaxException e){
            e.printStackTrace();
        }

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } else {
            System.out.println("Error: " + responseCode);
        }

        connection.disconnect();

        return response.toString();
    }
}
