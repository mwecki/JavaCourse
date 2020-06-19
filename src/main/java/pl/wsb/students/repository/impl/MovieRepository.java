package pl.wsb.students.repository.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernate.*;
import pl.wsb.students.model.MovieRequest;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import java.util.Date;

public class MovieRepository extends AbstractRepository<Movie, Integer> {
    @Override
    protected Class<Movie> getPersistentClass() {
        return Movie.class;
    }

    public Movie insertMovie(MovieRequest movieRequest) throws ValidationException {
        if (movieRequest == null) {
            throw new ValidationException("movieRequest");
        } //if
        movieRequest.validateData();

        MovieCategory movieCategory = CategoryRepository.findByAbbr(movieRequest.getCategory().toString());
        if (movieCategory == null) {
            throw new ValidationException("movieCategory");
        } //if
        MovieGenre movieGenre = GenreRepository.findByAbbr(movieRequest.getGenre().toString());
        if (movieGenre == null) {
            throw new ValidationException("movieGenre");
        } //if
        String[] directorName = movieRequest.getDirector().split(" ");

        Director director = DirectorRepository.findByNames(directorName[0], directorName[1]);
        if (director == null) {
            throw new ValidationException("director" + directorName[0] + directorName[1]);
        } //if

        MovieRequestStatus movieRequestStatus = MovieRequestStatusRepository.findByAbbr("Pending");
        if (movieRequestStatus == null) {
            throw new ValidationException("movieRequestStatus");
        } //if

        Movie movie = new Movie();
        movie.setCreated(new Date());
        movie.setModified(new Date());
        movie.setDirector(director);
        movie.setTitle(movieRequest.getTitle());
        movie.setReleaseYear(movieRequest.getYear());
        movie.setMovieGenre(movieGenre);
        movie.setMovieCategory(movieCategory);
        movie.setDeleted(0);
        movie.setMovieRequestStatus(movieRequestStatus);

        EntityManagerHelper.startTransaction();
        movie = merge(movie);
        EntityManagerHelper.commitTransaction();

        return movie;
    }
}

