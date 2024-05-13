package org.loanpayment.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

@Getter
@Setter
@NoArgsConstructor
@SoftDelete
@Entity
@SuperBuilder(setterPrefix = "set")
public class Spouse extends Person{


    @OneToOne
    private Student student;
}
