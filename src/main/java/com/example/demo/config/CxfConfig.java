package com.example.demo.config;

import com.example.demo.ws.CompteSoapService;
import com.example.demo.ws.ReservationSoapService;
import lombok.AllArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CxfConfig {

    private final CompteSoapService compteSoapService; // Constructor injection
    private final ReservationSoapService reservationSoapService ;
    private final Bus bus; // Constructor injection

    @Bean
    public EndpointImpl endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus,  reservationSoapService);
        endpoint.publish("/ws");
        return endpoint;
    }
}
