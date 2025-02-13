package FitnessAssesmentForm.utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> fillInCSVFormTest() throws IOException {
        // Создаем список для хранения данных для тестов
        List<Object[]> list = new ArrayList<>();
        // Открываем CSV файл для чтения
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/fitness_data.csv"));
        // Читаем первую строку из файла
        String line = reader.readLine();
        // Обрабатываем каждую строку файла до конца
        while (line != null) {
            // Разделяем строку на элементы по запятой
            String[] split = line.split(",");
            // Создаем объект Contact и устанавливаем его поля из прочитанных данных
            list.add(new Object[]{split[0], split[1], split[2]});
            // Читаем следующую строку из файла
            line = reader.readLine();
        }
        // Закрываем файл после чтения всех данных
        reader.close();
        // Возвращаем итератор для списка объектов
        return list.iterator();
    }
}
