public interface ICard {
    ECardNumber getNumber();
    ESuit getSuit();
    boolean isRevealed();
    void setRevealed(boolean revealed);
}
