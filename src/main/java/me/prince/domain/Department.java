package me.prince.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 6, max = 60)
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Size(min = 6, max = 60)
    @Column(name = "remarks", length = 60)
    private String remarks;

    @Size(min = 6, max = 60)
    @Column(name = "remarks1", length = 60)
    private String remarks1;

    @ManyToMany(mappedBy = "departments")
    @JsonIgnore
    private Set<Person> people = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Department name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public Department remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public Department people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public Department addPerson(Person person) {
        people.add(person);
        person.getDepartments().add(this);
        return this;
    }

    public Department removePerson(Person person) {
        people.remove(person);
        person.getDepartments().remove(this);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Department department = (Department) o;
        if(department.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, department.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", remarks='" + remarks + "'" +
            '}';
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }
}
