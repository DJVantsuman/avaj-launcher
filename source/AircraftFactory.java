package source;

class AircraftFactory {
    Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws Exception {
        Flyable flyable;

        switch (type) {
            case "Helicopter":
                flyable = new Helicopter(name, new Coordinates(longitude, latitude, height));
                break;
            case "JetPlane":
                flyable = new JetPlane(name, new Coordinates(longitude, latitude, height));
                break;
            case "Baloon":
                flyable = new Baloon(name, new Coordinates(longitude, latitude, height));
                break;
            default:
                throw new IllegalArgumentException("Illegal aircraft type");
        }

        return (flyable);
    }
}