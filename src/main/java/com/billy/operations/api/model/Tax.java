package com.billy.operations.api.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.math.BigDecimal;

public record Tax(@Id @GeneratedValue Long id, Regime regime, BigDecimal IVA, BigDecimal ISR) { }
