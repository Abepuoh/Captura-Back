package com.iesFrancisco.captura.Controllers;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.iesFrancisco.captura.Model.Obra;
import com.iesFrancisco.captura.Services.ObraService;


@RestController
@RequestMapping("/obra")
public class ObraController {

	@Autowired
	ObraService service;
	
	/**
	 * M�todo del controlador Crea una obra
	 * @param obra
	 * @return ResponseEntity
	 */ 
	@PostMapping("/guardar")
	public ResponseEntity<?> create(@RequestBody Obra obra) throws ResponseStatusException{
			try {
				return ResponseEntity.status(HttpStatus.CREATED).body(service.creaObra(obra));
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La obra no se ha podido crear", e);
			}
	}
	
	/**
	 * Trae una obra por su Id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Obra> listarPorId(@PathVariable(value ="id") Long id) throws ResponseStatusException  {
		try {
			//Obra obra = service.getObraById(id);
			return ResponseEntity.status(HttpStatus.OK).body(service.getObraById(id));
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La obra no se ha podido encontrar", e);
		}

	}
	/**
	 * Controlador que actualiza por ID una obra que ya esta creada
	 * @param obraDetails
	 * @param id
	 * @return obra
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Obra> update(@RequestBody Obra obraDetails, @PathVariable(value="id")Long id) throws ResponseStatusException{
		try {
			Optional<Obra> obra = Optional.of(service.getObraById(id));
			if(!obra.isPresent()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La obra no se ha podido encontrar");
			}else {
				BeanUtils.copyProperties(obraDetails, obra.get());// Copiaria todo el objeto, aqui no interesa por el Id que no lo queremos actualizar
				return ResponseEntity.status(HttpStatus.CREATED).body(service.actualizaObra(obra.get()));
			}

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La obra no se ha podido encontrar", e);
		}

					
	}
	/**
	 * Metodo que borra una obra
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Obra> delete(@PathVariable(value="id")Long id) throws ResponseStatusException{
			try {
				Optional<Obra> obra = Optional.of(service.getObraById(id));
				if(obra.isPresent()) {
					service.deleteObraById(id);
					return ResponseEntity.ok().build();
				}else {
					return ResponseEntity.notFound().build();
				}

			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La obra no se ha podido borrar", e);
			}

	}	
	/**
	 * Metodo que devuelve todas las obras
	 * @return Lista de Obras
	 * @throws ResponseStatusException
	 */
	@GetMapping
	public ResponseEntity<List<Obra>> readAll() throws ResponseStatusException{
		try {
			List<Obra> obras = service.getAllObras();
			return new ResponseEntity<List<Obra>>(obras,new HttpHeaders(),HttpStatus.OK);
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Obras no encontradas", e);	
		}


	}
	/**
	 * Metodo que devuelve una obra a traves del nombre de la mismas
	 * @param nombre de la obra
	 * @return una obra
	 * @throws ResponseStatusException
	 */
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<Obra> listarPorNombre(@PathVariable(value ="nombre") String nombre) throws ResponseStatusException{
		if(nombre!=null) {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(service.getObraByName(nombre));
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido encontrar el nombre de la obra", e);
			}
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido encontrar el nombre de la obra");
		}
	}
	/**
	 * Metodo que busca las obras de un usuario
	 * @param id del usuario
	 * @return obras del usuario
	 * @throws ResponseStatusException
	 */
	@GetMapping("usuario/{usuarioId}")
	public ResponseEntity<List<Obra>> listarPorUsuario(@PathVariable(value ="usuarioId") Long id) throws ResponseStatusException{
		if(id!=null) {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(service.getObraByUser(id));		
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido encontrar el usuario de la obra", e);
			}

		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido encontrar el usuario de la obra");
		}

	}
	
	/**
	 * Metodo que devuelve una obra a partir de sus coordenadas
	 * @param coordenadas
	 * @return obra
	 * @throws ResponseStatusException
	 */
	@GetMapping("/coordenadas/{coordenadas}")
	public ResponseEntity<Obra> listarPorCoordenada(@PathVariable(value="coordenadas")Point2D coordenadas) throws ResponseStatusException{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getObraByLoc(coordenadas));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido encontrar las coordenadas de la obra", e);
		}	
	}
	
	
	
}
