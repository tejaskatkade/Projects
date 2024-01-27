// File: MyWeatherApp.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyWeatherApp {
    public static void main(String[] args) {
        System.out.println("*** WEATHER APPLICATION IN JAVA ***");

        //StringBuilder response = new StringBuilder();
        try {
            System.out.println("Enter City Name : ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String cName = br.readLine();
            String APIId = "c3a0e077834ae56c4afd8cff591c1a1e";

            String jsonData = WeatherApiService.getWeatherData(cName, APIId);

            String cityName = WeatherDataParser.parseCityName(jsonData);
            System.out.println("City Name:" + cityName);

            int temperatureInCelsius = WeatherDataParser.parseTemperature(jsonData);
            System.out.println("Temperature: " + temperatureInCelsius +"  Degree Celsius");

            int humidity = WeatherDataParser.parseHumidity(jsonData);
            System.out.println("Humidity: " + humidity);

            double windSpeed = WeatherDataParser.parseWindSpeed(jsonData);
            System.out.println("Wind Speed: " + windSpeed);

            String weatherCondition = WeatherDataParser.parseWeatherCondition(jsonData);
            System.out.println("Weather Condition: " + weatherCondition);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
