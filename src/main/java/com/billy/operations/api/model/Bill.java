package com.billy.operations.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Bill {
    @Id
    @GeneratedValue
    private UUID billId;
    private String owner;
    private BigDecimal amount;
    private Boolean processedFlag;
}
