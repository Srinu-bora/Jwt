package com.example.credentials.repository;

// import com.example.credentials.model.CompanyUser;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.Optional;

// public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long> {
//     Optional<CompanyUser> findByUsername(String username);
//     boolean existsByCompanyId(String companyId);  // Custom query to check for existing company ID
//     boolean existsByUsername(String username);
// }

import com.example.credentials.model.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long> {
    Optional<CompanyUser> findByUsername(String username);
}
