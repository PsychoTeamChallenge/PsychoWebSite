package com.PsychoTeam.Psycho.Dtos;

import com.lowagie.text.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Getter
public class PurchaseApplicationDTO {
    private long cart_id;
    private String shipmentType,paymentMethod,address;
    private Document pdf;
}
