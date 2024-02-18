package ru.skaliush.superlab.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LineReader {
    private final InputStreamReader inputStreamReader;

    private Integer cachedCharCode = null;

    public LineReader(InputStream inputStream) {
        inputStreamReader = new InputStreamReader(inputStream);
    }

    public String nextLine() {
        StringBuilder builder = new StringBuilder();
        while (true) {
            int charCode = cachedCharCode != null ? cachedCharCode : readCharCode();
            cachedCharCode = null;
            if (charCode == -1) {
                throw new StopProgramException();
            }
            if (charCode == '\r' || charCode == '\n') {
                break;
            }
            builder.append((char) charCode);
        }
        return builder.toString();
    }

    public boolean hasNextLine() {
        int charCode = readCharCode();
        if (charCode == -1) {
            return false;
        }
        cachedCharCode = charCode;
        return true;
    }

    private int readCharCode() {
        int charCode;
        try {
            charCode = inputStreamReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return charCode;
    }
}
