package weatherApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyWeatherApp {
    public static void main(String[] args) {
        System.out.println("*** WEATHER APPLICATION IN JAVA ***");

        StringBuilder response = new StringBuilder();
        try {
            System.out.println("Enter City Name : ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String cName = br.readLine();
            String APIId = "c3a0e077834ae56c4afd8cff591c1a1e";

            // Specify the URL of the API
            // URL url = new URL(
            //         "https://api.openweathermap.org/data/2.5/weather?q=" + cName + "&APPID=" + APIId);

            try {
                    URI uri = new URI("https", "api.openweathermap.org", "/data/2.5/weather", "q=" + cName + "&APPID=" + APIId, null);
                    URL url = uri.toURL();
    
                } catch (URISyntaxException e){
                    e.printStackTrace();
                }

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method (GET, POST, etc.)
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the request was successful (HTTP 200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Process the response data
                // System.out.println("API Response: " + response.toString());
                
            } else {
                System.out.println("Error: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(response.toString());

        // City name
        String cityName = jsonObject.getString("name");
        System.out.println("City Name:" + cityName);
        // Extracting date time
        // long dateTime = jsonObject.getLong("dt");
        // System.out.println("Date Time: " + dateTime);

        // Extracting temperature
        JSONObject mainObject = jsonObject.getJSONObject("main");
        double temperatureInKelvin = mainObject.getDouble("temp");
        int temperatureInCelsius = (int) (temperatureInKelvin - 273.15);
        System.out.println("Temperature: " + temperatureInCelsius);

        // Extracting humidity
        int humidity = mainObject.getInt("humidity");
        System.out.println("Humidity: " + humidity);

        // Extracting wind speed
        JSONObject windObject = jsonObject.getJSONObject("wind");
        double windSpeed = windObject.getDouble("speed");
        System.out.println("Wind Speed: " + windSpeed);

        // Extracting weather condition
        JSONArray weatherArray = jsonObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        String weatherCondition = weatherObject.getString("description");
        System.out.println("Weather Condition: " + weatherCondition);

    }
}
