package com.baydijital.softtech.app.cus.controller;

import com.baydijital.softtech.app.cus.converter.CusCustomerMapper;
import com.baydijital.softtech.app.cus.dto.CusCustomerDto;
import com.baydijital.softtech.app.cus.dto.CusCustomerSaveRequestDto;
import com.baydijital.softtech.app.cus.dto.CusCustomerUpdateRequestDto;
import com.baydijital.softtech.app.cus.service.CusCustomerService;
import com.baydijital.softtech.app.gen.dto.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gokalp on 7/19/22
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CusCustomerController {
    private final CusCustomerService cusCustomerService;

    @Operation(
            tags = "Customer Controller",
            description = "Creates new customer.",
            summary = "Creates new customer.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Customers",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CusCustomerSaveRequestDto.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "New Foreign Customer",
                                                    summary = "New Foreign Customer Example",
                                                    description = "Complete request with all available fields for foreign customer",
                                                    value = "{\n" +
                                                            "  \"name\": \"John\",\n" +
                                                            "  \"surname\": \"Simit\",\n" +
                                                            "  \"identityNo\": 91111111111,\n" +
                                                            "  \"password\": \"John.Simith\"\n" +
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "New Customer",
                                                    summary = "New Customer Example",
                                                    description = "Complete request with all available fields",
                                                    value = "{\n" +
                                                            "  \"name\": \"Gökalp\",\n" +
                                                            "  \"surname\": \"Çelik\",\n" +
                                                            "  \"identityNo\": 11111111111,\n" +
                                                            "  \"password\": \"Gökalp.Çelik\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PostMapping
    public ResponseEntity save(@RequestBody CusCustomerSaveRequestDto cusCustomerSaveRequestDto){
        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);
        EntityModel entityModel = EntityModel.of(cusCustomerDto);
        WebMvcLinkBuilder linkGet = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(cusCustomerDto.getId()));
        WebMvcLinkBuilder linkDelete = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).delete(cusCustomerDto.getId()));
        entityModel.add(linkGet.withRel("find-by-id"));
        entityModel.add(linkDelete.withRel("delete"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);
        return ResponseEntity.ok(RestResponse.of(mappingJacksonValue));
    }

    @Operation(tags = "Customer Controller")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        CusCustomerDto cusCustomerDto = cusCustomerService.findById(id);
        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }

    @Operation(
            tags = "Customer Controller",
            description = "Gets all customer.",
            summary = "All customer")
    @GetMapping
    public ResponseEntity findAll(){
        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();
        return ResponseEntity.ok(RestResponse.of(cusCustomerDtoList));
    }

    @Operation(tags = "Customer Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        cusCustomerService.delete(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "Customer Controller")
    @PutMapping
    public ResponseEntity update(@RequestBody CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto) {
        CusCustomerDto cusCustomerDto = cusCustomerService.update(cusCustomerUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }
}
