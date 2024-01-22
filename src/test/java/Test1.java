
import org.aeonbits.owner.ConfigCache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;


public class Test1 {
    HibernateConfig hibernateConfig = ConfigCache.getOrCreate(HibernateConfig.class);

    Configuration cfg = new Configuration()
            .addAnnotatedClass(City.class)
            .setProperty("hibernate.connection.url", hibernateConfig.hibernateUrl())
            .setProperty("hibernate.connection.username", hibernateConfig.hibernateUsername())
            .setProperty("hibernate.connection.password", hibernateConfig.hibernatePassword())
            .setProperty("hibernate.show_sql", hibernateConfig.hibernateShowSql())
            .setProperty("hibernate.format_sql", hibernateConfig.hibernateFormatSql())
            .setProperty("hibernate.use_sql_comments", hibernateConfig.hibernateUseSqlComments());

    SessionFactory sessionFactory = cfg.buildSessionFactory();
    @Test
    void test1() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<City> query = session.createQuery("from City where id = 1", City.class);
        City result = query.uniqueResult();
        session.close();
        System.out.println(result.getName());
    }
}
