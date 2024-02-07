package org.example.db.store;

import org.example.db.store.enums.StoreCategory;
import org.example.db.store.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long>{

    //id + status (유효한 스토어)
    //select * from store where id = ? and status = ? order by id desc limit 1

    Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus status);

    //유효한 스토어 리스트
    //select * from store where status = ? where order by id desc
    List<StoreEntity> findAllByStatusOrderedByIdDesc(StoreStatus status);

    //유효한 특정 카테고리의 스토어 리스트
    List<StoreEntity> findAllByStatusAndCategoryOrderByStarDesc(StoreStatus status, StoreCategory storeCategory);

    //select * from store where name = ? and status = ? order by id desc limit 1
    Optional<StoreEntity> findFirstByNameAndStatusOrderByIdDesc(String name, StoreStatus status);


}
