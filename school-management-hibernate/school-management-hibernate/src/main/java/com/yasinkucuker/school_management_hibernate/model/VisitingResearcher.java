package com.yasinkucuker.school_management_hibernate.model;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class VisitingResearcher extends Instructor{
    public float hourlySalary;

    public VisitingResearcher(float hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public VisitingResearcher(String name, String address, String phoneNumber, float hourlySalary) {
        super(name, address, phoneNumber);
        this.hourlySalary = hourlySalary;
    }
    public float getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(float hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VisitingResearcher that = (VisitingResearcher) o;
        return Float.compare(hourlySalary, that.hourlySalary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hourlySalary);
    }

    @Override
    public String toString() {
        return "VisitingResearcher{" +
                "hourlySalary=" + hourlySalary +
                '}';
    }
}
