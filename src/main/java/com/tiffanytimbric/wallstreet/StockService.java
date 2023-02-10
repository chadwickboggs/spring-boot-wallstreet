package com.tiffanytimbric.wallstreet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {

    private final Map<String, Stock> stocks = Collections.synchronizedMap( new HashMap<>() );


    @NonNull
    public Mono<Stock> addStock( @NonNull final Stock stock ) {
        validateFull( stock );

        stock.setCreateDate( new Date() );
        stocks.put( stock.getName(), stock );

        return Mono.just( stock );
    }

    @NonNull
    public Mono<Stock> updateStock( @NonNull final Stock stock ) {
        if ( !stocks.containsKey( stock.getName() ) ) {
            return addStock( stock );
        }

        validateFull( stock );

        stock.setCreateDate( stocks.get( stock.getName() ).getCreateDate() );
        stock.setUpdateDate( new Date() );
        stocks.put( stock.getName(), stock );

        return Mono.just( stock );
    }

    @NonNull
    public Mono<Stock> patchStock( @NonNull final Stock stock ) {
        final String name = stock.getName();
        if ( !stocks.containsKey( name ) ) {
            return addStock( stock );
        }

        validatePatch( stock );

        final Stock existingStock = stocks.get( name );
        double price = stock.getPrice();
        if ( price > 0.0D ) {
            existingStock.setPrice( price );
        }

        existingStock.setUpdateDate( new Date() );

        return Mono.just( existingStock );
    }

    public boolean containsStock( @NonNull final Stock stock ) {
        return containsStock( stock.getName() );
    }

    public boolean containsStock( @NonNull final String stockName ) {
        return stocks.containsKey( stockName );
    }

    @NonNull
    public Flux<Stock> removeStock( @NonNull final Stock stock ) {
        final String name = stock.getName();
        if ( !stocks.containsKey( name ) ) {
            return Flux.empty();
        }

        stocks.remove( name );

        return Flux.just( stock );
    }

    @NonNull
    public Flux<Stock> removeAllStocks() {
        final Stock[] removedStocks = stocks.values().toArray( new Stock[0] ).clone();

        stocks.clear();

        return Flux.just( removedStocks );
    }

    @NonNull
    public Flux<Stock> removeStock( @NonNull final String name ) {
        if ( !stocks.containsKey( name ) ) {
            return Flux.empty();
        }

        final Stock stock = stocks.get( name );
        stocks.remove( name );

        return Flux.just( stock );
    }

    @NonNull
    public Mono<StockAuditInfo> infoByName( @NonNull final String name ) {
        if ( !stocks.containsKey( name ) ) {
            return Mono.empty();
        }

        final Stock stock = stocks.get( name );
        final StockAuditInfo stockAuditInfo = new StockAuditInfo();
        stockAuditInfo.setName( stock.getName() );
        stockAuditInfo.setCreateDate( stock.getCreateDate() );
        stockAuditInfo.setUpdateDate( stock.getUpdateDate() );

        return Mono.just( stockAuditInfo );
    }

    @NonNull
    public Flux<Stock> findByName( @NonNull final String name ) {
        if ( !stocks.containsKey( name ) ) {
            return Flux.empty();
        }

        return Flux.just( stocks.get( name ) );
    }

    @NonNull
    public Flux<Stock> findAll() {
        if ( stocks.isEmpty() ) {
            return Flux.empty();
        }

        return Flux.just( stocks.values().toArray( new Stock[0] ) );
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

    private void validatePatch( @NonNull final Stock stock ) {
        final String name = stock.getName();
        if ( StringUtils.isBlank( name ) ) {
            throw new IllegalArgumentException( "Parameter \"name\" must be non-null and non-empty." );
        }
    }
}
