package player;

import console.ConsoleOutput;
import storage.Storage;

public class Player extends Thread {
    public String playerName;
    private final int firstSleep;
    private final int secondSleep;
    private final int moneyLimit;

    ConsoleOutput consoleOutput = new ConsoleOutput();

    public Player(String name) {
        playerName = name;
        firstSleep = generateRandomInteger(500, 2000);
        secondSleep = generateRandomInteger(500, 2000);
        moneyLimit = generateRandomInteger(5000, 30000);
    }

    public void betting() {
        if (!isInterrupted()) {
            while (Storage.auctionInProgress) {
                if (Storage.youCanBet) {
                    if (Storage.blockedPlayersName.stream().noneMatch(player -> player.equals(playerName))) {
                        try {
                            Thread.currentThread().join(firstSleep);
                            bet();
                            Thread.currentThread().join(secondSleep);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                } else {
                    Thread.yield();
                }
            }
        }
    }

    public void bet() {
        Storage.currentWinnerName = playerName;
        Storage.currentPlayerMoneyLimit = moneyLimit;
        upBid();
        consoleOutput.printBet(playerName, Storage.currentLot, Storage.currentBidSize);
    }

    public void upBid() {
        int percent = generateRandomInteger(5, 50);
        int integerPercent = Storage.currentBidSize / 100 * percent;
        Storage.currentBidSize = Storage.currentBidSize + integerPercent;
    }

    private int generateRandomInteger(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void run() {
            betting();
    }
}
