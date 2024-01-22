import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:hibernate.properties"
})

public interface HibernateConfig extends Config {
    @Config.Key("hibernate.connection.url")
    String hibernateUrl();

    @Config.Key("hibernate.connection.username")
    String hibernateUsername();

    @Config.Key("hibernate.connection.password")
    String hibernatePassword();

    @Config.Key("hibernate.show_sql")
    String hibernateShowSql();

    @Config.Key("hibernate.format_sql")
    String hibernateFormatSql();

    @Config.Key("hibernate.use_sql_comments")
    String hibernateUseSqlComments();
}
