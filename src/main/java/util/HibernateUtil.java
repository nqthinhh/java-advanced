package util;

import entity.Account;
import entity.Department;
import entity.Group;
import entity.GroupAccount;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory(){
        var url = "jdbc:mysql://localhost:3306/lesson_03?createDatabaseIfNotExist=true";
        var configuration = new Configuration()
                .addAnnotatedClass(Group.class)
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(GroupAccount.class)
                .setProperty(AvailableSettings.URL, url)
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "Thinh166@")
                .setProperty(AvailableSettings.GLOBALLY_QUOTED_IDENTIFIERS, "true") // tự thêm cặp nháy ` `
                .setProperty(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true)
                .setProperty(AvailableSettings.HBM2DDL_AUTO, "create")
                .setProperty(AvailableSettings.SHOW_SQL, "true")
                .setProperty(AvailableSettings.HIGHLIGHT_SQL, "true");
        return configuration.buildSessionFactory();
    }
}
