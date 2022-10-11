package com.baydijital.softtech.app.acc.service;

import com.baydijital.softtech.app.acc.converter.AccAccountActivityMapper;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 7/26/22
 */
@Service
@RequiredArgsConstructor
public class AccAccountActivityService {
    private final AccAccountActivityEntityService accAccountActivityEntityService;
    private final AccAccountEntityService accAccountEntityService;

    public AccAccountActivityDto withdraw(AccMoneyActivityRequestDto accMoneyActivityRequestDto) {

        validateParameterIsNull(accMoneyActivityRequestDto);
        Long accAccountId = accMoneyActivityRequestDto.getAccAccountId();
        BigDecimal amount = accMoneyActivityRequestDto.getAmount();

        AccMoneyActivityDto accMoneyActivityDto = AccMoneyActivityDto.builder()
                .accAccountId(accAccountId)
                .amount(amount)
                .accAccountActivityType(AccAccountActivityType.WITHDRAW)
                .build();
        AccAccountActivity accAccountActivity = moneyOut(accMoneyActivityDto);

        AccAccountActivityDto accAccountActivityDto = AccAccountActivityMapper.INSTANCE.convertToAccAccountActivityDto(accAccountActivity);
        return accAccountActivityDto;
    }

    public <T> void validateParameterIsNull(T t) {
        if (t == null) {
            throw new GenBusinessException(GenErrorMessage.PARAMETER_CANNOT_BE_NULL);
        }
    }

    public AccAccountActivityDto deposit(AccMoneyActivityRequestDto accMoneyActivityRequestDto) {
        Long accAccountId = accMoneyActivityRequestDto.getAccAccountId();
        BigDecimal amount = accMoneyActivityRequestDto.getAmount();

        AccMoneyActivityDto accMoneyActivityDto = AccMoneyActivityDto.builder()
                .accAccountId(accAccountId)
                .amount(amount)
                .accAccountActivityType(AccAccountActivityType.DEPOSIT)
                .build();

        AccAccountActivity accAccountActivity = moneyIn(accMoneyActivityDto);

        AccAccountActivityDto accAccountActivityDto = AccAccountActivityMapper.INSTANCE.convertToAccAccountActivityDto(accAccountActivity);
        return accAccountActivityDto;


    }

    public AccAccountActivity moneyOut(AccMoneyActivityDto accMoneyActivityDto) {

        validateParameterIsNull(accMoneyActivityDto);
        Long accountId = accMoneyActivityDto.getAccAccountId();
        BigDecimal amount = accMoneyActivityDto.getAmount();
        AccAccountActivityType accAccountActivityType = accMoneyActivityDto.getAccAccountActivityType();

        AccAccount accAccount = accAccountEntityService.getByIdWithControl(accountId);

        BigDecimal newBalance = accAccount.getCurrentBalance().subtract(amount);

        validateBalance(newBalance);


        AccAccountActivity accAccountActivity = createAccAccountActivity(accountId, amount, newBalance, accAccountActivityType);

        updateCurrentBalance(accAccount, newBalance);

        return accAccountActivity;

    }



    public AccAccountActivity moneyIn(AccMoneyActivityDto accMoneyActivityDto) {

        validateParameterIsNull(accMoneyActivityDto);

        Long accountId = accMoneyActivityDto.getAccAccountId();
        BigDecimal amount = accMoneyActivityDto.getAmount();
        AccAccountActivityType accAccountActivityType = accMoneyActivityDto.getAccAccountActivityType();


        AccAccount accAccount = accAccountEntityService.getByIdWithControl(accountId);
        BigDecimal newBalance = accAccount.getCurrentBalance().add(amount);


        AccAccountActivity accAccountActivity = createAccAccountActivity(accountId, amount, newBalance, accAccountActivityType);

        updateCurrentBalance(accAccount, newBalance);

        return accAccountActivity;
    }

    private void updateCurrentBalance(AccAccount accAccount, BigDecimal newBalance) {
        accAccount.setCurrentBalance(newBalance);
        accAccount = accAccountEntityService.save(accAccount);
    }

    private void validateBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new GenBusinessException(AccErrorMessage.INSUFFICIENT_BALANCE);
        }
    }

    private AccAccountActivity createAccAccountActivity(Long accountId, BigDecimal amount, BigDecimal newBalance, AccAccountActivityType accAccountActivityType) {
        AccAccountActivity accAccountActivity = new AccAccountActivity();
        accAccountActivity.setActivityType(accAccountActivityType);
        accAccountActivity.setAccAccountId(accountId);
        accAccountActivity.setAmount(amount);
        accAccountActivity.setTransactionDate(new Date());
        accAccountActivity.setCurrentBalance(newBalance);
        accAccountActivity = accAccountActivityEntityService.save(accAccountActivity);
        return accAccountActivity;
    }
}

