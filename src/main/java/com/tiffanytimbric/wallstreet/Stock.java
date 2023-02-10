package com.tiffanytimbric.wallstreet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonComponent
public class Stock extends StockAuditInfo {

    @Id
    Integer id;
    Double price;

}
