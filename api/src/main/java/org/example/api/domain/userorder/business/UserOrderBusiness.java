package org.example.api.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.Business;
import org.example.api.domain.store.converter.StoreConverter;
import org.example.api.domain.store.service.StoreService;
import org.example.api.domain.storemenu.converter.StoreMenuConverter;
import org.example.api.domain.storemenu.service.StoreMenuService;
import org.example.api.domain.user.contoller.model.User;
import org.example.api.domain.userorder.controller.model.UserOrderDetailResponse;
import org.example.api.domain.userorder.controller.model.UserOrderRequest;
import org.example.api.domain.userorder.controller.model.UserOrderResponse;
import org.example.api.domain.userorder.converter.UserOrderConverter;
import org.example.api.domain.userorder.service.UserOrderService;
import org.example.api.domain.userordermenu.converter.OrderMenuConverter;
import org.example.api.domain.userordermenu.service.UserOrderMenuService;
import org.example.db.store.StoreEntity;
import org.example.db.useroder.UserOrderEntity;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {

    private final UserOrderService userOrderService;

    private final UserOrderConverter userOrderConverter;

    private final StoreMenuService storeMenuService; // 메뉴 정보 불러오기

    private final StoreMenuConverter storeMenuConverter;

    private final OrderMenuConverter orderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    //  주문 -> 메뉴 -> 가게 ->
    // 1. 사용자, menuId
    // 2. userOrder 생성
    // 3. userOrderMenu 생성
    // 4. 응답 생성
    public UserOrderResponse userOrder(User user, UserOrderRequest body){

        //유효한 메뉴 여부 체크: 유효하지 않은 메뉴는 날린다(WithThrow: 에러)
        var storeMenuEntityList = body.getStoreMenuIdList()
                .stream()
                .map(storeMenuService::getStoreMenuWithThrow)
                .collect(Collectors.toList());

        //주문내역리스트 --> 사용자의 주문 엔티티
        var userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList);

        //주문
        var newUserOrderEntity = userOrderService.order(userOrderEntity);

//맵핑 (유저 오더 메뉴 만듬 )
        var OrderMenuEntityList = storeMenuEntityList.stream()
             .map(it -> {
               //menu * user order
             var OrderMenuEntity = OrderMenuConverter.toEntity(newUserOrderEntity, it);
             return OrderMenuEntity;
           })
            .collect(Collectors.toList()); //-->사용자의 주문 리스트

        OrderMenuEntityList.forEach(it -> {
            userOrderMenuService.order(it);
        });

        //response
        return userOrderConverter.toResponse(newUserOrderEntity);


    }

    public List<UserOrderDetailResponse> current(User user) {

        var userOrderEntityList = userOrderService.current(user.getId());

        //주문 한 건씩 처리:
        var userOrderDetailResponseList = userOrderEntityList.stream().map(it->{


            //사용자가 주문한 메뉴
            var OrderMenuEntityList = userOrderMenuService.getUserOrderMenu(it.getId());
            var storeMenuEntityList = OrderMenuEntityList.stream()
               .map(OrderMenuEntity-> {
                   var storeMenuEntity = storeMenuService.getStoreMenuWithThrow(OrderMenuEntity.getStoreMenuId());
                   return storeMenuEntity;
               })
               .collect(Collectors.toList());

            //어떤 가게에서 주문했는지: TODO 리팩토링 필요
            var storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStoreId());
            return UserOrderDetailResponse.builder()
                    .userOrderResponse(userOrderConverter.toResponse(it))
                    .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                    .storeResponse(storeConverter.toResponse(storeEntity))
                    .build()
                    ;

        }).collect(Collectors.toList());
        return userOrderDetailResponseList;
    }

    public List<UserOrderDetailResponse> history(User user) {

        var userOrderEntityList = userOrderService.history(user.getId());

        //주문 한 건씩 처리:
        var userOrderDetailResponseList = userOrderEntityList.stream().map(it->{


            //사용자가 주문한 메뉴
            var OrderMenuEntityList = userOrderMenuService.getUserOrderMenu(it.getId());
            var storeMenuEntityList = OrderMenuEntityList.stream()
                    .map(OrderMenuEntity-> {
                        var storeMenuEntity = storeMenuService.getStoreMenuWithThrow(OrderMenuEntity.getStoreMenuId());
                        return storeMenuEntity;
                    })
                    .collect(Collectors.toList());

            //어떤 가게에서 주문했는지: TODO 리팩토링 필요
            var storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStoreId());
            return UserOrderDetailResponse.builder()
                    .userOrderResponse(userOrderConverter.toResponse(it))
                    .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                    .storeResponse(storeConverter.toResponse(storeEntity))
                    .build()
                    ;

        }).collect(Collectors.toList());
        return userOrderDetailResponseList;
    }

    public UserOrderDetailResponse read(User user, Long orderId){
        var userOrderEntity = userOrderService.getUserOrderWithOutStatusWithThrow(orderId, user.getId());

        //사용자가 주문한 메뉴
        var OrderMenuEntityList = userOrderMenuService.getUserOrderMenu(userOrderEntity.getId());
        var storeMenuEntityList = OrderMenuEntityList.stream()
                .map(OrderMenuEntity-> {
                    var storeMenuEntity = storeMenuService.getStoreMenuWithThrow(OrderMenuEntity.getStoreMenuId());
                    return storeMenuEntity;
                })
                .collect(Collectors.toList());

        //사용자가 주문한 스토어 TODO 리팩토링 필요
        var storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStoreId());

        return UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                .storeResponse(storeConverter.toResponse(storeEntity))
                .build()
                ;
    }
}
