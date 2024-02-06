package org.example.api.domain.userorder.controller.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api.domain.userorder.business.UserOrderBusiness;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;

import javax.security.auth.spi.LoginModule;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserOrderRequest {

    //주문

    //특정 사용자가 특정 메뉴를 주문

    //로그인 된 세션의 사용자

    //주문한 메뉴 리스트

    @NotNull
    private List<Long> storeMenuIdList;


}
