package com.finesec.pictogramas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.repository.IUsuariosRepository;
import com.finesec.pictogramas.service.IUsuarioService;

@Service
@Component

public class UsuarioServiceImpl implements IUsuarioService {
	@Autowired
	private IUsuariosRepository repo;

	@Override
	public void insertarUsuario(Usuarios nuevo) {
		repo.save(nuevo);
		// TODO Auto-generated method stub

	}

	@Override
	public List<Usuarios> ListarUsuario() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}


	@Override
	public Usuarios findByIdUsuario(int idUsuario) {
		return repo.findByIdUsuario(idUsuario);
	}

	// Método para inicio de sesión
	@Override
	public Usuarios findByEmail(String emeal) {
		// TODO Auto-generated method stub
		return repo.findByEmail(emeal);
	}

	@Override
	public void eliminarUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		repo.deleteById(idUsuario);
	}

	@Override
	public Usuarios buscar(String nombres) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuarios> buscarUsuarios(String nombres, Integer rolId) {
		if (nombres == null) {
	        nombres = "";
	    }

	    if (rolId == null) {
	        return repo.findByNombres(nombres);
	    }

	    return repo.findByNombresAndRol_IdRol(nombres, rolId);
	}

	@Override
	public Usuarios findByCi(String ci) {
		// TODO Auto-generated method stub
		return repo.findByCi(ci);
	}
	
	@Override
	public void actualizarUsuario(int idUsuario, Usuarios usuarioActualizado) {
		  
		System.out.println(idUsuario);
		System.out.println(usuarioActualizado);
	    // Buscar el usuario existente por su ID
	    Usuarios usuarioExistente = repo.findById(idUsuario).orElse(null);
	  
	    
	    // Verificar si se encontró el usuario
	    if (usuarioExistente != null) {
	        // Actualizar los campos del usuario existente con los del usuario actualizado
	        usuarioExistente.setNombres(usuarioActualizado.getNombres());
	        usuarioExistente.setApellidos(usuarioActualizado.getApellidos());
	        usuarioExistente.setDireccion(usuarioActualizado.getDireccion());
	        usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
	        usuarioExistente.setEstadoRegistro(usuarioActualizado.getEstadoRegistro());
	        usuarioExistente.setIdpreguntaUno(usuarioActualizado.getIdpreguntaUno());
	        usuarioExistente.setIdpreguntaDos(usuarioActualizado.getIdpreguntaDos());
	        usuarioExistente.setIdpreguntaTres(usuarioActualizado.getIdpreguntaTres());
	        usuarioExistente.setPreguntaUno(usuarioActualizado.getPreguntaUno());
	        usuarioExistente.setPreguntaDos(usuarioActualizado.getPreguntaDos());
	        usuarioExistente.setPreguntaTres(usuarioActualizado.getPreguntaTres());
	        usuarioExistente.setRol(usuarioActualizado.getRol());
	        usuarioExistente.setRestriccionRol(usuarioActualizado.getRestriccionRol());
	        usuarioExistente.setRestriccionPictograma(usuarioActualizado.getRestriccionPictograma());
	        usuarioExistente.setRestriccionCategoria(usuarioActualizado.getRestriccionCategoria());
	        usuarioExistente.setRestriccionUsuario(usuarioActualizado.getRestriccionUsuario());
	        
	        // Guardar los cambios en la base de datos
	         repo.save(usuarioExistente);
	    } else {
	        // Manejar el caso en el que el usuario no existe
	    }
	}


}
