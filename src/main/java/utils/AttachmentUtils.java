package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.InputStream;
import java.util.List;

public class AttachmentUtils {

    @Attachment
    public static String makeStringAttachment(List<String> collection) {
        return collection.toString();
    }

    @Attachment(type = "image/png", value = "try to use param {attName}")
    public byte[] attachPngImage(byte[] source, String attName) {
        return source;
    }

    public void attachFromInputStream(String name, InputStream is) {
        Allure.addAttachment(name, is);
    }
}