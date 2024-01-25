package org.example.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.Converter;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.user.contoller.model.UserRegisterRequest;
import org.example.api.domain.user.contoller.model.UserResponse;
import org.example.db.user.UserEntity;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request){
           //UserRegisterRequest--> UserEntity
      return Optional.ofNullable(request)
              .map(it->{
                  //request 유효 --> map을 통해 데이터 바꿈(to entity).
                  // null--> Exception
                  return UserEntity.builder()
                          .name(request.getName())
                          .email(request.getEmail())
                          .password(request.getPassword())
                          .address(request.getAddress())
                          .build();
                })
              .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
                //exceptionHandler --> apiException
    }


    public UserResponse toResponse(UserEntity userEntity){
        //UserEntity-->UserResponse
        return Optional.ofNullable(userEntity) //UserEntity 가 null 이면 nullPoint Check
                .map(it -> { // to response
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .status(userEntity.getStatus())
                            .address(userEntity.getAddress())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();
                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));

    }
}
