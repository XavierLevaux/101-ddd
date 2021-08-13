package com.jab.ddd.application;

import java.math.BigDecimal;
import java.util.Optional;

import com.jab.ddd.domain.model.Balance;
import com.jab.ddd.domain.service.BalanceRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class BalanceServiceTest {
    
    @MockBean
    private BalanceRepository balanceRepository;

    @Autowired
    private BalanceService balanceService;

    @Test
    public void given_service_when_withdraw_then_Ok() {

        //Given
        BigDecimal current = new BigDecimal("100.0");
        BigDecimal amount = new BigDecimal("10.0");
        Balance mockedBalance = new Balance(1L, current, 1L);
        Mockito.when(balanceRepository.findById(any())).thenReturn(Optional.of(mockedBalance));
        Mockito.when(balanceRepository.save(any())).thenReturn(new Balance(1L, current.subtract(amount), 1L));

        //When
        Balance newBalance = balanceService.witddraw(1L, amount);

        //Then
        then(newBalance).isNotNull();
    }

}
