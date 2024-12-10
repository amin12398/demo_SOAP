package com.example.demo.repositories;

import com.example.demo.entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChambreRepository extends JpaRepository<Chambre , Long> {
}
