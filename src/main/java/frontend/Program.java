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
               var pk = new GroupAccount.PrimaryKey();
               pk.setGroupId(1);
               pk.setAccountId(4);
               groupAccount.setPk(pk);
               session.persist(groupAccount);
            });
           factory.inTransaction(session -> {
               var groupAccount = new GroupAccount();
               var pk = new GroupAccount.PrimaryKey();
               pk.setGroupId(7);
               pk.setAccountId(9);
               groupAccount.setPk(pk);
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
