package org.loanpayment.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.loanpayment.base.entity.BaseEntity;
import org.loanpayment.model.enums.Gender;


@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder(setterPrefix = "set")

public class Person extends BaseEntity<Long> {

    @Pattern(regexp = "^[A-Za-z\\s]+$",message = "First name most consist of letter only.")
    @Size(min = 3,max = 60,message = "At least three characters must be written for the name")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    @Pattern(regexp = "^[A-Za-z\\s]+$",message = "Last name most consist of letter only.")
    @Size(min = 3,max = 60,message = "At least three characters must be written for the name")
    private String lastName;


    @Column(name = "father_name")
    @Pattern(regexp = "^[A-Za-z\\s]+$",message = "Last name most consist of letter only.")
    @Size(min = 3,max = 60,message = "At least three characters must be written for the name")
    private String fatherName;


    @Column(name = "mother_name")
    @Pattern(regexp = "^[A-Za-z\\s]+$",message = "Last name most consist of letter only.")
    @Size(min = 3,max = 60,message = "At least three characters must be written for the name")
    private String motherName;

    @Column(name = "national_code",nullable = false,unique = true)
    private String nationalCode;



    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;



}
