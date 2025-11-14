package sdu.coopbank.kb.account.statement.engine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private String sku;
    private String name;
    private String createdby;
    private int quantity;
}
