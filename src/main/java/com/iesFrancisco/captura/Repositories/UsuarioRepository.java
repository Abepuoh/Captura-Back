package com.iesFrancisco.captura.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesFrancisco.captura.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	
	//Aqui van m�todos que no est�n en el CRUD b�sico
}