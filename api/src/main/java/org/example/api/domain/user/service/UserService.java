package org.example.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.error.UserErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.db.user.UserEntity;
import org.example.db.user.UserRepository;
import org.example.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
* User 도메인 로직을 처리하는 서비스
* */
@Service
@RequiredArgsConstructor
public class UserService { //자신의 도메인 로직만 처리

 private final UserRepository userRepository;

 public UserEntity register(UserEntity userEntity){  //UserEntity 전달 받아 Repository 에 UserEntity 저장

  return Optional.ofNullable(userEntity)
          .map(it ->{ //넘어온 UserEntity 가 유효하면 로직 처리(가입)
            userEntity.setStatus(UserStatus.REGISTERED);  // 등록 상태 추가
            userEntity.setRegisteredAt(LocalDateTime.now()); //등록 시간 추가
            return userRepository.save(userEntity); // 새로운 userEntity
          }) // 저장된 UserEntity 를 Business 로직에서 다시 response
          .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "User Entity Null"));
    }

    public UserEntity login(
            String email,
            String password
    ){
       var entity = getUserWithThrow(email, password);
       return entity;
    }


    public UserEntity getUserWithThrow(
            String email,
            String password
    ){
       return userRepository.findFirstByEmailAndPasswordAndStatusOrderedByIdDesc(
             email, password, UserStatus.REGISTERED
     ).orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND, ""));
 }


    public UserEntity getUserWithThrow( //withThrow : 없으면 예외발생
            Long userId //메서드 명은 같지만 매개변수가 다름--> overloading
    ){
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(
                userId,
                UserStatus.REGISTERED
        ).orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND, ""));
    }



}


