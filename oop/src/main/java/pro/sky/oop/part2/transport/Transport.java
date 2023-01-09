package pro.sky.oop.part2.transport;

public class Transport {
    protected static final String DEFAULT_CAR = "default";
    private static final int DEFAULT_YEAR = 2000;
    private static final String DEFAULT_COLOR = "белый";
    private static final int DEFAULT_MAX_SPEED = 150;

    private final String brand;
    private final String model;
    private final int year;
    private final String country;
    private String color;
    private int maxSpeed;

    public Transport(String brand, String model, int year, String country, String color, int maxSpeed) {
        this.brand = brand == null || brand.isBlank() ? DEFAULT_CAR : brand;
        this.model = model == null || model.isBlank() ? DEFAULT_CAR : model;
        this.year = year <= 0 ? DEFAULT_YEAR : year;
        this.country = country == null || country.isBlank() ? DEFAULT_CAR : country;
        setColor(color);
        setMaxSpeed(maxSpeed);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null || color.isBlank() ? DEFAULT_COLOR : color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed <= 0 ? DEFAULT_MAX_SPEED : maxSpeed;
    }

    @Override
    public String toString() {
        return String.format("%s %s, цвет кузова - %s, страна сборки - %s, год производства - %d, максимальная скорость - %s",
                getBrand(), getModel(), getColor(), getCountry(), getYear(), getMaxSpeed());
    }

}
