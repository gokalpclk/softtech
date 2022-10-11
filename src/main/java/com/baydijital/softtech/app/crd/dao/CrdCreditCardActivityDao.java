package com.baydijital.softtech.app.crd.dao;

import com.baydijital.softtech.app.crd.entity.CrdCreditCardActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Gokalp on 8/3/22
 */
public interface CrdCreditCardActivityDao extends JpaRepository<CrdCreditCardActivity, Long> {
    List<CrdCreditCardActivity> findAllByCrdCreditCardIdAndTransactionDateBetween(Long id, Date startDate, Date endDate);
    Page<CrdCreditCardActivity> findAllByCrdCreditCardIdAndTransactionDateBetween(Long id, Date startDate, Date endDate, Pageable pageable);
}
