package com.example.livraisonms.repository;

import com.example.livraisonms.entities.Delivery;
import com.example.livraisonms.entities.DeliveryStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long>{


    @Query("select d from Delivery d where d.etat <> :done and d.livreur.id=:idLivreur ")
    List<Delivery> getCurrentDeliveriesForDeliveryMan(@Param("done") DeliveryStatus etat, @Param("idLivreur") Long id);


    @Query("select d from Delivery d where d.etat <> :done")
    List<Delivery> getCurrentDeliveries(@Param("done") DeliveryStatus etat);


    @Query("select d from Delivery d where d.etat = :done")
    List<Delivery> getHistoryDeliveries(@Param("done") DeliveryStatus etat);


    //@Query("select ABS(d.DeliveryDate.time-d.commande.dateOrder.time), d.livreur from Delivery d group by d.livreur")
//    @Query("select FUNCTION('DATEDIFF',d.DeliveryDate,d.commande.dateOrder), d.livreur from Delivery d group by d.livreur")
    //@Query(nativeQuery=true,value="select DATEDIFF('SECOND',d.DeliveryDate,d.commande.dateOrder), d.livreur from Delivery d group by d.livreur")
 //   List<Object> getAttenteMoyenParLivreur();


}