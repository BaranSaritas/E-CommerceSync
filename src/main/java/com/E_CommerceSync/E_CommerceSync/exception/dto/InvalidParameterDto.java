package com.E_CommerceSync.E_CommerceSync.exception.dto;

import lombok.Builder;

@Builder
public record InvalidParameterDto(String parameter, String message) { }
