package pro.sky.hashmap.rally.person;

import pro.sky.hashmap.rally.transport.Transport;

import java.util.Objects;

public class Mechanic<T extends Transport> extends Person {
    private String company;

    public Mechanic(String nick, String company) {
        super(nick);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void performMaintenance(T transport) {
        System.out.println("Механик " + this + " проводит техосмотр " + transport);
    }

    public void fixTransport(T transport) {
        System.out.println("Механик " + this + " ремонтирует " + transport);
    }

    @Override
    public String toString() {
        return "Механик " + nick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mechanic)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Mechanic<?> mechanic = (Mechanic<?>) o;
        return Objects.equals(getCompany(), mechanic.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCompany());
    }
}
