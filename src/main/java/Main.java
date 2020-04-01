import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {

    private static long start = System.currentTimeMillis();
    private static int regionsCount = 100;
    private static char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public static void main(String[] args) {
        Runnable r1 = () -> writeFile(1, 25);
        Runnable r2 = () -> writeFile(26, 50);
        Runnable r3 = () -> writeFile(51, 75);
        Runnable r4 = () -> writeFile(76, 100);
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
        new Thread(r4).start();

//        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static void writeFile(int regionFrom, int regionTo) {
        try {
            PrintWriter writer = new PrintWriter("res/numbers " + regionFrom + "-" + regionTo + " regions.txt");

            for (int i = regionFrom; i <= regionTo; i++) {
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                builder.append(firstLetter)
                                        .append(padNumber(number, 3))
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(padNumber(i, 2))
                                        .append("\n");
                            }
                        }
                    }
                }
                writer.write(builder.toString());
            }
            writer.flush();
            writer.close();
            System.out.println("Поток: " + Thread.currentThread().getName()+ " - " + (System.currentTimeMillis() - start) + " ms");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder builder = new StringBuilder();
        builder.append(number);
        int padSize = numberLength - builder.length();
        for (int i = 0; i < padSize; i++) {
            builder.insert(0, 0);
        }

        return builder.toString();
    }
}
