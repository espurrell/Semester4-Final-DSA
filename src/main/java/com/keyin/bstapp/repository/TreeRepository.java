package com.keyin.bstapp.repository;

import com.keyin.bstapp.entity.TreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<TreeEntity, Long> {
}