package source;

import java.io.FileWriter;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    
    protected Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        int height;
        String name;
        StringBuilder str = new StringBuilder();

        switch (this.weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": This is hot.\n");
                height = ((this.coordinates.getHeight() + 2) > 100) ? 100 : this.coordinates.getHeight() + 2;
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), height);
                break;
            case "RAIN":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": Shower is here!\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
                break;
            case "FOG":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": My lovely fog!\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 1, this.coordinates.getLatitude(), this.coordinates.getHeight());
                break;
            case "SNOW":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": My rotor is going to freeze!\n");
                height = (this.coordinates.getHeight() - 12) < 0 ? 0 : this.coordinates.getHeight() - 12;
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), height);
                break;
        }

        try {
            FileWriter fileWriter = new FileWriter("simulation.txt", true);

            if (this.coordinates.getHeight() <= 0) {
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": landing.\n");
                fileWriter.append(str.toString());
                fileWriter.close();
                weatherTower.unregister(this);
            } else {
                fileWriter.append(str.toString());
                fileWriter.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }
}