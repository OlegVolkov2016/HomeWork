package com.javarush.test.level25.lesson02.home01;

/* Свой enum
Реализуйте интерфейс Columnable у Column, описание методов смотрите в джавадоках.
Реализуйте логику метода Column.getVisibleColumns.
Создавать дополнительные поля нельзя.
Метод main не участвует в тестировании.
*/
public class Solution {
    /**
     * Output:
     * <p/>
     * Available Amount
     * Account Number
     * Bank Name
     * --------------------
     * Available Amount
     * Bank Name
     */
    public static void main(String[] args) {

        Column.configureColumns(Column.Amount, Column.AccountNumber, Column.BankName);

        for (Columnable columnable : Column.getVisibleColumns()) {
            System.out.println(columnable.getColumnName());
        }

        System.out.println("--------------------");
        Column.AccountNumber.hide();

        for (Columnable columnable : Column.getVisibleColumns()) {
            System.out.println(columnable.getColumnName());
        }
    }
}
