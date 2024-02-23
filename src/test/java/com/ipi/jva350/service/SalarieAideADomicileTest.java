package com.ipi.jva350.service;


import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SalarieAideADomicileTest {

    //Pour test unitaire MOCK
    @Mock
    private SalarieAideADomicileRepository salarieAideADomicileRepository;
    @InjectMocks
    private SalarieAideADomicileService salarieAideADomicileService;

    //______________TEST UNITAIRE - MOCKS______________
    //Date de début du contrat le 1er janvier 2023
    //Date de fin = actuelle
    //Jour travaillé année actuelle : mai 2023 à aujourd'hui = 180 jours
    //CP aquis cette année = 2,5 jours sur 9 mois = 22,5
    //Jour travaillé année précédente (janvier 2023 à avril 2023) = 4 mois x 20 jours/mois = 80jours
    //CP aquis année précédente : de janvier 2023 à avril 2023 = 4 mois x 2,5 = 10
    //CP pris année précédente = 5
    //CP pris cette année du 11 mars 2024 au 15 mars 2024 = 5 jours
    /*@Test
    public void testAjouteConge() throws SalarieException {
        //Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023,1,1),
                LocalDate.now(),
                180,
                22.5,
                80,
                10,
                5);
        //When
        salarieAideADomicileService.ajouteConge(monSalarie,
                LocalDate.of(2024,3,11),
                LocalDate.of(2024,3,15));
        //Then
        ArgumentCaptor<SalarieAideADomicile> salarieAideADomicileCaptor = ArgumentCaptor.forClass(SalarieAideADomicile.class);
        Mockito.verify(salarieAideADomicileRepository, Mockito.times(1)).save(salarieAideADomicileCaptor.capture()); // arg capture !
        Assertions.assertEquals(1L, salarieAideADomicileCaptor.getValue().getCongesPayesPrisAnneeNMoins1());
    }
    */




    //__________TEST UNITAIRE MOCK : SalarieAideADomicileService.calculeLimiteEntrepriseCongesPermis() __________
    //Le méthode fonctionne mais c'est assez complexe d'ajuster les valeurs d'entrées...
    @Test
    public void testCalculeLimiteEntrepriseCongesPermis() {
        //Given
        LocalDate moisEnCours = LocalDate.of(2024,2,1);
        double congesPayesAcquisAnneeNMoins1 = 2.0;
        LocalDate moisDebutContrat = LocalDate.of(2023,3,1);
        LocalDate premierJourDeConge = LocalDate.of(2023,6,14);
        LocalDate dernierJourDeConge = LocalDate.of(203,6,16);
        double congesPrisTotauxAnneeNMoins1 = 1.0;
        when(salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(congesPrisTotauxAnneeNMoins1);
        //When
        long res = salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(
                moisEnCours,
                congesPayesAcquisAnneeNMoins1,
                moisDebutContrat,
                premierJourDeConge,
                dernierJourDeConge
              );
        //Then
        Assertions.assertEquals(1, res);
    }





    //__________TEST D'INTEGRATION DE SERVICE : SalarieAideADomicileService.calculeLimiteEntrepriseCongesPermis() __________
    //Le méthode fonctionne mais c'est assez complexe d'ajuster les valeurs d'entrées...
    @Test
    public void testIntegrationServiceCalculeLimiteEntrepriseCongesPermis(){
        // Given
        LocalDate moisEnCours = LocalDate.of(2023,11,1);
        double congePayeAquisAnneeNMoins1 = 25.00;
        LocalDate moisDebutContrat = LocalDate.of(2022, 11, 1);
        LocalDate premierJourConge = LocalDate.of(2023, 11, 14);
        LocalDate dernierJourConge = LocalDate.of(2022, 11, 28);
        // When
        long res = salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(
                moisEnCours,
                congePayeAquisAnneeNMoins1,
                moisDebutContrat,
                premierJourConge,
                dernierJourConge);
        // Then : assertions sur le vrai état du système
        Assertions.assertEquals(22, res);
    }




}
