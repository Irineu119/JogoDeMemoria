public interface ICardManager {
    public ICard getCard(int idx);
    public void shuffle();
    public void generateCards(int number);
    public void clear();
    public void drawCards();
}
