package com.dashboard.modal;
import lombok.*;

/**
 * -------------> Collect n° information from user Request
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetails {

    private String device;
    private String country;
    private String uri;
    private long executionTime;
    private String platform;
    private String city;
    private String controllerName;
    private String methodName;
    private String microService;
    private String port;
    private StringBuffer url;
    private Long startRequest;
    private String userName;
    }









