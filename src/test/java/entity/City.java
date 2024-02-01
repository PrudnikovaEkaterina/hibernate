package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "name") // считать хеш код только по полю name или можно исключать поля на основе других entity,
// типо private CountryCode countryCode, так в нем для расчета хеша нужен хеш поля  private List<City> cityList приводит к ошибке переполнения
@Table(name = "city1")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @ManyToOne (cascade = CascadeType.ALL, optional = false)//много городов могут иметь одинаковый код страны
//    по умолчанию соединяет таблицы при помощи left join
//    если нужен inner join, то необходимо в @ManyToOne (cascade = CascadeType.ALL) добавить optional = false
    @JoinColumn(name = "country_code_id")
    private CountryCode countryCode;

    @Embedded
    private CityGeo cityGeo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "data_json")
    @JdbcTypeCode(SqlTypes.JSON)
    private DataJson dataJson;

}
