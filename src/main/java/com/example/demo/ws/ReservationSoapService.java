package com.example.demo.ws;

import com.example.demo.entities.Chambre;
import com.example.demo.entities.Client;
import com.example.demo.entities.Reservation;
import com.example.demo.repositories.ChambreRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.ReservationRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@WebService(serviceName = "ReservationWS")
public class ReservationSoapService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ChambreRepository chambreRepository;

    // Method to get all reservations
    @WebMethod
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    // Method to get a reservation by its ID
    @WebMethod
    public Reservation getReservationById(@WebParam(name = "id") Long id) {
        return reservationRepository.findById(id).orElse(null);
    }


    @WebMethod
    public Reservation createReservation(@WebParam(name = "clientId") Long clientId,
                                         @WebParam(name = "chambreId") Long chambreId,
                                         @WebParam(name = "dateDebut") String dateDebut,
                                         @WebParam(name = "dateFin") String dateFin,
                                         @WebParam(name = "preferences") String preferences) {

        // Retrieve the client from the repository using the clientId, create a default client if not found
        Client client = clientRepository.findById(clientId).orElseGet(() -> {
            // Create a default Client if not found
            Client newClient = new Client();
            newClient.setNom("Default");
            newClient.setPrenom("Client");
            newClient.setEmail("default.client@example.com");
            newClient.setTelephone("0000000000");
            return clientRepository.save(newClient);
        });

        // Retrieve the chambre from the repository using the chambreId, create a default chambre if not found
        Chambre chambre = chambreRepository.findById(chambreId).orElseGet(() -> {
            // Create a default Chambre if not found
            Chambre newChambre = new Chambre();
            newChambre.setType("Standard");
            newChambre.setPrix(BigDecimal.valueOf(100));  // Example price
            newChambre.setDisponible(true);  // Default chambre is available
            return chambreRepository.save(newChambre);
        });

        // Create a new Reservation and set its properties
        Reservation reservation = new Reservation();
        reservation.setClient(client);  // Set the actual Client object, not the ID
        reservation.setChambre(chambre);  // Set the actual Chambre object, not the ID
        reservation.setDateDebut(LocalDate.parse(dateDebut));
        reservation.setDateFin(LocalDate.parse(dateFin));
        reservation.setPreferences(preferences);

        // Save the reservation and return it
        return reservationRepository.save(reservation);
    }


    /*
    @WebMethod
    public Reservation createReservation(@WebParam(name = "clientId") Long clientId,
                                         @WebParam(name = "chambreId") Long chambreId,
                                         @WebParam(name = "dateDebut") String dateDebut,
                                         @WebParam(name = "dateFin") String dateFin,
                                         @WebParam(name = "preferences") String preferences) {

        // Retrieve the client and chambre from the repositories using their IDs
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        Chambre chambre = chambreRepository.findById(chambreId).orElseThrow(() -> new RuntimeException("Chambre not found"));

        // Create a new Reservation and set its properties
        Reservation reservation = new Reservation();
        reservation.setClient(client);  // Set the actual Client object, not the ID
        reservation.setChambre(chambre);  // Set the actual Chambre object, not the ID
        reservation.setDateDebut(LocalDate.parse(dateDebut));
        reservation.setDateFin(LocalDate.parse(dateFin));
        reservation.setPreferences(preferences);

        // Save the reservation and return it
        return reservationRepository.save(reservation);
    }

     */


    // Method to delete a reservation
    @WebMethod
    public boolean deleteReservation(@WebParam(name = "id") Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}