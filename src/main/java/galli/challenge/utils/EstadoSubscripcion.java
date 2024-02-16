package galli.challenge.utils;
import java.time.LocalDate;
import galli.challenge.models.Usuario;
import galli.challenge.models.Pago;
import galli.challenge.services.UsuarioService;
import galli.challenge.services.PagosServices;
import org.springframework.beans.factory.annotation.Autowired;
import galli.challenge.utils.ProfileProjection;
import java.util.Optional;





public class EstadoSubscripcion {

    @Autowired
    private PagosServices pagosServices;

    @Autowired
    private UsuarioService usuarioService;

    

    public void actualizarEstado(String email,String tipoSubscripcion){

ProfileProjection usuario = usuarioService.findByEmail(email);

Optional<Usuario> optional = usuarioService.getUsuarioById(Long.parseLong(usuario.getId()));
Usuario usuarioActual = optional.get();
        Pago pagoArevisar  = pagosServices.getPagoByEmail(email);
        String estadoSubsc = "";
        LocalDate fechaPag = pagoArevisar.getFecha();
        LocalDate fechaDelDia = LocalDate.now();



        if (pagoArevisar.getTipoSubscripcion().equals("anual")){
            if(fechaDelDia.equals(fechaPag.plusYears(1)) || fechaDelDia.isAfter(fechaPag.plusYears(1))) {
                estadoSubsc="INACTIVO";
            } else {
                estadoSubsc="ACTIVO";
            }
        } else if (pagoArevisar.getTipoSubscripcion().equals("mensual")) {
            if(fechaDelDia.equals(fechaPag.plusMonths(1)) || fechaDelDia.isAfter(fechaPag.plusMonths(1))) {
                estadoSubsc="INACTIVO";
            } else {
                estadoSubsc="ACTIVO";
            }

            usuarioActual.setEstadoSubscripcion(estadoSubsc);

            usuarioService.updateUsuario(usuarioActual.getId(),usuarioActual);

        
        }




    }
    
}
