package pro.sky.oop.part4.person;

import pro.sky.oop.part4.Driving;

public class Driver implements Driving {

    private String nick;
    private boolean drivingLicense;
    private int experience;

    public Driver(String nick, boolean drivingLicense, int experience) {
        this.nick = nick;
        this.drivingLicense = drivingLicense;
        this.experience = experience;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public boolean isDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(boolean drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public void startDriving() {
        System.out.println("Стартует " + this);
    }

    @Override
    public void finishDriving() {
        System.out.println("Финиширует " + this);
    }

    @Override
    public void fillTransport() {
        System.out.println("Заправляет " + this);
    }

    @Override
    public String toString() {
        return "Водитель " + getNick();
    }
}
