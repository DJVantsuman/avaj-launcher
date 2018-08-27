package source;

import java.io.FileWriter;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    
    protected JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        int height;
        StringBuilder str = new StringBuilder();
        String name;

        switch (this.weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": Hello sunny!\n");
                height = ((this.coordinates.getHeight() + 2) > 100) ? 100 : this.coordinates.getHeight() + 2;
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 10, height);
                break;
            case "RAIN":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": It's raining. Better watch out for lightings.\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
                break;
            case "FOG":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": Can't see anything! Where are you, tower???\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
                break;
            case "SNOW":
                name = this.getClass().getSimpleName() + '#' + this.name;
                str.append(name).append('(').append(this.id).append(')').append(": OMG! Winter is coming!\n");
                height = (this.coordinates.getHeight() - 7) < 0 ? 0 : this.coordinates.getHeight() - 7;
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