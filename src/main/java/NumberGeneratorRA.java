import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.RecursiveAction;

// Generating numbers via RecursiveAction
public class NumberGeneratorRA extends RecursiveAction {
    private static long start = System.currentTimeMillis();
    private static char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    private static int regionsCount = 100;

    private static volatile int region = 0;

    NumberGeneratorRA() {
        region++;
        makeRegionNumber();
    }

    @Override
    protected void compute() {
        while (region < regionsCount) {
            NumberGeneratorRA numberGeneratorRA = new NumberGeneratorRA();
            numberGeneratorRA.fork();
        }
    }

    // generate car number for 1 region
    private static void makeRegionNumber() {
        try {
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
