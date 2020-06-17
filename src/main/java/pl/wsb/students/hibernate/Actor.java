package pl.wsb.students.hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "actor", catalog = "java_course_wsb")
public class Actor implements java.io.Serializable {
    private Integer id;
    private Date created;
    private Date modified;
    private String firstName;
    private String lastName;
    private Integer deleted;
    private Set<MovieActor> movieActors = new HashSet<>(0);
    public Actor() {
    }
    public Actor(Date modified) {
        this.modified = modified;
    }
    public Actor(Date created, Date modified, String firstName, String lastName, Integer
            deleted, Set<MovieActor> movieActors) {
        this.created = created;
        this.modified = modified;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deleted = deleted;
        this.movieActors = movieActors;
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
    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "deleted")
    public Integer getDeleted() {
        return this.deleted;
    }
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actor")
    public Set<MovieActor> getMovieActors() {
        return this.movieActors;
    }
    public void setMovieActors(Set<MovieActor> movieActors) {
        this.movieActors = movieActors;
    }
}
