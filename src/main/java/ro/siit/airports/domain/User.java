package ro.siit.airports.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Not a valid email")
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @NotBlank(message = "Username cannot be blank")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Password cannot be blank")
    @Size(min=6, max=20, message = "Password must contain 6 or more characters")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Password Two cannot be blank")
    @Column(name = "re_entered_password", nullable = false)
    private String re_entered_password;

    @Column(name = "role")
    private String role;

    @Column(name = "image", nullable  = true)
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRe_entered_password() {
        return re_entered_password;
    }

    public void setRe_entered_password(String re_entered_password) {
        this.re_entered_password = re_entered_password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Transient
    public String getImagePath() {
        if (image == null || id == null) {
            return null;
        }
        return "/user-images/" + id + "/" + image;
    }

    @Override
    public String toString() {
        return "User {" +
                ", username='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

