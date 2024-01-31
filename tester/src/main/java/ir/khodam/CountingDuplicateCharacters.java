package ir.khodam;

import java.util.HashMap;
import java.util.Map;

public class CountingDuplicateCharacters {
    public static void main(String[] args) {
        String str = "";
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }
    }
}


