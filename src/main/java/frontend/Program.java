package frontend;

import entity.Account;
import entity.Department;
import entity.Group;
//import entity.GroupAccount;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.function.Consumer;

public class Program {
    public static void main(String[] args) {
       try (var factory = HibernateUtil.buildSessionFactory()){
            factory.inTransaction(session -> {
               var group1 = new Group();
               group1.setName("Hibernate");
               session.persist(group1);

                var group2 = new Group();
                group2.setName("Spring Framework");
                session.persist(group2);

                   var account = new Account();
                   account.setName("Thinh");
                   account.setEmail("thinh@gmai.com");
                   session.persist(account);

                    var account2 = new Account();
                    account2.setName("Thuy");
                    account2.setEmail("thuy@gmai.com");
                    session.persist(account2);

                    account.setGroups(Arrays.asList(group1,group2));
                    account2.setGroups(Arrays.asList(group1,group2));
                    group1.setAccounts(Arrays.asList(account2,account));
                    group2.setAccounts(Arrays.asList(account2,account));


                    session.persist(group1);
                    session.persist(group2);   // save
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
