package com.baydijital.softtech.app.acc.dao;

import com.baydijital.softtech.app.acc.entity.AccMoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gokalp on 7/23/22
 */
@Repository
public interface AccMoneyTransferDao extends JpaRepository<AccMoneyTransfer, Long> {
}
