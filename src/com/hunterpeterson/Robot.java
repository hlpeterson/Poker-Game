package com.hunterpeterson;

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

    public void robotDecision(int betSize, Player player, Window window) {
        double rand = Math.random() * 100;
        int difference;
        if (this.money > 0) {
            if (rand >= 80 && this.money - betSize != 0 && player.money >= betSize * 2) {
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
            } else if (rand >= 5 || this.money - betSize == 0) {
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
            window.getDirectionsLabel().setText("Robot is at 0 and cannot react");
            robotBet = false;
        }
    }

    public void robotDecision(Window window) {
        double rand = Math.random() * 100;
        int betSize;
        if (this.money > 0) {
            if (rand >= 60) {
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
            window.getDirectionsLabel().setText("");
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
}
