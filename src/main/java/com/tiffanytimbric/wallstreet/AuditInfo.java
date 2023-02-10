package com.tiffanytimbric.wallstreet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@JsonComponent
public class AuditInfo {

    private Date createDate;
    private Date updateDate;

    @Nullable
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate( @Nullable final Date createDate ) {
        this.createDate = createDate;
    }

    @Nullable
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate( @Nullable final Date updateDate ) {
        this.updateDate = updateDate;
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
        AuditInfo rhs = (AuditInfo) obj;
        return new EqualsBuilder()
                .append( this.createDate, rhs.createDate )
                .append( this.updateDate, rhs.updateDate )
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append( createDate )
                .append( updateDate )
                .toHashCode();
    }

    @Override
    @NonNull
    public String toString() {
        return new ToStringBuilder( this )
                .append( "createDate", createDate )
                .append( "updateDate", updateDate )
                .toString();
    }
}
