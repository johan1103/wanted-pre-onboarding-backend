package com.wanted.wantedlab.repository;

import com.wanted.wantedlab.entity.DeletedApplicationLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeletedApplicationLetterRepository extends JpaRepository<DeletedApplicationLetter,Long> {
}
