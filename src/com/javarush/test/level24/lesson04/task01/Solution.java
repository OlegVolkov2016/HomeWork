package com.javarush.test.level24.lesson04.task01;

import java.math.BigDecimal;

/* Наследование от внутреннего класса
Внутри класса Solution_1 создайте 2 внутренних public класса Apt3Bedroom, BigHall.
Унаследуйте их от Apartments и Hall.
*/
public class Solution {
    public class Building {
        public class Hall {
            private BigDecimal square;

            public Hall(BigDecimal square) {
                this.square = square;
            }
        }

        public class Apartments {
        }
    }

    public class Apt3Bedroom extends Building.Apartments {
        public Apt3Bedroom(Building building)
        {
            building.super();
        }
    }

    public class BigHall extends Building.Hall {
        public BigHall(Building building, BigDecimal square)
        {
            building.super(square);
        }
    }
}
