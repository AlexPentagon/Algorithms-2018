package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     */


    static public int josephTask(int menNumber,int choiceInterval){
        int mn = menNumber;
        if (choiceInterval < 1 || mn < 1) throw new IllegalArgumentException();      //Checking some popular and
        if (choiceInterval == 1) return mn;                                          //easy predictable cases
        if (choiceInterval == 2) {                                                   //
            while (mn % 2 == 0) mn /= 2;                                            //
            if (mn == 1) return 1;                                                  //
        }

        if(choiceInterval <= 100 && menNumber <=10000000 ||
                menNumber <= 100 && choiceInterval <=10000000 )
            return josephMN(menNumber,choiceInterval);
        return josephN(menNumber,choiceInterval);
    }
    // нужно более четко отрегулировать выбор какой либо из функций
    // первая(M * N) функция имеет право на жизнь т.к.
    // 1)у реализации через LinkedList может возникнуть переполнение памяти
    // 2)при параметрах : одно близкое к нулю, другое в районе 10 000 000 и больше,
    // первая(M * N)функция работает гораздо быстрее
    // 3)первая функция проходит тесты быстрее

    static int josephN(int menNumber, int choiceInterval){
        List<Integer> list = new LinkedList<>();                    //R=O(N) + LinkedList требует еще памяти
        for(int i = 1;i <= menNumber;i++){
            list.add(i);
        }
        int c = -1;
        for(int i = 0; i < menNumber - 1; i++) {                    // T=O(N)
            c += choiceInterval;
            if (c >= list.size()) c = c % list.size();

            list.remove(c);
            c--;

        }
        return list.get(0);
    }


// Итог: T=O(N);R=O(N)



    static public int josephMN(int menNumber, int choiceInterval) {
        int mn = menNumber;
        int[] arr = new int[mn];
        for (int i = 0; i < mn; i++) {                //T=O(menNumber)
            arr[i] = i + 1;                           //R=O(menNumber)
        }

        int c = 0;
        int counter = 0;
        while (counter != mn - 1) {                 //T=O(c) c = choiceInterval
            for (int i = 0; i < mn; i++) {          //T=O(m)  m = menNumber
                if (arr[i] != 0) c++;
                if (c == choiceInterval) {
                    arr[i] = 0;
                    c = 0;
                    counter++;
                }
            }
        }

        for (int j = 0; j < mn; j++) {
            if (arr[j] != 0) return arr[j];
        }

        return 0;
    }
    // Итог: T=O(m*n);R=O(N)

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) {
        if (first.isEmpty() || second.isEmpty())
            return "";

        int[][] arr = new int[first.length()][second.length()];

        for (int i = 0; i < first.length(); i++) {                  //T=O(m)   m=first.length
            for (int j = 0; j < second.length(); j++) {            //T=O(m*n) n=second.length
                if (first.charAt(i) == second.charAt(j)) {
                    if (i != 0 && j != 0 && first.charAt(i - 1) == second.charAt(j - 1))
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    else arr[i][j] += 1;
                }
            }
        }
        int maxArr = 0;
        int lastLetter = 0;
        int firstLetter = 0;
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (arr[i][j] > maxArr) {
                    maxArr = arr[i][j];
                    lastLetter = i;
                }
            }
        }
        firstLetter = lastLetter - maxArr;

        return first.substring(firstLetter + 1, lastLetter + 1);
    }
//Итог: T=O(m*n); R=O(m*n), m = first.length, n = second.length

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}

