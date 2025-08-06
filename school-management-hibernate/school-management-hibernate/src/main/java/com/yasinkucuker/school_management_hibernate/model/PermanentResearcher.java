package com.yasinkucuker.school_management_hibernate.model;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class PermanentResearcher extends Instructor{
    public float fixedSalary;

    public PermanentResearcher(float fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    public PermanentResearcher(String name, String address, String phoneNumber, float fixedSalary) {
        super(name, address, phoneNumber);
        this.fixedSalary = fixedSalary;
    }

    public float getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(float fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PermanentResearcher that = (PermanentResearcher) o;
        return Float.compare(fixedSalary, that.fixedSalary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fixedSalary);
    }

    @Override
    public String toString() {
        return "PermanentResearcher{" +
                "fixedSalary=" + fixedSalary +
                '}';
    }
}
