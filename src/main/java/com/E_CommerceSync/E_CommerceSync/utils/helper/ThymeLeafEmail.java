package com.E_CommerceSync.E_CommerceSync.utils.helper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
public class ThymeLeafEmail {
    private String from;
    private String to;
    private String subject;
    private String template;
    private Map <String,Object> properties;
}
