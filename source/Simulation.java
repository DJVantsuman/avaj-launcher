package source;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Simulation {
    private static AircraftFactory aircraftFactory = new AircraftFactory();
    
    public static void main(String args[]) throws Exception {
        WeatherTower weatherTower = new WeatherTower();
        Flyable flyable;

        try {
            BufferedReader buferReader = new BufferedReader(new FileReader(args[0]));
            String var = buferReader.readLine();
            String planes[];

            if (var != null) {
                Integer countSimulations = Integer.parseInt(var);
                if (countSimulations < 1) {
                    System.out.println("ERROR: Wrong number of simulations");
                    System.exit(1);
                }

                new File("simulation.txt");

                while ((var = buferReader.readLine()) != null) {
                    planes = var.split(" ");
                    if (planes.length != 5)
                        throw new IllegalArgumentException("Wrong data for aircraft");
                    flyable = aircraftFactory.newAircraft(planes[0], planes[1],
                            Integer.parseInt(planes[2]), Integer.parseInt(planes[3]),
                            Integer.parseInt(planes[4]));
                    flyable.registerTower(weatherTower);
                    weatherTower.register(flyable);
                }

                for (int i = 0; i < countSimulations; i++) {
                    weatherTower.changeWeather();
                }

            } else
                throw new IllegalArgumentException("File is empty");
        } catch (Exception ex) {
            System.out.println("ERROR: Something wrong and exception message is: " + ex.getMessage());
        }
    }
}