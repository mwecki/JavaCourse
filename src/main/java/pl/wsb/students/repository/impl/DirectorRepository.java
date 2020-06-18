package pl.wsb.students.repository.impl;

import pl.wsb.students.hibernate.Director;
import pl.wsb.students.repository.AbstractRepository;

public class DirectorRepository extends AbstractRepository<Director, Integer> {
    @Override
    protected Class<Director> getPersistentClass() {
        return Director.class;
    }
}
