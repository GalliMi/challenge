package galli.challenge.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import galli.challenge.models.Pago;


public interface PagosRepository extends JpaRepository<Pago, Long> { 

    public Pago findByEmail(String email);

}
