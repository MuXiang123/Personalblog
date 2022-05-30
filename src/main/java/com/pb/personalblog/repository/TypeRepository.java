package com.pb.personalblog.repository;

import com.pb.personalblog.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);

    //根据每个Type包含blog大小排序查询

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
