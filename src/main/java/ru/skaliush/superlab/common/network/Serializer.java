package ru.skaliush.superlab.common.network;

import java.io.*;
import java.util.Base64;

public class Serializer implements Serializable {
    public String serializeToString(Object o) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    public Object deserializeFromString(String serialized) {
        byte[] decodedData = Base64.getDecoder().decode(serialized);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(decodedData))) {
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
