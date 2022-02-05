package auction;

import console.ConsoleOutput;
import player.Player;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Auction {
    ConsoleOutput consoleOutput = new ConsoleOutput();
    public static List<Thread> players = new ArrayList<>();

    public void auctionStart() {
        consoleOutput.printGreetings();
        initializePlayersThread();

        for (int i = 0; i < Storage.lotsName.length; i++) {
            reduceBlockedPlayersRound();
            consoleOutput.printBlockedPlayers();

            Storage.currentBidSize = 1000;
            lotStart(Storage.lotsName[i]);
            Storage.currentLot = Storage.lotsName[i];

            if (Storage.currentPlayerMoneyLimit >= Storage.currentBidSize) {
                consoleOutput.printLotFinish(Storage.currentWinnerName, Storage.lotsName[i], Storage.currentBidSize);
            } else {
                Storage.blockedPlayersName.add(Storage.currentWinnerName);
                Storage.blockedPlayersRound.add(3);
                consoleOutput.printPlayerCantPay(Storage.currentWinnerName, Storage.lotsName[i], Storage.currentBidSize, Storage.currentPlayerMoneyLimit);
            }
        }

        Storage.youCanBet = false;
        Storage.auctionInProgress = false;
        finishPlayersThread();
        consoleOutput.printAuctionFinish();
    }

    public void lotStart(String lotName) {
        try {
            consoleOutput.printStartLot(lotName);
            Storage.youCanBet = true;
            TimeUnit.SECONDS.sleep(5);
            Storage.youCanBet = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initializePlayersThread() {
        for (int i = 0; i < Storage.playersName.length; i++) {
            players.add(new Player(Storage.playersName[i]));
            players.get(i).start();
        }
    }

    private void finishPlayersThread() {
        for (Thread player : players) {
            player.interrupt();
        }
    }

    private void reduceBlockedPlayersRound() {
        if (Storage.blockedPlayersName.size() != 0) {
            for (int i = 0; i < Storage.blockedPlayersRound.size(); i++) {
                int tempRound = Storage.blockedPlayersRound.get(i);
                tempRound--;
                if (tempRound == 0) {
                    Storage.blockedPlayersRound.remove(i);
                    Storage.blockedPlayersName.remove(i);
                } else {
                    Storage.blockedPlayersRound.set(i, tempRound);
                }
            }
        }
    }
}
