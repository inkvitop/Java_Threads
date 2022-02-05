package storage;

import java.util.ArrayList;

public class Storage {
    public static String[] playersName = {"Mr. White", "Mr. Orange", "Mr. Blonde", "Mr. Pink", "Mr. Blue", "Mr. Brown"};
    public static String[] lotsName = {"Mikhail Shemyakin. \"Portrait. Sectional Head\"", "Brunel De Neuyille. Cozy \"Still Life with Berries\"", "Armando Gentilini. A touching composition \"Childrens Carnival\"", "Chaim Solomonovich Soutine \"Landscape\"", "Ivan Konstantinovich Gavrilov \"Seascape with a sailing ship\""};
    public static int currentBidSize = 1000;
    public static int currentPlayerMoneyLimit = 0;
    public static String currentLot = lotsName[0];
    public static String currentWinnerName = "";
    public static ArrayList<String> blockedPlayersName = new ArrayList<>();
    public static ArrayList<Integer> blockedPlayersRound = new ArrayList<>();
    public static Boolean auctionInProgress = true;
    public static Boolean youCanBet = false;
}