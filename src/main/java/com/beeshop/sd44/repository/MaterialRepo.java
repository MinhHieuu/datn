package com.beeshop.sd44.repository;

import com.beeshop.sd44.entity.Marterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MaterialRepo extends JpaRepository<Marterial, UUID> {
    Marterial save(Marterial marterial);
    Boolean existsByName(String name);
}
