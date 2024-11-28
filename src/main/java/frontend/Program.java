package frontend;

import entity.Department;
import entity.GroupAccount;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.function.Consumer;

public class Program {
    public static void main(String[] args) {
       try (var factory = HibernateUtil.buildSessionFactory()){
            factory.inTransaction(session -> {
               var groupAccount = new GroupAccount();
               groupAccount.setAccountId(4);
               groupAccount.setGroupId(1);
               session.persist(groupAccount);
            });
           factory.inTransaction(session -> {
               var groupAccount = new GroupAccount();
               groupAccount.setGroupId(9);
               groupAccount.setAccountId(7);
               session.persist(groupAccount);
           });
           
           factory.inSession(session -> {
               var hql = "FROM GroupAccount";
               var groupAccounts = session
                        .createSelectionQuery(hql, GroupAccount.class)
                       .getResultList();
               for (var groupAccount : groupAccounts) {
                   System.out.println("👌 groupAccount = " + groupAccount);
               }
                   }
                   );
        }
    }
}
