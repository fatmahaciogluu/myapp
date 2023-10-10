package com.project.myapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

    RefreshToken findByUserId(Long userId);

}
