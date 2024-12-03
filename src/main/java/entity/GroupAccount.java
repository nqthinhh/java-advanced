package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.val;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
@Table(name = "ga")
@IdClass(value = GroupAccount.PrimaryKey.class)
public class GroupAccount {
    @Id
    private int accountId;
    @Id
    private int groupId;

    @Column(name = "joined_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate joinedDate;

    @Getter
    @Setter
    @ToString
    @Embeddable
    public static class PrimaryKey implements Serializable {
        @Column(name = "group_id", nullable = false)
        private int groupId;

        @Column(name = "account_id", nullable = false)
        private int accountId;

    }
}
