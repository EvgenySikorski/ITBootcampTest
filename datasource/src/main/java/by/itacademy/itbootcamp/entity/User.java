package by.itacademy.itbootcamp.entity;

import by.itacademy.itbootcamp.core.enums.EUserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Entity
@Table(name = "users")
@Validated
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1, max = 20)
    private String firstname;

    @Column(nullable = false)
    @Size(min = 1, max = 40)
    private String lastname;

    @Column(nullable = false)
    @Size(min = 1, max = 40)
    private String surname;

    @Column(nullable = false, unique = true)
    @Email
    @Size(max = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EUserRole role;

    public User() {
    }

    public User(Long id, String firstname, String lastname, String surname, String email, EUserRole role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EUserRole getRole() {
        return role;
    }

    public void setRole(EUserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, surname, email, role);
    }
}
