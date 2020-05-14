import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Runner {
    private static final String FILE_PATH = "C:/Users/egor.vakulenko/Desktop/TestData.csv";
    private static final String NEW_FILE_PATH = "C:/Users/egor.vakulenko/Desktop/Players_anonymized.csv";

    public static void main(String[] args) {
        encryptFile();
    }

    private static void encryptFile() {
        Encryption encryption = new Encryption();
        int counter = 0;
        String row;
        try (
                BufferedReader csvReader = new BufferedReader(new FileReader(FILE_PATH));
                FileWriter csvWriter = new FileWriter(NEW_FILE_PATH)
        ) {
            while ((row = csvReader.readLine()) != null) {

                if (counter > 0) {
                    String[] rowColumns = row.split(",");
                    String[] changedRow = encryption.getChangedRow(rowColumns);
                    row = build(changedRow);
                    csvWriter.append(row).append("\n");
                } else {
                    String[] firstRow = row.split(",");
                    row = build(firstRow);
                    csvWriter.append(row).append("\n");
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String build(String[] rowColumns) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rowColumns.length; i++) {
            builder.append(rowColumns[i]);
            builder.append(",");
        }
        return builder.toString();
    }
}
