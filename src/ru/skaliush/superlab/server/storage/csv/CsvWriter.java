package ru.skaliush.superlab.server.storage.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    private char delimiter = ',';
    private char textQualifier = '"';

    public CsvWriter() {
    }

    public CsvWriter(char delimiter, char textQualifier) {
        this.delimiter = delimiter;
        this.textQualifier = textQualifier;
    }

    public void write(String fileName, List<Row> rows) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Row row : rows) {
                StringBuilder line = new StringBuilder();
                for (String cell : row.cells()) {
                    if (cell == null) {
                        cell = "";
                    }
                    if (!line.isEmpty()) {
                        line.append(delimiter);
                    }
                    String qualifierStr = String.valueOf(textQualifier);
                    String preparedColumn = cell.replaceAll(qualifierStr, qualifierStr + qualifierStr);
                    if (containsWhiteSpace(preparedColumn)) {
                        line.append(textQualifier);
                        line.append(preparedColumn);
                        line.append(textQualifier);
                    } else {
                        line.append(preparedColumn);
                    }
                }
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean containsWhiteSpace(String str) {
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                if (Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
