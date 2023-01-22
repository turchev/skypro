package pro.sky.hashset.rally.person;

import pro.sky.hashset.rally.transport.Transport;

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
}
