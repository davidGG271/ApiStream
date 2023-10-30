import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // a. Crear cadenas de manera aleatoria.
        Stream<String> randomStringsStream = Stream.generate(() -> generateRandomString(0,10, "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"))
                .limit(10);

        List<String> randomStrings = randomStringsStream.collect(Collectors.toList());

        // b. Contar cuántas cadenas vacías tiene la lista de cadenas.
        long emptyStringsCount = randomStrings.stream()
                .filter(String::isEmpty)
                .count();

        // c. Contabilizar cuántas cadenas tienen longitud superior a 5.
        long longerThan5Count = randomStrings.stream()
                .filter(s -> s.length() > 5)
                .count();

        // d. Contabilizar cuántas cadenas comienzan con "s".
        long startsWithSCount = randomStrings.stream()
                .filter(s -> s.startsWith("s"))
                .count();

        // e. Eliminar todas las cadenas vacías de la lista.
        List<String> nonEmptyStrings = randomStrings.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        // f. Filtrar la lista anterior con cadenas de más de 5 caracteres.
        List<String> longStrings = nonEmptyStrings.stream()
                .filter(s -> s.length() > 5)
                .collect(Collectors.toList());

        // g. Convertir las palabras a mayúsculas y concatenarlas usando una coma ','
        String concatenatedUppercase = longStrings.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));

        System.out.println("Cadenas aleatorias: " + randomStrings);
        System.out.println("Cadenas vacías: " + emptyStringsCount);
        System.out.println("Cadenas con longitud superior a 5: " + longerThan5Count);
        System.out.println("Cadenas que comienzan con 's': " + startsWithSCount);
        System.out.println("Lista sin cadenas vacías: " + nonEmptyStrings);
        System.out.println("Lista de cadenas con más de 5 caracteres: " + longStrings);
        System.out.println("Cadenas en mayúsculas separadas por comas: " + concatenatedUppercase);


        // a. Generar una lista de números aleatorios.
        List<Integer> randomNumbers = generateRandomNumbers(10, 1, 100);

        // b. Obtener un IntStream a partir de la lista de números.
        IntStream numberStream = randomNumbers.stream()
                .mapToInt(Integer::intValue);

        // c. Utilizar IntSummaryStatistics para obtener el recuento, mínimo, máximo, suma y promedio de números.
        IntSummaryStatistics stats = numberStream.summaryStatistics();

        // d. Imprimir los resultados.
        System.out.println("Lista de números aleatorios: " + randomNumbers);
        System.out.println("Recuento: " + stats.getCount());
        System.out.println("Mínimo: " + stats.getMin());
        System.out.println("Máximo: " + stats.getMax());
        System.out.println("Suma: " + stats.getSum());
        System.out.println("Promedio: " + stats.getAverage());
    }

    // Método para generar una lista de números aleatorios
    private static List<Integer> generateRandomNumbers(int count, int min, int max) {
        Random random = new Random();
        return random.ints(count, min, max + 1)
                .boxed()
                .collect(Collectors.toList());
    }


    // Método para generar una cadena aleatoria
    private static String generateRandomString(int minLength, int maxLength, String characters) {
        Random random = new Random();
        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        return random.ints(length, 0, characters.length())
                .mapToObj(index -> characters.substring(index, index + 1))
                .collect(Collectors.joining());
    }

}