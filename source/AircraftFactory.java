package source;

class AircraftFactory {
    Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws Exception {
        Flyable flyable;

        if(type.equals("Helicopter"))
            flyable = new Helicopter(name, new Coordinates(longitude, latitude, height));
        else if(type.equals("JetPlane"))
            flyable = new JetPlane(name, new Coordinates(longitude, latitude, height));
        else if(type.equals("Baloon"))
            flyable = new Baloon(name, new Coordinates(longitude, latitude, height));
        else
            throw new IllegalArgumentException("Illegal aircraft type");
        return (flyable);
    }
}