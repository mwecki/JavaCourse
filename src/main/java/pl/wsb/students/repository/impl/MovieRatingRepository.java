package pl.wsb.students.repository.impl;

import pl.wsb.students.hibernate.MovieRating;
import pl.wsb.students.repository.AbstractRepository;

public class MovieRatingRepository extends AbstractRepository<MovieRating, Integer> {
    @Override
    protected Class<MovieRating> getPersistentClass() {
        return MovieRating.class;
    }
}
