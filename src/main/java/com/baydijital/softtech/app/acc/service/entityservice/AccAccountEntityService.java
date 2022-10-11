package com.baydijital.softtech.app.acc.service.entityservice;

import com.baydijital.softtech.app.acc.dao.AccAccountDao;
import com.baydijital.softtech.app.acc.entity.AccAccount;
import com.baydijital.softtech.app.cus.enums.CusErrorMessage;
import com.baydijital.softtech.app.gen.enums.GenStatusType;
import com.baydijital.softtech.app.gen.exceptions.ItemNotFoundException;
import com.baydijital.softtech.app.gen.service.BaseEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Gokalp on 7/23/22
 */
@Service
@Transactional
public class AccAccountEntityService extends BaseEntityService<AccAccount, AccAccountDao> {

    public AccAccountEntityService(AccAccountDao dao) {
        super(dao);
    }

    public List<AccAccount> findAllActiveAccAccountList(){
        return getDao().findAllByStatusType(GenStatusType.ACTIVE);
    }

    public List<AccAccount> findAllActiveAccAccountList(Optional<Integer> pageOptional, Optional<Integer> sizeOptional){
        PageRequest pageRequest = getPageRequest(pageOptional, sizeOptional);

        Page<AccAccount> accAccountPage = getDao().findAllByStatusType(GenStatusType.ACTIVE, pageRequest);
        return accAccountPage.toList();
    }




    public List<AccAccount> findAllByStatusType(GenStatusType genStatusType) {
        return getDao().findAllByStatusType(genStatusType);
    }

}
