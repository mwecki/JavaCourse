package pl.wsb.students.repository.impl;

import org.apache.commons.lang3.StringUtils;
import pl.wsb.students.hibernate.Director;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DirectorRepository extends AbstractRepository<Director, Integer> {
    @Override
    protected Class<Director> getPersistentClass() {
        return Director.class;
    }

    public static Director findByNames(String firstName, String lastName) {
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
            return null;
        } //if
        CriteriaBuilder criteriaBuilder =
                EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Director> criteriaQuery = criteriaBuilder.createQuery(Director.class);
        Root<Director> directors = criteriaQuery.from(Director.class);
        criteriaQuery.where(
            criteriaBuilder.equal(
                criteriaBuilder.lower(
                    directors.<String>get("firstName")
                ),
                firstName.toLowerCase()
            )
        ).where(
            criteriaBuilder.equal(
                criteriaBuilder.lower(
                    directors.<String>get("lastName")
                ),
                lastName.toLowerCase()
            )
        );
        return firstResultOrNull(EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList());
    }
}
