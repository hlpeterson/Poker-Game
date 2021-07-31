package com.hunterpeterson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Robot extends Player {
    public static int robotMostRecentBet;
    static boolean robotBet = false;
    private static int raiseValue;
    private static boolean hasRobotFolded;

    Robot(String avatar){
        this.name = avatar;
        this.card1 = generateCard();
        this.card2 = generateCard();
        this.money = 10000;
    }
/* Previously used functions to determine the robot player's decision
    public void betPreFlop() {
        if(this.card1.substring(0, 2).equals(this.card2.substring(0, 2))) {
            System.out.println("Robot bets " + (this.money / 50));
        } else {
            System.out.println("Robot checks.");
        }
    }

    public void robotReact(int callSize) {
        double rand = Math.random() * 100;
        if(this.money < callSize) {
            potSize += this.money;
            System.out.println("Robot calls for " + this.money + ". The pot is now " + potSize);
            this.money = 0;
        } else if (rand > 20 && this.money >= callSize) {
            potSize += callSize;
            System.out.println("Robot calls for " + callSize + ". The pot is now " + potSize);
            this.money -= callSize;
        } else {
            System.out.println("Robot folds. Human wins!");
            hasRobotFolded = true;
        }
    }

 */

    public void robotDecision(int betSize, Player player, Window window) throws IOException, InterruptedException {
        int difference;
        String round;
        if (this.money > 0 && !Main.getFirstHand()) {
            if (this.robotAction().equals("Raise") && this.money - betSize != 0 && player.money >= betSize * 2) {
                int raiseSize;
                if (this.money >= betSize * 2) {
                    raiseSize = betSize * 2;
                    potSize += raiseSize;
                    potSize -= raiseValue;
                    this.money -= raiseSize;
                    this.money += raiseValue;
                    window.getDirectionsLabel().setText("Robot raises to " + raiseSize + ".");
                    window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
                    window.getRobotChipsLabel().setText("The robot has " + this.money + " chips remaining.");
                    robotMostRecentBet = raiseSize;
                    robotBet = true;
                    raiseValue = raiseSize;
                } else {
                    difference = betSize - this.money;
                    raiseSize = this.money;
                    potSize += raiseSize;
                    potSize -= difference;
                    player.money += difference;
                    window.getDirectionsLabel().setText("Robot goes all in for " + raiseSize + ".");
                    window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
                    this.money = 0;
                    window.getRobotChipsLabel().setText("The robot has " + this.money + " chips remaining.");
                    robotMostRecentBet = raiseSize;
                    robotBet = true;
                }
            } else if (this.robotAction().equals("Call") || this.money - betSize == 0) {
                if (this.money > betSize) {
                    potSize += betSize - raiseValue; //added the - raiseValue part in order to attempt a bug fix. may need to be deleted
                    this.money -= betSize;
                    this.money += raiseValue;
                    window.getDirectionsLabel().setText("Robot calls for " + (betSize - raiseValue) + ".");
                    window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
                    window.getRobotChipsLabel().setText("The robot has " + this.money + " chips remaining.");
                    robotBet = false;
                    Main.setHumanBet(false);
                    raiseValue = 0;
                    Main.setRaiseValue();
                } else {
                    difference = betSize - this.money;
                    potSize += this.money;
                    potSize -= difference;
                    player.money += difference;
                    window.getDirectionsLabel().setText("Robot goes all in for " + this.money + ".");
                    window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
                    this.money = 0;
                    window.getRobotChipsLabel().setText("The robot has " + this.money + " chips remaining.");
                    robotBet = false;
                    Main.setHumanBet(false);
                }
            } else {
                window.getDirectionsLabel().setText("Robot folds. Human wins!");
                hasRobotFolded = true;
                raiseValue = 0;
                Main.setRaiseValue();
            }
        } else {
            if(this.money <= 0) {
                window.getDirectionsLabel().setText("Robot is at 0 and cannot react");
                robotBet = false;
            } else {
                if (this.money > betSize) {
                    potSize += betSize - raiseValue; //added the - raiseValue part in order to attempt a bug fix. may need to be deleted
                    this.money -= betSize;
                    this.money += raiseValue;
                    window.getDirectionsLabel().setText("Robot calls for " + (betSize - raiseValue) + ".");
                    window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
                    window.getRobotChipsLabel().setText("The robot has " + this.money + " chips remaining.");
                    robotBet = false;
                    Main.setHumanBet(false);
                    raiseValue = 0;
                    Main.setRaiseValue();
                } else {
                    difference = betSize - this.money;
                    potSize += this.money;
                    potSize -= difference;
                    player.money += difference;
                    window.getDirectionsLabel().setText("Robot goes all in for " + this.money + ".");
                    window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
                    this.money = 0;
                    window.getRobotChipsLabel().setText("The robot has " + this.money + " chips remaining.");
                    robotBet = false;
                    Main.setHumanBet(false);
                }
            }
        }
    }

    public void robotDecision(Window window) throws IOException, InterruptedException {
        double rand = Math.random() * 100;
        int betSize;
        if (this.money > 0 && !Main.getFirstHand()) {
            if (this.robotAction().equals("Raise")) {
                if (this.money >= potSize / 2) {
                    betSize = potSize / 2;
                    potSize += betSize;
                    this.money -= betSize;
                    window.getDirectionsLabel().setText("Robot bets for " + betSize + ".");
                    window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
                    window.getRobotChipsLabel().setText("The robot has " + this.money + " chips remaining.");
                    robotMostRecentBet = betSize;
                    robotBet = true;
                    raiseValue = betSize;
                } else {
                    betSize = this.money;
                    potSize += betSize;
                    window.getDirectionsLabel().setText("Robot goes all in for " + betSize + ".");
                    window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
                    this.money = 0;
                    window.getRobotChipsLabel().setText("The robot has " + this.money + " chips remaining.");
                    robotMostRecentBet = betSize;
                    robotBet = true;
                }
            } else {
                window.getDirectionsLabel().setText("Robot checks.");
                robotBet = false;
            }
        } else {
            if(this.money <= 0) {
                window.getDirectionsLabel().setText("");
            } else {
                window.getDirectionsLabel().setText("Robot checks.");
            }
            robotBet = false;
        }
    }

    public static int getRobotRecent() {
        return robotMostRecentBet;
    }

    public static boolean getRobotBet() {
        return robotBet;
    }

    public static void setRobotBet(boolean didBet) {
        robotBet = didBet;
    }

    public static void setRobotRaiseValue() {raiseValue = 0; }

    public static boolean getHasRobotFolded() {return hasRobotFolded; }

    public static void setHasRobotFolded(boolean didFold) {hasRobotFolded = didFold;}

    private static int makeDecisionRiver(int preflop, int flop, int turn, int river) throws IOException, InterruptedException {
        String prediction = connect(preflop, preflop, preflop, preflop);
        prediction = prediction.substring(1, prediction.length() - 1);
        double doublePredict = Double.parseDouble(prediction);
        int numPredict = (int) Math.round(doublePredict);
        return numPredict;
    }

    private static int makeDecisionPreFlop(int preflop) throws IOException, InterruptedException {
        String prediction = connect(preflop, preflop, preflop, preflop);
        prediction = prediction.substring(1, prediction.length() - 1);
        double doublePredict = Double.parseDouble(prediction);
        int numPredict = (int) Math.round(doublePredict);
        return numPredict;
    }

    private static int makeDecisionFlop(int preflop, int flop) throws IOException, InterruptedException {
        int input = (preflop + flop) / 2;
        String prediction = connect(preflop, flop, input, input);
        prediction = prediction.substring(1, prediction.length() - 1);
        double doublePredict = Double.parseDouble(prediction);
        int numPredict = (int) Math.round(doublePredict);
        return numPredict;
    }

    private static int makeDecisionTurn(int preflop, int flop, int turn) throws IOException, InterruptedException {
        int river = (preflop + flop + turn) / 3;
        String prediction = connect(preflop, flop, turn, river);
        prediction = prediction.substring(1, prediction.length() - 1);
        double doublePredict = Double.parseDouble(prediction);
        int numPredict = (int) Math.round(doublePredict);
        return numPredict;
    }

    private static String connect(int preflop, int flop, int turn, int river) throws IOException, InterruptedException {
            String preString = String.valueOf(preflop);
            String flopString = String.valueOf(preflop);
            String turnString = String.valueOf(preflop);
            String riverString = String.valueOf(preflop);
            ProcessBuilder processBuilder = new ProcessBuilder("python", "/Users/hunterpeterson/PycharmProjects/MLforpokergame/main.py", preString, flopString, turnString, riverString);
            Process process = processBuilder.start();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = bfr.readLine();
            return line;
        }

        private String robotAction() throws IOException, InterruptedException {
            int robotHandValue;
            int decision;
            if(this.checkRoyalFlush()) {
                robotHandValue = 9;
            } else if(this.checkStraightFlush() > 0) {
                robotHandValue = 8;
            } else if(this.evaluate4Kind()[0] > 0) {
                robotHandValue = 7;
            } else if(this.checkFullHouse()[0] > 0) {
                robotHandValue = 6;
            } else if(this.evaluateFlush()[0] > 0) {
                robotHandValue = 5;
            } else if(this.evaluateStraight()[0] > 0) {
                robotHandValue = 4;
            } else if(this.evaluateTrips()[0] > 0) {
                robotHandValue = 3;
            } else if(this.evaluateTwoPair()[0] > 0) {
                robotHandValue = 2;
            } else if(this.evaluateOnePair()[0] > 0) {
                robotHandValue = 1;
            } else {
                robotHandValue = 0;
            }

            if(Robot.findRound().equals("Preflop")) {
                decision = Robot.makeDecisionPreFlop(Main.getRound()[0]);
            } else if(Robot.findRound().equals("Flop")) {
                decision = Robot.makeDecisionFlop(Main.getRound()[0], Main.getRound()[1]);
            } else if(Robot.findRound().equals("Turn")) {
                decision = Robot.makeDecisionTurn(Main.getRound()[0], Main.getRound()[1], Main.getRound()[2]);
            } else {
                decision = Robot.makeDecisionRiver(Main.getRound()[0], Main.getRound()[1], Main.getRound()[2], Main.getRound()[3]);
            }

            if(robotHandValue - decision > 2) {
                return "Raise";
            } else if(robotHandValue - decision <= 2 && robotHandValue - decision >= -2) {
                return "Call";
            } else {
                return "Fold";
            }
        }

        private static String findRound() {
        int[] roundValues = Main.getRound();
        if(roundValues[0] == 1000) {
            return "Preflop";
        } else if(roundValues[1] == 1000) {
            return "Flop";
        } else if(roundValues[2] == 1000) {
            return "Turn";
        } else {
            return "River";
        }
        }
    }

