package com.tiffanytimbric.wallstreet;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

public interface StockReactiveCrudRepository extends ReactiveCrudRepository<Stock, Integer> {

    @Query( "select id, name, price from stock where name = :name" )
    @NonNull
    Mono<Stock> findByName( @NonNull final String name );

    @Query( "delete from stock where name = :name" )
    @NonNull
    Mono<Void> deleteByName( @NonNull final String name );

}
