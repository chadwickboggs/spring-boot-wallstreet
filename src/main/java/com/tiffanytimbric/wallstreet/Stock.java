package com.tiffanytimbric.wallstreet;

import org.springframework.boot.jackson.JsonComponent;
import reactor.util.annotation.Nullable;

@JsonComponent
public class Stock {

    private String name;

    @Nullable
    public String getName() {
        return name;
    }

    public void setName( @Nullable final String name ) {
        this.name = name;
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
        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append( this.name, rhs.name )
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder()
                .append( name )
                .toHashCode();
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder( this )
                .append( "name", name )
                .toString();
    }

}
