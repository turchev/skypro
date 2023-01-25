package pro.sky.hashmap.rally.person;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Driver extends Person {

    private final Set<CategoryType> drivingLicenses = new HashSet<>();
    private int experience;

    public Driver(String nick, int experience) {
        super(nick);
        this.experience = experience;
    }

    public Driver(String nick, int experience, CategoryType... drivingLicenses) {
        this(nick, experience);
        setDrivingLicenses(drivingLicenses);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Driver)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Driver driver = (Driver) o;
        return getExperience() == driver.getExperience()
                && Objects.equals(getDrivingLicenses(), driver.getDrivingLicenses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDrivingLicenses(), getExperience());
    }
}
