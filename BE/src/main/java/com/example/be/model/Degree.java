package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id")
    private int degreeId;
    @Column(columnDefinition = "varchar(50)", unique = true, nullable = false)
    private String degreeName;
    @OneToMany(mappedBy = "degree")
    @JsonIgnore
    private Set<Teacher> teacherSet;

    public Degree() {
    }
    public Degree(int degreeId, String degreeName) {
        this.degreeId = degreeId;
        this.degreeName = degreeName;
    }
    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(int degreeId) {
        this.degreeId = degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }
}
