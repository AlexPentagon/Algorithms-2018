package lesson6;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        throw new NotImplementedError();
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputName)));

        String line = "";
        String string = "";
        while(true) {
            line = br.readLine();
            if(line == null)break;
            string = string + line + "-";
        }
        int width = string.split("-")[0].split(" ").length;
        int heigh = string.split("-").length;
        int[][] field = new int[heigh][width];

        for(int i = 0;i < heigh;i++){
            for(int j = 0;j < width;j++){
                field[i][j] = Integer.parseInt(string.split("-")[i].split(" ")[j]);
            }
        }


        Map<String,Integer> list = new HashMap<>();
        rec(field,list,heigh-1,width-1,"");

        int value = Collections.min(list.values());

        return value;
    }

    public static Map<String,Integer> rec(int[][] field,Map<String,Integer> map,int x,int y,String st){
        int curr = field[x][y];
        int sum = 0;
        String key = st + curr + " ";
            if(key.charAt(key.length()-2) == '0'){
                for(int i = 0;i < key.split(" ").length-1;i++){

                   sum = sum + Integer.parseInt(key.split(" ")[i]);
                }
                if(sum != 0)map.put(key,sum);
            }

        if(x - 1 != -1 && y - 1 != -1) rec(field,map,x - 1, y - 1, key);
        if(x - 1 != -1) rec(field,map,x - 1, y, key);
        if(y - 1 != -1) rec(field,map,x, y - 1, key);

return map;
    }


    public static void main(String[] args) {
        try{
            System.out.println(shortestPathOnField("D://note5.txt"));
        }catch (IOException e){}
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
    // DONE.
}
