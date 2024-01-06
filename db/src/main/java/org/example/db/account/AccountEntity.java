package org.example.db.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.example.db.BaseEntity;

@Data
@Entity @Table(name = "account")
@SuperBuilder //부모로부터 상속받은 변수도 포함
@EqualsAndHashCode(callSuper = true) //객체 비교 시 사용
// call super = true: 부모클래스의 값까지 포함
// call super = false : 현재 entity 의 값만 비교
public class AccountEntity extends BaseEntity{


}
