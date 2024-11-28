package util;

import entity.Department;
import entity.GroupAccount;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory(){
        var url = "jdbc:mysql://localhost:3306/lesson_02?createDatabaseIfNotExist=true";
        var configuration = new Configuration()
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(GroupAccount.class)
                .setProperty(AvailableSettings.URL, url)
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "Thinh166@")
                .setProperty(AvailableSettings.HBM2DDL_AUTO, "create")
                .setProperty(AvailableSettings.SHOW_SQL, "true")
                .setProperty(AvailableSettings.HIGHLIGHT_SQL, "true");
        return configuration.buildSessionFactory();
    }
}
