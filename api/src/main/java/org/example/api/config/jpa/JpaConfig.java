package org.example.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration //Bean 등록을 위한 어노테이션
@EntityScan(basePackages = "org.example.db")
@EnableJpaRepositories(basePackages = "org.example.db")
//외부패키지의 클래스를 Bean으로 사용할 수 있도록 경로지정
public class JpaConfig {
}
