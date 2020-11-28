package games;

import java.io.IOException;

/**
 * @author Pavel Tokarev, 28.11.2020
 */

public class BlackJack {
    private static final int[] playersMoney = {100, 100};
    private static final int MAX_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 8;
    private static int[] cardDeck;
    private static int cursor;
    private static int[][] playersCards;
    private static int[] playersCursors;

    public static void main(String... args) throws IOException {

        do {
            initRound();
            for (int i = 0; i < 2; i++) {
                System.out.println("Вам выпала карта " + CardUtils.toString(addCardToPlayer(0)));
            }
            boolean isContinuing = true;
            while (isContinuing) {
                if (sum(0) < MAX_VALUE) {
                    if (Choice.confirm("Хотите взять еще?")) {
                        System.out.println("Вам выпала карта " + CardUtils.toString(addCardToPlayer(0)));
                    } else {
                        isContinuing = false;
                    }
                } else {
                    isContinuing = false;
                }
            }

            for (int i = 0; i < 2; i++) {
                System.out.println("Компьютеру выпала карта " + CardUtils.toString(addCardToPlayer(1)));
            }
            while (sum(1) < 17) {
                System.out.println("Компьютер решил взят еще и ему выпала карта " + CardUtils.toString(addCardToPlayer(1)));
            }

            System.out.println("Сумма Ваших очков - " + getFinalSum(0) + ", компьютера - " + getFinalSum(1));
            if (getFinalSum(0) > getFinalSum(1)) {
                System.out.println("Вы выиграли раунд! Получаете 10$");
                playersMoney[0] += 10;
                playersMoney[1] -= 10;
            } else if (getFinalSum(0) < getFinalSum(1)) {
                System.out.println("Вы проиграли раунд. Теряете 10$");
                playersMoney[0] -= 10;
                playersMoney[1] += 10;
            } else {
                System.out.println("Ничья!");
            }


        } while (playersMoney[0] > 0 && playersMoney[1] > 0);

        if (playersMoney[0] > 0) {
            System.out.println("Вы выиграли! Поздравляем!");
        } else {
            System.out.println("Вы проиграли. Соболезнуем...");
        }

    }

    private static void initRound() {
        System.out.println("\nУ Вас " + playersMoney[0] + "$, у компьютера - " + playersMoney[1] + "$. Начинаем новый раунд!");
        cardDeck = CardUtils.getShuffledCardDeck();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[]{0, 0};
        cursor = 0;
    }

    private static int value(int card) {
        switch (CardUtils.getPar(card)) {
            case JACK: return 2;
            case QUEEN: return 3;
            case KING: return 4;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case ACE: return 11;
            default: return 0;
        }
    }

    private static int addCardToPlayer(int player) {
        int card = cardDeck[cursor];
        playersCards[player][playersCursors[player]] = card;
        playersCursors[player]++;
        cursor++;
        return card;
    }

    private static int sum(int player) {
        int sum = 0;
        for (int i = 0; i < playersCursors[player]; i++) {
            sum += value(playersCards[player][i]);
        }
        return sum;
    }

    private static int getFinalSum(int player) {
        int sum = sum(player);
        return sum <= 21 ? sum : 0;
    }
}
