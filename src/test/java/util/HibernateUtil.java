package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory(){
        Configuration cfg = new Configuration()
                .addAnnotatedClass(entity.City.class)
                .addAnnotatedClass(entity.CountryCode.class);
        return cfg.buildSessionFactory();
    }
}
