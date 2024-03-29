package org.example.api.domain.storemenu.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor @AllArgsConstructor
public class StoreMenuRegisterRequest {  //메뉴 등록 요청

    @NotNull
    private Long storeId;

    @NotBlank
    private String name; //상품가격

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String thumbnailUrl;


}
