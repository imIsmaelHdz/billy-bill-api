package com.billy.operations.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Regime {
    VERSION("626", "Régimen Simplificado de Confianza");
    private final String key;
    private final String value;

}
