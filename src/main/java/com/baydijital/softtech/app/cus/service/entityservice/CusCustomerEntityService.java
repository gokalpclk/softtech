package com.baydijital.softtech.app.cus.service.entityservice;

import com.baydijital.softtech.app.cus.dao.CusCustomerDao;
import com.baydijital.softtech.app.cus.entity.CusCustomer;
import com.baydijital.softtech.app.cus.enums.CusErrorMessage;
import com.baydijital.softtech.app.gen.exceptions.ItemNotFoundException;
import com.baydijital.softtech.app.gen.service.BaseEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Gokalp on 7/19/22
 */
@Service
@Transactional
public class CusCustomerEntityService extends BaseEntityService<CusCustomer, CusCustomerDao> {

    public CusCustomerEntityService(CusCustomerDao dao) {
        super(dao);
    }

    public List<CusCustomer> findAllBySurname(String surname){
        return getDao().findAllBySurname(surname);
    }

    public CusCustomer findByIdentityNo(Long identityNo){
        return getDao().findByIdentityNo(identityNo);
    }
}
