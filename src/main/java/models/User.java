package models;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
@Data
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false)
    private String username;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false)
    private String email;

    public User(){

    }
    public User(String username,String password, String email){
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User {" + this.username + ", " + this.password + ", " + this.email + "}";
    }
}
