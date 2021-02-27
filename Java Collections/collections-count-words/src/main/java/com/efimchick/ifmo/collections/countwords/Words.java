package com.efimchick.ifmo.collections.countwords;


import java.util.*;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> dictionary = new HashMap<>();

        for (String element : lines) {
            String[] str = element.split("[^a-zA-Zа-яА-Я]");
            for (String word : str) {
                word = word.toLowerCase();
                if (word.length() < 4) continue;
                dictionary.put(word, 0);
            }
        }
        for (String element : lines) {
            String[] str = element.split("[^a-zA-Zа-яА-Я]");
            for (String word : str) {
                word = word.toLowerCase();
                if (word.length() < 4) continue;
                int count = dictionary.get(word);
                dictionary.put(word, count + 1);
            }
        }

        List<String> lit_words = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            if(entry.getValue() < 10) {
                lit_words.add(entry.getKey());
            }
        }
        for(String key : lit_words) {
            dictionary.remove(key);
        }

        int size = dictionary.size();
        String[] words = new String[size];
        int[] numbers = new int[size];
        int max = 0;
        String max_key = "";
        for(int i = 0; i < size; i++) {
            for(Map.Entry<String, Integer> entry : dictionary.entrySet()) {
                if(entry.getValue() > max) {
                    max = entry.getValue();
                    max_key = entry.getKey();
                }
            }
            dictionary.remove(max_key);
            words[i] = max_key;
            numbers[i] = max;
            max = 0;
            max_key = "";
        }

        String[] pom = new String[2];
        for(int j = 0; j < size - 1; j++) {
            for (int i = 0; i < size - 1; i++) {
                if (numbers[i] == numbers[i + 1]) {
                    pom[0] = words[i];
                    pom[1] = words[i + 1];
                    Arrays.sort(pom);
                    words[i] = pom[0];
                    words[i + 1] = pom[1];
                }
            }
        }

        String result = "";
        for(int i = 0; i < size - 1; i++) {
            result = result + words[i] + " - " + numbers[i] + '\n';
        }
        result = result + words[size - 1] + " - " + numbers[size - 1];
        return result;
    }
}
