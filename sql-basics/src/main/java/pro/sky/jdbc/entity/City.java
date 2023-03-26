package pro.sky.jdbc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private int cityId;
    @Column(name = "city_name", length = 60)
    private String cityName;
}
