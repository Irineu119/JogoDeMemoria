//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    static ICard chosenCard = null;
    static ILeaderboardManager leaderboardManager = new LeaderboardManager();

    public static void main(String[] args) {
        leaderboardManager.loadFromFile("leaderboard.txt");
        System.out.println("Escolha uma opção:");
        System.out.println("\t1. Jogar jogo de memória.");
        System.out.println("\t2. Ver placar.");
        System.out.println("\t3. Sair.");
        Scanner sc = new Scanner(System.in);
        char choice = sc.nextLine().charAt(0);
        switch (choice) {
            case '1':
                startGame();
                break;
            case '2':
                leaderboardManager.display();
                break;
        }
    }

    public static void startGame() {
        long startTime = System.currentTimeMillis() / 1000;
        ICardManager manager = new CardManager();
        System.out.print("Escolha um número de cartas (4 até 52 e somente divisível por 4): ");
        Scanner scanner = new Scanner(System.in);
        int cardNumber = scanner.nextInt();
        if (cardNumber < 4 || cardNumber > 52 || (cardNumber % 4) != 0) {
            System.out.println("Número inválido de cartas.");
            String[] args = {};
            return;
        }

        manager.generateCards(cardNumber);
        manager.shuffle();
        int paresFeitos = 0;
        while (paresFeitos < (cardNumber / 2)) {
            manager.drawCards();
            System.out.printf("Escolha uma carta (1 - %d): ", cardNumber);
            int idx = scanner.nextInt();
            idx--;
            if (idx < 0 || idx > cardNumber) {
                System.out.println("Número de carta inválido.");
                continue;
            }

            ICard card = manager.getCard(idx);
            if (card.isRevealed()) {
                System.out.println("Carta ja foi escolhida.");
                continue;
            }

            card.setRevealed(true);
            if (chosenCard != null) {
                if (chosenCard.equals(card)) {
                    paresFeitos++;
                }
                else {
                    manager.drawCards();
                    chosenCard.setRevealed(false);
                    card.setRevealed(false);
                    try {
                        Thread.sleep(3 * 1000);
                    }
                    catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                chosenCard = null;
            }
            else {
                chosenCard = card;
            }
        }
        manager.drawCards();
        long endTime = System.currentTimeMillis() / 1000;
        finishGame((int)(endTime - startTime), cardNumber);
    }

    public static void finishGame(int timeElapsed, int cardCount) {
        System.out.print("Parabéns! Deseja salvar seu recorde? (Y/N): ");
        Scanner scanner = new Scanner(System.in);
        char choice = scanner.nextLine().charAt(0);
        if (choice != 'Y' && choice != 'y')
            return;

        System.out.flush();
        System.out.print("Digite seu nome: ");
        String name = scanner.nextLine();
        LeaderboardData data = new LeaderboardData();
        data.name = name;
        data.cardCount = cardCount;
        data.time = timeElapsed;
        leaderboardManager.add(data);
        leaderboardManager.saveToFile("leaderboard.txt");
        String[] args = {};
    }
}