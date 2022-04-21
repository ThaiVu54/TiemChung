package com.example.tiemchungbe.repository;

import com.example.tiemchungbe.model.entity.AccountRole;
import com.example.tiemchungbe.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select * from role",nativeQuery = true)
    List<Role> findAllRole();

    @Modifying
    @Query(value = "insert into account_role(account_id,role_id) value (?1,?2)",nativeQuery = true)
    void setDefaultRole(int accountId , Integer roleId);
}
