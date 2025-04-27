package gp2.StudentLifeCycle.StudentLifecylce.models;

import jakarta.persistence.*;

@Table(name = "Levels")
@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String faculty;

    private String lang;


    public String getName() {
        return name;
    }

    public Level setName(String name) {
        this.name = name;
        return this;
    }

    private int year;

    public Long getId() {
        return id;
    }

    public Level setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFaculty() {
        return faculty;
    }

    public Level setFaculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Level setYear(int year) {
        this.year = year;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
