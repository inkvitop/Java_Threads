package console;

import storage.Storage;

public class ConsoleOutput {
    public void printGreetings() {
        System.out.println("Welcome to the Auction House 'Beijing Council'");
        System.out.println("The auction is starting!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public void printStartLot(String lotName) {
        System.out.println("We present to you lot - (" + lotName + ")");
        System.out.println("Make your bets!");
    }

    public void printLotFinish(String winnerName, String lotName, int winnersBid) {
        System.out.println("*   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *");
        System.out.println("The highest bid for the lot " + lotName + " came from " + winnerName + " and it was " + winnersBid + "$! Our congratulations!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public void printPlayerCantPay(String winnerName, String lotName, int winnersBid, int playerMoney) {
        System.out.println("!   !   !   !   !   !   !   !   !   !   !   !   !   !   !   !");
        System.out.println("Unfortunately " + winnerName + " cannot pay for " + lotName + ".");
        System.out.println("The lot price was " + winnersBid + "$, but " + winnerName + " has " + playerMoney + "$ on his account.");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public void printAuctionFinish() {
        System.out.println("The auction is over!");
    }

    public void printBet(String playerName, String lot, int currentBet) {
        System.out.println("I am " + playerName + " i bet " + currentBet + "$ per " + lot);
    }

    public void printBlockedPlayers() {
        if (Storage.blockedPlayersName.size() != 0) {
            System.out.println("These players do not take part in this lot: " + Storage.blockedPlayersName.toString());
        }
    }
}
