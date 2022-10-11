package com.baydijital.softtech.app.log.dao;

import com.baydijital.softtech.app.log.entity.LogDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gokalp on 9/20/22
 */
public interface LogDetailDao extends JpaRepository<LogDetail, Long> {
}
