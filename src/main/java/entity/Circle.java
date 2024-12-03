package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "circle")
public class Circle extends Shape{
    @Column(name = "radius")
    private int radius;
}
