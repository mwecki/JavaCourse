package pl.wsb.students.hibernate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "api_token", catalog = "java_course_wsb", uniqueConstraints =
@UniqueConstraint(columnNames = "access_token"))
public class ApiToken implements java.io.Serializable {
    private Integer id;
    private UserAccount userAccount;
    private Date created;
    private Date modified;
    private String accessToken;
    private String refreshToken;
    private Date validTo;
    public ApiToken() {
    }
    public ApiToken(UserAccount userAccount, Date modified, String accessToken) {
        this.userAccount = userAccount;
        this.modified = modified;
        this.accessToken = accessToken;
    }
    public ApiToken(UserAccount userAccount, Date created, Date modified, String
            accessToken, String refreshToken,
                    Date validTo) {
        this.userAccount = userAccount;
        this.created = created;
        this.modified = modified;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.validTo = validTo;
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
    @Column(name = "access_token", unique = true, nullable = false)
    public String getAccessToken() {
        return this.accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    @Column(name = "refresh_token")
    public String getRefreshToken() {
        return this.refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "valid_to", length = 19)
    public Date getValidTo() {
        return this.validTo;
    }
    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
}

