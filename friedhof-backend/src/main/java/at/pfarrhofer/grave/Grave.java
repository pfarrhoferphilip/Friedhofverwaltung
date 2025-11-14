package at.pfarrhofer.grave;

import at.pfarrhofer.person.Person;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Grave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer sector;
    Integer row;
    Integer col;
    @Column(nullable = true)
    Integer col2;
    @Column(name = "special_name")
    String specialName;
    @OneToMany(mappedBy = "grave")
    List<Person> persons;

    public String getGraveName() {

        if (specialName != null && !specialName.isEmpty()) {
            return specialName;
        }

        String name = sector + "-" + row;

        if (col != null) {
            name += "-" + col;

            if (col2 != null) {
                name += "+" + col2;
            }
        }

        return name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSector() {
        return sector;
    }

    public void setSector(Integer sector) {
        this.sector = sector;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getCol2() {
        return col2;
    }

    public void setCol2(Integer col2) {
        this.col2 = col2;
    }
}
