package ir.khodam;

import java.util.HashMap;
import java.util.Map;

public class CountingDuplicateCharacters {
    public static void d(String[] args) {
        String str = "";
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }

        String strMaskan="Hibabyjan0123456";
        System.out.println(strMaskan.substring(strMaskan.length()-13, 15));
    }
}


