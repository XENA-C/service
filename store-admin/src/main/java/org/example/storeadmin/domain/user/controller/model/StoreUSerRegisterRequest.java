package org.example.storeadmin.domain.user.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.db.storeuser.enums.StoreUserRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreUSerRegisterRequest {

    @NotBlank
    private String storeName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private StoreUserRole role;


}
