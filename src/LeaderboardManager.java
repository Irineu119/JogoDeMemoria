import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LeaderboardManager implements ILeaderboardManager {
    List<LeaderboardData> data;

    public LeaderboardManager() {
        data = new ArrayList<LeaderboardData>();
    }

    @Override
    public void display() {
        if (data.isEmpty()) {
            System.out.println("Sem recordes salvos.");
            return;
        }

        for (LeaderboardData data : data) {
            int minutes = (data.time % 3600) / 60;
            int seconds = data.time % 60;
            System.out.printf("%s - Cartas: %d. Tempo: %02d:%02d.\n", data.name, data.cardCount, minutes, seconds);
        }
    }

    @Override
    public void loadFromFile(String fileName) {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            return;
        }

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                LeaderboardData storedData = new LeaderboardData();
                String[] strData = line.split("\t");
                storedData.name = strData[0];
                storedData.cardCount = Integer.parseInt(strData[1]);
                storedData.time = Integer.parseInt(strData[2]);
                add(storedData);
            }
        }catch (IOException e) {
            System.out.println("Erro ao ler recordes.");
            e.printStackTrace();
        }
    }

    @Override
    public void add(LeaderboardData data) {
        this.data.add(data);
        sort();
    }

    @Override
    public void sort() {
        data.sort(new Comparator<LeaderboardData>() {
            @Override
            public int compare(LeaderboardData data1, LeaderboardData data2) {
                if (data1.cardCount == data2.cardCount) {
                    return Integer.compare(data1.time, data2.time);
                }
                return Integer.compare(data2.cardCount, data1.cardCount);
            }
        });
    }

    @Override
    public void saveToFile(String fileName) {
        if (data.isEmpty()) {
            return;
        }

        String s = "";
        for (LeaderboardData data : data) {
            s += data.name;
            s += "\t";
            s += data.cardCount;
            s += "\t";
            s += data.time;
            s += "\n";
        }

        try {
            Files.write(Paths.get(fileName)
                    , s.getBytes());
        } catch (IOException e) {
            System.out.println("Erro ao salvar recordes.");
            e.printStackTrace();
        }
    }
}
