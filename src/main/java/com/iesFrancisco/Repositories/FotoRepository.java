package com.iesFrancisco.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesFrancisco.captura.Model.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto,Long> {
	
	//Aqui van m�todos que no est�n en el CRUD b�sico
}