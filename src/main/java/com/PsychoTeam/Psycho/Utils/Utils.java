package com.PsychoTeam.Psycho.Utils;

import net.bytebuddy.utility.RandomString;

import javax.servlet.http.HttpServletResponse;
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
}
