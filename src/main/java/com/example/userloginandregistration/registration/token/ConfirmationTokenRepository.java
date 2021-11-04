package com.example.userloginandregistration.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {

    @Query("SELECT t from ConfirmationToken t WHERE t.token=?1")
    Optional<ConfirmationToken> findByToken(String token);

}
