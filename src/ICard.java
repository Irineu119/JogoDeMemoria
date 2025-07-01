public interface ICard {
    public ECardNumber getNumber();
    public ESuit getSuit();
    public boolean isRevealed();
    public void setRevealed(boolean revealed);
}
