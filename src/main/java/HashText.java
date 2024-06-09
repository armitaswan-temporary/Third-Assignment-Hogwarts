import org.apache.commons.codec.digest.DigestUtils;
public class HashText {
    public static String encodeText(String text) {
        return DigestUtils.sha256Hex(text);
    }
}