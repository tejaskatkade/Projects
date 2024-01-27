// File: WeatherDataParser.java


import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherDataParser {
    public static String parseCityName(String jsonData) {
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject.getString("name");
    }

    public static int parseTemperature(String jsonData) {
        JSONObject mainObject = new JSONObject(jsonData);
        double temperatureInKelvin = mainObject.getJSONObject("main").getDouble("temp");
        return (int) (temperatureInKelvin - 273.15);
    }

    public static int parseHumidity(String jsonData) {
        return new JSONObject(jsonData).getJSONObject("main").getInt("humidity");
    }

    public static double parseWindSpeed(String jsonData) {
        return new JSONObject(jsonData).getJSONObject("wind").getDouble("speed");
    }

    public static String parseWeatherCondition(String jsonData) {
        JSONArray weatherArray = new JSONObject(jsonData).getJSONArray("weather");
        return weatherArray.getJSONObject(0).getString("description");
    }
}
