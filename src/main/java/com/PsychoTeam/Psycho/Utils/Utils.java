package com.PsychoTeam.Psycho.Utils;

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

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        PdfPTable table = new PdfPTable(6);
        float[] columnWidths = new float[]{12f, 14f, 26f, 16f, 16f, 16f};

        table.setWidths(columnWidths);
        table.setWidthPercentage(110);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        List<String> headers = List.of("PRODUCT", "NAME", "DESCRIPTION", "PRICE / U", "QUANTITY", "SUBTOTAL");

        headers.forEach(header -> {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(234, 88, 25));
            cell.setPadding(8);
            table.addCell(cell);
        });

        purchase.getProductCarts().forEach(product -> {
            Image img;
            try {
                img = Image.getInstance(product.getUrl());
            } catch (BadElementException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            img.scaleToFit(50,50);
            PdfPCell imgCell = new PdfPCell(img);
            imgCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            imgCell.setVerticalAlignment(Element.ALIGN_CENTER);
            imgCell.setBackgroundColor(new Color(231, 198, 184));
            imgCell.setPadding(8);
            table.addCell(imgCell);

        });

        document.add(table);
        document.close();

    }

}
