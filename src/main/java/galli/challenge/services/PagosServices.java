package galli.challenge.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import  galli.challenge.repositories.PagosRepository;
import galli.challenge.models.Pago;

@Service
public class PagosServices {
    
    @Autowired
    private PagosRepository pagosRepository;

    public Pago createPago(Pago pago) {
        return pagosRepository.save(pago);
    }

    public Pago getPagoByEmail(String email) {
        return pagosRepository.findByEmail(email);
    }



}
