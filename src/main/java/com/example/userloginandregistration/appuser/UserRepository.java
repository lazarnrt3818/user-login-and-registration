package com.example.userloginandregistration.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {

    @Query("select u from AppUser u where u.email=?1")
Optional<AppUser> findByEmail(String email);
}
