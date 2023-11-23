package com.billy.operations.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Regime {
    RSDC("626", "Régimen Simplificado de Confianza"),
    PFAEMP("612", "Personas Físicas con Actividades Empresariales y Profesionales");

    private final String key;
    private final String description;
}
