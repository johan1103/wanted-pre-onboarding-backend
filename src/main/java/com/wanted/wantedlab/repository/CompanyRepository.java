package com.wanted.wantedlab.repository;

import com.wanted.wantedlab.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
