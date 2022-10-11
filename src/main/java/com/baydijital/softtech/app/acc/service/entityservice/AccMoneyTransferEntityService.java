package com.baydijital.softtech.app.acc.service.entityservice;

import com.baydijital.softtech.app.acc.dao.AccMoneyTransferDao;
import com.baydijital.softtech.app.acc.entity.AccMoneyTransfer;
import com.baydijital.softtech.app.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Gokalp on 7/23/22
 */
@Service
@Transactional
public class AccMoneyTransferEntityService extends BaseEntityService<AccMoneyTransfer, AccMoneyTransferDao> {

    public AccMoneyTransferEntityService(AccMoneyTransferDao dao) {
        super(dao);
    }
}
