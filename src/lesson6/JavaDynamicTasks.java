package lesson6;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        int height = string.split("-").length;
        int[][] field = new int[height][width];

        for(int i = 0;i < height;i++){
            for(int j = 0;j < width;j++){
                field[i][j] = Integer.parseInt(string.split("-")[i].split(" ")[j]);
            }
        }

        for(int i = 0;i < height;i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = field[i][j] + getMinFromNeighbours(field,i,j);
            }
        }

        return field[height-1][width-1];
    }
//    Итог:  T=O(m * n),  m - height of the field
//           R=O(m * n),  n - width of the field

    public static int getMinFromNeighbours(int[][] field,int i,int j){
        if(i == 0 && j == 0) return 0;
        else if(i == 0) return field[i][j-1];
        else if(j == 0) return field[i-1][j];
        else return Math.min(Math.min(field[i-1][j-1],field[i][j-1]),field[i-1][j]);

    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
    // DONE.
}
