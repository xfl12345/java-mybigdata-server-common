import cc.xfl12345.mybigdata.server.common.utility.ConsoleCharsetUtils;

public class TestConsoleCharsetUtils {
    public static void main(String[] args) throws Exception {
        ConsoleCharsetUtils consoleCharsetUtils = new ConsoleCharsetUtils();
        consoleCharsetUtils.init();
        System.out.println("Current console charset name is [" + consoleCharsetUtils.getCharset().name() + "]");
    }
}
