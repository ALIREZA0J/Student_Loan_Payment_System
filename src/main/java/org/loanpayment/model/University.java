package org.loanpayment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SoftDelete;
import org.loanpayment.base.entity.BaseEntity;
import org.loanpayment.model.enums.City;
import org.loanpayment.model.enums.UniversityType;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@SoftDelete

public class University extends BaseEntity<Long> {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "uni_type")
    private UniversityType universityType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "uni_city")
    private City city;

    @Column(name = "uni_name")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "university")
    private List<Student> studentList;

    public University(UniversityType universityType, City city, String name) {
        this.universityType = universityType;
        this.city = city;
        this.name = name;
    }

    @Override
    public String toString() {
        return "University{" + super.getId() + "- " +
                ", name='" + name +
                ", universityType=" + universityType +
                ", city=" + city +
                "} " ;
    }
}
