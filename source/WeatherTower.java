package source;

class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        String currentWeather = WeatherProvider.getProvider().getCurrentWeather(coordinates);
        return (currentWeather);
    }

    public void changeWeather() {
        this.conditionsChanged();
    }
}