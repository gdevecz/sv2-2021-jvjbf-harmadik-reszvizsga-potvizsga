package examinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExamService {

    public static final double MIN_LIMIT = 0.51;

    private int theoryMax;

    private int practiceMax;

    private Map<String, ExamResult> results = new TreeMap<>();

    public void readFromFIle(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = br.readLine();
            String[] maxDataParts = line.split(" ");
            theoryMax = Integer.parseInt(maxDataParts[0]);
            practiceMax = Integer.parseInt(maxDataParts[1]);
            while ((line = br.readLine()) != null) {
                addNewResult(line);
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file: " + path, ioe);
        }
    }

    private void addNewResult(String line) {
        String[] parts = line.split("[;]");
        String[] resultParts = parts[1].split(" ");
        results.put(parts[0],
                new ExamResult(Integer.parseInt(resultParts[0]), Integer.parseInt(resultParts[1])));
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public Map<String, ExamResult> getResults() {
        return results;
    }

    public List<String> findPeopleFailed() {
        return results.entrySet().stream()
                .filter(e -> e.getValue().getTheory() < theoryMax * MIN_LIMIT
                        || e.getValue().getPractice() < practiceMax * MIN_LIMIT)
                .map(Map.Entry::getKey)
                .toList();
    }

    public String findBestPerson() {
        return results.entrySet().stream()
                .filter(e -> !findPeopleFailed().contains(e.getKey()))
                .max(Comparator.comparing(e -> e.getValue().getTheory() + e.getValue().getPractice()))
                .orElseThrow(() -> new IllegalStateException("Empty.")).getKey();
    }
}
