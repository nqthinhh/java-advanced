package frontend;

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
                var circle = new Circle();
                circle.setId(1);
                circle.setColor("red");
                circle.setRadius(5);
                session.persist(circle);

                var rectangle = new Rectangle();
                rectangle.setId(2);
                rectangle.setColor("blue");
                rectangle.setWidth(3);
                rectangle.setHeight(4);
                session.persist(rectangle);

                    });
            factory.inSession(session -> {
                        var hql = "FROM Circle";
                        var shapes = session
                                .createSelectionQuery(hql, Shape.class)
                                .getResultList();
                        for (var shape : shapes) {
                            System.out.println("shape.getColor() = " + shape.getColor());
                        }
                    });
        }
    }
}
