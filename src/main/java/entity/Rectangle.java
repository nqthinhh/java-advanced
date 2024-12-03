package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rectangle")
@PrimaryKeyJoinColumn(name = "id")
public class Rectangle extends Shape{
    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;
}
