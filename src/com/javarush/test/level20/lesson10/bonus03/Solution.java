package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same", "fderlk");
        for (Word word : list) {
            System.out.println(word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<Word>();
        System.out.println(crossword.length + "x" + crossword[0].length);
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword[i].length; j++) {
                for (int k = 0; k < words.length; k++) {
                    if (crossword[i][j] == words[k].charAt(0)) {
                        int startX = i;
                        int startY = j;
                        int endX  = i;
                        int endY = j;
                        boolean match = false;
                        // 1
                        if (startX + words[k].length() <= crossword.length) {
                            match = true;
                            for (int l = 1; l < words[k].length(); l++) {
                                if (crossword[startX+l][startY] != words[k].charAt(l)) {
                                    match = false;
                                    break;
                                }
                            }
                        }
                        if (match) {
                            endX = startX + words[k].length() - 1;
                            addWord(list, words[k], startX, startY, endX, endY);
                            continue;
                        }
                        // 2
                        if ((startX + words[k].length() <= crossword.length) && (startY + words[k].length() <= crossword[i].length)) {
                            match = true;
                            for (int l = 1; l < words[k].length(); l++) {
                                if (crossword[startX+l][startY+l] != words[k].charAt(l)) {
                                    match = false;
                                    break;
                                }
                            }
                        }
                        if (match) {
                            endX = startX + words[k].length() - 1;
                            endY = startY + words[k].length() - 1;
                            addWord(list, words[k], startX, startY, endX, endY);
                            continue;
                        }
                        // 3
                        if (startY + words[k].length() <= crossword[i].length) {
                            match = true;
                            for (int l = 1; l < words[k].length(); l++) {
                                if (crossword[startX][startY+l] != words[k].charAt(l)) {
                                    match = false;
                                    break;
                                }
                            }
                        }
                        if (match) {
                            endY = startY + words[k].length() - 1;
                            addWord(list, words[k], startX, startY, endX, endY);
                            continue;
                        }
                        // 4
                        if ((startX + 1 - words[k].length() >= 0) && (startY + words[k].length() <= crossword[i].length)) {
                            match = true;
                            for (int l = 1; l < words[k].length(); l++) {
                                if (crossword[startX-l][startY+l] != words[k].charAt(l)) {
                                    match = false;
                                    break;
                                }
                            }
                        }
                        if (match) {
                            endX = startX + 1 - words[k].length();
                            endY = startY + words[k].length() - 1;
                            addWord(list, words[k], startX, startY, endX, endY);
                            continue;
                        }
                        // 5
                        if (startX + 1 - words[k].length() >= 0) {
                            match = true;
                            for (int l = 1; l < words[k].length(); l++) {
                                if (crossword[startX-l][startY] != words[k].charAt(l)) {
                                    match = false;
                                    break;
                                }
                            }
                        }
                        if (match) {
                            endX = startX + 1 - words[k].length();
                            addWord(list, words[k], startX, startY, endX, endY);
                            continue;
                        }
                        // 6
                        if ((startX + 1 - words[k].length() >= 0) && (startY + 1 - words[k].length() >= 0)) {
                            match = true;
                            for (int l = 1; l < words[k].length(); l++) {
                                if (crossword[startX-l][startY-l] != words[k].charAt(l)) {
                                    match = false;
                                    break;
                                }
                            }
                        }
                        if (match) {
                            endX = startX + 1 - words[k].length();
                            endY = startY + 1 - words[k].length();
                            addWord(list, words[k], startX, startY, endX, endY);
                            continue;
                        }
                        // 7
                        if (startY + 1 - words[k].length() >= 0) {
                            match = true;
                            for (int l = 1; l < words[k].length(); l++) {
                                if (crossword[startX][startY-l] != words[k].charAt(l)) {
                                    match = false;
                                    break;
                                }
                            }
                        }
                        if (match) {
                            endY = startY + 1 - words[k].length();
                            addWord(list, words[k], startX, startY, endX, endY);
                            continue;
                        }
                        // 8
                        if ((startX + words[k].length() <= crossword.length) && (startY + 1 - words[k].length() >= 0)) {
                            match = true;
                            for (int l = 1; l < words[k].length(); l++) {
                                if (crossword[startX+l][startY-l] != words[k].charAt(l)) {
                                    match = false;
                                    break;
                                }
                            }
                        }
                        if (match) {
                            endX = startX + words[k].length() - 1;
                            endY = startY + 1 - words[k].length();
                            addWord(list, words[k], startX, startY, endX, endY);
                            continue;
                        }
                    }
                }
            }
        }

        return list;
    }

    public static void addWord (List<Word> list, String text, int startX, int startY, int endX, int endY) {
        Word word = new Word(text);
        word.setStartPoint(startY, startX);
        word.setEndPoint(endY, endX);
        list.add(word);
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
