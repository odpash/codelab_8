package org.olegpash.common.util;

import org.olegpash.common.abstractions.AbstractRequest;
import org.olegpash.common.abstractions.AbstractResponse;
import org.olegpash.common.util.requests.Request;
import org.olegpash.common.util.responses.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public final class DeSerializer {

    private DeSerializer() {
    }

    public static AbstractRequest deSerializeRequest(byte[] acceptedBuf) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(acceptedBuf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        AbstractRequest request = (AbstractRequest) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return request;
    }

    public static AbstractResponse deSerializeResponse(byte[] acceptedBuf) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(acceptedBuf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        AbstractResponse response = (AbstractResponse) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return response;
    }
    public static Request deSerializeRequestOld(byte[] acceptedBuf) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(acceptedBuf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request request = (Request) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return request;
    }

    public static Response deSerializeResponseOld(byte[] acceptedBuf) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(acceptedBuf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Response response = (Response) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return response;
    }
}