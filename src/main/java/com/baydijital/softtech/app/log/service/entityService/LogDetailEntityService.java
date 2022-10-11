package com.baydijital.softtech.app.log.service.entityService;

import com.baydijital.softtech.app.gen.service.BaseEntityService;
import com.baydijital.softtech.app.log.dao.LogDetailDao;
import com.baydijital.softtech.app.log.entity.LogDetail;
import org.springframework.stereotype.Service;

/**
 * @author Gokalp on 9/20/22
 */
@Service
public class LogDetailEntityService extends BaseEntityService<LogDetail, LogDetailDao> {


    public LogDetailEntityService(LogDetailDao dao) {
        super(dao);
    }
}
