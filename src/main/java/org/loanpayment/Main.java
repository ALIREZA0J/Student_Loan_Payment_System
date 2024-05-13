package org.loanpayment;

import com.github.mfathi91.time.PersianDate;
import org.hibernate.SessionFactory;

import org.loanpayment.Menu.MainMenu;
import org.loanpayment.connection.AppContext;
import org.loanpayment.connection.SessionFactorySingleton;
import org.loanpayment.model.*;
import org.loanpayment.model.enums.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {


        SessionFactory instance = SessionFactorySingleton.getInstance();

        //todo: add semesters and universities for default
//        essentialInsert();


        MainMenu mainMenu = new MainMenu();
        mainMenu.menu();

    }


    public static void essentialInsert(){
        University university1 = new University(UniversityType.DOLATI_ROZANE, City.TEHRAN,"SHARIF");
        University university10 = new University(UniversityType.DOLATI_ROZANE, City.HAMEDAN,"HAMEDAN");
        University university11 = new University(UniversityType.DOLATI_ROZANE, City.EAST_AZARBAIJAN,"TABRIZ");

        University university2 = new University(UniversityType.PARDIS, City.TEHRAN,"SHARIF");
        University university20 = new University(UniversityType.PARDIS, City.HAMEDAN,"HAMEDAN");
        University university21 = new University(UniversityType.PARDIS, City.EAST_AZARBAIJAN,"TABRIZ");

        University university3 = new University(UniversityType.DOLATI_SHABANEH, City.TEHRAN,"SHARIF");
        University university30 = new University(UniversityType.DOLATI_SHABANEH, City.HAMEDAN,"HAMEDAN");
        University university31 = new University(UniversityType.DOLATI_SHABANEH, City.EAST_AZARBAIJAN,"TABRIZ");

        University university4 = new University(UniversityType.ELMI_KARBORDI, City.TEHRAN,"ELMI_KARBORDI_QODS");
        University university40 = new University(UniversityType.ELMI_KARBORDI, City.HAMEDAN,"ELMI_KARBORDI_HAMEDAN");
        University university41 = new University(UniversityType.ELMI_KARBORDI, City.EAST_AZARBAIJAN,"ELMI_KARBORDI_TABRIZ");


        University university5 = new University(UniversityType.AZAD, City.TEHRAN,"AZAD_TEHRAN");
        University university50 = new University(UniversityType.AZAD, City.HAMEDAN,"AZAD_HAMEDAN");
        University university51 = new University(UniversityType.AZAD, City.EAST_AZARBAIJAN,"AZAD_TABRIZ");

        University university6 = new University(UniversityType.GHEIRE_ENTEFAIE, City.TEHRAN,"GHEIRE_ENTEFAIE_THERAN");
        University university60 = new University(UniversityType.GHEIRE_ENTEFAIE, City.HAMEDAN,"GHEIRE_ENTEFAIE_HAMEDAN");
        University university61 = new University(UniversityType.GHEIRE_ENTEFAIE, City.EAST_AZARBAIJAN,"GHEIRE_ENTEFAIE_TABRIZ");

        University university7 = new University(UniversityType.PAYAME_NOOR, City.TEHRAN,"PAYAME_NOOR_TEHRAN");
        University university70 = new University(UniversityType.PAYAME_NOOR, City.HAMEDAN,"PAYAME_NOOR_HAMEDAN");
        University university71 = new University(UniversityType.PAYAME_NOOR, City.EAST_AZARBAIJAN,"PAYAME_NOOR_TABRIZ");


        List<University> universities = new ArrayList<>();
        universities.add(university1);
        universities.add(university10);
        universities.add(university11);
        universities.add(university2);
        universities.add(university20);
        universities.add(university21);
        universities.add(university3);
        universities.add(university30);
        universities.add(university31);
        universities.add(university4);
        universities.add(university40);
        universities.add(university41);
        universities.add(university5);
        universities.add(university50);
        universities.add(university51);
        universities.add(university6);
        universities.add(university60);
        universities.add(university61);
        universities.add(university7);
        universities.add(university70);
        universities.add(university71);


        for (int i = 0; i < universities.size(); i++) {
            AppContext.getUniversityService().saveOrUpdate(universities.get(i));
        }
        ////////////////////////////////////////////////////////////////////////

        LocalDate startDate1 = PersianDate.of(1401, 6, 20).toGregorian();
        LocalDate endDate1 = PersianDate.of(1401, 11, 15).toGregorian();
        Semester semester1 = new Semester(4011,startDate1,endDate1);
//
        LocalDate startDate2 = PersianDate.of(1401, 11, 20).toGregorian();
        LocalDate endDate2 = PersianDate.of(1402, 4, 15).toGregorian();
        Semester semester2 = new Semester(4012,startDate2,endDate2);

        LocalDate startDate3 = PersianDate.of(1402, 6, 20).toGregorian();
        LocalDate endDate3 = PersianDate.of(1402, 11, 15).toGregorian();
        Semester semester3 = new Semester(4021,startDate3,endDate3);

        LocalDate startDate4 = PersianDate.of(1402, 11, 20).toGregorian();
        LocalDate endDate4 = PersianDate.of(1403, 4, 15).toGregorian();
        Semester semester4 = new Semester(4022, startDate4, endDate4);


        List<Semester> semesters = new ArrayList<>();
        semesters.add(semester1);
        semesters.add(semester2);
        semesters.add(semester3);
        semesters.add(semester4);

        for (int i = 0; i < semesters.size(); i++) {
            AppContext.getSemesterService().saveOrUpdate(semesters.get(i));
        }

    }

}