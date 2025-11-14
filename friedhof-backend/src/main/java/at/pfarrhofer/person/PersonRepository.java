package at.pfarrhofer.person;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public List<Person> findByName(String name) {
        return getEntityManager()
                .createNamedQuery(Person.FIND_BY_NAME, Person.class)
                .setParameter("search", name)
                .getResultList();
    }

    public List<Person> findAllSorted() {
        return getEntityManager()
                .createNamedQuery(Person.FIND_ALL, Person.class)
                .getResultList();
    }
}
