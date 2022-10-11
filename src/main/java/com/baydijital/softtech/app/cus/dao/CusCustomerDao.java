package com.baydijital.softtech.app.cus.dao;

import com.baydijital.softtech.app.cus.entity.CusCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Gokalp on 7/19/22
 */
@Repository
public interface CusCustomerDao extends JpaRepository<CusCustomer,Long> {
    List<CusCustomer> findAllBySurname(String surname);
    CusCustomer findByIdentityNo(Long identityNo);
}
