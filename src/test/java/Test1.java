
import entity.City;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Test1 {
//    HibernateConfig hibernateConfig = ConfigCache.getOrCreate(HibernateConfig.class);

    Configuration cfg = new Configuration().addAnnotatedClass(entity.City.class);
//            .setProperty("hibernate.connection.url", hibernateConfig.hibernateUrl())
//            .setProperty("hibernate.connection.username", hibernateConfig.hibernateUsername())
//            .setProperty("hibernate.connection.password", hibernateConfig.hibernatePassword())
//            .setProperty("hibernate.show_sql", hibernateConfig.hibernateShowSql())
//            .setProperty("hibernate.format_sql", hibernateConfig.hibernateFormatSql())
//            .setProperty("hibernate.use_sql_comments", hibernateConfig.hibernateUseSqlComments());




    @Test
    void test1() {
        try ( SessionFactory sessionFactory = cfg.buildSessionFactory();
              Session session = sessionFactory.openSession();)
        {
            session.beginTransaction();

            //сохраняем город в таблицу
            session.persist(City.builder()
                    .name("Ukraine")
                    .countryCode("Uk")
                    .district("EU")
                    .build());

            Query<City> query = session.createQuery("from City where id = 1", City.class);
            City result = query.uniqueResult();
            System.out.println(result.getName());
            session.getTransaction().commit();
        }
    }
    @Test
    void test2(){
        var city = City.builder()
                .name("Ukraine")
                .countryCode("Uk")
                .district("EU")
                .build();

        var sql = """
                insert into 
                %s
                (%s)
                values
                %s
                """;
    // При помощи рефлексии получаем из @Table(name = "city") - имя city, кладем в  Optional.ofNullable, так как аннотации может не быть,
    // получаем имя аннтотации, а если там null, то получаем имя класса
        String tableName = Optional.ofNullable(City.class.getAnnotation(Table.class))
                .map(table -> table.name())
                .orElse(Stream.of( city.getClass().getName().split("\\.")).reduce( (first,last) -> last ).get());
        System.out.println(tableName);

        //При помощи рефлексии получаем список полей класса
        Field[] fields = City.class.getDeclaredFields();
        List<String> fieldsList = Arrays.stream(fields).map(field -> Optional.ofNullable(field.getAnnotation(Column.class)).map(Column::name)
                .orElse(field.getName())).collect(Collectors.toList());
        System.out.println(fieldsList);


    }





}
