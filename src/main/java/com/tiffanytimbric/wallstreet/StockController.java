package com.tiffanytimbric.wallstreet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Optional;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping(
            path = "/stock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Flux<Stock> addStock(
            @RequestBody @NonNull final Stock stock
    ) {
        return stockService.addStock( stock );
    }

    @PutMapping(
            path = "/stock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Flux<Stock> updateStock(
            @RequestBody @NonNull final Stock stock
    ) {
        return stockService.updateStock( stock );
    }

    @PatchMapping(
            path = "/stock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Flux<Stock> patchStock(
            @RequestBody @NonNull final Stock stock
    ) {
        return stockService.patchStock( stock );
    }

    @DeleteMapping(
            path = "/stock",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Flux<Stock> removeAllStocks() {
        return stockService.removeAllStocks();
    }

    @DeleteMapping(
            path = "/stock/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Flux<Stock> removeStock(
            @PathVariable( required = false ) @NonNull final Optional<String> name
    ) {
        return name
                .map( theName -> stockService.removeStock( name.get() ) )
                .orElseGet( () -> stockService.removeAllStocks() );
    }

    @GetMapping(
            path = "/stock",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Flux<Stock> findAllStocks() {
        return stockService.findAll();

    }

    @GetMapping(
            path = "/stock/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Flux<Stock> findStock(
            @PathVariable( required = false ) @NonNull final Optional<String> name
    ) {
        return name
                .map( theName -> stockService.findByName( theName ) )
                .orElseGet( () -> stockService.findAll() );

    }

}
