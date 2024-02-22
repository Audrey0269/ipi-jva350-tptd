package com.ipi.jva350.service;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;

public class SalarieAideADomicileTest {

    //Pour test d'intégration service
    //@ExtendWith(SpringExtension.class) // Junit 4 : @RunWith(SpringRunner.class)
    //@SpringBootTest
    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    //@Autowired


    //Pour test unitaire MOCK
    @Mock
    private SalarieAideADomicileRepository salarieAideADomicileRepository;
    @InjectMocks
    private SalarieAideADomicileService salarieAideADomicileService;


    //______________TEST UNITAIRE - MOCKS______________
    /*@Test
    public void testAjouteConge() throws SalarieException {
        //Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                80,
                2.5,
                200,
                20,
                8);
        //When
        salarieService.ajouteConge(monSalarie, LocalDate.of(2024,12,17), LocalDate.of(2024,12,18));
        //Then
        ArgumentCaptor<SalarieAideADomicile> salarieAideADomicileCaptor = ArgumentCaptor.forClass(SalarieAideADomicile.class);
        Mockito.verify(salarieAideADomicileRepository, Mockito.times(1)).save(salarieAideADomicileCaptor.capture()); //arg capture
        Assertions.assertEquals(salarieAideADomicileCaptor.getValue().getCongesPayesPrisAnneeNMoins1(), 1L);
    }
     */


    //__________TEST UNITAIRE MOCK : SalarieAideADomicileService.calculeLimiteEntrepriseCongesPermis() __________
    @Test
    public void testCalculeLimiteEntrepriseCongesPermis() {
        //Given
        LocalDate moisEnCours = LocalDate.of(2024,2,10);
        double congesPayesAcquisAnneeNMoins1 = 10;
        LocalDate moisDebutContrat = LocalDate.of(2023,9,1);
        LocalDate premierJourDeConge = LocalDate.of(2023,11,13);
        LocalDate dernierJourDeConge = LocalDate.of(2023,11,17);
        double congesPrisTotauxAnneeNMoins1 = 5;
        Mockito.when(salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(congesPrisTotauxAnneeNMoins1);
        //When
        long res = salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(
                moisEnCours,
                congesPayesAcquisAnneeNMoins1,
                moisDebutContrat,
                premierJourDeConge,
                dernierJourDeConge
              );
        //Then
        Assertions.assertEquals(5, res);
    }





    //__________TEST D'INTEGRATION DE SERVICE : SalarieAideADomicileService.calculeLimiteEntrepriseCongesPermis() __________
    /*@Test
    public void testCalculeLimiteEntrepriseCongesPermis(){
        // Given
        LocalDate moisEnCours = LocalDate.of(2023,11,10);
        double congePayeAquisAnneeNMoins1 = 20.00;
        LocalDate moisDebutContrat = LocalDate.of(2022, 11, 1);
        LocalDate premierJourConge = LocalDate.of(2023, 11, 11);
        LocalDate dernierJourConge = LocalDate.of(2022, 11, 18);

        // When
        Long res = salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(
                moisEnCours,
                congePayeAquisAnneeNMoins1,
                moisDebutContrat,
                premierJourConge,
                dernierJourConge);

        // Then : assertions sur le vrai état du système
        Assertions.assertEquals(LocalDate.of(2024, 11, 10), res, "Expected result is not as expected");
    }

     */


}
