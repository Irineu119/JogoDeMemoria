import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardManager implements ICardManager {
    List<Card> cards;

    public CardManager() {
        cards = new ArrayList<Card>();
    }

    @Override
    public ICard getCard(int idx) {
        if (idx >= 0 && idx < cards.size()) {
            return cards.get(idx);
        }

        return null;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public void generateCards(int number) {
        if ((number % 4) != 0)
            return;

        for (int i = 0; i < (number / 2); i++) {
            ESuit suit = ESuit.values()[(int)(Math.random() * 4)];
            ECardNumber cardNumber = ECardNumber.values()[(int)(Math.random() * 13)];
            cards.add(new Card(cardNumber, suit));
            cards.add(new Card(cardNumber, suit));
        }
    }

    @Override
    public void clear() {
        cards.clear();
    }

    @Override
    public void drawCards() {
        for (int i = 0; i < cards.size(); i += 4) {
            for (int j = 0; j < 11; j++) {
                for (int k = i; k < (i + 4); k++) {
                    Card card = cards.get(k);
                    String drawing = card.isRevealed() ? revealedCard : hiddenCard;
                    for (int c = 0; drawing.charAt(c + (j * 19)) != '\n'; c++ ) {
                        char toPrint = drawing.charAt(c + (j * 19));
                        if (toPrint == 'v')
                            toPrint = card.getNumber().toChar();
                        else if (toPrint == 'n')
                            toPrint = card.getSuit().toChar();

                        System.out.print(toPrint);
                        if (toPrint == '1') {
                            System.out.print('0');
                            c++;
                        }
                    }
                    if (k == (i + 3))
                        System.out.print("\n");
                    else
                        System.out.print(" ");
                }
            }
        }
    }

    public static final String revealedCard =
            """
           _________________
            |               |
            |               |
            |       v       |
            |               |
            |               |
            |               |
            |               |
            |       n       |
            |               |
            |_______________|
           """;
    public static final String hiddenCard =
            """
            _________________
             |/\\/\\/\\/\\/\\/\\/\\/|
             |\\/\\/\\/\\/\\/\\/\\/\\|
             |/\\/\\/\\/\\/\\/\\/\\/|
             |\\/\\/\\/\\/\\/\\/\\/\\|
             |/\\/\\/\\/\\/\\/\\/\\/|
             |\\/\\/\\/\\/\\/\\/\\/\\|
             |/\\/\\/\\/\\/\\/\\/\\/|
             |\\/\\/\\/\\/\\/\\/\\/\\|
             |/\\/\\/\\/\\/\\/\\/\\/|
             |\\/\\/\\/\\/\\/\\/\\/\\|
            """;
}
