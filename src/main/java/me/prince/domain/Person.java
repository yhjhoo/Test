package me.prince.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 6, max = 60)
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Min(value = 18)
    @Max(value = 100)
    @Column(name = "age")
    private Integer age;

    @ManyToMany
    @JoinTable(name = "person_department",
               joinColumns = @JoinColumn(name="people_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="departments_id", referencedColumnName="ID"))
    private Set<Department> departments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public Person age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public Person departments(Set<Department> departments) {
        this.departments = departments;
        return this;
    }

    public Person addDepartment(Department department) {
        departments.add(department);
        department.getPeople().add(this);
        return this;
    }

    public Person removeDepartment(Department department) {
        departments.remove(department);
        department.getPeople().remove(this);
        return this;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        if(person.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", age='" + age + "'" +
            '}';
    }
}
