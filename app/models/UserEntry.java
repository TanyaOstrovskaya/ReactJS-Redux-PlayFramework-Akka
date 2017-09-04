package models;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class UserEntry {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator="some_seq_gen")
    @SequenceGenerator(name="some_seq_gen", sequenceName="t_seq_2", allocationSize=1)
    private int id;

    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String email;

    public UserEntry () {}

    public UserEntry(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
