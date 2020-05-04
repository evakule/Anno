import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SsnProcess {
    private static final String FILE_PATH = "src/main/java/prerequisites/tn_ssn4_lookup.csv";
    private final HashMap<String, String> ssnList = new HashMap<>();

    public SsnProcess() {
        String row;
        try (
                BufferedReader csvReader = new BufferedReader(new FileReader(FILE_PATH));
        ) {
            while ((row = csvReader.readLine()) != null) {
                String[] dividedRow = row.split(",");
                ssnList.put(dividedRow[1], dividedRow[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getSsnList() {
        return ssnList;
    }
}
