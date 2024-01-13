package org.example.db.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.db.user.enums.UserStatus;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long>{
//참고할 Entity(클래스)와 오토인크리먼트(@Id 어노테이션을 가진)변수의 타입 입력

    // select * from user where id = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    //select = from user where email = ? and password = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderedByIdDesc(String email, String password, UserStatus status);


}
