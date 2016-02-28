package com.javarush.test.level40.lesson06.task02;

/* Принадлежность точки многоугольнику
Дан многоугольник, заданный координатами своих вершин.
Ребра многоугольника не пересекаются.
Необходимо реализовать метод isPointInPolygon(Point point, List<Point> polygon), который проверит,
принадлежит ли переданная точка многоугольнику.
*/

import java.util.ArrayList;
import java.util.List;

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        int intersectCoint = 0;
        for (int i = 0; i < polygon.size(); i++) {
            Point point1 = polygon.get(i > 0 ? (i - 1) : polygon.size() - 1);
            Point point2 = polygon.get(i);

            if(isIntersect(point, point1, point2))
                intersectCoint++;
        }
        return (intersectCoint % 2 == 1);
    }

    private static boolean isIntersect(Point point, Point point1, Point point2)
    {
        double deltaY1 = point1.y - point.y;
        double deltaY2 = point2.y - point.y;
        if (Math.signum(deltaY1) == Math.signum(deltaY2)) {
            return false;
        }
        double deltaX1 = point1.x - point.x;
        double deltaX2 = point2.x - point.x;
        if ((deltaX1 >= 0) && (deltaX2 >= 0))
            return true;
        if ((deltaX1 < 0) && (deltaX2 < 0)) {
            return false;
        }
        double deltaX0 = deltaY1 * (point1.x-point2.x) / (point1.y-point2.y);
        return deltaX0 <= deltaX1;
    }

}
