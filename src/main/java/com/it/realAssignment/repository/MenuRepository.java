package com.it.realAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.realAssignment.entity.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
}
