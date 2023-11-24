package com.billy.operations.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Profile {
    ADMINISTRATOR,
    CLIENT,
    ACCOUNTANT
}
