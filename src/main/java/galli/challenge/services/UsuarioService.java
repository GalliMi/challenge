package galli.challenge.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import  galli.challenge.repositories.UsuarioRepository;
import galli.challenge.models.Usuario;
import galli.challenge.utils.ProfileProjection;
import galli.challenge.utils.BCrypt;




@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario createUsuario(Usuario usuario) {
        usuario.setPassword(BCrypt.hashpw(usuario.getPassword(),BCrypt.gensalt()));
        return usuarioRepository.save(usuario);
    }

   
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

   
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }


   
    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario existingUsuario = usuario.get();
            existingUsuario.setNombre(usuarioDetails.getNombre());
            existingUsuario.setEmail(usuarioDetails.getEmail());
            existingUsuario.setEstadoSubscripcion(usuarioDetails.getEstadoSubscripcion());
            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }

   
    public void deleteAllUsuarios() {
        usuarioRepository.deleteAll();
    }

   
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public ProfileProjection findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    /*public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }*/

  

   
}