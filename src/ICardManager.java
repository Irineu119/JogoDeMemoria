public interface ICardManager {
    ICard getCard(int idx);
    void shuffle();
    void generateCards(int number);
    void drawCards();
}
