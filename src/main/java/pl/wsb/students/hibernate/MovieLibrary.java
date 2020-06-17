package pl.wsb.students.hibernate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "movie_library", catalog = "java_course_wsb", uniqueConstraints =
@UniqueConstraint(columnNames = {
        "movie_id", "user_account_id" }))
public class MovieLibrary implements java.io.Serializable {
    private Integer id;
    private Movie movie;
    private MovieLibraryStatus movieLibraryStatus;
    private UserAccount userAccount;
    private Date created;
    private Date modified;
    public MovieLibrary() {
    }
    public MovieLibrary(Movie movie, UserAccount userAccount, Date modified) {
        this.movie = movie;
        this.userAccount = userAccount;
        this.modified = modified;
    }
    public MovieLibrary(Movie movie, MovieLibraryStatus movieLibraryStatus, UserAccount
            userAccount, Date created,
                        Date modified) {
        this.movie = movie;
        this.movieLibraryStatus = movieLibraryStatus;
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
    @JoinColumn(name = "movie_id", nullable = false)
    public Movie getMovie() {
        return this.movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_library_status_id")
    public MovieLibraryStatus getMovieLibraryStatus() {
        return this.movieLibraryStatus;
    }
    public void setMovieLibraryStatus(MovieLibraryStatus movieLibraryStatus) {
        this.movieLibraryStatus = movieLibraryStatus;
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
