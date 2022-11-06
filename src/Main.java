
import util.Start;

public class Main {
    public static void main(String[] args) {

        try {
            Start.menu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
