package com.tiffanytimbric.wallstreet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.annotation.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {

    private final Map<String, Stock> stocks = Collections.synchronizedMap( new HashMap<>() );


    public Flux<Stock> addStock( @NonNull final Stock stock ) {
        validate( stock );

        stocks.put( stock.getName(), stock );

        return Flux.just( stock );
    }

    public Flux<Stock> updateStock( @NonNull final Stock stock ) {
        if ( !stocks.containsKey( stock ) ) {
            return addStock( stock );
        }

        validate( stock );

        stocks.put( stock.getName(), stock );

        return Flux.just( stock );
    }

    public boolean containsStock( @NonNull final Stock stock ) {
        return containsStock( stock.getName() );
    }

    public boolean containsStock( @NonNull final String stockName ) {
        return stocks.containsKey( stockName );
    }

    public Flux<Stock> removeStock( @NonNull final Stock stock ) {
        final String name = stock.getName();
        if ( !stocks.containsKey( name ) ) {
            return Flux.empty();
        }

        stocks.remove( name );

        return Flux.just( stock );
    }

    public Flux<Stock> removeAllStocks() {
        final Stock[] removedStocks = stocks.values().toArray( new Stock[0] ).clone();

        stocks.clear();

        return Flux.just( removedStocks );
    }

    public Flux<Stock> removeStock( @NonNull final String name ) {
        if ( !stocks.containsKey( name ) ) {
            return Flux.empty();
        }

        final Stock stock = stocks.get( name );
        stocks.remove( name );

        return Flux.just( stock );
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

    private void validate( @NonNull final Stock stock ) {
        final String name = stock.getName();
        if ( StringUtils.isBlank( name ) ) {
            throw new IllegalArgumentException( String.format(
                    "Parameter \"%s\" must be non-null and non-empty", name
            ) );
        }
    }
}
