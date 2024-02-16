package galli.challenge.controllers;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import galli.challenge.models.Pago;
import galli.challenge.services.PagosServices;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.time.LocalDate;



@RestController
public class PagoController {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Autowired
    private PagosServices pagosService;

    @PostMapping("/realizarCompra")
    public String realizarCompra(@RequestParam String nombre, @RequestParam String email, @RequestParam String tipoSubscripcion) {
        Stripe.apiKey = stripeApiKey;
        Long monto;
        Pago pago = new Pago();
        LocalDate fecha = LocalDate.now();


        if(tipoSubscripcion.equals("anual")){
            monto = (long) 90;
        } else {
            monto = (long) 10;
        }

        try {
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(monto)
                    .setCurrency("usd")
                    .setDescription("Compra de prueba")
                    .setSource("tok_visa") // Utiliza tok_visa para pruebas
                    .putMetadata("nombre", nombre)
                    .putMetadata("email", email)
                    .build();

            Charge charge = Charge.create(params);

            
            pago.setStatus("REALIZADO");
            pago.setTipoSubscripcion(tipoSubscripcion);
            pago.setFecha(fecha);
            pago.setEmail(email);

            pagosService.createPago(pago);

            return "Compra realizada con éxito. ID de la transacción: " + charge.getId();
        } catch (StripeException e) {
            e.printStackTrace();

            pago.setStatus("RECHAZADO");
            pago.setTipoSubscripcion(tipoSubscripcion);
            pago.setFecha(fecha);

            pagosService.createPago(pago);
            return "Error al procesar la compra: " + e.getMessage();
        }
    }
}