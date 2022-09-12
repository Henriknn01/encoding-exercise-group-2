package no.ntnu.datakomm;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The encoder for exercise in IDATA2304. Your task is to implement the missing functions according to the protocol.
 */
public class Encoder {
    /*
     * ------------------------------------------------------------
     * The protocol for encoding
     * ------------------------------------------------------------
     * Human-readable message strings are encoded to bit-strings according to the following rules:
     * 1) Only the following characters are allowed in the plain-text, human-readable messages:
     *   - lowercase letters a-z
     *   - uppercase letters A-Z
     *   - digits 0-9
     *   - space ' '
     *   - comma ','
     *   - period '.'
     *   - exclamation mark '!'
     * 2) Each character of the message is translated to an integer code first, by taking the ASCII code of that
     *    character. See ASCII table here: https://www.asciitable.com/
     *    Examples: uppercase letter `A` has ASCII code 65, letter `B` has ASCII code 66, etc.; while lowercase
     *    letter `a` has code 97, lowercase `b` has code 98, etc.
     * 3) Then the integer code is translated to a binary string, always eight bits for each integer code.
     *    The binary string contains the most significant bit first.
     *    Examples: number 65 is translated to "01000001", 66 is translated to "01000010", 98 is translated to
     *    "01100010". Number 0 is translated to "00000000" and number 255 is translated to "11111111".
     * 4) The binary strings for each character are then concatenated in the order as they appear.
     *    Examples:
     *      "ABC" -> 65, 66, 67 -> "010000010100001001000011"
     *      "Hi!" -> 72, 105, 33 -> "010010000110100100100001"
     *
     * Conversion in the opposite direction happens according to the same rules, just in the opposite direction:
     * 1) Split the bit string into 8-character chunks (8-bit blocks)
     * 2) Convert each 8-bit block to an integer
     * 3) Convert the integer to a character, using the ASCII table
     * 4) Only the same limited set of characters is allowed (a-z, A-Z, 0-9, space, comma, period, exclamation mark).
     * If the decoded symbols is not in the allowed character set, an exception must be thrown.
     * 5) Concatenate the individual characters together to form a message
     */
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * A dummy help-printer. You should not run this method. Run unit tests instead!
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("This is the encoding exercise, used in the course IDATA2304!");
        System.out.println("You can run the unit tests by either executing `mvn test` in the terminal, or");
        System.out.println("right click on the src/test/java and choose Run 'All tests'");
        // You can use this method for some experimentation, if needed - run som encoding and decoding tests
        // Better yet - create your own unit tests (or run those provided)
        String testString = "test";
        String ecodedMessage = encode(testString);
        String decodedMessage = decode(ecodedMessage);

        System.out.println("original= " + testString);
        System.out.println("ecoded= " + ecodedMessage);
        System.out.println("decoded= " + decodedMessage);
    }

    /**
     * An encoding function. Takes in a "plaintext message", returns a bit array
     *
     * @param message A message where each character can only be one of the allowed symbols, see the protocol
     * @return The message encoded as a bit string according to the protocol described above
     * each bit must be represented as one character in a string. For example, if the result is 10101011,
     * return the string "10101011"
     * If the input message is null or an empty string, return null
     * If the input message is an empty string, return empty string
     * @throws IllegalArgumentException If the message contains an illegal character (for example, Å, -, [, etc)
     */
    public static String encode(String message) throws IllegalArgumentException {
        byte[] bytes = message.getBytes(UTF_8);
        StringBuilder binaryMessage = new StringBuilder();
        for(byte a: bytes){
            int byteVal = a;
            for (int i = 0; i < 8; i++) {
                binaryMessage.append((byteVal & 128) == 0 ? 0 : 1);
                byteVal <<= 1;
            }
            binaryMessage.append(" ");
        }
        return binaryMessage.toString();
    }

    /**
     * Read a binary signal (ones and zeroes), decode it to a human-readable message.
     * Inverse function for encode(m). When implemented correctly, decode(encode(m)) == m
     *
     * @param binaryString The signal in a binary-string format, consisting of ones and zeroes, as a string. For example,
     *        binary 10101011 will be represented as a string "10101011".
     *        Constraints for the input data:
     *        - Each character in the binaryString must be either '0' or '1', no other values allowed
     *        - The binaryString must always consist of 8-character blocks, where each block will be
     *        translated to one character in the decoded message. For example, "01000001" is
     *        translated to "A".
     * @return Decoded message, as a string.
     * If the binaryString is null, return null.
     * If the binaryString is an empty string, return empty string
     * @throws IllegalArgumentException If the format for binaryString is invalid
     */
    public static String decode(String binaryString) throws IllegalArgumentException {
        return Arrays.stream(binaryString.split(" "))
            .map(binary -> Integer.parseInt(binary, 2))
            .map(Character::toString)
            .collect(Collectors.joining());
    }
}