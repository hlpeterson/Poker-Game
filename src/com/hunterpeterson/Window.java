package com.hunterpeterson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Window implements ActionListener {
    JFrame frame = new JFrame();
    JLabel robotChipsLabel = new JLabel("Robot chip count: 10000" );
    JLabel humanChipsLabel = new JLabel("Human chip count: 10000");
    JLabel potSizeLabel = new JLabel("Potsize: ");
    JLabel robotCardLabel = new JLabel("The robot's cards are: ");
    JButton raiseButton = new JButton("Raise");
    JButton betButton = new JButton("Bet");
    JButton callButton = new JButton("Call");
    JButton checkButton = new JButton("Check");
    JButton foldButton = new JButton("Fold");
    JButton continueButton = new JButton("Continue");
    JButton playAgainButton = new JButton("Play again");
    JLabel card1Label = new JLabel();
    JLabel card2Label = new JLabel();
    JLabel flop1Label = new JLabel();
    JLabel flop2Label = new JLabel();
    JLabel flop3Label = new JLabel();
    JLabel turnLabel = new JLabel();
    JLabel riverLabel = new JLabel();
    JLabel robot1Label = new JLabel();
    JLabel robot2Label = new JLabel();
    JLabel yourCardsLabel = new JLabel("Your cards are: ");
    JLabel directionsLabel = new JLabel();
    JLabel errorLabel = new JLabel();
    JLabel fieldLabel = new JLabel("Type your raise or bet in here: ");
    JTextField betField = new JTextField();
    private static boolean wasContinueClicked;

    public static void setWasCheckClicked(boolean wasCheckClicked) {
        Window.wasCheckClicked = wasCheckClicked;
    }

    private static boolean wasCheckClicked;

    public static void setWasBetClicked(boolean wasBetClicked) {
        Window.wasBetClicked = wasBetClicked;
    }

    private static boolean wasBetClicked;

    public static void setWasRaiseClicked(boolean wasRaiseClicked) {
        Window.wasRaiseClicked = wasRaiseClicked;
    }

    private static boolean wasRaiseClicked;

    public static void setWasCallClicked(boolean wasCallClicked) {
        Window.wasCallClicked = wasCallClicked;
    }

    private static boolean wasCallClicked;

    public static void setWasFoldClicked(boolean wasFoldClicked) {
        Window.wasFoldClicked = wasFoldClicked;
    }

    private static boolean wasFoldClicked;

    private static boolean wasPlayAgainClicked;


    Window() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(null);
        robotChipsLabel.setBounds(20, 50, 280, 25);
        humanChipsLabel.setBounds(20, 550, 230, 25);
        potSizeLabel.setBounds(1200, 400, 200, 25);

        raiseButton.setBounds(1050, 500, 100, 25);
        raiseButton.addActionListener(this);
        betButton.setBounds(1150, 500, 100, 25);
        betButton.addActionListener(this);
        callButton.setBounds(1250, 500, 100, 25);
        callButton.addActionListener(this);
        checkButton.setBounds(1100, 600, 100, 25);
        checkButton.addActionListener(this);
        foldButton.setBounds(1200, 600, 100, 25);
        foldButton.addActionListener(this);
        continueButton.setBounds(500, 80, 100, 25);
        continueButton.addActionListener(this);
        playAgainButton.setBounds(400, 80, 100, 25);
        playAgainButton.addActionListener(this);

        yourCardsLabel.setBounds(500, 550, 100, 25);
        directionsLabel.setBounds(400, 40, 400, 25);
        errorLabel.setBounds(400, 20, 550, 25);
        fieldLabel.setBounds(950, 550, 200, 25);
        robotCardLabel.setBounds(900, 50, 150, 25);

        betField.setBounds(1200, 550, 100, 25);


        card1Label.setBounds(500, 600, 75, 150);
        card2Label.setBounds(600, 600, 75, 150);
        flop1Label.setBounds(300, 250, 75, 150);
        flop2Label.setBounds(400, 250, 75, 150);
        flop3Label.setBounds(500, 250, 75, 150);
        turnLabel.setBounds(600, 250, 75, 150);
        riverLabel.setBounds(700, 250, 75, 150);
        robot1Label.setBounds(900, 100, 75, 150);
        robot2Label.setBounds(1000, 100, 75, 150);
        frame.add(betField);
        frame.add(fieldLabel);
        frame.add(robotChipsLabel);
        frame.add(humanChipsLabel);
        frame.add(card1Label);
        frame.add(potSizeLabel);
        frame.add(robotCardLabel);
        frame.add(raiseButton);
        frame.add(betButton);
        frame.add(callButton);
        frame.add(checkButton);
        frame.add(foldButton);
        frame.add(continueButton);
        frame.add(card2Label);
        frame.add(flop1Label);
        frame.add(flop2Label);
        frame.add(flop3Label);
        frame.add(turnLabel);
        frame.add(riverLabel);
        frame.add(robot1Label);
        frame.add(robot2Label);
        frame.add(yourCardsLabel);
        frame.add(directionsLabel);
        frame.add(errorLabel);
        frame.setVisible(true);
    }

    public static boolean isWasPlayAgainClicked() {
        return wasPlayAgainClicked;
    }

    public static void setWasPlayAgainClicked(boolean wasPlayAgainClicked) {
        Window.wasPlayAgainClicked = wasPlayAgainClicked;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == continueButton) {
            wasContinueClicked = true;
        }
        if(e.getSource() == checkButton) {
            wasCheckClicked = true;
        }
        if(e.getSource() == betButton) {
            wasBetClicked = true;
        }
        if(e.getSource() == raiseButton) {
            wasRaiseClicked = true;
        }
        if(e.getSource() == callButton) {
            wasCallClicked = true;
        }
        if(e.getSource() == foldButton) {
            wasFoldClicked = true;
        }
        if(e.getSource() == playAgainButton) {
            wasPlayAgainClicked = true;
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void setAllButtons() {
        wasContinueClicked = false;
        wasFoldClicked = false;
        wasBetClicked = false;
        wasRaiseClicked = false;
        wasCallClicked = false;
        wasCheckClicked = false;
    }
    public JLabel getDirectionsLabel() {
        return directionsLabel;
    }

    public JLabel getPotSizeLabel() {
        return potSizeLabel;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public boolean hasClicked() {
        return wasContinueClicked;
    }

    public void setHasClicked(boolean status) {
        wasContinueClicked = status;
    }

    public JTextField getBetField() {
        return betField;
    }

    public void setPotSizeLabel(int potSize) {
        potSizeLabel.setText(String.valueOf(potSize));
    }

    public JLabel getHumanChipsLabel() {
        return humanChipsLabel;
    }

    public JLabel getRobotChipsLabel() {
        return robotChipsLabel;
    }

    public void setCard1Label(ImageIcon image) {
        card1Label.setIcon(image);
    }

    public JLabel getCard1Label() {
        return card1Label;
    }

    public void setCard2Label(ImageIcon image) {
        card2Label.setIcon(image);
    }

    public JLabel getCard2Label() {
        return card2Label;
    }
    public void setFlop1Label(ImageIcon image) {
        flop1Label.setIcon(image);
    }

    public JLabel getFlop1Label() {
        return flop1Label;
    }
    public void setFlop2Label(ImageIcon image) {
        flop2Label.setIcon(image);
    }

    public JLabel getFlop2Label() {
        return flop2Label;
    }
    public void setFlop3Label(ImageIcon image) {
        flop3Label.setIcon(image);
    }

    public JLabel getFlop3Label() {
        return flop3Label;
    }
    public void setTurnLabel(ImageIcon image) {
        turnLabel.setIcon(image);
    }

    public JLabel getTurnLabel() {
        return turnLabel;
    }
    public void setRiverLabel(ImageIcon image) {
        riverLabel.setIcon(image);
    }

    public JLabel getRiverLabel() {
        return riverLabel;
    }
    public void setRobot1Label(ImageIcon image) {
        robot1Label.setIcon(image);
    }

    public JLabel getRobot1Label() {
        return robot1Label;
    }
    public void setRobot2Label(ImageIcon image) {
        robot2Label.setIcon(image);
    }

    public JLabel getRobot2Label() {
        return robot2Label;
    }

    public static boolean isWasCallClicked() {
        return wasCallClicked;
    }

    public static boolean isWasFoldClicked() {
        return wasFoldClicked;
    }

    public static boolean isWasRaiseClicked() {
        return wasRaiseClicked;
    }

    public static boolean isWasBetClicked() {
        return wasBetClicked;
    }

    public static boolean isWasCheckClicked() {
        return wasCheckClicked;
    }

    public JButton getPlayAgainButton() {return playAgainButton;}

    public JButton getContinueButton() {return continueButton;}

    public void addPlayAgain() {
        playAgainButton.setBounds(400, 80, 100, 25);
        playAgainButton.addActionListener(this);
        frame.add(playAgainButton);
        frame.setVisible(true);
    }

    public void removePlayAgain() {
        frame.remove(playAgainButton);
    }
}
