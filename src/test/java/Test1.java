import entity.*;
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

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
//            session.beginTransaction();

            City city = City.builder()
                    .name("Ukraine")
                    .countryCode("PSE")
                    .cityGeo(CityGeo
                            .builder()
                            .district("New")
                            .population(50)
                            .build())
                    .role(Role.ADMIN)
                    .dataJson(DataJson
                            .builder()
                            .id(1)
                            .title(Title
                                    .builder()
                                    .titleFull("aaaaaa")
                                    .build())
                            .garRegion("0c5b2444-70a0-4932-980c-b4dc0d3f02b5")
                            .build())
                    .build();

//            session.persist(city); // добавить запись в таблицу
//            session.merge(city);  // обновить
//            session.remove(city); // удалить

            City cityGet = session.get(City.class, 30); // получить из базы по id
            System.out.println(cityGet);

            transaction.commit();

//            session.getTransaction().commit();

//            Query<City> query = session.createQuery("from City where id = 1", City.class);



//            System.out.println(result.getName());
//            session.getTransaction().commit();
        }
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
