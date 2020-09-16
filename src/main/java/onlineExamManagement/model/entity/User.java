package onlineExamManagement.model.entity;

import onlineExamManagement.model.enumeration.userEnum.UserRole;
import onlineExamManagement.model.enumeration.userEnum.UserStatus;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String token;
    private UserRole role;
    private UserStatus status;
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Course> courses;

    public Long getId() {
        return id;
    }

    @Column(name = "id")
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @Column(name = "firstName", nullable = false)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Column(name = "lastName", nullable = false)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Column(name = "emailAddress", unique = true, nullable = false)
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    @Column(name = "password", nullable = false)
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Column(name = "courses")
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getToken() {
        return token;
    }

    @Column(name = "token")
    public void setToken(String token) {
        this.token = token;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
