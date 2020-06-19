package pl.wsb.students.repository.impl;

import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernate.Movie;
import pl.wsb.students.hibernate.MovieFavorite;
import pl.wsb.students.hibernate.UserAccount;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;

public class MovieFavoriteRepository extends AbstractRepository<MovieFavorite, Integer> {
    @Override
    protected Class<MovieFavorite> getPersistentClass() {
        return MovieFavorite.class;
    }

    public Movie findById(Integer movieId) {
        if (movieId == null) {
            return null;
        } //if
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> movie = criteriaQuery.from(Movie.class);
        criteriaQuery.where(
                criteriaBuilder.equal(
                        criteriaBuilder.lower(movie.get("id")),
                        movieId
                )
        );
        return firstResultOrNull(
                EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList()
        );
    }

    public boolean markAsFavorite(Integer movieId, UserAccount userAccount) throws ValidationException {
        if (movieId == null) {
            throw new ValidationException("unsifficient data..." + movieId);
        } //if
        if (userAccount == null) {
            throw new ValidationException("unsifficient data...");
        } //if
        Movie movie = findById(movieId);

        if (movie == null) {
            throw new ValidationException("Movie not found...");
        } //if

        MovieFavorite movieFavorite = new MovieFavorite();
        movieFavorite.setCreated(new Date());
        movieFavorite.setModified(new Date());
        movieFavorite.setUserAccount(userAccount);
        movieFavorite.setMovie(movie);

        EntityManagerHelper.startTransaction();
        merge(movieFavorite);
        EntityManagerHelper.commitTransaction();

        return true;
    }
}
