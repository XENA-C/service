package org.example.db.userordermenu;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.db.BaseEntity;
import org.example.db.userordermenu.enums.OrderMenuStatus;

@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder @Entity
@Table(name = "user_order_menu")
public class OrderMenuEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userOrderId; // 1:n

    @Column(nullable = false)
    private Long storeMenuId; // 1:n

    //메뉴 누락 혹은 취소 --> 상태 정보
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderMenuStatus status;


}
