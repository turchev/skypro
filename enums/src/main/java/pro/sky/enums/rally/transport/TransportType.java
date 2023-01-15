package pro.sky.enums.rally.transport;

public enum TransportType {
    BUS("Автобус"),
    CAR("Легковой автомобиль"),
    TRUCK("Грузовой автомобиль");

    private final String value;

    TransportType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
