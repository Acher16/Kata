package com.example.spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @NotEmpty(message = "NotEmpty")
    @Size(min = 2, max = 45, message = "Size")
    @Column(name = "name")
    private String name;

//    @NotEmpty(message = "NotEmpty")
    @Size(min = 2, max = 45, message = "Size")
    @Column(name = "last_name")
    private String lastName;

    @PositiveOrZero(message = "PositiveOrZero")
    @Column(name = "age")
    private int age;

    public User(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
