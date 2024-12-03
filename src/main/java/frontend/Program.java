package frontend;

import dto.DepartmentDto;
import entity.*;
//import entity.GroupAccount;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.function.Consumer;

public class Program {
    public static void main(String[] args) {
       try (var factory = HibernateUtil.buildSessionFactory()){
           factory.inTransaction(session -> {
               var sql = "INSERT INTO department(id, name, type, created_at, updated_at)" +
                       " VALUES (:id, :name, :type, NOW(), NOW())";
               var result = session.createNativeMutationQuery(sql)
                       .setParameter("id", "VA0000001")
                       .setParameter("name", "Ky thuat")
                       .setParameter("type", "D")
                       .executeUpdate();
               System.out.println("Them thanh cong = " + result);
           });

           factory.inSession(session -> {
               var sql = "SELECT * FROM department";
               var departments = session
                       .createNativeQuery(sql, Department.class)
                       .getResultList();
               for (Department department : departments) {
                   System.out.println("department.getName() = " + department.getName());
                   System.out.println("department.getId() = " + department.getId());
               }
           });
       }
    }
}
