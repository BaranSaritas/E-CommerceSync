package com.E_CommerceSync.E_CommerceSync.repository.security;

import com.E_CommerceSync.E_CommerceSync.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(@Param("email") String email);

    boolean existsByEmailIgnoreCase(@Param("email") String email);

    boolean existsByEmail(@Param("email")  String email);
}
