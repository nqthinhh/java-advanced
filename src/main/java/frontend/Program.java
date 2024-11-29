package frontend;

import entity.Account;
import entity.Department;
import entity.Group;
import entity.GroupAccount;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.function.Consumer;

public class Program {
    public static void main(String[] args) {
       try (var factory = HibernateUtil.buildSessionFactory()){
            factory.inTransaction(session -> {
               var group = new Group();
               group.setName("Hibernate");
               session.persist(group);

               var account = new Account();
               account.setName("Thinh");
               account.setEmail("thinh@gmai.com");
               account.setGroup(group);
               session.persist(account);

                var account2 = new Account();
                account2.setName("Thuy");
                account2.setEmail("thuy@gmai.com");
                account2.setGroup(group);
                session.persist(account2);
            });

            factory.inSession(session -> {
                        var hql = "FROM Group";
                        var groups = session
                                .createSelectionQuery(hql, Group.class)
                                .getResultList();
                        for (var group : groups) {
                            System.out.println("group.getName() = " + group.getName());

                        var accounts = group.getAccounts();
                        for (var account : accounts){
                            System.out.println("account.getName() = " + account.getName());
                        }}
                    });
        }
    }
}
