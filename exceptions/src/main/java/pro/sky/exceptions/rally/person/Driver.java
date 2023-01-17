package pro.sky.exceptions.rally.person;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Driver {

    private String nick;
    private final Set<CategoryType> drivingLicenses;
    private int experience;

    public Driver(String nick, int experience) {
        this.nick = nick;
        this.experience = experience;
        this.drivingLicenses = new HashSet<>();
    }

    public Driver(String nick, int experience, CategoryType... drivingLicenses) {
        this(nick, experience);
        setDrivingLicenses(drivingLicenses);
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Set<CategoryType> getDrivingLicenses() {
        return drivingLicenses;
    }

    public boolean isCategory(CategoryType categoryType) {
        return this.drivingLicenses.contains(categoryType);
    }

    public void setDrivingLicenses(CategoryType... drivingLicenses) {
        this.drivingLicenses.clear();
        if (drivingLicenses != null) {
            this.drivingLicenses.addAll(Arrays.asList(drivingLicenses));
        }
    }

    public boolean setDrivingLicense(CategoryType drivingLicense) {
        return this.drivingLicenses.add(drivingLicense);
    }

    @Override
    public String toString() {
        return String.format("Водитель %s, %s", getNick(),
                Objects.requireNonNullElse(getDrivingLicenses(), "Нет прав"));
    }

}
