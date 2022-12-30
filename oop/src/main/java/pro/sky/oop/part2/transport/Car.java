package pro.sky.oop.part2.transport;

import java.time.LocalDate;
import java.util.Objects;

public class Car {
    private static final String DEFAULT_CAR = "default";
    private static final float DEFAULT_ENGINE_VOLUME = 1.5f;
    private static final String DEFAULT_COLOR = "белый";
    private static final int DEFAULT_YEAR = 2000;
    private static final int DEFAULT_NUMBER_SEATS = 5;

    private final String brand;
    private final String model;
    private float engineVolume;
    private String color;
    private final int year;
    private final String country;
    private String gearbox;
    private final String bodyType;
    private String licensePlate;
    private final int numberSeats;
    private boolean winterTires;
    private Key key;


    public Car(String brand, String model, float engineVolume, String color, int year, String country,
               String gearbox, String bodyType, String licensePlate, int numberSeats) {
        this(brand, model, engineVolume, color, year, country,
                gearbox, bodyType, licensePlate, numberSeats, chooseTiresForSeason(LocalDate.now().getMonthValue()), null);
    }

    public Car(String brand, String model, float engineVolume, String color, int year, String country,
               String gearbox, String bodyType, String licensePlate, int numberSeats, boolean winterTires, Key key) {
        // 1
        this.brand = brand == null || brand.isBlank() ? DEFAULT_CAR : brand;
        this.model = model == null || model.isBlank() ? DEFAULT_CAR : model;
        this.engineVolume = engineVolume <= 0.0f ? DEFAULT_ENGINE_VOLUME : engineVolume;
        this.color = color == null || color.isBlank() ? DEFAULT_COLOR : color;
        this.year = year <= 0 ? DEFAULT_YEAR : year;
        this.country = country == null || country.isBlank() ? DEFAULT_CAR : country;
        // 2
        this.gearbox = gearbox == null || gearbox.isBlank() ? DEFAULT_CAR : gearbox;
        this.bodyType = bodyType == null || bodyType.isBlank() ? DEFAULT_CAR : bodyType;
        this.licensePlate = licensePlate == null || licensePlate.isBlank() ? DEFAULT_CAR : licensePlate;
        this.numberSeats = numberSeats <= 0 ? DEFAULT_NUMBER_SEATS : numberSeats;
        this.winterTires = winterTires;
        setKey(key);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public void setWinterTires(boolean winterTires) {
        this.winterTires = winterTires;
    }

    public boolean isWinterTires() {
        return winterTires;
    }

    /**
     * Устанавливает значение winterTires в зависимости от номера месяца из параметра,
     *
     * @param month Номер месяца от 1 до 12
     * @return true - зимняя резина, false - летняя
     * @throws IllegalArgumentException если месяц вне диапазона 1 ... 12
     */
    protected static boolean chooseTiresForSeason(int month) {
        if (month < 0 || month > 12) {
            throw new IllegalArgumentException("Недопустимое значение номера месяца: " + month);
        }
        switch (month) {
            case 11:
            case 12:
            case 1:
            case 2:
            case 3:
                return true;
        }
        return false;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = Objects.requireNonNullElseGet(key, () -> new Key(false, false));
    }

    @Override
    public String toString() {
        return String.format("%s %s, цвет кузова - %s, объем двигателя - %.1f л., страна сборки - %s, год производства - %d, " +
                        "коробка передач - %s, %n\tтип кузова - %s, регистрационный номер - %s, количество мест - %s, %s, %s",
                getBrand(), getModel(), getColor(), getEngineVolume(), getCountry(), getYear(),
                getGearbox(), getBodyType(), getLicensePlate(), getNumberSeats(),
                (isWinterTires() ? "зимняя резина" : "летняя резина"), getKey());
    }

    public static class Key {
        private final boolean remoteEngineStart;
        private final boolean keylessAccess;

        public boolean isRemoteEngineStart() {
            return remoteEngineStart;
        }

        public boolean isKeylessAccess() {
            return keylessAccess;
        }

        public Key(boolean remoteEngineStart, boolean keylessAccess) {
            this.remoteEngineStart = remoteEngineStart;
            this.keylessAccess = keylessAccess;
        }

        @Override
        public String toString() {
            return String.format("Опции{%s, %s}",
                    "Удаленный запуск двигателя: " + (remoteEngineStart ? "есть" : "нет"),
                    "Бесключевой доступ: " + (keylessAccess ? "есть" : "нет"));
        }
    }

}
