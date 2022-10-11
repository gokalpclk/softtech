package com.baydijital.softtech.app.acc.service;

import com.baydijital.softtech.app.acc.converter.AccMoneyTransferMapper;
import com.baydijital.softtech.app.acc.dto.AccMoneyActivityDto;
import com.baydijital.softtech.app.acc.dto.AccMoneyTransferDto;
import com.baydijital.softtech.app.acc.dto.AccMoneyTransferSaveRequestDto;
import com.baydijital.softtech.app.acc.entity.AccMoneyTransfer;
import com.baydijital.softtech.app.acc.enums.AccAccountActivityType;
import com.baydijital.softtech.app.acc.service.entityservice.AccMoneyTransferEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gokalp on 7/26/22
 */
@Service
@RequiredArgsConstructor
public class AccMoneyTransferService {

    private final AccMoneyTransferEntityService accMoneyTransferEntityService;
    private final AccAccountActivityService accAccountActivityService;

    public AccMoneyTransferDto transferMoney(AccMoneyTransferSaveRequestDto accMoneyTransferSaveRequestDto) {
        AccMoneyTransfer accMoneyTransfer = AccMoneyTransferMapper.INSTANCE.convertToAccMoneyTransfer(accMoneyTransferSaveRequestDto);
        accMoneyTransfer.setTransferDate(new Date());

        Long accountIdFrom = accMoneyTransfer.getAccountIdFrom();
        Long accountIdTo = accMoneyTransfer.getAccountIdTo();
        BigDecimal amount = accMoneyTransfer.getAmount();

        AccMoneyActivityDto accMoneyActivityDtoSend = AccMoneyActivityDto.builder()
                .accAccountId(accountIdFrom)
                .amount(amount)
                .accAccountActivityType(AccAccountActivityType.SEND)
                .build();
        accAccountActivityService.moneyOut(accMoneyActivityDtoSend);

        AccMoneyActivityDto accMoneyActivityDtoGet = AccMoneyActivityDto.builder()
                .accAccountId(accountIdTo)
                .amount(amount)
                .accAccountActivityType(AccAccountActivityType.GET)
                .build();
        accAccountActivityService.moneyIn(accMoneyActivityDtoGet);

        accMoneyTransfer = accMoneyTransferEntityService.save(accMoneyTransfer);
        AccMoneyTransferDto accMoneyTransferDto = AccMoneyTransferMapper.INSTANCE.convertToAccMoneyTransferDto(accMoneyTransfer);
        return accMoneyTransferDto;
    }
}
