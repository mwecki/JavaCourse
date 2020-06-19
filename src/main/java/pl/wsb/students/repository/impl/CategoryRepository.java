package pl.wsb.students.repository.impl;

import org.apache.commons.lang3.StringUtils;
import pl.wsb.students.hibernate.MovieCategory;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CategoryRepository extends AbstractRepository<MovieCategory, Integer> {
    @Override
    protected Class<MovieCategory> getPersistentClass() {
        return MovieCategory.class;
    }

    public static MovieCategory findByAbbr(String abbr) {
        if (StringUtils.isBlank(abbr)) {
            return null;
        } //if
        CriteriaBuilder criteriaBuilder =
                EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<MovieCategory> criteriaQuery = criteriaBuilder.createQuery(MovieCategory.class);
        Root<MovieCategory> categories = criteriaQuery.from(MovieCategory.class);
        criteriaQuery.where(
            criteriaBuilder.equal(
                criteriaBuilder.lower(
                    categories.<String>get("abbr")
                ),
                abbr.toLowerCase()
            )
        );
        return firstResultOrNull(EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList());
    }

}
