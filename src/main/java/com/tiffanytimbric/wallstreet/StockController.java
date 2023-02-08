package com.tiffanytimbric.wallstreet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @NonNull
    @PostMapping(
            path = "/stock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<String> addStock(
            @NonNull @RequestBody final Stock stock
    ) {
        return Flux.just( String.valueOf( stockService.addStock( stock ) ) );
    }

    @NonNull
    @DeleteMapping(
            path = "/stock/{name}",
            params = "name",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<String> removeStock(
            @NonNull @PathVariable final String name
    ) {
        return Flux.just( String.valueOf( stockService.removeStock( name ) ) );
    }

    @NonNull
    @GetMapping(
            path = "/stock",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Stock> findAllStocks() {
        return stockService.findAll();

    }

    @NonNull
    @GetMapping(
            path = "/stock/{name}",
            params = "name",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Stock> findStock(
            @PathVariable @NonNull final String name
    ) {
        if ( StringUtils.isBlank( name ) ) {
            return stockService.findAll();
        }

        return stockService.findByName( name );
    }

}
