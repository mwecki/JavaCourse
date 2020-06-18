package pl.wsb.students.repository.impl;

import pl.wsb.students.hibernate.Movie;
import pl.wsb.students.repository.AbstractRepository;

public class MovieRepository extends AbstractRepository<Movie, Integer> {
    @Override
    protected Class<Movie> getPersistentClass() {
        return Movie.class;
    }
}

