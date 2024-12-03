package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "account")
    private List<GroupAccount> groupAccounts;

}
