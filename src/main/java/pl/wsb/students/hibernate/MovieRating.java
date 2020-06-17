package pl.wsb.students.hibernate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "movie_rating", catalog = "java_course_wsb", uniqueConstraints =
@UniqueConstraint(columnNames = {
        "movie_id", "user_account_id" }))
public class MovieRating implements java.io.Serializable {
    private Integer id;
    private Movie movie;
    private UserAccount userAccount;
    private Date created;
    private Date modified;
    private byte rate;
    public MovieRating() {
    }
    public MovieRating(Movie movie, UserAccount userAccount, Date modified, byte rate) {
        this.movie = movie;
        this.userAccount = userAccount;
        this.modified = modified;
        this.rate = rate;
    }
    public MovieRating(Movie movie, UserAccount userAccount, Date created, Date
            modified, byte rate) {
        this.movie = movie;
        this.userAccount = userAccount;
        this.created = created;
        this.modified = modified;
        this.rate = rate;
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
    @JoinColumn(name = "movie_id", nullable = false)
    public Movie getMovie() {
        return this.movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
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
    @Column(name = "rate", nullable = false)
    public byte getRate() {
        return this.rate;
    }
    public void setRate(byte rate) {
        this.rate = rate;
    }
}

