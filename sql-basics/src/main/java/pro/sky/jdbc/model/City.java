package pro.sky.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class City {
    private long cityId;
    private String cityName;

    @Override
    public String toString() {
        return cityName;
    }
}
