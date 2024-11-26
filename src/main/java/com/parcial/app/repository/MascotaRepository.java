package com.parcial.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parcial.app.models.entity.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
