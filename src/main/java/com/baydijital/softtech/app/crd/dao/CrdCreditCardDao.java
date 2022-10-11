package com.baydijital.softtech.app.crd.dao;

import com.baydijital.softtech.app.crd.dto.CrdCreditCardDetailsDto;
import com.baydijital.softtech.app.crd.entity.CrdCreditCard;
import com.baydijital.softtech.app.gen.enums.GenStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Gokalp on 8/3/22
 */
@Repository
public interface CrdCreditCardDao extends JpaRepository<CrdCreditCard, Long> {
    List<CrdCreditCard> findAllByStatusType(GenStatusType genStatusType);
    CrdCreditCard findByCardNoAndCvvNoAndExpireDateAndStatusType(Long cardNo, Long cvvNo, Date expireDate, GenStatusType genStatusType);

    @Query(
            "select new com.baydijital.softtech.app.crd.dto.CrdCreditCardDetailsDto(cusCustomer.name, cusCustomer.surname, crdCreditCard.cardNo, crdCreditCard.expireDate, crdCreditCard.currentDebt, crdCreditCard.minimumPaymentAmount, crdCreditCard.cutoffDate, crdCreditCard.dueDate) from CrdCreditCard crdCreditCard left join  CusCustomer  cusCustomer on crdCreditCard.cusCustomerId = cusCustomer.id where crdCreditCard.id = :creditCardId"
    )
    CrdCreditCardDetailsDto getCreditCardDetails(Long creditCardId);

}
