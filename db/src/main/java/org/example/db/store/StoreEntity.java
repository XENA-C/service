package org.example.db.store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.db.BaseEntity;
import org.example.db.store.enums.StoreCategory;
import org.example.db.store.enums.StoreStatus;

import java.lang.annotation.Target;
import java.math.BigDecimal;

@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true) //부모클래스 포함
@SuperBuilder
@Entity @Table(name="store")
public class StoreEntity extends BaseEntity {

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    private double star;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    @Column(length = 11, scale = 4, nullable = false)
    private BigDecimal minimumAmount;

    @Column(length = 11, scale = 4, nullable = false)
    private BigDecimal minimumDeliveryAmount;

    @Column(length = 20)
    private String phoneNumber;

}
