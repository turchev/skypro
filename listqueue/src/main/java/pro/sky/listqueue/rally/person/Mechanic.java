package pro.sky.listqueue.rally.person;

import pro.sky.listqueue.rally.transport.Transport;

import java.util.List;

public class Mechanic extends Person {
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

    public void performMaintenance(List<Transport> transports) {
        // TODO: 20.01.2023 в ТЗ действие метода не описано, наверное будет позже
    }

    public void fixTransport(List<Transport> transports) {
        // TODO: 20.01.2023 в ТЗ действие метода не описано, наверное будет позже
    }

    @Override
    public String toString() {
        return "Механик " + nick;
    }
}
