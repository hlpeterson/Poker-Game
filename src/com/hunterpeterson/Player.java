package com.hunterpeterson;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private static int humanMostRecentBet;
    protected static String[][] cards = {{"Ace of clubs", "Two of clubs", "Three of clubs", "Four of clubs", "Five of clubs", "Six of clubs", "Seven of clubs", "Eight of clubs", "Nine of clubs", "Ten of clubs", "Jack of clubs", "Queen of clubs", "King of clubs"},
            {"Ace of diamonds", "Two of diamonds", "Three of diamonds", "Four of diamonds", "Five of diamonds", "Six of diamonds", "Seven of diamonds", "Eight of diamonds", "Nine of diamonds", "Ten of diamonds", "Jack of diamonds", "Queen of diamonds", "King of diamonds"},
            {"Ace of hearts", "Two of hearts", "Three of hearts", "Four of hearts", "Five of hearts", "Six of hearts", "Seven of hearts", "Eight of hearts", "Nine of hearts", "Ten of hearts", "Jack of hearts", "Queen of hearts", "King of hearts"},
            {"Ace of spades", "Two of spades", "Three of spades", "Four of spades", "Five of spades", "Six of spades", "Seven of spades", "Eight of spades", "Nine of spades", "Ten of spades", "Jack of spades", "Queen of spades", "King of spades"}};
    protected static String[][] allCards = {{"Ace of clubs", "Two of clubs", "Three of clubs", "Four of clubs", "Five of clubs", "Six of clubs", "Seven of clubs", "Eight of clubs", "Nine of clubs", "Ten of clubs", "Jack of clubs", "Queen of clubs", "King of clubs"},
            {"Ace of diamonds", "Two of diamonds", "Three of diamonds", "Four of diamonds", "Five of diamonds", "Six of diamonds", "Seven of diamonds", "Eight of diamonds", "Nine of diamonds", "Ten of diamonds", "Jack of diamonds", "Queen of diamonds", "King of diamonds"},
            {"Ace of hearts", "Two of hearts", "Three of hearts", "Four of hearts", "Five of hearts", "Six of hearts", "Seven of hearts", "Eight of hearts", "Nine of hearts", "Ten of hearts", "Jack of hearts", "Queen of hearts", "King of hearts"},
            {"Ace of spades", "Two of spades", "Three of spades", "Four of spades", "Five of spades", "Six of spades", "Seven of spades", "Eight of spades", "Nine of spades", "Ten of spades", "Jack of spades", "Queen of spades", "King of spades"}};
    protected static String[][] cardImages = {{"A Clubs.png", "2 Clubs.png", "3 Clubs.png", "4 Clubs.png", "5 Clubs.png", "6 Clubs.png", "7 Clubs.png", "8 Clubs.png", "9 Clubs.png", "10 Clubs.png", "J Clubs.png", "Q Clubs.png", "K Clubs.png"},
            {"A Diamond.png", "2 Diamond.png", "3 Diamond.png", "4 Diamond.png", "5 Diamond.png", "6 Diamond.png", "7 Diamond.png", "8 Diamond.png", "9 Diamond.png", "10 Diamond.png", "J Diamond.png", "Q Diamond.png", "K Diamond.png"},
            {"A Hearts.png", "2 Hearts.png", "3 Hearts.png", "4 Hearts.png", "5 Hearts.png", "6 Hearts.png", "7 Hearts.png", "8 Hearts.png", "9 Hearts.png", "10 Hearts.png", "J Hearts.png", "Q Hearts.png", "K Hearts.png"},
            {"A Spades.png", "2 Spades.png", "3 Spades.png", "4 Spades.png", "5 Spades.png", "6 Spades.png", "7 Spades.png", "8 Spades.png", "9 Spades.png", "10 Spades.png", "J Spades.png", "Q Spades.png", "K Spades.png"}};
    protected static int[] cardValues = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    protected String card1;
    protected String card2;
    protected int money;
    public String name;
    protected static int potSize = 0;
    private static String flop1;
    private static String flop2;
    private static String flop3;
    private static String turn;
    private static String river;
    public static String[] chars = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};

    public Player() {

    }

    public Player(String avatar) {
        this.name = avatar;
        this.money = 10000;
    }

    public static int getHumanRecent() {
        return humanMostRecentBet;
    }

    public static void setHumanRecent(int bet) {
        humanMostRecentBet = bet;
    }

    public void setCards() {
        this.card1 = generateCard();
        this.card2 = generateCard();
    }

    public static String[][] getCardArray() {
        return cards;
    }

    public static String[][] getAllCards() {
        return allCards;
    }

    public static void setCardArray(String[][] newCards) {
        for(int i = 0; i < cards.length; i++) {
            for(int j = 0; j < cards[0].length; j++) {
                cards[i][j] = newCards[i][j];
            }
        }
    }



    public static String generateCard() {
        int row = (int) Math.round(Math.random() * 3);
        int column = (int) Math.round(Math.random() * 12);
        String theCard = cards[row][column];
        while (theCard.equals("0")) {
            row = (int) Math.round(Math.random() * 3);
            column = (int) Math.round(Math.random() * 12);
            theCard = cards[row][column];
        }
        staticSetNull(theCard);
        return theCard;
    }



    protected static void staticSetNull(String card) {
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[0].length; j++) {
                if (cards[i][j].equals(card)) {
                    cards[i][j] = "0";
                }
            }
        }
    }


    public String getCard1() {
        return this.card1;
    }

    public String getCard2() {
        return this.card2;
    }

    public String findCardImage(String card) {
        for(int i = 0; i < allCards.length; i++) {
            for(int j = 0; j < allCards[0].length; j++) {
                if(card.equals(allCards[i][j])) {
                    return cardImages[i][j];
                }
            }
        }
        return "image could not be retrieved";
    }

    public void ante() {
        if (this.money >= 100) {
            this.money -= 100;
            potSize += 100;
        } else {
            System.out.println("You lose.");
        }
    }

    public boolean betSize(int bet) {
        if (this.money >= bet && bet > 0) {
            this.money -= bet;
            potSize += bet;
            return true;
        } else {
            return false;
        }
    }

    public static void setPotSize(int bet) {
        potSize += bet;
    }

    public static void resetPotSize() {potSize = 0;}

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void subtractMoney(int amount) {
        this.money -= amount;
    }

    public static int getPotSize() {
        return potSize;
    }

    public static String getFlop1() {
        return flop1;
    }

    public static void setFlop1() {flop1 = generateCard();}

    public static String getFlop2() {
        return flop2;
    }

    public static void setFlop2() {flop2 = generateCard();}

    public static String getFlop3() {
        return flop3;
    }

    public static void setFlop3() {flop3 = generateCard();}

    public static String getTurn() {
        return turn;
    }

    public static void setTurn() {turn = generateCard();}

    public static String getRiver() {
        return river;
    }

    public static void setRiver() {river = generateCard();}

    public String[] checkCards() {
        String[] hand = {this.card1, this.card2, flop1, flop2, flop3, turn, river};
        return hand;
    }
    public boolean checkRoyalFlush() {
        ArrayList<String> tens = new ArrayList<String>();
        ArrayList<String> jacks = new ArrayList<String>();
        ArrayList<String> queens = new ArrayList<String>();
        ArrayList<String> kings = new ArrayList<String>();
        ArrayList<String> aces = new ArrayList<String>();
        String[] clubs = {"No", "No", "No", "No", "No"};
        String[] diamonds = {"No", "No", "No", "No", "No"};
        String[] hearts = {"No", "No", "No", "No", "No"};
        String[] spades = {"No", "No", "No", "No", "No"};
        int clubIncrement = 0;
        int diamondIncrement = 0;
        int heartIncrement = 0;
        int spadeIncrement = 0;
        for (int i = 0; i < this.checkCards().length; i++) {
            if (this.checkCards()[i].substring(0, 3).equals("Ten")) {
                tens.add(this.checkCards()[i]);
            } else if (this.checkCards()[i].substring(0, 4).equals("Jack")) {
                jacks.add(this.checkCards()[i]);
            } else if (this.checkCards()[i].substring(0, 5).equals("Queen")) {
                queens.add(this.checkCards()[i]);
            } else if (this.checkCards()[i].substring(0, 4).equals("King")) {
                kings.add(this.checkCards()[i]);
            } else if (this.checkCards()[i].substring(0, 3).equals("Ace")) {
                aces.add(this.checkCards()[i]);
            }
        }


        for (String ten : tens) {
            if (ten.contains("clubs")) {
                clubs[0] = "Yes";
                break;
            }
        }
        for (String jack : jacks) {
            if (jack.contains("clubs")) {
                clubs[1] = "Yes";
                break;
            }
        }
        for (String queen : queens) {
            if (queen.contains("clubs")) {
                clubs[2] = "Yes";
                break;
            }
        }
        for (String king : kings) {
            if (king.contains("clubs")) {
                clubs[3] = "Yes";
                break;
            }
        }
        for (String ace : aces) {
            if (ace.contains("clubs")) {
                clubs[4] = "Yes";
                break;
            }
        }

        for (String ten : tens) {
            if (ten.contains("diamonds")) {
                diamonds[0] = "Yes";
                break;
            }
        }
        for (String jack : jacks) {
            if (jack.contains("diamonds")) {
                diamonds[1] = "Yes";
                break;
            }
        }
        for (String queen : queens) {
            if (queen.contains("diamonds")) {
                diamonds[2] = "Yes";
                break;
            }
        }
        for (String king : kings) {
            if (king.contains("diamonds")) {
                diamonds[3] = "Yes";
                break;
            }
        }
        for (String ace : aces) {
            if (ace.contains("diamonds")) {
                diamonds[4] = "Yes";
                break;
            }
        }

        for (String ten1 : tens) {
            if (ten1.contains("hearts")) {
                hearts[0] = "Yes";
                break;
            }
        }
        for (String element : jacks) {
            if (element.contains("hearts")) {
                hearts[1] = "Yes";
                break;
            }
        }
        for (String item : queens) {
            if (item.contains("hearts")) {
                hearts[2] = "Yes";
                break;
            }
        }
        for (String value : kings) {
            if (value.contains("hearts")) {
                hearts[3] = "Yes";
                break;
            }
        }
        for (String s : aces) {
            if (s.contains("hearts")) {
                hearts[4] = "Yes";
                break;
            }
        }

        for (String ten : tens) {
            if (ten.contains("spades")) {
                spades[0] = "Yes";
                break;
            }
        }
        for (String jack : jacks) {
            if (jack.contains("spades")) {
                spades[1] = "Yes";
                break;
            }
        }
        for (String queen : queens) {
            if (queen.contains("spades")) {
                spades[2] = "Yes";
                break;
            }
        }
        for (String king : kings) {
            if (king.contains("spades")) {
                spades[3] = "Yes";
                break;
            }
        }
        for (String ace : aces) {
            if (ace.contains("spades")) {
                spades[4] = "Yes";
                break;
            }

        }


        for (int i = 0; i < clubs.length; i++) {
            if (clubs[i].equals("Yes")) {
                clubIncrement++;
            }
        }
        for (int i = 0; i < clubs.length; i++) {
            if (diamonds[i].equals("Yes")) {
                diamondIncrement++;
            }
        }
        for (int i = 0; i < clubs.length; i++) {
            if (hearts[i].equals("Yes")) {
                heartIncrement++;
            }
        }
        for (int i = 0; i < clubs.length; i++) {
            if (spades[i].equals("Yes")) {
                spadeIncrement++;
            }
        }

        return clubIncrement == 5 || diamondIncrement == 5 || heartIncrement == 5 || spadeIncrement == 5;
    }

    public int checkStraightFlush() {
        ArrayList<String> clubs = new ArrayList<String>();
        ArrayList<String> diamonds = new ArrayList<String>();
        ArrayList<String> hearts = new ArrayList<String>();
        ArrayList<String> spades = new ArrayList<String>();
        ArrayList<String> clubCheck = new ArrayList<String>();
        ArrayList<String> diamondCheck = new ArrayList<String>();
        ArrayList<String> heartCheck = new ArrayList<String>();
        ArrayList<String> spadeCheck = new ArrayList<String>();
        int diamondEval = 0;
        int heartEval = 0;
        int spadeEval = 0;


        for (int i = 0; i < this.checkCards().length; i++) {
            if (this.checkCards()[i].contains("clubs")) {
                clubs.add(this.checkCards()[i]);
            } else if (this.checkCards()[i].contains("diamonds")) {
                diamonds.add(this.checkCards()[i]);
            } else if (this.checkCards()[i].contains("hearts")) {
                hearts.add(this.checkCards()[i]);
            } else if (this.checkCards()[i].contains("spades")) {
                spades.add(this.checkCards()[i]);
            }
        }





        if (clubs.size() >= 5 || diamonds.size() >= 5 || hearts.size() >= 5 || spades.size() >= 5) {
            for (String club : clubs) {
                if (club.contains("Ace of clubs")) {
                    clubCheck.add("a");
                } else if (club.contains("Two of clubs")) {
                    clubCheck.add("b");
                } else if (club.contains("Three of clubs")) {
                    clubCheck.add("c");
                } else if (club.contains("Four of clubs")) {
                    clubCheck.add("d");
                } else if (club.contains("Five of clubs")) {
                    clubCheck.add("e");
                } else if (club.contains("Six of clubs")) {
                    clubCheck.add("f");
                } else if (club.contains("Seven of clubs")) {
                    clubCheck.add("g");
                } else if (club.contains("Eight of clubs")) {
                    clubCheck.add("h");
                } else if (club.contains("Nine of clubs")) {
                    clubCheck.add("i");
                } else if (club.contains("Ten of clubs")) {
                    clubCheck.add("j");
                } else if (club.contains("Jack of clubs")) {
                    clubCheck.add("k");
                } else if (club.contains("Queen of clubs")) {
                    clubCheck.add("l");
                } else if (club.contains("King of clubs")) {
                    clubCheck.add("m");
                }
            }





                if (clubCheck.contains("i") && clubCheck.contains("m") && clubCheck.contains("l") && clubCheck.contains("k") && clubCheck.contains("j")) {
                    return 9;
                }
                if (clubCheck.contains("i") && clubCheck.contains("h") && clubCheck.contains("l") && clubCheck.contains("k") && clubCheck.contains("j")) {
                    return 8;
                }
                if (clubCheck.contains("i") && clubCheck.contains("h") && clubCheck.contains("g") && clubCheck.contains("k") && clubCheck.contains("j")) {
                    return 7;
                }
                if (clubCheck.contains("i") && clubCheck.contains("h") && clubCheck.contains("g") && clubCheck.contains("f") && clubCheck.contains("j")) {
                    return 6;
                }
                if (clubCheck.contains("i") && clubCheck.contains("h") && clubCheck.contains("g") && clubCheck.contains("f") && clubCheck.contains("e")) {
                    return 5;
                }
                if (clubCheck.contains("d") && clubCheck.contains("h") && clubCheck.contains("g") && clubCheck.contains("f") && clubCheck.contains("e")) {
                    return 4;
                }
                if (clubCheck.contains("d") && clubCheck.contains("c") && clubCheck.contains("g") && clubCheck.contains("f") && clubCheck.contains("e")) {
                    return 3;
                }
                if (clubCheck.contains("d") && clubCheck.contains("c") && clubCheck.contains("b") && clubCheck.contains("f") && clubCheck.contains("e")) {
                    return 2;
                }
                if (clubCheck.contains("d") && clubCheck.contains("c") && clubCheck.contains("b") && clubCheck.contains("a") && clubCheck.contains("e")) {
                    return 1;
                }

                diamondEval = evalSF(diamondCheck);
                heartEval = evalSF(heartCheck);
                spadeEval = evalSF(spadeCheck);
                if(diamondEval > 0) {
                    return diamondEval;
                } else if(heartEval > 0) {
                    return heartEval;
                } else if(spadeEval > 0) {
                    return spadeEval;
                } else {
                    return 0;
                }
            }
        return 0;
        }



    private static int evalSF(ArrayList<String> values) {
        int count = 9;
        for(int i = 12; i >= 0; i--) {
            if(values.contains(Player.chars[i]) && values.contains(Player.chars[i - 1]) && values.contains(Player.chars[i - 2]) && values.contains(Player.chars[i - 3]) && values.contains(Player.chars[i - 4])){
                return count;
            }
            count--;
        }
        return 0;
    }

    //watch out for this bug (robot won over human when should have tied; could not get error to replicate)
    public int[] evaluate4Kind() {
        ArrayList<String> values = new ArrayList<>();

        int counter = 0;
        int[] value = new int[1];
        int[] empty = {0};

        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                if(this.checkCards()[i].substring(0, 3).equals(this.checkCards()[j].substring(0, 3)) && i != j) {
                    values.add(this.checkCards()[i]);
                    counter++;
                }
            }
            if(counter < 3) {
                counter = 0;
                values.clear();
            } else {
                break;
            }
        }




        if(counter >= 3) {
            for (String[] card : allCards) {
                for (int j = 0; j < allCards[0].length; j++) {
                    if (card[j].equals(values.get(0))) {
                        value[0] = cardValues[j];
                    }
                }
            }




            return value;

        } else {
            return empty;
        }
    }

    public int[] checkFullHouse() {
        ArrayList<String> threeKind = new ArrayList<String>();
        ArrayList<String> pairs = new ArrayList<String>();
        ArrayList<Integer> threeValue = new ArrayList<Integer>();
        ArrayList<Integer> pairValue = new ArrayList<Integer>();
        threeKind.add("0");
        threeKind.add("0");

        int[] order = {0, 0};
        int[] empty = new int[2];





        for(int i = 0; i < 7; i++) {
            for(int j = i + 1; j < 7; j++) {
                for(int k = j + 1; k < 7; k++) {
                    if(this.checkCards()[i].substring(0, 3).equals(this.checkCards()[j].substring(0, 3)) && this.checkCards()[i].substring(0, 3).equals(this.checkCards()[k].substring(0, 3)) && !threeKind.get(0).contains(this.checkCards()[i].substring(0, 3))) {
                        threeKind.add(this.checkCards()[i]);
                    }
                }
            }
        }

        for(int i = 0; i < 7; i++) {
            for(int j = i + 1; j < 7; j++) {
                if(this.checkCards()[i].substring(0, 3).equals(this.checkCards()[j].substring(0, 3)) && !threeKind.get(0).contains(this.checkCards()[i].substring(0, 3)) && !threeKind.get(1).contains(this.checkCards()[i].substring(0, 3))) {
                        pairs.add(this.checkCards()[i]);


                }
            }
        }





        if(threeKind.size() >= 1 && !threeKind.get(0).equals("0") && pairs.size() >= 1) {
            threeValue.addAll(setValues(threeKind));
            pairValue.addAll(setValues(pairs));
            Collections.sort(threeValue);
            Collections.reverse(threeValue);
            Collections.sort(pairValue);
            Collections.reverse(pairValue);





            order[0] = threeValue.get(0);
            order[1] = pairValue.get(0);






            return order;




        } else {
            return empty;
        }
    }

    private static ArrayList<Integer> setValues(ArrayList<String> playerCards) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (int k = 0; k < playerCards.size(); k++) {
            for (int i = 0; i < allCards.length; i++) {
                for (int j = 0; j < allCards[0].length; j++) {
                    if (allCards[i][j].equals(playerCards.get(k))) {
                        values.add(cardValues[j]);
                    }
                }
            }
        }
        return values;
    }


    public int[] evaluateFlush() {
        ArrayList<String> clubs = new ArrayList<String>();
        ArrayList<String> diamonds = new ArrayList<String>();
        ArrayList<String> hearts = new ArrayList<String>();
        ArrayList<String> spades = new ArrayList<String>();
        ArrayList<Integer> values = new ArrayList<Integer>();
        int[] highestValues = new int[5];
        int[] empty = {0, 0, 0, 0, 0};

        for (int i = 0; i < this.checkCards().length; i++) {
            if (this.checkCards()[i].contains("clubs")) {
                clubs.add(this.checkCards()[i]);
            } else if (this.checkCards()[i].contains("diamonds")) {
                diamonds.add(this.checkCards()[i]);
            } else if (this.checkCards()[i].contains("hearts")) {
                hearts.add(this.checkCards()[i]);
            } else if (this.checkCards()[i].contains("spades")) {
                spades.add(this.checkCards()[i]);
            }
        }

        if (clubs.size() >= 5 || diamonds.size() >= 5 || hearts.size() >= 5 || spades.size() >= 5) {
            if (clubs.size() >= 5) {
                for (int i = 0; i < allCards.length; i++) {
                    for (int j = 0; j < allCards[0].length; j++) {
                        for (String card : clubs) {
                            if (allCards[i][j].equals(card)) {
                                values.add(0, cardValues[j]);
                            }
                        }
                    }
                }
            } else if (diamonds.size() >= 5) {
                for (int i = 0; i < allCards.length; i++) {
                    for (int j = 0; j < allCards[0].length; j++) {
                        for (String card : diamonds) {
                            if (allCards[i][j].equals(card)) {
                                values.add(0, cardValues[j]);
                            }
                        }
                    }
                }
            } else if (hearts.size() >= 5) {
                for (int i = 0; i < allCards.length; i++) {
                    for (int j = 0; j < allCards[0].length; j++) {
                        for (String card : hearts) {
                            if (allCards[i][j].equals(card)) {
                                values.add(0, cardValues[j]);
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < allCards.length; i++) {
                    for (int j = 0; j < allCards[0].length; j++) {
                        for (String card : spades) {
                            if (allCards[i][j].equals(card)) {
                                values.add(0, cardValues[j]);
                            }
                        }
                    }
                }
            }

            Collections.sort(values);
            Collections.reverse(values);

            for(int i = 0; i < highestValues.length; i++) {
                highestValues[i] = values.get(i);
            }
            return highestValues;
        } else {
            return empty;
        }
    }

   /* Previously used/tested function for determining hand rankings
   private static String checkFlushOrStraight(Player a, Player b, ArrayList<Integer> aValues, ArrayList<Integer> bValues, String handType) {
        if(aValues.get(0).equals(bValues.get(0))) {
            if(aValues.get(1).equals(bValues.get(1))) {
                if(aValues.get(2).equals(bValues.get(2))) {
                    if(aValues.get(3).equals(bValues.get(3))) {
                        if(aValues.get(4).equals(bValues.get(4))) {
                            return a.name + " and " + b.name + " tie with " + handType;
                        } else if(aValues.get(4) > bValues.get(4)) {
                            return a.name + " wins with a " + handType;
                        } else {
                            return b.name + " wins with a " + handType;
                        }
                    } else if(aValues.get(3) > bValues.get(3)) {
                        return a.name + " wins with a " + handType;
                    } else {
                        return b.name + " wins with a " + handType;
                    }
                } else if(aValues.get(2) > bValues.get(2)) {
                    return a.name + " wins with a " + handType;
                } else {
                    return b.name + " wins with a " + handType;
                }
            } else if(aValues.get(1) > bValues.get(1)) {
                return a.name + " wins with a " + handType;
            } else {
                return b.name + " wins with a " + handType;
            }
        } else if (aValues.get(0) > bValues.get(0)) {
            return a.name + " wins with a " + handType;
        } else {
            return b.name + " wins with a " + handType;
        }
    }
*/

    public int[] evaluateStraight() {
        ArrayList<Integer> values = new ArrayList<Integer>();
        ArrayList<Integer> newValues = new ArrayList<Integer>();
        int count = 0;
        int[] empty = {0, 0, 0, 0, 0};
        int[] valueArray = new int[5];


        for (int i = 0; i < allCards.length; i++) {
            for (int j = 0; j < allCards[0].length; j++) {
                for (String card : this.checkCards()) {
                    if (allCards[i][j].equals(card)) {
                        values.add(0, cardValues[j]);
                    }
                }
            }
        }



        if(hasStraight(values)) {
            hasStraightValues(values, newValues);
            Collections.sort(newValues);
            Collections.reverse(newValues);

            for(int i = 0; i < 5; i++) {
                valueArray[i] = newValues.get(i);
            }

            return valueArray;
        } else {
            return empty;
        }

    }

    public int[] evaluateTrips() {
        ArrayList<String> trips = new ArrayList<>();
        ArrayList<String> noTrips = new ArrayList<String>();
        ArrayList<Integer> noTripValues = new ArrayList<Integer>();
        int value;
        int[] values = {0, 0, 0};
        int[] empty = {0, 0, 0};
        for(int i = 0; i < 7; i++) {
            for(int j = i + 1; j < 7; j++) {
                for(int k = j + 1; k < 7; k++) {
                    if(this.checkCards()[i].substring(0, 3).equals(this.checkCards()[j].substring(0, 3)) && this.checkCards()[i].substring(0, 3).equals(this.checkCards()[k].substring(0, 3))) {
                        trips.add(this.checkCards()[i]);
                    }
                }
            }
        }



        if (trips.size() == 1) {
            for (int i = 0; i < allCards.length; i++) {
                for (int j = 0; j < allCards[0].length; j++) {
                    for (String card : trips) {
                        if (allCards[i][j].equals(card)) {
                            values[0] = cardValues[j];
                            break;
                        }
                    }
                }
            }
            for(int i = 0; i < 7; i++) {
                if(!this.checkCards()[i].substring(0, 3).equals(trips.get(0))) {
                    noTrips.add(this.checkCards()[i]);
                }
            }
            for (int i = 0; i < allCards.length; i++) {
                for (int j = 0; j < allCards[0].length; j++) {
                    for (String card : noTrips) {
                        if (allCards[i][j].equals(card)) {
                            noTripValues.add(cardValues[j]);
                        }
                    }
                }
            }

            Collections.sort(noTripValues);
            Collections.reverse(noTripValues);

            values[1] = noTripValues.get(0);
            values[2] = noTripValues.get(1);

            return values;
        } else {
            return empty;
        }
    }

    public int[] evaluateTwoPair() {
        ArrayList<String> pairs = new ArrayList<String>();
        ArrayList<String> noPairs = new ArrayList<String>();
        ArrayList<Integer> values = new ArrayList<Integer>();
        ArrayList<Integer> noPairValues = new ArrayList<Integer>();
        //these can be condensed into one array at a later time (zeros will only be removed from newvalues if if statemnt runs)
        int[] newValues = {0, 0, 0};
        int[] empty = {0, 0, 0};


        for (int i = 0; i < 7; i++) {
            for (int j = i + 1; j < 7; j++) {
                if (this.checkCards()[i].substring(0, 3).equals(this.checkCards()[j].substring(0, 3))) {
                    pairs.add(this.checkCards()[i]);
                }
            }
        }
        if (pairs.size() >= 2) {
            for (int i = 0; i < allCards.length; i++) {
                for (int j = 0; j < allCards[0].length; j++) {
                    for (int k = 0; k < pairs.size(); k++) {
                        if (allCards[i][j].equals(pairs.get(k))) {
                            values.add(cardValues[j]);
                        }
                    }
                }
            }

            for (String card : this.checkCards()) {
                if (card.substring(0, 3).equals(pairs.get(0).substring(0, 3)) || card.substring(0, 3).equals(pairs.get(1).substring(0, 3))) {
                    continue;
                } else {
                    noPairs.add(card);
                }
            }

            for (int i = 0; i < allCards.length; i++) {
                for (int j = 0; j < allCards[0].length; j++) {
                    for (String card : noPairs) {
                        if (allCards[i][j].equals(card)) {
                            noPairValues.add(cardValues[j]);

                        }
                    }
                }
            }
            Collections.sort(values);
            Collections.reverse(values);
            Collections.sort(noPairValues);
            Collections.reverse(noPairValues);

            for (int i = 0; i < values.size(); i++) {
                newValues[i] = values.get(i);
            }
            newValues[2] = noPairValues.get(0);
            return newValues;
        } else {
            return empty;
        }
    }



    public int[] evaluateOnePair() {
        ArrayList<String> pair = new ArrayList<String>();
        ArrayList<String> noPair = new ArrayList<String>();
        int pairValue = 0;
        ArrayList<Integer> noPairValue = new ArrayList<Integer>();
        ArrayList<Integer> noPairValues = new ArrayList<Integer>();
        int[] values = {0, 0, 0, 0};
        int[] empty = {0, 0, 0, 0};
        for (int i = 0; i < 7; i++) {
            for (int j = i + 1; j < 7; j++) {
                if (this.checkCards()[i].substring(0, 3).equals(this.checkCards()[j].substring(0, 3))) {
                    pair.add(this.checkCards()[i]);
                }
            }
        }

        if (pair.size() == 1) {
            for (int i = 0; i < allCards.length; i++) {
                for (int j = 0; j < allCards[0].length; j++) {
                    if (allCards[i][j].equals(pair.get(0))) {
                        pairValue = cardValues[j];
                    }
                }
            }

            for (String card : this.checkCards()) {
                if (card.substring(0, 3).equals(pair.get(0).substring(0, 3))) {
                    continue;
                } else {
                    noPair.add(card);
                }
            }


            for (int i = 0; i < allCards.length; i++) {
                for (int j = 0; j < allCards[0].length; j++) {
                    for (String card : noPair) {
                        if (allCards[i][j].equals(card)) {
                            noPairValues.add(cardValues[j]);
                        }
                    }
                }
            }

            Collections.sort(noPairValues);
            Collections.reverse(noPairValues);

            values[0] = pairValue;
            for(int i = 1; i < 4; i++) {
                values[i] = noPairValues.get(i - 1);
            }
            return values;
        } else {
            return empty;
        }

    }

    public int[] evaluateHighCard() {
        ArrayList<Integer> values = new ArrayList<Integer>();
        int[] fiveValues = {0, 0, 0, 0, 0};
        int[] empty = {0, 0, 0, 0, 0};

        for (int i = 0; i < allCards.length; i++) {
            for (int j = 0; j < allCards[0].length; j++) {
                for (String card : this.checkCards()) {
                    if (allCards[i][j].equals(card)) {
                        values.add(cardValues[j]);
                    }
                }
            }
        }

        Collections.sort(values);
        Collections.reverse(values);

        for(int i = 0; i < 5; i++) {
            fiveValues[i] = values.get(i);
        }
        return fiveValues;

    }


    private static ArrayList<Integer> hasStraightValues(ArrayList<Integer> values, ArrayList<Integer> newValues) {
        for(int i = 12; i >= 5; i--) {
            if(values.contains(cardValues[i]) && values.contains(cardValues[i - 1]) && values.contains(cardValues[i - 2]) && values.contains(cardValues[i - 3]) && values.contains(cardValues[i - 4])){
                newValues.add(cardValues[i]);
                newValues.add(cardValues[i - 1]);
                newValues.add(cardValues[i - 2]);
                newValues.add(cardValues[i - 3]);
                newValues.add(cardValues[i - 4]);
                return newValues;
            }
        }
        if(values.contains(2) && values.contains(3) && values.contains(4) && values.contains(5) && values.contains(14)){
            newValues.add(2);
            newValues.add(1);
            newValues.add(3);
            newValues.add(4);
            newValues.add(5);
            return newValues;
        }
        if(values.contains(10) && values.contains(11) && values.contains(12) && values.contains(13) && values.contains(14)){
            newValues.add(10);
            newValues.add(11);
            newValues.add(13);
            newValues.add(14);
            newValues.add(12);
            return newValues;
        }
        return newValues;
    }

    private static boolean hasStraight(ArrayList<Integer> values) {
        for(int i = 12; i >= 5; i--) {
            if(values.contains(cardValues[i]) && values.contains(cardValues[i - 1]) && values.contains(cardValues[i - 2]) && values.contains(cardValues[i - 3]) && values.contains(cardValues[i - 4])){
                return true;
            }
        }
        if(values.contains(2) && values.contains(3) && values.contains(4) && values.contains(5) && values.contains(14)){
            return true;
        }
        if(values.contains(10) && values.contains(11) && values.contains(12) && values.contains(13) && values.contains(14)){
            return true;
        }
        return false;
    }




}




