package org.loanpayment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;
import org.loanpayment.base.entity.BaseEntity;
import org.loanpayment.model.enums.LoanType;
import org.loanpayment.model.enums.Section;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
@SoftDelete
@SuperBuilder(setterPrefix = "set")
public class Loan extends BaseEntity<Long> {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "loan_type")
    private LoanType loanType;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    @Column(name = "amout_of_loan")
    private Long amount;

    @Column(name = "section_of_loan")
    @Enumerated(EnumType.STRING)
    private Section loanSection;

    @JoinColumn(name = "semester_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Semester semester;

    @JoinColumn(name = "student_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;


    public Loan(LoanType loanType, LocalDate loanDate, Long amount, Section loanSection, Semester semester, Student student) {
        this.loanType = loanType;
        this.loanDate = loanDate;
        this.amount = amount;
        this.loanSection = loanSection;
        this.semester = semester;
        this.student = student;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanType=" + loanType +
                ", loanDate=" + loanDate +
                ", amount=" + amount +
                ", loanSection=" + loanSection +
                ", semester=" + semester +
                ", student=" + student +
                "} " + super.toString();
    }
}
