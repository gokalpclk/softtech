package com.baydijital.softtech.app.acc.service.entityservice;

import com.baydijital.softtech.app.acc.dao.AccAccountActivityDao;
import com.baydijital.softtech.app.acc.entity.AccAccountActivity;
import com.baydijital.softtech.app.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Gokalp on 7/23/22
 */
@Service
@Transactional
public class AccAccountActivityEntityService extends BaseEntityService<AccAccountActivity, AccAccountActivityDao> {

    public AccAccountActivityEntityService(AccAccountActivityDao dao) {
        super(dao);
    }

}
