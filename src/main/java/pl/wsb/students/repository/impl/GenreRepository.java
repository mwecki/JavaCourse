package pl.wsb.students.repository.impl;

import org.apache.commons.lang3.StringUtils;
import pl.wsb.students.hibernate.MovieGenre;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenreRepository extends AbstractRepository<MovieGenre, Integer> {
    @Override
    protected Class<MovieGenre> getPersistentClass() {
        return MovieGenre.class;
    }

    public static MovieGenre findByAbbr(String abbr) {
        if (StringUtils.isBlank(abbr)) {
            return null;
        } //if
        CriteriaBuilder criteriaBuilder =
                EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<MovieGenre> criteriaQuery = criteriaBuilder.createQuery(MovieGenre.class);
        Root<MovieGenre> genres = criteriaQuery.from(MovieGenre.class);
        criteriaQuery.where(
            criteriaBuilder.equal(
                criteriaBuilder.lower(
                    genres.<String>get("abbr")
                ),
                abbr.toLowerCase()
            )
        );
        return firstResultOrNull(EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList());
    }

}
