package com.workintech.twitter.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterErrorResponse {
    private Integer status;
    private String message;
    private  Long timestamp;
}
