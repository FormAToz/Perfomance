import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Generating numbers via Single Thread
public class NumberGeneratorST {
    private static long start = System.currentTimeMillis();
    private static char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    // generate car numbers for 100 regions
    public static void makeRegionNumber() {
        try {
            for (int region = 1; region <= 100; region++) {
                PrintWriter writer = new PrintWriter("res/region_" + region + ".txt");
                StringBuilder builder = new StringBuilder();

                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                builder.append(firstLetter)
                                        .append(padNumber(number, 3))
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(padNumber(region, 2))
                                        .append("\n");
                            }
                        }
                    }
                }
                writer.write(builder.toString());
                writer.flush();
                writer.close();
            }
            System.out.println("Поток: " + Thread.currentThread().getName() + " - " + (System.currentTimeMillis() - start) + " ms");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}
