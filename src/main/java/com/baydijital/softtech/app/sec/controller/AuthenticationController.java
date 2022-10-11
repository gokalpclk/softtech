package com.baydijital.softtech.app.sec.controller;

import com.baydijital.softtech.app.cus.dto.CusCustomerDto;
import com.baydijital.softtech.app.cus.dto.CusCustomerSaveRequestDto;
import com.baydijital.softtech.app.gen.dto.RestResponse;
import com.baydijital.softtech.app.sec.dto.SecLoginRequestDto;
import com.baydijital.softtech.app.sec.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gokalp on 9/3/22
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(
            tags = "Authentication Controller",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Auth Login Sample",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CusCustomerSaveRequestDto.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Login",
                                                    value = "{\n" +
                                                            "  \"identityNo\": 22222222222,\n" +
                                                            "  \"password\": \"222\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody SecLoginRequestDto secLoginRequestDto){

        String token = authenticationService.login(secLoginRequestDto);

        return ResponseEntity.ok(RestResponse.of(token));
    }

    @Operation(tags = "Authentication Controller")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomerDto cusCustomerDto =authenticationService.register(cusCustomerSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }
}
