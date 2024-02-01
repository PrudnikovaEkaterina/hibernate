import entity.*;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;


@Slf4j
public class Test1 {
    //    private static final Logger log = LoggerFactory.getLogger(Test1.class); вместо этого аннотация @Slf4j

    @Test
    void test1() {
        City cityPrint = new City();
//        вместо try с ресурсами можно использовать аннтотацию ломбок @Cleanup
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

//        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
//             Session session = sessionFactory.openSession()) {}

            Transaction transaction = session.beginTransaction();

//            CountryCode countryCode = CountryCode.builder()
//                    .countryCode("RU")
//                    .build();
        var countryCode = session.get(CountryCode.class, 1);

            City city = City.builder()
                    .name("Belarus")
                    .countryCode(countryCode)
                    .cityGeo(CityGeo
                            .builder()
                            .district("Bel")
                            .population(30)
                            .build())
                    .role(Role.USER)
                    .dataJson(DataJson
                            .builder()
                            .id(1)
                            .title(Title
                                    .builder()
                                    .titleFull("qqqq")
                                    .build())
                            .garRegion("0c5b2444-70a0-4932-980c-b4dc0d3f02b5")
                            .build())
                    .build();

//     session.persist(countryCode);
//     session.persist(city); // добавить запись в таблицу

//      session.merge(city);  // обновить
//      session.remove(city); // удалить

//      cityPrint = session.get(City.class, 1); // получить из базы по id
//       System.out.println(cityPrint);

        var countryCode1 = session.get(CountryCode.class, 1);
        System.out.println(countryCode1.getCityList());

            transaction.commit();



//            session.getTransaction().commit();

//            Query<City> query = session.createQuery("from City where id = 1", City.class);



//            System.out.println(result.getName());
//            session.getTransaction().commit();

    }



//    @Test
//    void test2() {
//        // по такому принципу работает Hibernate (рефлексия)
//        var city = City.builder()
//                .name("Ukraine")
//                .countryCode("PSE")
//                .district("EU")
//                .population(30)
//                .build();
//
//        var sql = """
//                insert into
//                %s
//                (%s)
//                values
//                %s
//                """;
//        // При помощи рефлексии получаем из @Table(name = "city") - имя city, кладем в  Optional.ofNullable, так как аннотации может не быть,
//        // получаем имя аннтотации, а если там null, то получаем имя класса
//        String tableName = Optional.ofNullable(City.class.getAnnotation(Table.class))
//                .map(table -> table.name())
//                .orElse(Stream.of(city.getClass().getName().split("\\.")).reduce((first, last) -> last).get());
//
//        System.out.println(tableName);
//
//        //При помощи рефлексии получаем список полей класса
//        Field[] fields = City.class.getDeclaredFields();
//        String columnNames = Arrays.stream(fields).map(field -> Optional.ofNullable(field.getAnnotation(Column.class)).map(Column::name)
//                .orElse(field.getName())).collect(Collectors.joining(", "));
//
//        System.out.println(columnNames);
//
//        String columnValues = Arrays.stream(fields).map(field -> "?").collect(Collectors.joining(", "));
//
//        System.out.println(columnValues);
//
//    }

}
