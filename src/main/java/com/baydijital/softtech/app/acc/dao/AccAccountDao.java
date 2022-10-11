package com.baydijital.softtech.app.acc.dao;

import com.baydijital.softtech.app.acc.entity.AccAccount;
import com.baydijital.softtech.app.gen.enums.GenStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Gokalp on 7/22/22
 */
@Repository
public interface AccAccountDao extends JpaRepository<AccAccount, Long> {

    List<AccAccount> findAllByStatusType(GenStatusType statusType);

    Page<AccAccount> findAllByStatusType(GenStatusType statusType, Pageable pageable);
}
