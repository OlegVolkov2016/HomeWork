package com.javarush.test.level38.lesson06.home01;

/* Фабрика исключений
Создайте класс - фабрику исключений, который содержит один статический метод, возвращающий нужное исключение.
Этот метод должен принимать один параметр - энум.
Если передан энум:
* ExceptionApplicationMessage, верните исключение Exception
* ExceptionDBMessage, верните исключение RuntimeException
* ExceptionUserMessage, верните Error
иначе верните IllegalArgumentException без сообщения.

В качестве сообщения (message) для каждого возвращаемого объекта используйте элементы энума, в которых все знаки
подчеркивания замените на пробелы. Сообщение должно быть в нижнем регистре за исключением первого символа.
Например, сообщение для ExceptionApplicationMessage.SOCKET_IS_CLOSED - "Socket is closed".

Верните класс созданной фабрики в методе Solution.getFactoryClass.

Энумы не меняйте.
*/

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {
        try
        {
            Class factoryClass = getFactoryClass();
            Method factoryMethod = factoryClass.getMethod("getException", java.lang.Enum.class);
            throw (Throwable) factoryMethod.invoke(factoryClass, null);
//            throw (Throwable) factoryMethod.invoke(factoryClass, ExceptionApplicationMessage.UNHANDLED_EXCEPTION);
//            throw (Throwable) factoryMethod.invoke(factoryClass, ExceptionApplicationMessage.SOCKET_IS_CLOSED);
//            throw (Throwable) factoryMethod.invoke(factoryClass, ExceptionDBMessage.NOT_ENOUGH_CONNECTIONS);
//            throw (Throwable) factoryMethod.invoke(factoryClass, ExceptionDBMessage.RESULT_HAS_NOT_GOTTEN_BECAUSE_OF_TIMEOUT);
//            throw (Throwable) factoryMethod.invoke(factoryClass, ExceptionUserMessage.USER_DOES_NOT_EXIST);
//            throw (Throwable) factoryMethod.invoke(factoryClass, ExceptionUserMessage.USER_DOES_NOT_HAVE_PERMISSIONS);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
