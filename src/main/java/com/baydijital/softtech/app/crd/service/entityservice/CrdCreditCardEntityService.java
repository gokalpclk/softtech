package com.baydijital.softtech.app.crd.service.entityservice;

import com.baydijital.softtech.app.crd.dao.CrdCreditCardDao;
import com.baydijital.softtech.app.crd.dto.CrdCreditCardDetailsDto;
import com.baydijital.softtech.app.crd.entity.CrdCreditCard;
import com.baydijital.softtech.app.gen.enums.GenStatusType;
import com.baydijital.softtech.app.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


/**
 * @author Gokalp on 8/3/22
 */
@Service
@Transactional
public class CrdCreditCardEntityService extends BaseEntityService<CrdCreditCard, CrdCreditCardDao> {
    public CrdCreditCardEntityService(CrdCreditCardDao dao) {
        super(dao);
    }

    public List<CrdCreditCard> findAllActiveCrdCreditCard(){
        return getDao().findAllByStatusType(GenStatusType.ACTIVE);
    }
    public List<CrdCreditCard> findAllByStatusType(GenStatusType genStatusType){
        return getDao().findAllByStatusType(genStatusType);
    }

    public  CrdCreditCard findByCardNoAndCvvNoAndExpireDateAndStatusType(Long cardNo, Long cvvNo, Date expireDate){
        return getDao().findByCardNoAndCvvNoAndExpireDateAndStatusType(cardNo, cvvNo, expireDate, GenStatusType.ACTIVE);
    }
    public CrdCreditCardDetailsDto getCreditCardDetails(Long creditCardId){
        return getDao().getCreditCardDetails(creditCardId);
    }
}
