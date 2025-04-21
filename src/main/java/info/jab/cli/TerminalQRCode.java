///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.7.6
//DEPS com.github.lalyos:jfiglet:0.0.9
//DEPS com.diogonunes:JColor:5.5.1
//DEPS com.google.zxing:core:3.5.1
//DEPS com.google.zxing:javase:3.5.1

package info.jab.cli;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
    name = "TerminalQRCode", 
    description = "Generates a QR code from the given text and prints it to the terminal.",
    mixinStandardHelpOptions = true, 
    usageHelpAutoWidth = true
)
public class TerminalQRCode implements Callable<Integer> {

    private static final int DEFAULT_SIZE = 25;

    @Option(
        names = {"--url"}, 
        required = true, 
        description = "The URL to encode into the QR code.")
    private String urlToEncode;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new TerminalQRCode()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        generateAndPrintQRCode(urlToEncode);
        return 0; // Return 0 on success
    }

    private static void generateAndPrintQRCode(String urlToEncode) {
        try {
            // Generate QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(urlToEncode, BarcodeFormat.QR_CODE, DEFAULT_SIZE, DEFAULT_SIZE);
            
            // Print QR code to terminal
            for (int y = 0; y < bitMatrix.getHeight(); y++) {
                for (int x = 0; x < bitMatrix.getWidth(); x++) {
                    // Print black module as █ and white module as space
                    System.out.print(bitMatrix.get(x, y) ? "██" : "  ");
                }
                System.out.println();
            }
            
            System.out.println("QR Code url: " + urlToEncode);
            
        } catch (WriterException e) {
            System.err.println("Error generating QR code: " + e.getMessage());
        }
    }
}