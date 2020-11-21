package games;

import java.io.IOException;

/**
 * @author Pavel Tokarev, 21.11.2020
 */

public class Choice {
    public static void main(String[] args) throws IOException {
        System.out.println("Выберите игру:\n1 - 'однорукий бандит', 2 - 'пьяница'");
        switch (System.in.read()) {
            case '1': Slot.main(); break;
            case '2': Drunkard.main(); break;
            default:
                System.out.println("Игры с таким номером нет!");
        }
    }
}
