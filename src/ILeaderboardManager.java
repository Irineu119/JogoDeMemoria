public interface ILeaderboardManager {
    public void display();
    public void loadFromFile(String fileName);
    public void add(LeaderboardData data);
    public void sort();
    public void saveToFile(String fileName);
}
