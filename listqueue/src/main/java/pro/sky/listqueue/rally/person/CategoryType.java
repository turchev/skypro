package pro.sky.listqueue.rally.person;

public enum CategoryType {
    B ("Легковые автомобили"),
    C("Грузовые автомобили"),
    D("Автобусы");

    private final String value;

    CategoryType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Категория " + this.name() + " (" + this.value + ") ";
    }
}
