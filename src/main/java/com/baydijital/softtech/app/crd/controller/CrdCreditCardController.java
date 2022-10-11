package com.baydijital.softtech.app.crd.controller;

import com.baydijital.softtech.app.crd.dto.*;
import com.baydijital.softtech.app.crd.service.CrdCreditCardService;
import com.baydijital.softtech.app.gen.dto.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Gokalp on 8/3/22
 */
@RestController
@RequestMapping("api/v1/credit-cards")
@RequiredArgsConstructor
@CrossOrigin
public class CrdCreditCardController {
    private final CrdCreditCardService crdCreditCardService;

    @Operation(tags = "Credit Card Controller")
    @PostMapping
    public ResponseEntity save(@RequestBody CrdCreditCardSaveRequestDto crdCreditCardSaveRequestDto) {
        CrdCreditCardResponseDto crdCreditCardResponseDto = crdCreditCardService.saveCrdCreditCard(crdCreditCardSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(crdCreditCardResponseDto));
    }

    @Operation(tags = "Credit Card Controller")
    @GetMapping
    public ResponseEntity findAll() {
        List<CrdCreditCardResponseDto> creditCardResponseDtoList = crdCreditCardService.findAll();
        return ResponseEntity.ok(RestResponse.of(creditCardResponseDtoList));
    }

    @Operation(tags = "Credit Card Controller")
    @GetMapping("/active")
    public ResponseEntity findAllActiveCrdCreditCard() {
        List<CrdCreditCardResponseDto> creditCardResponseDtoList = crdCreditCardService.findAllActiveCrdCreditCard();
        return ResponseEntity.ok(RestResponse.of(creditCardResponseDtoList));
    }

    @Operation(tags = "Credit Card Controller")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        CrdCreditCardResponseDto crdCreditCardResponseDto = crdCreditCardService.findById(id);
        return ResponseEntity.ok(RestResponse.of(crdCreditCardResponseDto));
    }

    @Operation(tags = "Credit Card Controller")
    @PatchMapping("/cancel/{id}")
    public ResponseEntity cancel(@PathVariable Long id) {
        crdCreditCardService.cancel(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "Credit Card Controller")
    @PostMapping("/spend")
    public ResponseEntity spend(@RequestBody CrdCreditCardSpendDto crdCreditCardSpendDto) {
        CrdCreditCardActivityDto crdCreditCardActivityResponseDto = crdCreditCardService.spend(crdCreditCardSpendDto);
        return ResponseEntity.ok(RestResponse.of(crdCreditCardActivityResponseDto));
    }

    @Operation(tags = "Credit Card Controller")
    @PostMapping("/refund/{activityId}")
    public ResponseEntity refund(@PathVariable Long activityId) {
        CrdCreditCardActivityDto crdCreditCardActivityDto = crdCreditCardService.refund(activityId);
        return ResponseEntity.ok(RestResponse.of(crdCreditCardActivityDto));
    }

    @Operation(tags = "Credit Card Controller")
    @PostMapping("/payment")
    public ResponseEntity payment(@RequestBody CrdCreditCardPaymentDto crdCreditCardPaymentDto) {
        CrdCreditCardActivityDto crdCreditCardActivityDto = crdCreditCardService.payment(crdCreditCardPaymentDto);
        return ResponseEntity.ok(RestResponse.of(crdCreditCardActivityDto));
    }

    @Operation(tags = "Credit Card Controller")
    @GetMapping("/{id}/activities")
    public ResponseEntity findAllActivities(
            @PathVariable Long id,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            Optional<Integer> pageOptional,
            Optional<Integer> sizeOptional
    ) {
        List<CrdCreditCardActivityDto> crdCreditCardActivityDtoList = crdCreditCardService.findAllActivities(id, startDate, endDate, pageOptional, sizeOptional);
        return ResponseEntity.ok(RestResponse.of(crdCreditCardActivityDtoList));
    }

    @Operation(tags = "Credit Card Controller")
    @GetMapping("/{id}/statements")
    public ResponseEntity statement(@PathVariable Long id){
        CrdCreditCardDetailsDto crdCreditCardDetailsDto = crdCreditCardService.statement(id);
        return ResponseEntity.ok(RestResponse.of(crdCreditCardDetailsDto));
    }
}
