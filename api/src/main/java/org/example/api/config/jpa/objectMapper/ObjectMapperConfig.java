package org.example.api.config.jpa.objectMapper;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//response 형식

@Configuration
public class ObjectMapperConfig { //매핑에 관한 설정을 다루는 클래스

    @Bean //스프링부트 실행 시 objectMapper 유무 검사 후 디폴트로 생성함
    public ObjectMapper objectMapper(){ //Bean 이름은 메서드명(objectMapper)을 따름

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());

        //모르는 컬럼(json value)는 무시하고 나머지만 파싱
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //비어있는 Bean 생성방식
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);

        //날짜 관련 직렬화
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //스네이크 케이스
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

        return objectMapper;


    }

}

