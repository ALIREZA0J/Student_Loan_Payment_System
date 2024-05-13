package org.loanpayment.model;


import com.github.mfathi91.time.PersianDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SoftDelete;
import org.loanpayment.base.entity.BaseEntity;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SoftDelete
public class Semester extends BaseEntity<Long> {

    @Column(name = "semester_num",unique = true)
    private Integer semesterNumber;

    @Column(name = "smester_start_date")
    private LocalDate startDate;

    @Column(name = "semester_end_date")
    private LocalDate endDate;


    @ToString.Exclude
    @OneToMany(mappedBy = "semester")
    private List<Student> students;

    @ToString.Exclude
    @OneToMany(mappedBy = "semester")
    private List<Loan> loanList;

    public Semester(Integer semesterNumber, LocalDate startDate, LocalDate endDate) {
        this.semesterNumber = semesterNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return  super.getId() + "- "+
                "semesterNumber=" + semesterNumber +
                ", startDate=" + PersianDate.fromGregorian(startDate) +
                ", endDate=" + PersianDate.fromGregorian(endDate);
    }
}
