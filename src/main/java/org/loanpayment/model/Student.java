package org.loanpayment.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.loanpayment.model.enums.MaritalStatus;
import org.loanpayment.model.enums.Section;

import java.util.List;

import static org.passay.AllowedRegexRule.ERROR_CODE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SoftDelete
@SuperBuilder(setterPrefix = "set")
public class Student extends Person{

    @Min(value = 10000000000000L,message = "Student code could not be under 10_000_000_000_000.")
    @Column(name = "student_code",nullable = false,unique = true)
    private Long studentCode;

    @Column(name = "section")
    private Section section;

    @Column(name = "username",nullable = false)
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])"+"(?=.*[0-9])(?=.*[@#!%&*])[A-Za-z0-9@#!%&*]{8,}$"
            ,message = "Password should contain At least one (small word, capital letter, number , @ # ! % & *) and " +
            "it must be at least eight characters.")
    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "dormitory_user")
    private Boolean dormitoryUser;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;


    @JoinColumn(name = "semester_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Semester semester;

    @ToString.Exclude
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Loan> loanList;


    @ManyToOne
    @JoinColumn(name = "university_id",nullable = false)
    private University university;

    @ToString.Exclude
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<BankCard> bankCardList;




}
