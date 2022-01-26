package com.iesFrancisco.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesFrancisco.captura.Model.Visita;

@Repository
public interface VisitaRepository extends JpaRepository<Visita,Long> {
	
	//Aqui van m�todos que no est�n en el CRUD b�sico
}

