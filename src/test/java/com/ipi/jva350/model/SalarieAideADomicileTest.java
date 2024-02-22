package com.ipi.jva350.model;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.LinkedHashSet;


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
    //TEST NON PARAMETRE
    @Test
    public void testNonParamCalculeJoursDeCongeDecomptesPourPlage(){
        //Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                5,
                120,
                15,
                8);
        //When
        LinkedHashSet<LocalDate> res = monSalarie.calculeJoursDeCongeDecomptesPourPlage(LocalDate.of(2023,12,17), LocalDate.of(2023,12,28));
        //Then
        Assertions.assertEquals(9, res.size());
    }

    //TEST PARAMETRE
    @ParameterizedTest
    @CsvSource({
            "'2023-12-17', '2023-12-28', 9",
            "'2023-12-17', '2024-01-08', 17"
    })
    void testCalculeJoursDeCongeDecomptesPourPlage(String dateDebut, String dateFin, int exceptedNb){
        //Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                5,
                120,
                15,
                8);
        //When
        LinkedHashSet<LocalDate> resNb = monSalarie.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.parse(dateDebut),
                LocalDate.parse(dateFin));
        //Then
        Assertions.assertEquals(exceptedNb, resNb.size());
    }

}
