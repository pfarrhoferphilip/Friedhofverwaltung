package at.pfarrhofer.person;

import at.pfarrhofer.grave.Grave;
import jakarta.persistence.*;

import java.time.LocalDate;

@NamedQuery(
        name = Person.FIND_BY_NAME,
        query = """
        SELECT p 
        FROM Person p
        WHERE LOWER(p.firstname) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(p.lastname) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(p.housename) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(CONCAT(p.firstname, ' ', p.lastname)) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(CONCAT(p.lastname, ' ', p.firstname)) LIKE LOWER(CONCAT('%', :search, '%'))
        """
)



@Entity
public class Person {
public static final String FIND_BY_NAME = "Person.findByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstname;
    String lastname;
    @Column(nullable = true)
    String housename;
    Integer birthyear;
    @Column(name = "date_of_death")
    LocalDate dateOfDeath;
    Integer age;
    @ManyToOne
    Grave grave;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        if (firstname != null) {
            return firstname;
        }

        return "";
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        if (lastname != null) {
            return lastname;
        }

        return "";
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHousename() {
        if (housename != null) {
            return housename;
        }

        return "";
    }

    public void setHousename(String housename) {
        this.housename = housename;
    }

    public Integer getBirthyear() {
        if (birthyear != null) {
            return birthyear;
        }

        return 0;
    }

    public void setBirthyear(Integer birthyear) {
        this.birthyear = birthyear;
    }

    public LocalDate getDateOfDeath() {
        if (dateOfDeath != null) {
            return dateOfDeath;
        }

        return LocalDate.now();
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Integer getAge() {
        if (age != null) {
            return age;
        }

        return -1;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Grave getGrave() {
        return grave;
    }

    public void setGrave(Grave grave) {
        this.grave = grave;
    }
}
