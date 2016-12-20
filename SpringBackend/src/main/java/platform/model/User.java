package platform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Wenyu
 * @since 9/27/16
 */
@Entity
@Table(name="platform_user")
public class User implements Serializable {

    /**
     * See also: http://stackoverflow.com/questions/10628099/hibernate-could-not-get-next-sequence-value
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="id_sequence")
    @SequenceGenerator(name="id_sequence", sequenceName="id_sequence", allocationSize=1)
    private Long id;

    @NotNull
    @Column(name="user_name", length = 30)
    private String username;

    @NotNull
    @Column(name="user_email")
    private String email;

    @NotNull
    @Column(name="user_password", length = 50)
    private String password;

    @NotNull
    @Column(name="date_created")
    private Date dateCreated;

    @NotNull
    @Column(name="date_updated")
    private Date dateUpdated;

    @NotNull
    @Column(name="uuid", length = 32)
    private String uuid;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(final Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUunid(final String uuid) {
        this.uuid = uuid;
    }
}
