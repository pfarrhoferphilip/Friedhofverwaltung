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
    Double posX;
    Double posY;
    @Column(nullable = true)
    Integer col2;
    @Column(name = "special_name")
    String specialName;
    @OneToMany(mappedBy = "grave", cascade = CascadeType.REMOVE)
    List<Person> persons;

    public Grave() {}

    public Grave(Integer sector, Integer row, Integer col, Integer col2, String specialName, double posX, double posY) {
        this.sector = sector;
        this.row = row;
        this.col = col;
        this.col2 = col2;
        this.specialName = specialName;
        this.posX = posX;
        this.posY = posY;
    }

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

    public Double getPosX() {
        if (posX != null) {
            return posX;
        }
        return 0.0;
    }

    public void setPosX(Double posX) {
        this.posX = posX;
    }

    public Double getPosY() {
        if (posY != null) {
            return posY;
        }
        return 0.0;
    }

    public void setPosY(Double posY) {
        this.posY = posY;
    }

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
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
        if (sector == null) {
            return 0;
        }
        return sector;
    }

    public void setSector(Integer sector) {
        this.sector = sector;
    }

    public Integer getRow() {
        if (row == null) {
            return 0;
        }
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        if (col == null) {
            return 0;
        }
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getCol2() {
        if (col2 == null) {
            return 0;
        }
        return col2;
    }

    public void setCol2(Integer col2) {
        this.col2 = col2;
    }
}
