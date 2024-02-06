package org.example.db.storeuser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.db.BaseEntity;
import org.hibernate.annotations.Columns;

import java.time.LocalDateTime;

@Data @Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store_user")
public class StoreUserEntity extends BaseEntity{

    @Column(nullable= false)
    private Long storeId;

    @Column(length = 100, nullable= false)
    private String email;

    @Column(length = 100, nullable= false)
    private String password;

    @Column(length = 50 ,nullable= false)
    private String status;

    @Column(length = 50, nullable= false)
    private String role;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;
}
