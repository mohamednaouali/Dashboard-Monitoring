package com.dashboard;


import lombok.*;

/**
 *  --------------------> Producer  Properties
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producer {
     private String userName;
     private String device;
     private String country;
     private String platform;
}
