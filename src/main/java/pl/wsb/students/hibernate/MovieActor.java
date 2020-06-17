package pl.wsb.students.hibernate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "movie_actor", catalog = "java_course_wsb", uniqueConstraints =
@UniqueConstraint(columnNames = {
        "movie_id", "actor_id" }))
public class MovieActor implements java.io.Serializable {
    private Integer id;
    private Actor actor;
    private Movie movie;
    private Date created;
    private Date modified;
    public MovieActor() {
    }
    public MovieActor(Actor actor, Movie movie, Date modified) {
        this.actor = actor;
        this.movie = movie;
        this.modified = modified;
    }
    public MovieActor(Actor actor, Movie movie, Date created, Date modified) {
        this.actor = actor;
        this.movie = movie;
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
    @JoinColumn(name = "actor_id", nullable = false)
    public Actor getActor() {
        return this.actor;
    }
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    public Movie getMovie() {
        return this.movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
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

