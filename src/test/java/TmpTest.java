import java.io.File;

public class TmpTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("json/microsoft_windows_code_page.json"));
        System.out.println(System.getProperty("user.dir"));
        File localMapFile = new File(System.getProperty("user.dir"), "src/main/resources/" + "json/microsoft_windows_code_page.json");
        System.out.println(localMapFile.toPath());
    }
}
