package org.example.api.domain.userorder.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api.domain.store.controller.model.StoreResponse;
import org.example.api.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderDetailResponse {

    private UserOrderResponse userOrderResponse; //주문 건 정보

    private StoreResponse storeResponse; //주문한 가게 정보

    private List<StoreMenuResponse> storeMenuResponseList; //주문한 메뉴 정보




}
