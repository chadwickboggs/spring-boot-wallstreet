package com.tiffanytimbric.wallstreet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonComponent
public class StockAuditInfo extends AuditInfo {

    String name;

}
