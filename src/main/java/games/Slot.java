package games;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Pavel Tokarev, 20.11.2020
 */

public class Slot {
    private static final Logger log = LoggerFactory.getLogger(Slot.class);

    public static void main(String... args) {
        int firstCounter = 1;
        int secondCounter = 1;
        int thirdCounter = 1;
        int balance = 100;
        int bet = 10;

        do {
            firstCounter = (firstCounter + (int) Math.round(Math.random() * 100)) % 7;
            secondCounter = (secondCounter + (int) Math.round(Math.random() * 100)) % 7;
            thirdCounter = (thirdCounter + (int) Math.round(Math.random() * 100)) % 7;

            log.info("У Вас {}$, ставка - {}", balance, bet);
            log.info("Крутим барабаны! Розыгрыш дал следующие результаты:");
            balance -= bet;
            log.info("Первый барабан - {}, второй - {}, третий - {}", firstCounter, secondCounter, thirdCounter);

            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                balance += 1000;
                log.info("Поздравляем! Вы выиграли 1000$ Ваш капитал теперь составляет: {}$\n", balance);
            } else {
                log.info("Проигрыш {}$, Ваш капитал теперь составляет: {}$\n", bet, balance);
            }
        } while (balance >= bet);
    }
}
