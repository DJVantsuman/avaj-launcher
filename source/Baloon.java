package source;

import java.io.FileWriter;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    
    protected Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions () {
        int height;
        String name;
        StringBuilder str = new StringBuilder();

        switch (this.weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": Let's enjoy the good weather and take some pics.\n");
                height = ((this.coordinates.getHeight() + 4) > 100) ? 100 : this.coordinates.getHeight() + 4;
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), height);
                break;
            case "RAIN":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": Damn you rain! You messed up my baloon.\n");
                height = this.coordinates.getHeight() - 5;
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), height);
                break;
            case "FOG":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": This fog... can't see anything...\n");
                height = this.coordinates.getHeight() - 3;
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), height);
                break;
            case "SNOW":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": It's snowing. We're gonna crash.\n");
                height = (this.coordinates.getHeight() - 15) < 0 ? 0 : this.coordinates.getHeight() - 15;
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