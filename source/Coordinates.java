package source;

class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }s

    public int getLongitude() {

        return (this.longitude);
    }

    public int getLatitude() {

        return (this.latitude);
    }

    public int getHeight() {

        return (this.height);
    }
}