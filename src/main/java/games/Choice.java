package games;

import java.io.IOException;

/**
 * @author Pavel Tokarev, 21.11.2020
 */

public class Choice {
    static final String LINE_SEPARATOR = System.lineSeparator();

    public static void main(String[] args) throws IOException {
        System.out.println("Выберите игру:\n1 - 'однорукий бандит', 2 - 'пьяница', 3 - 'Очко'");
        switch (getCharacterFromUser()) {
            case '1': Slot.main(); break;
            case '2': Drunkard.main(); break;
            case '3': BlackJack.main(); break;
            default:
                System.out.println("Игры с таким номером нет!");
        }
    }

    static char getCharacterFromUser() throws IOException {
        byte[] input = new byte[LINE_SEPARATOR.length()];
        if (System.in.read(input) != input.length) {
            throw new RuntimeException("Пользователь ввел недостаточное количество символов");
        }
        return (char) input[0];
    }

    static boolean confirm(String message) throws IOException {
        System.out.println(message + " 'Y' - Да, {любой другой символ} - нет (Чтобы выйти из игры нажмите Ctrl + C)");
        switch (getCharacterFromUser()) {
            case 'Y':
            case 'y': return true;
            default: return false;
        }
    }
}
