package pl.wsb.students.hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "movie_category", catalog = "java_course_wsb", uniqueConstraints =
@UniqueConstraint(columnNames = "abbr"))
public class MovieCategory implements java.io.Serializable {
    private Integer id;
    private Date created;
    private Date modified;
    private String name;
    private String abbr;
    private Integer deleted;
    private Set<Movie> movies = new HashSet<>(0);
    public MovieCategory() {
    }
    public MovieCategory(Date modified, String abbr) {
        this.modified = modified;
        this.abbr = abbr;
    }
    public MovieCategory(Date created, Date modified, String name, String abbr, Integer
            deleted, Set<Movie> movies) {
        this.created = created;
        this.modified = modified;
        this.name = name;
        this.abbr = abbr;
        this.deleted = deleted;
        this.movies = movies;
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
    @Column(name = "name")
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "abbr", unique = true, nullable = false, length = 20)
    public String getAbbr() {
        return this.abbr;
    }
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    @Column(name = "deleted")
    public Integer getDeleted() {
        return this.deleted;
    }
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movieCategory")
    public Set<Movie> getMovies() {
        return this.movies;
    }
    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}

