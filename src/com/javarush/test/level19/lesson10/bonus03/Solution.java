package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();
        String tag = args[0];
        String inTag = "<" + tag;
        String outTag = "</" + tag + ">";
        int lenghtInTag = ("<" + tag).length();
        int lenghtOutTag = ("</" + tag + ">").length();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String[] lines = new String[10];
        for (int i = 0; i < 10; i++) lines[i] = "";
        String lineIn = "";
        int countDeep = 0;
        boolean needFlash = false;
        while ((lineIn = reader.readLine()) != null)
        {
            for (int i = 0; i < lineIn.length(); i++)
            {
                if (((i + lenghtInTag) <= lineIn.length())
                        && (inTag.equals(lineIn.substring(i, i + lenghtInTag))))
                {
                    countDeep++;
                    needFlash = false;
                }
                if (((i + lenghtOutTag) <= lineIn.length())
                        && (outTag.equals(lineIn.substring(i, i + lenghtOutTag))))
                {
                    countDeep--;
                    needFlash = true;
                }
                for (int y = 1; y <= countDeep; y++)
                {
                    lines[y] = lines[y] + lineIn.substring(i, i + 1);
                }
                if ((countDeep == 0) & needFlash)
                {
                    for (int y = 1; y < lines.length; y++)
                    {
                        if (lines[y] != "")
                        {
                            System.out.println(lines[y] + outTag);
                            lines[y] = "";
                        }
                    }
                }
            }
        }
    }
}
