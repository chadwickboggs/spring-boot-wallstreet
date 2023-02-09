package com.tiffanytimbric.wallstreet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.lang.NonNull;
import reactor.util.annotation.Nullable;

@JsonComponent
public class Stock {

    private String name;
    private double price = 0.0D;

    @Nullable
    public String getName() {
        return name;
    }

    public void setName( @Nullable final String name ) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice( double price ) {
        this.price = price;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( obj == this ) {
            return true;
        }
        if ( obj.getClass() != getClass() ) {
            return false;
        }
        Stock rhs = (Stock) obj;
        return new EqualsBuilder()
                .append( this.name, rhs.name )
                .append( this.price, rhs.price )
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append( name )
                .append( price )
                .toHashCode();
    }

    @Override
    @NonNull
    public String toString() {
        return new ToStringBuilder( this )
                .append( "name", name )
                .append( "price", price )
                .toString();
    }
}
