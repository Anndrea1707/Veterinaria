package com.parcial.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial.app.models.entity.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
	
	

}
