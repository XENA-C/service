package org.example.db.userordermenu;

import org.example.db.userordermenu.enums.OrderMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenuEntity, Long>{

    //select * from user_order_menu where user_order_id = ? status = ?
    List<OrderMenuEntity> findAllByUserOrderIdAndStatus(Long userOrderId, OrderMenuStatus status);
                                                    // 주문의 id, 해당 주문의 메뉴 List
}
