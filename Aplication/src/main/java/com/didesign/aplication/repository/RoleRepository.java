package com.didesign.aplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.didesign.aplication.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
