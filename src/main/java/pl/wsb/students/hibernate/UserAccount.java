package pl.wsb.students.hibernate;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import pl.wsb.students.exceptions.ValidationException;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_account", catalog = "java_course_wsb", uniqueConstraints =
@UniqueConstraint(columnNames = "email"))
public class UserAccount implements java.io.Serializable {
    private Integer id;
    private Date created;
    private Date modified;
    private String email;
    private String passHash;
    private String passSalt;
    private Integer deleted;
    private Set<MovieLibrary> movieLibraries = new HashSet<>(0);
    private Set<UserAccountRole> userAccountRoles = new HashSet<>(0);
    private Set<MovieRating> movieRatings = new HashSet<>(0);
    private Set<MovieFavorite> movieFavorites = new HashSet<>(0);
    private Set<ApiToken> apiTokens = new HashSet<>(0);
    public UserAccount() {
    }
    public UserAccount(Date modified, String email, String passHash, String passSalt) {
        this.modified = modified;
        this.email = email;
        this.passHash = passHash;
        this.passSalt = passSalt;
    }
    public UserAccount(Date created, Date modified, String email, String passHash,
                       String passSalt, Integer deleted,
                       Set<MovieLibrary> movieLibraries, Set<UserAccountRole>
                               userAccountRoles, Set<MovieRating> movieRatings,
                       Set<MovieFavorite> movieFavorites,
                       Set<ApiToken> apiTokens) {
        this.created = created;
        this.modified = modified;
        this.email = email;
        this.passHash = passHash;
        this.passSalt = passSalt;
        this.deleted = deleted;
        this.movieLibraries = movieLibraries;
        this.userAccountRoles = userAccountRoles;
        this.movieRatings = movieRatings;
        this.movieFavorites = movieFavorites;
        this.apiTokens = apiTokens;
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
    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "pass_hash", nullable = false)
    public String getPassHash() {
        return this.passHash;
    }
    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }
    @Column(name = "pass_salt", nullable = false)
    public String getPassSalt() {
        return this.passSalt;
    }
    public void setPassSalt(String passSalt) {
        this.passSalt = passSalt;
    }
    @Column(name = "deleted")
    public Integer getDeleted() {
        return this.deleted;
    }
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
    public Set<MovieLibrary> getMovieLibraries() {
        return this.movieLibraries;
    }
    public void setMovieLibraries(Set<MovieLibrary> movieLibraries) {
        this.movieLibraries = movieLibraries;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
    public Set<UserAccountRole> getUserAccountRoles() {
        return this.userAccountRoles;
    }
    public void setUserAccountRoles(Set<UserAccountRole> userAccountRoles) {
        this.userAccountRoles = userAccountRoles;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
    public Set<MovieRating> getMovieRatings() {
        return this.movieRatings;
    }
    public void setMovieRatings(Set<MovieRating> movieRatings) {
        this.movieRatings = movieRatings;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
    public Set<MovieFavorite> getMovieFavorites() {
        return this.movieFavorites;
    }
    public void setMovieFavorites(Set<MovieFavorite> movieFavorites) {
        this.movieFavorites = movieFavorites;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
    public Set<ApiToken> getApiTokens() {
        return this.apiTokens;
    }
    public void setApiTokens(Set<ApiToken> apiTokens) {
        this.apiTokens = apiTokens;
    }

    public boolean validatePass(String password) throws ValidationException {
        if (StringUtils.isBlank(password)) {
            return false;
        } //if
        return generatePassHash(password, this.passSalt).equalsIgnoreCase(this.passHash);
    }
    public String generatePassHash(String password, String salt) throws ValidationException {
        if (StringUtils.isBlank(password)) {
            throw new ValidationException("Password is empty...");
        } //if
        if (StringUtils.isBlank(salt)) {
            throw new ValidationException("Salt is empty...");
        } //if
        return DigestUtils.sha256Hex(
            String.format(
                "%s%s",
                password,
                salt
            )
        );
    }
}

