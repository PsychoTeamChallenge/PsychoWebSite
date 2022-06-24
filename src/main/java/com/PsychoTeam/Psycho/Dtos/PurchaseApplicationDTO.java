package com.PsychoTeam.Psycho.Dtos;

import com.lowagie.text.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PurchaseApplicationDTO {
    private long cart_id;
    private String shipmentType,paymentMethod,address;
}
