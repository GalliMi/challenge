package galli.challenge.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import galli.challenge.models.Usuario;
import galli.challenge.utils.ProfileProjection;
import java.util.Optional;

 


public interface UsuarioRepository extends JpaRepository<Usuario, Long> { 

    ProfileProjection findByEmail(String email);

   //public Usuario findByEmail(String email);

}
