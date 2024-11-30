package com.example.DTO;

import lombok.Data;

@Data
public class TransferRequest {
    private Float amount;
    private String toId;
    private String fromId;
}
