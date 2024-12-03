package frontend;

import entity.Account;
import entity.Department;
import entity.Group;
//import entity.GroupAccount;
import entity.GroupAccount;
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

                        var groupAccount1 = new GroupAccount();
                        var pk1 = new GroupAccount.PrimaryKey();
                        pk1.setGroupId(1);
                        pk1.setAccountId(1);
                        groupAccount1.setPk(pk1);
                        session.persist(groupAccount1);

                        var groupAccount2 = new GroupAccount();
                        var pk2 = new GroupAccount.PrimaryKey();
                        pk2.setGroupId(1);
                        pk2.setAccountId(2);
                        groupAccount2.setPk(pk2);
                        session.persist(groupAccount2);


                    });
            factory.inSession(session -> {
                        var hql = "FROM Group";
                        var groups = session
                                .createSelectionQuery(hql, Group.class)
                                .getResultList();
                        for (var group : groups) {
                            System.out.println("group.getName() = " + group.getName());

                        var groupAccounts = group.getGroupAccounts();
                        for (var groupAccount : groupAccounts){
                            System.out.println("groupAccount.getAccount().getName() = " + groupAccount.getAccount().getName());
                            System.out.println("groupAccount.getJoinedAt() = " + groupAccount.getJoinedAt());
                        }}
                    });
        }
    }
}
