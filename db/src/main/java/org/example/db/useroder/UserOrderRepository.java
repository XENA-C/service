package org.example.db.useroder;

import org.example.db.useroder.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {

    //특정 유저의 모든 주문 가져오기
    //select * from user_order where user_id= ? and status in (??....) order by id desc
    List<UserOrderEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);


    List<UserOrderEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> statusList);


    //특정 주문 가져오기: 주문 아이디, 주문 상태, 유저 아이디
    //select * from user_order where id = ? and status = ? and user_id = ?
    Optional<UserOrderEntity> findAllByIdAndStatusAndUserId(Long id, UserOrderStatus status, Long userId);

    Optional<UserOrderEntity> findAllByIdAndUserId(Long id, Long userId);
}
