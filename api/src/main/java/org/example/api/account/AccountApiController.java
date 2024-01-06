package org.example.api.account;

import lombok.RequiredArgsConstructor;
import org.example.api.account.model.AccountMeResponse;
import org.example.db.account.AccountEntity;
import org.example.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountApiController {

    private final AccountRepository accountRepository;
    //Spring은 자신과 동일한 경로에 속한 패키지 내 Bean을 등록하여 사용
    //다른 패키지에 속한 bean(객체)는 사용 X --> 다른 패키지의 Bean도 사용할 수 있도록
    // @EntityScan(basePackages = "org.example.db") @EnableJpaRepositories(basePackages = "org.example.db")

    @GetMapping("/me")
    public AccountMeResponse me(){

        return AccountMeResponse.builder()
                .name("Xen")
                .email("xena@gmail.com")
                .registeredAt(LocalDateTime.now())
                .build();

        //void save(){
//      var account =  AccountEntity.builder().build();
//        accountRepository.save(account); //accountRepository-->다른 패키지에 속함
//        //계정 변수 저장
    }
}
