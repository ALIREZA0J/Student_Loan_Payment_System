package org.loanpayment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;
import org.loanpayment.base.entity.BaseEntity;
import org.loanpayment.model.enums.InstallmentStatus;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
@SoftDelete
public class Installment extends BaseEntity<Long> {

    private Integer installmentNumber;

    private Double amount;

    private LocalDate dueDate;

    private LocalDate paymentDate;

    @Enumerated(value = EnumType.STRING)
    private InstallmentStatus installmentStatus;

    @JoinColumn(name = "loan_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Loan loan;


    public Installment(Integer installmentNumber, Double amount, LocalDate dueDate, LocalDate paymentDate, InstallmentStatus installmentStatus, Loan loan) {
        this.installmentNumber = installmentNumber;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.installmentStatus = installmentStatus;
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "Installment{" +
                " id: " + super.getId() +
                "| installment number:" + installmentNumber +
                "| amount:" + amount +
                "| dueDate:" + dueDate +
                "| loan type: " + loan.getLoanType() +
                "| semester loan: "+ loan.getSemester().getSemesterNumber() +
                "} " ;
    }
}
