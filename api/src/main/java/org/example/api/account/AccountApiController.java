package org.example.api.account;

import lombok.RequiredArgsConstructor;
import org.example.api.account.model.AccountMeResponse;
import org.example.api.common.api.Api;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.db.account.AccountEntity;
import org.example.db.account.AccountRepository;
import org.springframework.http.ResponseEntity;
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
    public Api<AccountMeResponse> me(){  //Api는 항상 성공 Or 실패 ---> 성공만
        var response = AccountMeResponse.builder()
                .name("Xen")
                .email("xena@gmail.com")
                .registeredAt(LocalDateTime.now())
                .build();

        var str = "안녕하세요";
        var age = 0;
        try {
            Integer.parseInt(str);
        }catch (Exception e){
            throw new ApiException(ErrorCode.SERVER_ERROR, e, "사용자 Me 호출 시 에러 발생");
        }

        return Api.OK(response); //---> 예외는 ExceptionHandler 에서(코드분리)

//        try{
//            age = Integer.parseInt(str); //문자를 ParseInt에 넣으면 예외발생(NumberFormatException)
//            return ResponseEntity
//                    .status(200)
//                    .body(Api.OK(response));
//
//        }catch (Exception e){
//            return ResponseEntity
//                    .status(500)
//                    .body(Api.ERROR(ErrorCode.SERVER_ERROR)); //return Type
        }


        //void save(){
//      var account =  AccountEntity.builder().build();
//        accountRepository.save(account); //accountRepository-->다른 패키지에 속함
//        //계정 변수 저장
    }

