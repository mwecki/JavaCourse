package pl.wsb.students.hibernate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_account_role", catalog = "java_course_wsb", uniqueConstraints =
@UniqueConstraint(columnNames = {
        "user_account_id", "role_id" }))
public class UserAccountRole implements java.io.Serializable {
    private Integer id;
    private Role role;
    private UserAccount userAccount;
    private Date created;
    private Date modified;
    public UserAccountRole() {
    }
    public UserAccountRole(Role role, UserAccount userAccount, Date modified) {
        this.role = role;
        this.userAccount = userAccount;
        this.modified = modified;
    }
    public UserAccountRole(Role role, UserAccount userAccount, Date created, Date
            modified) {
        this.role = role;
        this.userAccount = userAccount;
        this.created = created;
        this.modified = modified;
    }
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    public Role getRole() {
        return this.role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id", nullable = false)
    public UserAccount getUserAccount() {
        return this.userAccount;
    }
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", length = 19)
    public Date getCreated() {
        return this.created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified", nullable = false, length = 19)
    public Date getModified() {
        return this.modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }
}
