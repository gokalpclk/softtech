package com.baydijital.softtech.app.cus.dao;

import com.baydijital.softtech.app.cus.entity.CusCustomer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gokalp on 9/8/22
 */
@DataJpaTest
class CusCustomerDaoTest {

    @Autowired
    private CusCustomerDao cusCustomerDao;

    @Test
    void findAllBySurname() {
        List<CusCustomer> cusCustomerList = cusCustomerDao.findAllBySurname("celik");
        for (CusCustomer cusCustomer : cusCustomerList) {
            System.out.println(cusCustomer.getId());
        }
    }

    @Test
    void findByIdentityNo() {
    }
}