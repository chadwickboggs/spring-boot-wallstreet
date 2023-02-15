package com.tiffanytimbric.wallstreet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockReactiveCrudRepository stockRepository;


    @NonNull
    public Mono<Stock> add(@NonNull final Stock stock ) {
        validateFull( stock );

        stock.setCreateDate( new Date() );
        stockRepository.save( stock );

        return Mono.just( stock );
    }

    @NonNull
    public Mono<Stock> update(@NonNull final Stock stock ) {
        validateFull( stock );

        final Stock existingStock = getByName( stock.getName() );
        stock.setCreateDate( existingStock.getCreateDate() );
        stock.setUpdateDate( new Date() );

        return stockRepository.save( stock );
    }

    @NonNull
    public Mono<Stock> patch(@NonNull final Stock stock ) {
        validatePatch( stock );

        final Stock existingStock = getByName( stock.getName() );
        double price = stock.getPrice();
        if ( price > 0.0D ) {
            existingStock.setPrice( price );
        }

        existingStock.setUpdateDate( new Date() );

        return Mono.just( existingStock );
    }

    public boolean contains(@NonNull final Stock stock ) {
        return contains( stock.getName() );
    }

    public boolean contains(@NonNull final String stockName ) {
        final Optional<Stock> stockOpt = stockRepository.findByName( stockName )
                .blockOptional( Duration.ZERO );

        return stockOpt.isPresent();
    }

    @NonNull
    public Mono<Stock> removeStock( @NonNull final Stock stock ) {
        final Stock deletedStock = getByName( stock.getName() );

        stockRepository.deleteByName( stock.getName() );

        return Mono.just( deletedStock );
    }

    @NonNull
    public Mono<Void> removeAll() {
        return stockRepository.deleteAll();
    }

    @NonNull
    public Flux<Stock> removeByName(@NonNull final String name ) {
        final Stock deletedStock = getByName( name );

        stockRepository.deleteByName( name );

        return Flux.just( deletedStock );
    }

    @NonNull
    public Mono<Stock> findByName( @NonNull final String name ) {
        return stockRepository.findByName( name );
    }

    @NonNull
    public Flux<Stock> findAll() {
        return stockRepository.findAll();
    }

    private void validateFull( @NonNull final Stock stock ) {
        validatePatch( stock );

        double price = stock.getPrice();
        if ( price <= 0.0D ) {
            throw new IllegalArgumentException( String.format(
                    "Parameter \"price\" must have a positive value.  Value: %g", price
            ) );
        }
    }

    @NonNull
    private Stock getByName(@NonNull final String name ) {
        final Optional<Stock> existingStockOpt = findByName( name ).blockOptional( Duration.ZERO );
        if ( !existingStockOpt.isPresent() ) {
            throw new IllegalArgumentException( String.format(
                    "Stock not found.  Name: %s", name
            ) );
        }

        return existingStockOpt.get();
    }

    private void validatePatch( @NonNull final Stock stock ) {
        final String name = stock.getName();
        if ( StringUtils.isBlank( name ) ) {
            throw new IllegalArgumentException( "Parameter \"name\" must be non-null and non-empty." );
        }
    }
}
