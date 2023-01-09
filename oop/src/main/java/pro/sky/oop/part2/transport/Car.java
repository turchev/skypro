package pro.sky.oop.part2.transport;

import java.time.LocalDate;
import java.util.Objects;

public class Car extends Transport {

    private static final float DEFAULT_ENGINE_VOLUME = 1.5f;
    private static final int DEFAULT_NUMBER_SEATS = 5;

    private float engineVolume;
    private String gearbox;
    private final String bodyType;
    private String licensePlate;
    private final int numberSeats;
    private boolean winterTires;
    private Key key;

    public Car(String brand, String model, int year, String country, String color, int maxSpeed, String bodyType, int numberSeats) {
        this(brand, model, year, country, color, maxSpeed, bodyType, numberSeats, 0.0f, null, null,
                chooseTiresForSeason(LocalDate.now().getMonthValue()), null);
    }

    public Car(String brand, String model, int year, String country, String color, int maxSpeed, String bodyType, int numberSeats,
               float engineVolume, String gearbox, String licensePlate,
               boolean winterTires, Key key) {
        super(brand, model, year, country, color, maxSpeed);
        this.engineVolume = engineVolume <= 0.0f ? DEFAULT_ENGINE_VOLUME : engineVolume;
        this.gearbox = gearbox == null || gearbox.isBlank() ? DEFAULT_CAR : gearbox;
        this.bodyType = bodyType == null || bodyType.isBlank() ? DEFAULT_CAR : bodyType;
        this.licensePlate = licensePlate == null || licensePlate.isBlank() ? DEFAULT_CAR : licensePlate;
        this.numberSeats = numberSeats <= 0 ? DEFAULT_NUMBER_SEATS : numberSeats;
        this.winterTires = winterTires;
        setKey(key);
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume;
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
        return String.format("Автомобиль: %s, %n\tобъем двигателя - %.1f л., коробка передач - %s, тип кузова - %s, " +
                        "регистрационный номер - %s, количество мест - %s, %s, %n\t%s",
                super.toString(), getEngineVolume(), getGearbox(), getBodyType(), getLicensePlate(), getNumberSeats(),
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
