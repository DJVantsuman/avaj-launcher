package source;

import java.util.Random;

class WeatherProvider {

    private static String[] weather = {"SUN", "FOG", "RAIN", "SNOW"};
    private static WeatherProvider weatherProvider;

    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null)
            weatherProvider = new WeatherProvider();
        return (weatherProvider);
    }

    public String getCurrentWeather(Coordinates coordinates) {
        String currentWeather;
        if (coordinates.getHeight() > 70)
            currentWeather = weather[(new Random().nextInt(3)) + 1];
        else
            currentWeather = weather[new Random().nextInt(4)];
        return currentWeather;
    }
}