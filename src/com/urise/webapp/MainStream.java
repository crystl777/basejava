package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStream {

    private static int[] values = {1, 2, 3, 3, 2, 3};
    private static List<Integer> integers = new ArrayList<>();


    /*
    Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
    составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
     */
    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((x, y) -> x * 10 + y)
                .orElse(0);
    }


    /*
    Метод принимает список числе. Если сумма всех чисел нечетная - удалить все нечетные,
    если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
     */

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(Collectors
                        .groupingBy(x -> (x % 2) > 0));
        return map.get(map.get(true).size() % 2 == 0);
    }


    public static void main(String[] args) {
        System.out.println(minValue(values));

        integers.add(2);
        integers.add(10);
        integers.add(3);
        integers.add(1);
        integers.add(7);
        System.out.println(oddOrEven(integers));

    }
}
