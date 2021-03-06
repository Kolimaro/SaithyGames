package games;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Pavel Tokarev, 21.11.2020
 */

public class Drunkard {
  private static final Logger log = LoggerFactory.getLogger(Drunkard.class);
  private static final int CARDS_TOTAL_COUNT = CardUtils.getCardsTotalCount() + 1;
  private static final int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
  private static final int[] playerCardTails = new int[2];
  private static final int[] playerCardHeads = new int[2];

  public static void main(String... args) {
    boolean isFirstWinner = false;
    int iteration = 0;

    dealCards(CardUtils.getShuffledCardDeck());

    do {
      iteration++;
      int card1 = playersCards[0][playerCardTails[0]];
      playerCardTails[0] = incrementIndex(playerCardTails[0]);
      int card2 = playersCards[1][playerCardTails[1]];
      playerCardTails[1] = incrementIndex(playerCardTails[1]);
      log.info("Итерация №{} Игрок №1 карта: {}; игрок №2 карта: {}", iteration, CardUtils.toString(card1), CardUtils.toString(card2));
      if (CardUtils.getPar(card1).ordinal() == CardUtils.getPar(card2).ordinal()) {
        playerCardHeads[0] = incrementIndex(playerCardHeads[0]);
        playersCards[0][playerCardHeads[0]] = card1;
        playerCardHeads[1] = incrementIndex(playerCardHeads[1]);
        playersCards[1][playerCardHeads[1]] = card2;
        log.info("Спор - каждый остается при своих!");
      } else if (CardUtils.getPar(card1).ordinal() > CardUtils.getPar(card2).ordinal()) {
        if (CardUtils.getPar(card1).equals(CardUtils.Par.ACE) && CardUtils.getPar(card2).equals(CardUtils.Par.SIX)) {
          grabCards(1, card1, card2);
          log.info("Выиграл игрок 2!");
          isFirstWinner = false;
        } else {
          grabCards(0, card1, card2);
          log.info("Выиграл игрок 1!");
          isFirstWinner = true;
        }
      } else if (CardUtils.getPar(card1).equals(CardUtils.Par.SIX) && CardUtils.getPar(card2).equals(CardUtils.Par.ACE)) {
        grabCards(0, card1, card2);
        log.info("Выиграл игрок 1!");
        isFirstWinner = true;
      } else {
        grabCards(1, card1, card2);
        log.info("Выиграл игрок 2!");
        isFirstWinner = false;
      }

      log.info("У игрока №1 {} карт, у игрока №2 {} карт", playerCardsCount(0), playerCardsCount(1));

    } while (!playerCardsIsEmpty(0) && !playerCardsIsEmpty(1));

    if (isFirstWinner) {
      log.info("Выиграл первый игрок! Количество итераций: {}", iteration);
    } else {
      log.info("Выиграл второй игрок! Количество итераций: {}", iteration);
    }
  }

  private static int playerCardsCount(int playerIndex) {
    if (playerCardHeads[playerIndex] >= playerCardTails[playerIndex]) {
      return playerCardHeads[playerIndex] - playerCardTails[playerIndex];
    } else {
      return playerCardHeads[playerIndex] + CARDS_TOTAL_COUNT - playerCardTails[playerIndex];
    }
  }

  private static int incrementIndex(int i) {
    return (i + 1) % CARDS_TOTAL_COUNT;
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
