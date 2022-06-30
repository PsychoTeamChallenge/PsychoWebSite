package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.ShipmentType;
import com.lowagie.text.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PurchaseApplicationDTO {
 private ShipmentType shipmentType;
 private String paymentMethod,address;

}
