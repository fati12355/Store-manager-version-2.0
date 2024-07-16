package entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

//import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String hash; // This stores the hashed password

    // Constructors, Getters, and Setters

    public Long getId() {
        return id;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}


