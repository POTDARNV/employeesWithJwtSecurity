package com.example.crudOperations.demoCrudOperation.repo;

import com.example.crudOperations.demoCrudOperation.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Users existsByEmail(String email);
    Users existsByPhoneNumber(String phoneNumber);
    Users existsByUsername(String username);
}
