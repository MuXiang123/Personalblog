package com.pb.personalblog.repository;

import com.pb.personalblog.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);
}
