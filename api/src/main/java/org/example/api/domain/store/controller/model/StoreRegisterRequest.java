package org.example.api.domain.store.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.db.store.enums.StoreCategory;

import java.math.BigDecimal;

@Data
@NoArgsConstructor @AllArgsConstructor
public class StoreRegisterRequest {

    @NotBlank //"", " ", null 모두 안됨
    private String name;

    @NotBlank
    private String address;

    @NotNull  // EnumType 으로 매핑 -> null 들어오면 안됨
    private StoreCategory storeCategory;

    @NotBlank
    private String thumbnailUrl;

    @NotNull
    private BigDecimal minimalAmount;

    @NotNull
    private BigDecimal minimalDeliveryAmount;

    @NotBlank
    private String phoneNumber;

}
