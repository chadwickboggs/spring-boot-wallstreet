package com.tiffanytimbric.wallstreet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import reactor.util.annotation.Nullable;

public class StockAuditInfo extends AuditInfo {

    protected String name;

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
        StockAuditInfo rhs = (StockAuditInfo) obj;
        return new EqualsBuilder()
                .appendSuper( super.equals( obj ) )
                .append( this.name, rhs.name )
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper( super.hashCode() )
                .append( name )
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder( this )
                .appendSuper( super.toString() )
                .append( "name", name )
                .toString();
    }
}
