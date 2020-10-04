package com.ppphuang.web.dao;

import com.ppphuang.web.beans.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);

    @Query("select t from t_type t")
    Page<Type> findTop(Pageable pageable);
}
