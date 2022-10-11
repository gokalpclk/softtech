package com.baydijital.softtech.app.crd.service.entityservice;

import com.baydijital.softtech.app.crd.dao.CrdCreditCardActivityDao;
import com.baydijital.softtech.app.crd.entity.CrdCreditCardActivity;
import com.baydijital.softtech.app.gen.service.BaseEntityService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Gokalp on 8/3/22
 */
@Service
@Transactional
public class CrdCreditCardActivityEntityService extends BaseEntityService<CrdCreditCardActivity, CrdCreditCardActivityDao> {
    public CrdCreditCardActivityEntityService(CrdCreditCardActivityDao dao) {
        super(dao);
    }
    public List<CrdCreditCardActivity> findAllByCrdCreditCardIdAndTransactionDateBetween(Long crdCreditCardId, Date startDate, Date endDate){
        return getDao().findAllByCrdCreditCardIdAndTransactionDateBetween(crdCreditCardId, startDate, endDate);
    }

    public List<CrdCreditCardActivity> findAllByCrdCreditCardIdAndTransactionDateBetween(Long crdCreditCardId, Date startDate, Date endDate, Optional<Integer> pageOptional, Optional<Integer> sizeOptional){

        PageRequest pageRequest = getPageRequest(pageOptional, sizeOptional);
        return getDao().findAllByCrdCreditCardIdAndTransactionDateBetween(crdCreditCardId, startDate, endDate, pageRequest).toList();
    }

}
