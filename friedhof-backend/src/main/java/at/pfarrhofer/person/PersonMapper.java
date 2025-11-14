package at.pfarrhofer.person;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonMapper {

    public PersonDTO toResource(Person person) {
        return new PersonDTO(person.id, person.getFirstname(), person.getLastname(), person.getHousename(), person.getBirthyear(),
                person.getDateOfDeath(), person.getAge(), person.getGrave().getGraveName());
    }
}
