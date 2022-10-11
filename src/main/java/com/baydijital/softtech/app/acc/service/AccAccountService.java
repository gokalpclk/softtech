package com.baydijital.softtech.app.acc.service;

import com.baydijital.softtech.app.acc.converter.AccAccountMapper;
import com.baydijital.softtech.app.acc.dto.AccAccountDto;
import com.baydijital.softtech.app.acc.dto.AccAccountSaveRequestDto;
import com.baydijital.softtech.app.acc.entity.AccAccount;
import com.baydijital.softtech.app.acc.service.entityservice.AccAccountActivityEntityService;
import com.baydijital.softtech.app.acc.service.entityservice.AccAccountEntityService;
import com.baydijital.softtech.app.acc.service.entityservice.AccMoneyTransferEntityService;
import com.baydijital.softtech.app.gen.enums.GenStatusType;
import com.baydijital.softtech.app.gen.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Gokalp on 7/23/22
 */
@Service
@RequiredArgsConstructor
public class AccAccountService {

    private final AccAccountEntityService accAccountEntityService;
    private final AccMoneyTransferEntityService accMoneyTransferEntityService;
    private final AccAccountActivityEntityService accAccountActivityEntityService;


    public List<AccAccountDto> findAll() {
        List<AccAccount> accAccountList = accAccountEntityService.findAll();
        List<AccAccountDto> accAccountDtoList = AccAccountMapper.INSTANCE.convertToAccAccountDtoList(accAccountList);
        return accAccountDtoList;
    }



    public List<AccAccountDto> findAllActiveAccAccountList() {
        List<AccAccount> accAccountList = accAccountEntityService.findAllActiveAccAccountList();
        List<AccAccountDto> accAccountDtoList = AccAccountMapper.INSTANCE.convertToAccAccountDtoList(accAccountList);
        return accAccountDtoList;
    }

    public List<AccAccountDto> findAllActiveAccAccountList(Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {
        List<AccAccount> accAccountList = accAccountEntityService.findAllActiveAccAccountList(pageOptional, sizeOptional);
        List<AccAccountDto> accAccountDtoList = AccAccountMapper.INSTANCE.convertToAccAccountDtoList(accAccountList);
        return accAccountDtoList;
    }


    public AccAccountDto findById(Long id) {
        AccAccount accAccount = accAccountEntityService.getByIdWithControl(id);
        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);
        return accAccountDto;

    }

    public AccAccountDto save(AccAccountSaveRequestDto accAccountSaveRequestDto) {
        String ibanNo = getIbanNo();
        AccAccount accAccount = AccAccountMapper.INSTANCE.convertToAccAccount(accAccountSaveRequestDto);
        Long currentCustomerId = accAccountEntityService.getCurrentCustomerId();
        accAccount.setCusCustomerId(currentCustomerId);
        accAccount.setIbanNo(ibanNo);
        accAccount = accAccountEntityService.save(accAccount);

        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);
        return accAccountDto;
    }

    private String getIbanNo() {
        return StringUtil.getRandomNumberAsString(26);
    }

    public void cancel(Long accountId) {
        AccAccount accAccount = accAccountEntityService.getByIdWithControl(accountId);

        accAccount.setStatusType(GenStatusType.PASSIVE);
        accAccount.setCancelDate(new Date());

        accAccountEntityService.save(accAccount);
    }
}
