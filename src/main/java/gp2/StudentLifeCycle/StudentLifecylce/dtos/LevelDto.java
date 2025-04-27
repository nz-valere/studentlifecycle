package gp2.StudentLifeCycle.StudentLifecylce.dtos;

public class LevelDto {

    private String name;

    public String getName() {
        return name;
    }

    public LevelDto setName(String name) {
        this.name = name;
        return this;
    }

    private String faculty;
    private int year;

    private String lang;

    public String getFaculty() {
        return faculty;
    }

    public LevelDto setFaculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    public int getYear() {
        return year;
    }

    public LevelDto setYear(int year) {
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
