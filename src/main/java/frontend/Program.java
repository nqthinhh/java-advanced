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
            });

            factory.inSession(session -> {
                        var hql = "FROM Group";
                        var groups = session
                                .createSelectionQuery(hql, Group.class)
                                .getResultList();
                        for (var group : groups) {
                            System.out.println("group.getName() = " + group.getName());
                            System.out.println("group.getGroup().getName() = " + group.getAccount().getName());
                        }
                    });
           
           factory.inSession(session -> {
               var hql = "FROM Account";
               var accounts = session
                        .createSelectionQuery(hql, Account.class)
                       .getResultList();
               for (var account : accounts) {
                   System.out.println("account.getName() = " + account.getName());
                   System.out.println("account.getGroup().getName() = " + account.getGroup().getName());
               }
                   }
                   );
        }
    }
}
