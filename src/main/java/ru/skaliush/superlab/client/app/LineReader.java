package ru.skaliush.superlab.client.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LineReader {
    private final InputStreamReader inputStreamReader;

    private Integer cachedCharCode = null;
    private boolean exceptionOnEOL = false;

    public LineReader(InputStream inputStream) {
        inputStreamReader = new InputStreamReader(inputStream);
    }

    public LineReader(InputStream inputStream, boolean exceptionOnEOL) {
        inputStreamReader = new InputStreamReader(inputStream);
        this.exceptionOnEOL = exceptionOnEOL;
    }

    public String nextLine() {
        StringBuilder builder = new StringBuilder();
        if (cachedCharCode != null) {
            builder.append((char) cachedCharCode.intValue());
            cachedCharCode = null;
        }
        while (true) {
            int charCode = readCharCode();
            if (charCode == -1) {
                if (exceptionOnEOL) {
                    throw new EndOfFileException();
                } else {
                    break;
                }
            } else if (charCode == '\n') {
                break;
            } else if (charCode == '\r') {
                readCharCode(); // for crlf
                break;
            }
            builder.append((char) charCode);
        }
        return builder.toString();
    }

    public boolean hasNextLine() {
        if (cachedCharCode != null) {
            return true;
        }
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
