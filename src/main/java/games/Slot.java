package games;

/**
 * @author Pavel Tokarev, 20.11.2020
 */

public class Slot {

    public static void main() {
        int firstCounter = 1;
        int secondCounter = 1;
        int thirdCounter = 1;
        int balance = 100;
        int bet = 10;

        do {
            firstCounter = (firstCounter + (int) Math.round(Math.random() * 100)) % 7;
            secondCounter = (secondCounter + (int) Math.round(Math.random() * 100)) % 7;
            thirdCounter = (thirdCounter + (int) Math.round(Math.random() * 100)) % 7;

            System.out.println("У Вас " + balance + "$, ставка - " + bet);
            System.out.println("Крутим барабаны! Розыгрыш дал следующие результаты:");
            balance -= bet;
            System.out.println("Первый барабан - " + firstCounter + ", второй - " + secondCounter + ", третий - " + thirdCounter);

            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                balance += 1000;
                System.out.println("Поздравляем! Вы выиграли 1000$ Ваш капитал теперь составляет: " + balance + "$\n");
            } else {
                System.out.println("Проигрыш " + bet + "$, Ваш капитал теперь составляет: " + balance + "$\n");
            }
        } while (balance >= bet);
    }
}
