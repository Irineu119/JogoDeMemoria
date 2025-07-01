public interface ILeaderboardManager {
    void display();
    void loadFromFile(String fileName);
    void add(LeaderboardData data);
    void sort();
    void saveToFile(String fileName);
}
