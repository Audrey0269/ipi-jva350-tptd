package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class SalarieAideADomicileTest {

    @Autowired
    private SalarieAideADomicileRepository salarieAideADomicileRepository;

    //Je test la part de congés pris totaux de l'année N-1 :
    //Le résulat de ce test = congesPayesPrisAnneeNMoins1 / congePayesAcquisAnneeNMoins1
    //Soit ici 5/10 = 0.5, donc le test est true.
    @Test
    public void testPartCongesPrisTotauxAnneeNMoins1() {
        //Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023, 1, 1),
                LocalDate.now(),
                180,
                22.5,
                80,
                10,
                5);
        salarieAideADomicileRepository.save(monSalarie);
        //When
        Double res = salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1();
        //Then
        Assertions.assertEquals(0.5, res);
    }

}





