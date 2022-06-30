package com.PsychoTeam.Psycho.Utils;

import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Purchase;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import net.bytebuddy.utility.RandomString;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Utils {

    private static List<String> tokensCreated = new ArrayList<>();
    private static List<String> codesCreated = new ArrayList<>();
    private static List<Long> idsCreated = new ArrayList<>();

    public static String GenerateToken(int tokenL) {
        String token = "";
        do {
            token = RandomString.make(tokenL);
        } while (tokensCreated.contains(token));
        tokensCreated.add(token);
        return token;
    }

    public static String GenerateCode(int max, int min) {
        int number;
        String numberFinal = "";

        do {
            for (int i = 0; i < 6; i++) {
                number = (int) ((Math.random() * (max - min)) + min);
                numberFinal += number;
            }
        }
        while (codesCreated.contains(numberFinal));
        codesCreated.add(numberFinal);
        return numberFinal;
    }

    public static void DeleteToken(String tokenD) {
        tokensCreated = tokensCreated.stream().filter(token -> token != tokenD).collect(Collectors.toList());
    }

    public static long GenerateIdCrypt() {
        long number;

        do {
            number = (int) ((Math.random() * (9 - 0)) + 0);

        }
        while (idsCreated.contains(number));
        idsCreated.add(number);
        return number;
    }

    public static void CreatePDF(HttpServletResponse response, Purchase purchase) throws IOException, DocumentException {

        Font catFont = new Font(Font.STRIKETHRU, 20, Font.BOLD);
        Font smallBold = new Font(Font.STRIKETHRU, 12);
        Font tableFont = new Font(Font.STRIKETHRU, 10, Font.NORMAL);
        Font headerFont = new Font(Font.STRIKETHRU, 12, Font.BOLD);
        Font subTitle =  new Font(Font.STRIKETHRU, 14, Font.BOLD);

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        PdfPTable table = new PdfPTable(6);
        float[] columnWidths = new float[]{14f, 14f, 26f, 15f, 14f, 17f};

        table.setWidths(columnWidths);
        table.setWidthPercentage(110);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        List<String> headers = List.of("PRODUCT", "NAME", "DESCRIPTION", "PRICE / U", "QUANTITY", "SUBTOTAL");

        headers.forEach(header -> {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(214, 126, 235));
            cell.setPadding(8);
            table.addCell(cell);
        });


        AtomicReference<Double> totalExpense = new AtomicReference<>((double) 0);


        purchase.getProductCarts().forEach(product -> {

            Image image = null;
            try {
                image = Image.getInstance(product.getUrl());
            } catch (BadElementException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            image.setBackgroundColor(new Color(222, 184, 231));
            table.addCell(image);

            PdfPCell nameCell = new PdfPCell(new Phrase(product.getName(), tableFont));
            nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            nameCell.setVerticalAlignment(Element.ALIGN_CENTER);
            nameCell.setBackgroundColor(new Color(222, 184, 231));
            nameCell.setPadding(8);
            table.addCell(nameCell);

            PdfPCell descriptionCell = new PdfPCell(new Phrase(product.getDescription(), tableFont));
            descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            descriptionCell.setVerticalAlignment(Element.ALIGN_CENTER);
            descriptionCell.setBackgroundColor(new Color(222, 184, 231));
            descriptionCell.setPadding(8);
            table.addCell(descriptionCell);

            PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf(product.getPrice()), tableFont));
            priceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            priceCell.setVerticalAlignment(Element.ALIGN_CENTER);
            priceCell.setBackgroundColor(new Color(222, 184, 231));
            priceCell.setPadding(8);
            table.addCell(priceCell);

            PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(product.getQuantity()), tableFont));
            quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantityCell.setVerticalAlignment(Element.ALIGN_CENTER);
            quantityCell.setBackgroundColor(new Color(222, 184, 231));
            quantityCell.setPadding(8);
            table.addCell(quantityCell);

            PdfPCell subtotalCell = new PdfPCell(new Phrase(String.valueOf(product.getQuantity() * product.getPrice()), tableFont));
            subtotalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            subtotalCell.setVerticalAlignment(Element.ALIGN_CENTER);
            subtotalCell.setBackgroundColor(new Color(222, 184, 231));
            subtotalCell.setPadding(8);
            table.addCell(subtotalCell);

            totalExpense.updateAndGet(v -> (double) (v + (product.getPrice() * product.getQuantity())));
        });

        PdfPCell spaceCell = new PdfPCell(new Phrase(""));
        spaceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        spaceCell.setBackgroundColor(new Color(222, 184, 231));
        spaceCell.setPadding(8);
        spaceCell.setColspan(3);
        table.addCell(spaceCell);

        headerFont.setStyle(Font.ITALIC);

        PdfPCell totalExpenseCell = new PdfPCell(new Phrase("TOTAL EXPENSE",headerFont ));
        totalExpenseCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalExpenseCell.setBackgroundColor(new Color(222, 184, 231));
        totalExpenseCell.setPadding(8);
        totalExpenseCell.setColspan(2);
        table.addCell(totalExpenseCell);

        PdfPCell amountExpenseCell = new PdfPCell(new Phrase(String.valueOf(totalExpense) + "usd", headerFont));
        amountExpenseCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        amountExpenseCell.setBackgroundColor(new Color(222, 184, 231));
        amountExpenseCell.setPadding(8);
        table.addCell(amountExpenseCell);

        Client client = purchase.getClient();

        Paragraph title = new Paragraph("PSYCHO PURCHASE RESUME", catFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);

        Paragraph paragraphSubTitle = new Paragraph("Details of client:  " , subTitle);
        paragraphSubTitle.setSpacingAfter(5);

        Paragraph nameParr = new Paragraph("full name:  " + client.getFullName(), smallBold);
        nameParr.setSpacingAfter(5);

        Paragraph shipmentParr = new Paragraph("shipment type:  " + purchase.getShipmentType(), smallBold);
        shipmentParr.setSpacingAfter(5);

        Paragraph addressParr = new Paragraph("address:  " + purchase.getAddress(), smallBold);
        addressParr.setSpacingAfter(5);





        Image logo  = Image.getInstance("https://cdn.filestackcontent.com/3zaIVummRKPgMvZyW8BV");
        logo.setAbsolutePosition(460f, 730f);
        logo.scalePercent(10f);
        document.add(title);
        document.add(paragraphSubTitle);
        document.add(nameParr);
        document.add(shipmentParr);
        document.add(addressParr);
        document.add(logo);
        document.add(table);
        document.close();

    }

}
