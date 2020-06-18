package pl.wsb.students.repository.impl;

import pl.wsb.students.hibernate.MovieLibrary;
import pl.wsb.students.repository.AbstractRepository;

public class MovieLibraryRepository extends AbstractRepository<MovieLibrary, Integer> {
    @Override
    protected Class<MovieLibrary> getPersistentClass() {
        return MovieLibrary.class;
    }
}
