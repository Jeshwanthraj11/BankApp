package com.raj.Bank_App.repository;

import com.raj.Bank_App.entity.CusAndAccRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CusAccRelRepository extends JpaRepository<CusAndAccRel,Long> {
}
