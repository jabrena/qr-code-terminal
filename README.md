# QR Code Terminal

Generate QR Code from the terminal

```bash
sdk env

./mvnw compile exec:java -Dexec.mainClass="info.jab.cli.TerminalQRCode" -Dexec.args="--url https://www.as.com"

jbang src/main/java/info/jab/cli/TerminalQRCode.java
jbang src/main/java/info/jab/cli/TerminalQRCode.java --url https://www.as.com
```