package com.baydijital.softtech.app.crd.service;

import com.baydijital.softtech.app.crd.converter.CrdCreditCardActivityMapper;
import com.baydijital.softtech.app.crd.converter.CrdCreditCardMapper;
import com.baydijital.softtech.app.crd.dto.*;
import com.baydijital.softtech.app.crd.entity.CrdCreditCard;
import com.baydijital.softtech.app.crd.entity.CrdCreditCardActivity;
import com.baydijital.softtech.app.crd.enums.CrdCreditCardActivityType;
import com.baydijital.softtech.app.crd.enums.CrdErrorMessage;
import com.baydijital.softtech.app.crd.service.entityservice.CrdCreditCardActivityEntityService;
import com.baydijital.softtech.app.crd.service.entityservice.CrdCreditCardEntityService;
import com.baydijital.softtech.app.gen.enums.GenStatusType;
import com.baydijital.softtech.app.gen.exceptions.GenBusinessException;
import com.baydijital.softtech.app.gen.util.DateUtil;
import com.baydijital.softtech.app.gen.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Gokalp on 8/4/22
 */
@Service
@RequiredArgsConstructor
public class CrdCreditCardService {
    private final CrdCreditCardEntityService crdCreditCardEntityService;
    private final CrdCreditCardActivityEntityService crdCreditCardActivityEntityService;

    public CrdCreditCardResponseDto saveCrdCreditCard(CrdCreditCardSaveRequestDto crdCreditCardSaveRequestDto) {

        BigDecimal earning = crdCreditCardSaveRequestDto.getEarning();
        String strCutoffDay = crdCreditCardSaveRequestDto.getCutoffDay();

        BigDecimal limit = calculateLimit(earning);

        LocalDate cutoffDateLocal = getCutoffDateLocal(strCutoffDay);
        Date cutoffDate = DateUtil.convertToDate(cutoffDateLocal);

        Date dueDate = getDueDate(getDueDateLocal(cutoffDateLocal));
        CrdCreditCardResponseDto crdCreditCardResponseDto = createCardAndConvertToCrdCreditCardResponseDto(limit, cutoffDate, dueDate);

        return crdCreditCardResponseDto;
    }

    private CrdCreditCardResponseDto createCardAndConvertToCrdCreditCardResponseDto(BigDecimal limit, Date cutoffDate, Date dueDate) {
        Long currentCustomerId = crdCreditCardEntityService.getCurrentCustomerId();
        CrdCreditCard crdCreditCard = createCrdCreditCard(currentCustomerId, limit, cutoffDate, dueDate);
        crdCreditCard.setCusCustomerId(currentCustomerId);
        CrdCreditCardResponseDto crdCreditCardResponseDto = CrdCreditCardMapper.INSTANCE.convertToCrdCreditCardResponseDto(crdCreditCard);
        return crdCreditCardResponseDto;
    }

    private CrdCreditCard createCrdCreditCard(Long cusCustomerId, BigDecimal limit, Date cutoffDate, Date dueDate) {
        Date expireDate = getExpireDate();
        Long cardNo = getCardNo();
        Long cvvNo = getCvvNo();


        CrdCreditCard crdCreditCard = new CrdCreditCard();
        crdCreditCard.setCusCustomerId(cusCustomerId);
        crdCreditCard.setCardNo(cardNo);
        crdCreditCard.setCvvNo(cvvNo);
        crdCreditCard.setTotalLimit(limit);
        crdCreditCard.setCutoffDate(cutoffDate);
        crdCreditCard.setDueDate(dueDate);
        crdCreditCard.setExpireDate(expireDate);
        crdCreditCard.setAvailableCardLimit(limit);
        crdCreditCard.setMinimumPaymentAmount(BigDecimal.ZERO);
        crdCreditCard.setCurrentDebt(BigDecimal.ZERO);
        crdCreditCard.setStatusType(GenStatusType.ACTIVE);

        crdCreditCard = crdCreditCardEntityService.save(crdCreditCard);
        return crdCreditCard;
    }

    private static Date getExpireDate() {
        LocalDate expireDateLocal = getExpireDateLocal();
        Date expireDate = DateUtil.convertToDate(expireDateLocal);
        return expireDate;
    }

    private static Date getDueDate(LocalDate cutoffDateLocal) {
        LocalDate dueDateLocal = cutoffDateLocal;
        Date dueDate = DateUtil.convertToDate(dueDateLocal);
        return dueDate;
    }

    private static LocalDate getExpireDateLocal() {
        LocalDate expireDateLocal = LocalDate.now().plusYears(3);
        return expireDateLocal;
    }

    private static LocalDate getDueDateLocal(LocalDate cutoffDateLocal) {
        LocalDate dueDateLocal = cutoffDateLocal.plusDays(10);
        return dueDateLocal;
    }

    private static LocalDate getCutoffDateLocal(String strCutoffDay) {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        Month nextMonth = Month.of(currentMonth).plus(1);

        Integer cutoffDay = getCutoffDay(strCutoffDay);
        LocalDate cutoffDateLocal = LocalDate.of(currentYear, nextMonth, cutoffDay);
        return cutoffDateLocal;
    }

    private static BigDecimal calculateLimit(BigDecimal earning) {
        BigDecimal limit = earning.multiply(BigDecimal.valueOf(3));
        return limit;
    }

    private static Integer getCutoffDay(String strCutoffDay) {
        if (!StringUtils.hasText(strCutoffDay)) {
            strCutoffDay = "1";
        }
        Integer cutoffDay = Integer.valueOf(strCutoffDay);
        return cutoffDay;
    }

    private static Long getCvvNo() {
        Long cvvNo = StringUtil.getRandomNumber(3);
        return cvvNo;
    }

    private Long getCardNo() {
        Long cardNo = StringUtil.getRandomNumber(16);
        return cardNo;
    }


    public List<CrdCreditCardResponseDto> findAll() {
        List<CrdCreditCard> crdCreditCardList = crdCreditCardEntityService.findAll();
        List<CrdCreditCardResponseDto> crdCreditCardResponseDtoList = CrdCreditCardMapper.INSTANCE.convertToCrdCreditCardResponseDtoList(crdCreditCardList);
        return crdCreditCardResponseDtoList;

    }

    public List<CrdCreditCardResponseDto> findAllActiveCrdCreditCard() {
        List<CrdCreditCard> crdCreditCardList = crdCreditCardEntityService.findAllActiveCrdCreditCard();
        List<CrdCreditCardResponseDto> crdCreditCardResponseDtoList = CrdCreditCardMapper.INSTANCE.convertToCrdCreditCardResponseDtoList(crdCreditCardList);
        return crdCreditCardResponseDtoList;

    }

    public CrdCreditCardResponseDto findById(Long id) {
        CrdCreditCard crdCreditCard = crdCreditCardEntityService.getByIdWithControl(id);
        CrdCreditCardResponseDto crdCreditCardResponseDto = CrdCreditCardMapper.INSTANCE.convertToCrdCreditCardResponseDto(crdCreditCard);
        return crdCreditCardResponseDto;
    }


    public void cancel(Long id) {
        CrdCreditCard crdCreditCard = crdCreditCardEntityService.getByIdWithControl(id);
        crdCreditCard.setStatusType(GenStatusType.PASSIVE);
        crdCreditCard.setCancelDate(new Date());
        crdCreditCardEntityService.save(crdCreditCard);

    }

    public CrdCreditCardActivityDto spend(CrdCreditCardSpendDto crdCreditCardSpendDto) {
        BigDecimal amount = crdCreditCardSpendDto.getAmount();

        CrdCreditCard crdCreditCard = getCrdCreditCard(crdCreditCardSpendDto);

        validateCreditCard(crdCreditCard);


        BigDecimal currentDebt = crdCreditCard.getCurrentDebt().add(amount);
        BigDecimal availableCardLimit = crdCreditCard.getAvailableCardLimit().subtract(amount);

        validateCardLimit(availableCardLimit);
        updateCreditCardForSpend(crdCreditCard, currentDebt, availableCardLimit);

        crdCreditCard = crdCreditCardEntityService.save(crdCreditCard);

        CrdCreditCardActivity crdCreditCardActivity = createCrdCreditCardActivityForSpend(crdCreditCardSpendDto, crdCreditCard);

        CrdCreditCardActivityDto crdCreditCardActivityDto = createCrdCreditCardActivityForSpend(crdCreditCardActivity);


        return crdCreditCardActivityDto;

    }

    private static CrdCreditCardActivity createCrdCreditCardActivityForSpend(CrdCreditCardSpendDto crdCreditCardSpendDto, CrdCreditCard crdCreditCard) {
        CrdCreditCardActivity crdCreditCardActivity = new CrdCreditCardActivity();
        crdCreditCardActivity.setAmount(crdCreditCardSpendDto.getAmount());
        crdCreditCardActivity.setCrdCreditCardId(crdCreditCard.getId());
        crdCreditCardActivity.setDescription(crdCreditCardSpendDto.getDescription());
        crdCreditCardActivity.setCrdCreditCardActivityType(CrdCreditCardActivityType.SPEND);
        crdCreditCardActivity.setTransactionDate(new Date());
        return crdCreditCardActivity;
    }

    private CrdCreditCardActivityDto createCrdCreditCardActivityForSpend(CrdCreditCardActivity crdCreditCardActivity) {
        crdCreditCardActivity = crdCreditCardActivityEntityService.save(crdCreditCardActivity);
        CrdCreditCardActivityDto crdCreditCardActivityDto = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDto(crdCreditCardActivity);
        return crdCreditCardActivityDto;
    }

    private CrdCreditCard updateCreditCardForSpend(CrdCreditCard crdCreditCard, BigDecimal currentDebt, BigDecimal availableCardLimit) {
        crdCreditCard.setCurrentDebt(currentDebt);
        crdCreditCard.setAvailableCardLimit(availableCardLimit);
        crdCreditCard = crdCreditCardEntityService.save(crdCreditCard);
        return crdCreditCard;
    }

    private static void validateCardLimit(BigDecimal availableCardLimit) {
        if (availableCardLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new GenBusinessException(CrdErrorMessage.INSUFFICIENT_CREDIT_CARD_LIMIT);
        }
    }

    private CrdCreditCard getCrdCreditCard(CrdCreditCardSpendDto crdCreditCardSpendDto) {
        CrdCreditCard crdCreditCard = crdCreditCardEntityService.findByCardNoAndCvvNoAndExpireDateAndStatusType(crdCreditCardSpendDto.getCardNo(), crdCreditCardSpendDto.getCvvNo(), crdCreditCardSpendDto.getExpireDate());
        return crdCreditCard;
    }

    private void validateCreditCard(CrdCreditCard crdCreditCard) {
        if (crdCreditCard == null) {
            throw new GenBusinessException(CrdErrorMessage.INVALID_CREDIT_CARD);
        }
        if (crdCreditCard.getExpireDate().before(new Date())) {
            throw new GenBusinessException(CrdErrorMessage.CREDIT_CARD_EXPIRED);
        }
    }

    public CrdCreditCardActivityDto refund(Long activityId) {
        CrdCreditCardActivity crdCreditOldCardActivity = crdCreditCardActivityEntityService.getByIdWithControl(activityId);
        BigDecimal amount = crdCreditOldCardActivity.getAmount();

        CrdCreditCard crdCreditCard = updateCreditCardForRefund(crdCreditOldCardActivity, amount);

        CrdCreditCardActivity crdCreditCardActivity = crdCreditCardActivityForRefund(crdCreditOldCardActivity, amount, crdCreditCard);

        CrdCreditCardActivityDto crdCreditCardActivityDto = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDto(crdCreditCardActivity);
        return crdCreditCardActivityDto;
    }

    private CrdCreditCardActivity crdCreditCardActivityForRefund(CrdCreditCardActivity crdCreditOldCardActivity, BigDecimal amount, CrdCreditCard crdCreditCard) {
        CrdCreditCardActivity crdCreditCardActivity = new CrdCreditCardActivity();
        crdCreditCardActivity.setAmount(amount);
        crdCreditCardActivity.setCrdCreditCardId(crdCreditCard.getId());
        crdCreditCardActivity.setDescription("REFUND" + crdCreditOldCardActivity.getDescription());
        crdCreditCardActivity.setCrdCreditCardActivityType(CrdCreditCardActivityType.REFUND);
        crdCreditCardActivity.setTransactionDate(new Date());
        crdCreditCardActivity = crdCreditCardActivityEntityService.save(crdCreditCardActivity);
        return crdCreditCardActivity;
    }

    private CrdCreditCard updateCreditCardForRefund(CrdCreditCardActivity crdCreditOldCardActivity, BigDecimal amount) {
        CrdCreditCard crdCreditCard = crdCreditCardEntityService.getByIdWithControl(crdCreditOldCardActivity.getCrdCreditCardId());

        crdCreditCard = addLimitToCard(crdCreditCard, amount);
        return crdCreditCard;
    }

    private CrdCreditCard addLimitToCard(CrdCreditCard crdCreditCard, BigDecimal amount) {
        BigDecimal currentDebt = crdCreditCard.getCurrentDebt().subtract(amount);
        BigDecimal currentAvailableCardLimit = crdCreditCard.getAvailableCardLimit().add(amount);
        crdCreditCard.setCurrentDebt(currentDebt);
        crdCreditCard.setAvailableCardLimit(currentAvailableCardLimit);
        crdCreditCard = crdCreditCardEntityService.save(crdCreditCard);
        return crdCreditCard;
    }

    public CrdCreditCardActivityDto payment(CrdCreditCardPaymentDto crdCreditCardPaymentDto) {
        BigDecimal amount = crdCreditCardPaymentDto.getAmount();

        CrdCreditCard crdCreditCard = crdCreditCardEntityService.getByIdWithControl(crdCreditCardPaymentDto.getCrdCreditCardId());

        addLimitToCard(crdCreditCard, amount);

        CrdCreditCardActivity crdCreditCardActivity = crdCreditCardActivityForPayment(crdCreditCard, amount);
        CrdCreditCardActivityDto crdCreditCardActivityDto = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDto(crdCreditCardActivity);
        return crdCreditCardActivityDto;
    }

    private CrdCreditCardActivity crdCreditCardActivityForPayment(CrdCreditCard crdCreditCard, BigDecimal amount) {
        CrdCreditCardActivity crdCreditCardActivity = new CrdCreditCardActivity();
        crdCreditCardActivity.setAmount(amount);
        crdCreditCardActivity.setCrdCreditCardId(crdCreditCard.getId());
        crdCreditCardActivity.setDescription("PAYMENT");
        crdCreditCardActivity.setCrdCreditCardActivityType(CrdCreditCardActivityType.PAYMENT);
        crdCreditCardActivity.setTransactionDate(new Date());
        crdCreditCardActivity = crdCreditCardActivityEntityService.save(crdCreditCardActivity);
        return crdCreditCardActivity;
    }

    public List<CrdCreditCardActivityDto> findAllActivities(Long id, Date startDate, Date endDate) {

        List<CrdCreditCardActivity> crdCreditCardActivityList = crdCreditCardActivityEntityService
                .findAllByCrdCreditCardIdAndTransactionDateBetween(id, startDate, endDate);

        List<CrdCreditCardActivityDto> result = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDtoList(crdCreditCardActivityList);

        return result;
    }

    public List<CrdCreditCardActivityDto> findAllActivities(Long id, Date startDate, Date endDate, Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {

        List<CrdCreditCardActivity> crdCreditCardActivityList = crdCreditCardActivityEntityService
                .findAllByCrdCreditCardIdAndTransactionDateBetween(id, startDate, endDate, pageOptional, sizeOptional);

        List<CrdCreditCardActivityDto> result = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDtoList(crdCreditCardActivityList);

        return result;
    }


    public CrdCreditCardDetailsDto statement(Long id) {
        CrdCreditCard crdCreditCard = crdCreditCardEntityService.getByIdWithControl(id);
        Date termEndDate = crdCreditCard.getCutoffDate();
        Long crdCreditCardId = crdCreditCard.getId();
        LocalDate termEndDateLocal = DateUtil.convertToLocalDate(termEndDate);

        LocalDate termStartDateLocal = termEndDateLocal.minusMonths(1);
        Date termStartDate = DateUtil.convertToDate(termStartDateLocal);

        CrdCreditCardDetailsDto crdCreditCardDetailsDto = crdCreditCardEntityService.getCreditCardDetails(crdCreditCardId);
        List<CrdCreditCardActivity> crdCreditCardActivityList = crdCreditCardActivityEntityService.findAllByCrdCreditCardIdAndTransactionDateBetween(crdCreditCardId, termStartDate, termEndDate);
        List<CrdCreditCardActivityDto> crdCreditCardActivityDtoList = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDtoList(crdCreditCardActivityList);
        crdCreditCardDetailsDto.setCrdCreditCardActivityDtoList(crdCreditCardActivityDtoList);
        return crdCreditCardDetailsDto;

    }
}