package com.tiffanytimbric.wallstreet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.NonNull;


@SpringBootApplication
public class Application {

    public static void main( @NonNull final String... args ) {
        SpringApplication.run( Application.class, args );
    }

}
