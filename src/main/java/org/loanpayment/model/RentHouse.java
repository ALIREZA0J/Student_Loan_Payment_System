package org.loanpayment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;
import org.loanpayment.base.entity.BaseEntity;
import org.loanpayment.model.enums.City;


@Getter
@Setter
@NoArgsConstructor
@Entity
@SoftDelete
@SuperBuilder(setterPrefix = "set")
public class RentHouse extends BaseEntity<Long> {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "province")
    private City province;

    @Column(name = "postal_code",nullable = false,unique = true)
    private Long postalCode;

    @Column(name = "rental_num",unique = true)
    private Long rentalNumber;

    @OneToOne
    private Student student;
}
