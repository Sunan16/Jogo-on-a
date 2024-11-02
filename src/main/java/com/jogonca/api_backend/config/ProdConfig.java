package com.jogonca.api_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig implements CommandLineRunner {

    @Override
    public void run(String... args) {
        try {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("$-----------------------------------------------------------$");
            System.out.println("$                                                           $");
            System.out.println("$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$");
            System.out.println("$                   AMBIENTE DE PRODUÇÃO                    $");
            System.out.println("$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$");
            System.out.println("$                                                           $");
            System.out.println("$-----------------------------------------------------------$");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        } catch (Exception e) {

        }
    }

}
