package org.example.api.domain.storemenu.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.db.storeMenu.enums.StoreMenuStatus;

import java.math.BigDecimal;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class StoreMenuResponse {

    private Long id; //등록된 메뉴에 id필요

    private Long storeId;
    private String name;
    private BigDecimal amount;
    private StoreMenuStatus status;
    private String thumbnailUrl;
    private int linkCount;
    private int sequence;


}
