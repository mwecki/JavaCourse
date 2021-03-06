package pl.wsb.students.repository.impl;

import org.apache.commons.lang3.StringUtils;
import pl.wsb.students.hibernate.MovieRequestStatus;
import pl.wsb.students.hibernate.Role;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MovieRequestStatusRepository extends AbstractRepository<MovieRequestStatus, Integer> {
    @Override
    protected Class<MovieRequestStatus> getPersistentClass() {
        return MovieRequestStatus.class;
    }

    public static MovieRequestStatus findByAbbr(String abbr) {
        if (StringUtils.isBlank(abbr)) {
            return null;
        } //if
        CriteriaBuilder criteriaBuilder =
                EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<MovieRequestStatus> criteriaQuery = criteriaBuilder.createQuery(MovieRequestStatus.class);
        Root<MovieRequestStatus> movieRequestStatus = criteriaQuery.from(MovieRequestStatus.class);
        criteriaQuery.where(
            criteriaBuilder.equal(
                criteriaBuilder.lower(
                    movieRequestStatus.<String>get("abbr")
                ),
                abbr.toLowerCase())
        );
        return firstResultOrNull(EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList());
    }
}
