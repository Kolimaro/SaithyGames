package games;

import org.apache.commons.math3.util.MathArrays;

/**
 * @author Pavel Tokarev, 28.11.2020
 */

public class CardUtils {
  private static final int PARS_TOTAL_COUNT = Par.values().length;
  private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;

  enum Suit {
    SPADES,
    HEARTS,
    CLUBS,
    DIAMONDS
  }

  enum Par {
    //    TWO,
    //    THREE,
    //    FOUR,
    //    FIVE,
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

  public static Suit getSuit(int cardNumber) {
    return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
  }

  public static Par getPar(int cardNumber) {
    return Par.values()[cardNumber % PARS_TOTAL_COUNT];
  }

  public static String toString(int cardNumber) {
    return getPar(cardNumber) + " " + getSuit(cardNumber);
  }

  public static int[] getShuffledCardDeck() {
    int[] cardDeck = new int[CARDS_TOTAL_COUNT];
    for (int i = 0; i < cardDeck.length; i++) {
      cardDeck[i] = i;
    }
    MathArrays.shuffle(cardDeck);
    return cardDeck;
  }

  public static int getParsTotalCount() {
    return PARS_TOTAL_COUNT;
  }

  public static int getCardsTotalCount() {
    return CARDS_TOTAL_COUNT;
  }
}
