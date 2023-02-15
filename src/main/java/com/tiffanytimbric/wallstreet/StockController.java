package com.tiffanytimbric.wallstreet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<Stock> addStock(
            @RequestBody @NonNull final Stock stock
    ) {
        return stockService.add( stock );
    }

    @PutMapping(
            path = "/stock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Mono<Stock> updateStock(
            @RequestBody @NonNull final Stock stock
    ) {
        return stockService.update( stock );
    }

    @PatchMapping(
            path = "/stock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Mono<Stock> patchStock(
            @RequestBody @NonNull final Stock stock
    ) {
        return stockService.patch( stock );
    }

    @DeleteMapping(
            path = "/stock",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Mono<Void> removeStockAll() {
        return stockService.removeAll();
    }

    @DeleteMapping(
            path = "/stock/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Flux<Stock> removeStockByName(
            @PathVariable @NonNull final Optional<String> name
    ) {
        return name
                .map( theName -> stockService.removeByName( name.get() ) )
                .orElseThrow();
    }

    @GetMapping(
            path = "/stock",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Flux<Stock> findStockAll() {
        return stockService.findAll();

    }

    @GetMapping(
            path = "/stock/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @NonNull
    public Flux<Stock> findStockByName(
            @PathVariable @NonNull final Optional<String> name
    ) {
        return name
                .map( theName -> stockService.findByName( theName ).flux() )
                .orElseThrow();

    }

}
