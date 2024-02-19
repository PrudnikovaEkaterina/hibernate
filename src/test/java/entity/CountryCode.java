package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "cityList")
@EqualsAndHashCode(exclude = "cityList")
@Table(name = "country_code")
public class CountryCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code")
    private String name;

    @OneToMany (mappedBy = "countryCode") // 1 код страны может принадлежать многим городам
    private List<City> cityList;
}
