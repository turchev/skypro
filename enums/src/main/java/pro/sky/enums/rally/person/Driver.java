package pro.sky.enums.rally.person;

public class Driver {

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
    public String toString() {
        return "Водитель " + getNick();
    }
}
