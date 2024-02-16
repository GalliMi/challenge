package galli.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import galli.challenge.services.UsuarioService;
import galli.challenge.models.Usuario;
import galli.challenge.utils.ProfileProjection;
import galli.challenge.utils.BCrypt;
import galli.challenge.utils.EstadoSubscripcion;




@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }



  
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        return usuarioService.updateUsuario(id, usuarioDetails);
    }

  
    @DeleteMapping
    public String deleteAllUsuarios() {
        usuarioService.deleteAllUsuarios();
        return "Todos los usuarios se han eliminado correctamente";
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    @GetMapping("/perfil")
    public ProfileProjection obtenerDatosUsuarioPorEmail(@RequestParam String email, String password) {
        //Usuario usuarioActual = new Usuario;
        EstadoSubscripcion estado = new EstadoSubscripcion();
        ProfileProjection usuarioActual = usuarioService.findByEmail(email);

        Optional<Usuario> optional = usuarioService.getUsuarioById(Long.parseLong(usuarioActual.getId()));
Usuario usuario = optional.get();


if(BCrypt.checkpw( password, usuario.getPassword()))
{
    try {
        estado.actualizarEstado(email,usuarioActual.getTipoSubscripcion());
         } catch (Exception e ){
             return usuarioService.findByEmail(email);
 
         }
         return usuarioService.findByEmail(email);
} else {
    return null;
}

       
       
    } 


   /*  @GetMapping("/perfil")
    public Usuario obtenerDatosUsuarioPorEmail(@RequestParam String email, String password) {
       
       Usuario usuarioActual = usuarioService.getUsuarioByEmail(email);

       String hashedPassword =BCrypt.hashpw(usuarioActual.getPassword(),BCrypt.gensalt());

System.out.println(usuarioActual.getPassword());
System.out.println(hashedPassword);
       if(hashedPassword.equals(usuarioActual.getPassword())) {
        return usuarioService.getUsuarioByEmail(email);
       } else {
        return null;

       }*/

    


    }

   /* @GetMapping("/email")
    public Optional<Usuario> obtenerUsuarioPorEmail(@RequestParam String email) {
        return usuarioService.getUsuarioByEmail(email);
    }*/ 


