package games;

import org.apache.commons.math3.util.MathArrays;

/**
 * @author Pavel Tokarev, 21.11.2020
 */

public class Drunkard {
    private static final int PARS_TOTAL_COUNT = Par.values().length;
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length + 1;
    private static final int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static final int[] playerCardTails = new int[2];
    private static final int[] playerCardHeads = new int[2];

    public static void main(String... args) {
        boolean isFirstWinner = false;
        int iteration = 0;
        int[] cardDeck = new int[CARDS_TOTAL_COUNT - 1];
        for (int i = 0; i < cardDeck.length; i++) {
            cardDeck[i] = i;
        }
        MathArrays.shuffle(cardDeck);

        dealCards(cardDeck);

        do {
            iteration++;
            int card1 = playersCards[0][playerCardTails[0]];
            playerCardTails[0] = incrementIndex(playerCardTails[0]);
            int card2 = playersCards[1][playerCardTails[1]];
            playerCardTails[1] = incrementIndex(playerCardTails[1]);
            System.out.println("Итерация №" + iteration + " Игрок №1 карта: " + toString(card1)
                                + "; игрок №2 карта: " + toString(card2));
            if (getPar(card1).ordinal() == getPar(card2).ordinal()) {
                playerCardHeads[0] = incrementIndex(playerCardHeads[0]);
                playersCards[0][playerCardHeads[0]] = card1;
                playerCardHeads[1] = incrementIndex(playerCardHeads[1]);
                playersCards[1][playerCardHeads[1]] = card2;
                System.out.println("Спор - каждый остается при своих!");
            } else if (getPar(card1).ordinal() > getPar(card2).ordinal()) {
                if (getPar(card1).equals(Par.ACE) && getPar(card2).equals(Par.SIX)) {
                    grabCards(1, card1, card2);
                    System.out.println("Выиграл игрок 2!");
                    isFirstWinner = false;
                } else {
                    grabCards(0, card1, card2);
                    System.out.println("Выиграл игрок 1!");
                    isFirstWinner = true;
                }
            } else if (getPar(card1).equals(Par.SIX) && getPar(card2).equals(Par.ACE)) {
                grabCards(0, card1, card2);
                System.out.println("Выиграл игрок 1!");
                isFirstWinner = true;
            } else {
                grabCards(1, card1, card2);
                System.out.println("Выиграл игрок 2!");
                isFirstWinner = false;
            }

            System.out.println("У игрока №1 " + playerCardsCount(0) + " карт, у игрока №2 " + playerCardsCount(1) + " карт");

        } while (!playerCardsIsEmpty(0) && !playerCardsIsEmpty(1));

        if (isFirstWinner) {
            System.out.println("Выиграл первый игрок! Количество итераций: " + iteration);
        } else {
            System.out.println("Выиграл второй игрок! Количество итераций: " + iteration);
        }
    }

    enum Suit {
        SPADES,
        HEARTS,
        CLUBS,
        DIAMONDS
    }

    enum Par {
//        TWO,
//        THREE,
//        FOUR,
//        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }

    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    private static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    private static int playerCardsCount(int playerIndex) {
        if (playerCardHeads[playerIndex] >= playerCardTails[playerIndex]) {
            return playerCardHeads[playerIndex] - playerCardTails[playerIndex];
        } else {
            return playerCardHeads[playerIndex] + CARDS_TOTAL_COUNT - playerCardTails[playerIndex];
        }
    }

    private static int incrementIndex(int i) {
        return (i + 1) % (CARDS_TOTAL_COUNT);
    }

    private static boolean playerCardsIsEmpty(int playerIndex) {
        int tail = playerCardTails[playerIndex];
        int head = playerCardHeads[playerIndex];

        return tail == head;
    }

    private static void dealCards(int[] cardDeck) {
        System.arraycopy(cardDeck, 0, playersCards[0], 0, cardDeck.length / 2);
        playerCardHeads[0] = cardDeck.length / 2;
        System.arraycopy(cardDeck, cardDeck.length / 2, playersCards[1], 0, cardDeck.length / 2);
        playerCardHeads[1] = cardDeck.length / 2;
    }

    private static void grabCards(int playerIndex, int card1, int card2) {
        playerCardHeads[playerIndex] = incrementIndex(playerCardHeads[playerIndex]);
        playersCards[playerIndex][playerCardHeads[playerIndex]] = card1;
        playerCardHeads[playerIndex] = incrementIndex(playerCardHeads[playerIndex]);
        playersCards[playerIndex][playerCardHeads[playerIndex]] = card2;
    }
}
