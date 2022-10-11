package com.baydijital.softtech.app.acc.service;

import com.baydijital.softtech.app.acc.dto.AccAccountActivityDto;
import com.baydijital.softtech.app.acc.dto.AccMoneyActivityDto;
import com.baydijital.softtech.app.acc.dto.AccMoneyActivityRequestDto;
import com.baydijital.softtech.app.acc.entity.AccAccount;
import com.baydijital.softtech.app.acc.entity.AccAccountActivity;
import com.baydijital.softtech.app.acc.enums.AccAccountActivityType;
import com.baydijital.softtech.app.acc.enums.AccErrorMessage;
import com.baydijital.softtech.app.acc.service.entityservice.AccAccountActivityEntityService;
import com.baydijital.softtech.app.acc.service.entityservice.AccAccountEntityService;
import com.baydijital.softtech.app.gen.enums.GenErrorMessage;
import com.baydijital.softtech.app.gen.exceptions.GenBusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gokalp on 9/8/22
 */
@ExtendWith(MockitoExtension.class)
class AccAccountActivityServiceTest {

    @Spy
    @InjectMocks
    private AccAccountActivityService accAccountActivityService;

    @Mock
    private AccAccountEntityService accAccountEntityService;

    @Mock
    private AccAccountActivityEntityService accAccountActivityEntityService;


    @Test
    void shouldWithdraw() {
        Long accountId = 1L;
        BigDecimal amount = new BigDecimal(100);
        AccAccountActivityType activityType = AccAccountActivityType.WITHDRAW;

        AccAccountActivity accAccountActivity = Mockito.mock(AccAccountActivity.class);
        Mockito.when(accAccountActivity.getCurrentBalance()).thenReturn(amount);

        AccMoneyActivityRequestDto accMoneyActivityRequestDto = Mockito.mock(AccMoneyActivityRequestDto.class);
        Mockito.when(accMoneyActivityRequestDto.getAccAccountId()).thenReturn(accountId);
        Mockito.when(accMoneyActivityRequestDto.getAmount()).thenReturn(amount);

        AccMoneyActivityDto accMoneyActivityDto = AccMoneyActivityDto.builder()
                .accAccountId(accountId)
                .amount(amount)
                .accAccountActivityType(activityType)
                .build();


        Mockito.doReturn(accAccountActivity).when(accAccountActivityService).moneyOut(accMoneyActivityDto);

        AccAccountActivityDto result = accAccountActivityService.withdraw(accMoneyActivityRequestDto);

        assertEquals(amount, result.getCurrentBalance());



        Mockito.verify(accAccountActivityService).moneyOut(accMoneyActivityDto);
    }

    @Test
    void shouldNotValidateParameterNull() {

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> accAccountActivityService.validateParameterIsNull(null));

        assertEquals(GenErrorMessage.PARAMETER_CANNOT_BE_NULL , genBusinessException.getBaseErrorMessage());


    }

    @Test
    void deposit() {
        Long accountId = 1L;
        BigDecimal amount = new BigDecimal(100);
        AccAccountActivityType activityType = AccAccountActivityType.DEPOSIT;

        AccAccountActivity accAccountActivity = Mockito.mock(AccAccountActivity.class);
        Mockito.when(accAccountActivity.getCurrentBalance()).thenReturn(amount);

        AccMoneyActivityRequestDto accMoneyActivityRequestDto = Mockito.mock(AccMoneyActivityRequestDto.class);
        Mockito.when(accMoneyActivityRequestDto.getAccAccountId()).thenReturn(accountId);
        Mockito.when(accMoneyActivityRequestDto.getAmount()).thenReturn(amount);


        AccMoneyActivityDto accMoneyActivityDto = AccMoneyActivityDto.builder()
                .accAccountId(accountId)
                .amount(amount)
                .accAccountActivityType(activityType)
                .build();

        Mockito.doReturn(accAccountActivity).when(accAccountActivityService).moneyIn(accMoneyActivityDto);

        AccAccountActivityDto result = accAccountActivityService.deposit(accMoneyActivityRequestDto);

        assertEquals(amount, result.getCurrentBalance());

        Mockito.verify(accAccountActivityService).moneyIn(accMoneyActivityDto);

    }

    @Test
    void shouldMoneyOut() {
        Long accountId = 1L;
        BigDecimal amount = new BigDecimal(100);
        BigDecimal currentBalance = new BigDecimal(1000);
        BigDecimal newBalance = currentBalance.subtract(amount);
        AccAccountActivityType activityType = AccAccountActivityType.WITHDRAW;


        AccAccount accAccount = Mockito.mock(AccAccount.class);
        Mockito.when(accAccount.getCurrentBalance()).thenReturn(currentBalance);

        AccAccountActivity accAccountActivity = Mockito.mock(AccAccountActivity.class);
        Mockito.when(accAccountActivity.getCurrentBalance()).thenReturn(newBalance);

        Mockito.when(accAccountEntityService.getByIdWithControl(accountId)).thenReturn(accAccount);
        Mockito.when(accAccountActivityEntityService.save(Mockito.any())).thenReturn(accAccountActivity);
        Mockito.when(accAccountEntityService.save(accAccount)).thenReturn(accAccount);

        AccMoneyActivityDto accMoneyActivityDto = AccMoneyActivityDto.builder()
                .accAccountId(accountId)
                .amount(amount)
                .accAccountActivityType(activityType)
                .build();

        AccAccountActivity result = accAccountActivityService.moneyOut(accMoneyActivityDto);
        Assertions.assertEquals(newBalance, result.getCurrentBalance() );

    }

    @Test
    void shouldNotMoneyOutWhenBalanceIsInsufficient() {
        Long accountId = 1L;
        BigDecimal amount = new BigDecimal(2000);
        BigDecimal currentBalance = new BigDecimal(1000);
        BigDecimal newBalance = currentBalance.subtract(amount);
        AccAccountActivityType activityType = AccAccountActivityType.WITHDRAW;


        AccAccount accAccount = Mockito.mock(AccAccount.class);
        Mockito.when(accAccount.getCurrentBalance()).thenReturn(currentBalance);



        Mockito.when(accAccountEntityService.getByIdWithControl(accountId)).thenReturn(accAccount);

        AccMoneyActivityDto accMoneyActivityDto = AccMoneyActivityDto.builder()
                .accAccountId(accountId)
                .amount(amount)
                .accAccountActivityType(activityType)
                .build();

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> accAccountActivityService.moneyOut(accMoneyActivityDto));


        Assertions.assertEquals(AccErrorMessage.INSUFFICIENT_BALANCE, genBusinessException.getBaseErrorMessage());

    }



    @Test
    void shouldMoneyIn() {
        Long accountId = 1L;
        BigDecimal amount = new BigDecimal(100);
        BigDecimal currentBalance = new BigDecimal(1000);
        BigDecimal newBalance = currentBalance.add(amount);
        AccAccountActivityType activityType = AccAccountActivityType.DEPOSIT;


        AccAccount accAccount = Mockito.mock(AccAccount.class);
        Mockito.when(accAccount.getCurrentBalance()).thenReturn(currentBalance);

        AccAccountActivity accAccountActivity = Mockito.mock(AccAccountActivity.class);
        Mockito.when(accAccountActivity.getCurrentBalance()).thenReturn(newBalance);

        Mockito.when(accAccountEntityService.getByIdWithControl(accountId)).thenReturn(accAccount);
        Mockito.when(accAccountActivityEntityService.save(Mockito.any())).thenReturn(accAccountActivity);
        Mockito.when(accAccountEntityService.save(accAccount)).thenReturn(accAccount);

        AccMoneyActivityDto accMoneyActivityDto = AccMoneyActivityDto.builder()
                .accAccountId(accountId)
                .amount(amount)
                .accAccountActivityType(activityType)
                .build();

        AccAccountActivity result = accAccountActivityService.moneyIn(accMoneyActivityDto);
        Assertions.assertEquals(newBalance, result.getCurrentBalance() );
    }
}