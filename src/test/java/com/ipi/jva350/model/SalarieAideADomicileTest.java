package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;


public class SalarieAideADomicileTest {

    //______________TESTS UNITAIRES SIMPLES______________
    @Test // indique que sera exécutée par Junit
    public void testALegalementDroitADesCongesPayesTrue(){
        // Given : Mise en place de l'environnement du test et de ses données (hypothèses)
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                5,
                10,
                15,
                8);
        // When : Comportement à tester, en pratique une (ou des) méthode(s) à exécuter
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
        Assertions.assertEquals(true,res, "joursTravaillesAnneeNMoins1 doit etre supérieur à 10");
    }

    @Test
    public void testALegalementDroitADesCongesPayesFalse(){
        // Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                5,
                9,
                15,
                8);
        // When
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then
        Assertions.assertEquals(false,res, "joursTravaillesAnneeNMoins1 doit etre inférieur à 10");
    }

    //Jours travaillés hors WE
    @Test
    public void testHabituellementTravailleTrue(){
        // Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                5,
                120,
                15,
                8);
        // When
        boolean res = monSalarie.estHabituellementTravaille(LocalDate.of(2024, 1,12));
        // Then
        Assertions.assertEquals(true,res, "le jour doit se situer apres la date début");
    }
















    //______________TEST UNITAIRE - PARAMETRE______________
    @Test
    public void testCalculeJoursDeCongeDecomptesPourPlage(LocalDate dateDebut, LocalDate dateFin, Boolean expected){
         //Given, When, Then
        SalarieAideADomicile monSalarie = new SalarieAideADomicile();

        Assertions.assertEquals(expected, monSalarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut,dateFin));
    }



    //______________TEST UNITAIRE - MOCKS______________
}
