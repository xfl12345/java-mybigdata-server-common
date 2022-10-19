package cc.xfl12345.mybigdata.server.common.utility;

import com.alibaba.fastjson2.JSONReader;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.Kernel32;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.LinkedHashMap;

@Slf4j
public class ConsoleCharsetUtils {
    protected String microsoftWindowsCodePageJsonResourcePath = "json/microsoft_windows_code_page.json";

    protected LinkedHashMap<String, String> microsoftWindowsCodePageMap = new LinkedHashMap<>();

    @PostConstruct
    public void init() throws Exception {
        URL localMapFileURL = Thread.currentThread().getContextClassLoader().getResource(microsoftWindowsCodePageJsonResourcePath);
        if (localMapFileURL != null) {
            JSONReader reader = JSONReader.of(
                localMapFileURL.openConnection().getInputStream(),
                StandardCharsets.UTF_8
            );
            reader.read(
                microsoftWindowsCodePageMap,
                0
            );
            reader.close();
        } else {
            throw new FileNotFoundException(microsoftWindowsCodePageJsonResourcePath);
        }
    }

    /**
     * 尝试获取当前环境的编码。如果获取失败，则返回 null
     */
    public Charset getCharset() {
        Charset charset = null;
        switch (Platform.getOSType()) {
            case Platform.WINDOWS -> {
                String charsetName = microsoftWindowsCodePageMap.get(
                    "" + getWindowsShellCodePageNumber()
                );
                charset = getCharsetForName(charsetName);
            }
            case Platform.LINUX -> {
                String currentLang = System.getenv("LANG");
                int dotSplitCharacterIndex = currentLang.lastIndexOf('.');
                if (dotSplitCharacterIndex > 0 && dotSplitCharacterIndex < currentLang.length() - 2) {
                    String charsetName = currentLang.substring(dotSplitCharacterIndex + 1);
                    charset = getCharsetForName(charsetName);
                }
            }
        }

        return charset;
    }

    protected int getWindowsShellCodePageNumber() {
        // String codePageNumber = null;
        // String content = null;
        // try {
        //     Process process = Runtime.getRuntime().exec("cmd /c chcp");
        //     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //     content = bufferedReader.readLine();
        //     bufferedReader.close();
        // } catch (IOException e) {
        //     log.warn(e.getMessage(), e);
        // }
        //
        // if (content != null) {
        //     // 10 个 零
        //     StringBuilder codePageNumberBuilder = new StringBuilder("0000000000");
        //     // 获取最后面的那段数字
        //     for (int i = 0; i < content.length() && i < codePageNumberBuilder.length(); i++) {
        //         char currentChar = content.charAt(content.length() - 1 - i);
        //         if (!Character.isDigit(currentChar)) {
        //             break;
        //         }
        //         codePageNumberBuilder.setCharAt(codePageNumberBuilder.length() - 1 - i, currentChar);
        //     }
        //     codePageNumber = codePageNumberBuilder.toString();
        // }
        return Kernel32.INSTANCE.GetConsoleCP();
    }


    protected Charset getCharsetForName(String charsetName) {
        Charset charset = null;
        try {
            charset = Charset.forName(charsetName);
        } catch (UnsupportedCharsetException e) {
            log.warn("Unsupported charset: " + e.getCharsetName());
        } catch (IllegalCharsetNameException e) {
            log.warn("Illegal charset name: " + e.getCharsetName());
        } catch (Exception e) {
            // ignore
        }

        return charset;
    }
}
