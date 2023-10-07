package com.wanted.wantedlab.repository;

import com.wanted.wantedlab.entity.ApplicationLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationLetterRepository extends JpaRepository<ApplicationLetter,Long> {
}
