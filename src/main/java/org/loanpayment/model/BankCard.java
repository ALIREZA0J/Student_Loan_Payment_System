package org.loanpayment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;
import org.loanpayment.base.entity.BaseEntity;
import org.loanpayment.model.enums.BankType;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@SoftDelete
@Entity
@SuperBuilder(setterPrefix = "set")
public class BankCard extends BaseEntity<Long> {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "bank_name")
    private BankType bankType;

    @Column(name = "card_number",nullable = false,unique = true)
    private Long cardNumber;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;


    @Column(name = "cvv2")
    private Integer cvv2;

    @Column(name = "inventory")
    private Long inventory;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
