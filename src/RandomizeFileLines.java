import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomizeFileLines {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java RandomizeFileLines <input_file> <output_directory>");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputDirectory = args[1];

        try {
            List<String> lines = readNonEmptyLinesFromFile(inputFile);

            Collections.shuffle(lines);

            int blockSize = 500;
            int blockCount = (int) Math.ceil((double) lines.size() / blockSize);
            for (int i = 0; i < blockCount; i++) {
                int fromIndex = i * blockSize;
                int toIndex = Math.min(fromIndex + blockSize, lines.size());
                List<String> block = lines.subList(fromIndex, toIndex);
                String outputFileName = outputDirectory + "/cities" + (i + 1) + ".txt";
                writeLinesToFile(outputFileName, block);
            }

            System.out.println("Randomization, filtering, and splitting complete.");
        } catch (IOException e) {
            System.out.println("Error while processing files: " + e.getMessage());
        }
    }

    private static List<String> readNonEmptyLinesFromFile(String inputFile) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        }

        return lines;
    }

    private static void writeLinesToFile(String outputFile, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
