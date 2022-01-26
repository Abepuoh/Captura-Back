package com.iesFrancisco.captura.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesFrancisco.captura.Model.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro,Long> {
	
	//Aqui van m�todos que no est�n en el CRUD b�sico
}
