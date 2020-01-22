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

        int[] arr = Arrays.stream(values).distinct().sorted().toArray();
        int num = 0;

        for (int i = 0, n = arr.length - 1; n >= 0; i++, --n) {
            int pos = (int) Math.pow(10, n);
            num += arr[i] * pos;
        }
        return num;
    }


    /*
    Метод принимает список числе. Если сумма всех чисел нечетная - удалить все нечетные,
    если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
     */

    private static List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> list = null;
        Map<Boolean, List<Integer>> oddNum = integers.stream().collect(Collectors.groupingBy(x -> (x % 2) > 0));
        for (Map.Entry<Boolean, List<Integer>> item : oddNum.entrySet()) {
            if (item.getValue().size() % 2 > 0) {
                list = oddNum.get(false);
            } else list = oddNum.get(true);
        }
        return list;
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
