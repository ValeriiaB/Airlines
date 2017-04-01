package com.example.service;


import com.example.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UsersRepository extends CrudRepository<Users,Long>{
    @Modifying
    @Transactional
    @Query("update Users u set u.bonuses = ?1 where u.idUser = ?2")
    void setFixedBonus(Float bonus, Long id);
}
