public class Card implements ICard {
    ECardNumber number;
    ESuit suit;
    boolean isRevealed;

    public Card(ECardNumber number, ESuit suit) {
        this.number = number;
        this.suit = suit;
        isRevealed = false;
    }

    @Override
    public ECardNumber getNumber() {
        return number;
    }

    @Override
    public ESuit getSuit() {
        return suit;
    }

    @Override
    public boolean isRevealed() {
        return isRevealed;
    }

    @Override
    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Card) {
            Card card = (Card) o;
            return number == card.number && suit == card.suit;
        }

        return false;
    }
}
