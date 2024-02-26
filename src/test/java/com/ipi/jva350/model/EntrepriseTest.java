package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EntrepriseTest {

    //__________TEST TDD : Entreprise.estDansPlage()__________
    //Test des cas limites :
    //Test d'une date qui se situe entre la date de début et la date de fin donc renvoie true.
    //Test d'une date qui ne se situe pas entre la date de début et date de fin donc renvoie false.
    //Test d'une date qui correspond à la date de début, renvoie true.
    @ParameterizedTest
    //paramètres : date à tester, date début, date fin
    @CsvSource({
            "'2023-12-21', '2023-12-20', '2023-12-30', true",
            "'2023-12-19', '2023-12-20', '2023-12-30', false",
            "'2023-12-20', '2023-12-20', '2023-12-30', true"
    })
    void testEstDansPlage(String dateATester, String dateDebut, String DateFin, Boolean excepted){
        // Given
        LocalDate jourATester = LocalDate.parse(dateATester);
        LocalDate jourDebut = LocalDate.parse(dateDebut);
        LocalDate jourFin = LocalDate.parse(DateFin);
        //When
        boolean res = Entreprise.estDansPlage(jourATester, jourDebut, jourFin);
        //Then
        Assertions.assertEquals(excepted, res);
    }


    //__________TEST UNITAIRE PARAMETRE : Entreprise.estJourFerie() __________
    //Test pour le 1er janvier 2024, qui est un jour ferié, donc renvoie true.
    //Test pour le 2 janvier 2024 qui n'est pas un jour ferié donc renvoie false.
    @ParameterizedTest
    @CsvSource({
            "'2024-01-01', true",
            "'2024-01-02', false"
    })
    void testEstJourFerie(String jour, Boolean excepted){
        //Given
        LocalDate jourATester = LocalDate.parse(jour);
        //When
        boolean res = Entreprise.estJourFerie(jourATester);
        //Then
        Assertions.assertEquals(excepted, res);
    }


    //__________TEST UNITAIRE PARAMETRE : Entreprise.proportionPondereeDuMois() __________
    //Tests en début d'année (fevrier), milieu d'année (juin) et fin d'année (novembre),
    //car la proportion pondéré est différente en fonction des mois de l'année (plus élevé en début et fin d'année).
    @ParameterizedTest
    @CsvSource({
            "'2023-02-20', 0.9666666666666666",
            "'2023-06-20', 0.23333333333333334",
            "'2023-11-20', 0.7666666666666667"
    })
    void testProportionPondereeDuMois(String moisDuConge, double excepted){
        //Given
        LocalDate moisDuCongeATester = LocalDate.parse(moisDuConge);
        //When
        double res = Entreprise.proportionPondereeDuMois(moisDuCongeATester);
        //Then
        Assertions.assertEquals(excepted, res);
    }

    //__________TEST UNITAIRE PARAMETRE : Entreprise.getPremierJourAnneeDeConges() __________
    //Test si le mois est inférieur à 5 (Mai) alors la date du premier jour de congé sera Année-1, 6, 1 = 1er juin de l'année précédente = renvoie true.
    //Test si le mois est égale à 5 (Mai), idem à ci-dessus (car date > 5 et non >=5).
    //Test si le mois est supérieur à 5 (Mai)
    //Erreur dans la méthode "getPremierJourAnneeDeConges" (modification dans Model/Entreprise.java)
    @ParameterizedTest
    @CsvSource({
            "'2024-04-02', '2023-06-01'",
            "'2024-05-02', '2023-06-01'",
            "'2024-06-02', '2024-06-01'",
    })
    void testGetPremierJourAnneeDeConges(String d, LocalDate excepted){
        //Given
        LocalDate date = LocalDate.parse(d);
        //When
        LocalDate res = Entreprise.getPremierJourAnneeDeConges(date);
        //Then
        Assertions.assertEquals(excepted, res);
    }

}
