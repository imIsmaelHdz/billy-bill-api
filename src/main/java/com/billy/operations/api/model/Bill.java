package com.billy.operations.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node
public class Bill {
    @Id
    @GeneratedValue
    private UUID billId;
    private String owner;
    private String processedId;
    private BigDecimal amount;
    private BigDecimal taxPercentage;
}
