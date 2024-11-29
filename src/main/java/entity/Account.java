package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table ( name = "account")
public class Account {
    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column ( name = "name", length = 50, nullable = false)
    private String name;

    @Column( name = "email", unique = true, nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id",
            unique = true,
            nullable = false)
    private Group group;

}
