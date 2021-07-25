package com.hunterpeterson;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public abstract class Main implements ActionListener {
static boolean humanBet;
static int raiseValue;
static boolean hasChecked;
static boolean hasHumanFolded;
static boolean hasWon;
static String winningMessage = "";



	public static void main(String[] args) {
		Window window = new Window();
		BufferedImage humanCardImage1 = null;
		BufferedImage humanCardImage2 = null;
		BufferedImage flopImage1 = null;
		BufferedImage flopImage2 = null;
		BufferedImage flopImage3 = null;
		BufferedImage turnImage = null;
		BufferedImage riverImage = null;
		BufferedImage robotCard1Image = null;
		BufferedImage robotCard2Image = null;
		BufferedImage cardBack = null;



		Player human = new Player("human");
		Robot robot = new Robot("robot");

		while (true) {
			hasWon = false;
			Player.resetPotSize();
			Player.setCardArray(Player.getAllCards());
			human.setCards();
			robot.setCards();
			Player.setFlop1();
			Player.setFlop2();
			Player.setFlop3();
			Player.setTurn();
			Player.setRiver();
			String flop1 = Player.getFlop1();
			String flop2 = Player.getFlop2();
			String flop3 = Player.getFlop3();
			String turn = Player.getTurn();
			String river = Player.getRiver();
			hasHumanFolded = false;
			Robot.setHasRobotFolded(false);
			window.getContinueButton().setText("Continue"); 
			window.getPotSizeLabel().setText("Pot size: " + Player.getPotSize());
			window.getHumanChipsLabel().setText("You have " + human.money + " chips remaining.");
			window.getRobotChipsLabel().setText("The robot has " + robot.money + " chips remaining.");
			try {
				humanCardImage1 = ImageIO.read(new File(human.findCardImage(human.getCard1())));
				humanCardImage2 = ImageIO.read(new File(human.findCardImage(human.getCard2())));
				cardBack = ImageIO.read(new File("cardback.jpg"));

			} catch (IOException e) {
				e.printStackTrace();
			}
			Image resizedHCI1 = humanCardImage1.getScaledInstance(75, 150, Image.SCALE_SMOOTH);
			Image resizedHCI2 = humanCardImage2.getScaledInstance(75, 150, Image.SCALE_SMOOTH);
			ImageIcon finalHCI1 = new ImageIcon(resizedHCI1);
			ImageIcon finalHCI2 = new ImageIcon(resizedHCI2);
			window.getCard1Label().setIcon(finalHCI1);
			window.getCard2Label().setIcon(finalHCI2);
			Image cardBackImage = cardBack.getScaledInstance(75, 150, Image.SCALE_SMOOTH);
			ImageIcon finalBackImage = new ImageIcon(cardBackImage);
			window.getFlop1Label().setIcon(finalBackImage);
			window.getFlop2Label().setIcon(finalBackImage);
			window.getFlop3Label().setIcon(finalBackImage);
			window.getTurnLabel().setIcon(finalBackImage);
			window.getRiverLabel().setIcon(finalBackImage);
			window.getRobot1Label().setIcon(finalBackImage);
			window.getRobot2Label().setIcon(finalBackImage);


			if (human.money >= 100 && robot.money >= 100) {
				human.ante();
				robot.ante();


				window.getDirectionsLabel().setText("Each player will now ante 100.");

				while(!window.hasClicked()) {
					window.getErrorLabel().setText("Please press the continue button to continue.");
				}
				Window.setAllButtons();
				window.getPotSizeLabel().setText("Pot size: " + Player.getPotSize());
				window.getHumanChipsLabel().setText("You have " + human.money + " chips remaining.");
				window.getRobotChipsLabel().setText("The robot has " + robot.money + " chips remaining.");
				if(human.money > 0 && robot.money > 0) {
					window.getDirectionsLabel().setText("Would you like to bet or check?");

					while (!Window.isWasCheckClicked() && !Window.isWasBetClicked()) {
						if (Window.isWasRaiseClicked() || Window.isWasCallClicked() || Window.isWasFoldClicked()) {
							Window.setAllButtons();
						}
						window.getErrorLabel().setText("Please press the check button or enter a bet size and press the bet button to continue.");
					}
				}
				game(human, robot, window);

				window.getBetField().setText("");

				if (!hasHumanFolded && !Robot.getHasRobotFolded()) {
					while(!window.hasClicked()) {
							window.getErrorLabel().setText("Please press the continue button to continue.");

						}
					Window.setAllButtons();

					try {
						flopImage1 = ImageIO.read(new File(human.findCardImage(Player.getFlop1())));
						flopImage2 = ImageIO.read(new File(human.findCardImage(Player.getFlop2())));
						flopImage3 = ImageIO.read(new File(human.findCardImage(Player.getFlop3())));

						} catch (IOException e) {
							e.printStackTrace();
						}
						Image resizedFlop1 = flopImage1.getScaledInstance(75, 150, Image.SCALE_SMOOTH);
						Image resizedFlop2 = flopImage2.getScaledInstance(75, 150, Image.SCALE_SMOOTH);
						Image resizedFlop3 = flopImage3.getScaledInstance(75, 150, Image.SCALE_SMOOTH);
						ImageIcon finalFlop1 = new ImageIcon(resizedFlop1);
						ImageIcon finalFlop2 = new ImageIcon(resizedFlop2);
						ImageIcon finalFlop3 = new ImageIcon(resizedFlop3);
						window.getFlop1Label().setIcon(finalFlop1);
						window.getFlop2Label().setIcon(finalFlop2);
						window.getFlop3Label().setIcon(finalFlop3);

						if(human.money > 0 && robot.money > 0) {
							window.getDirectionsLabel().setText("Would you like to bet or check?");

							while (!Window.isWasCheckClicked() && !Window.isWasBetClicked()) {
								if (Window.isWasRaiseClicked() || Window.isWasCallClicked() || Window.isWasFoldClicked()) {
									Window.setAllButtons();
								}
								window.getErrorLabel().setText("Please press the check button or enter a bet size and press the bet button to continue.");
							}
						}


						game(human, robot, window);
						window.getBetField().setText("");



						if (!hasHumanFolded && !Robot.getHasRobotFolded()) {

							while(!window.hasClicked()) {
								window.getErrorLabel().setText("Please press the continue button to continue.");
							}
							Window.setAllButtons();

							try {
								turnImage = ImageIO.read(new File(human.findCardImage(Player.getTurn())));

							} catch (IOException e) {
								e.printStackTrace();
							}
							Image resizedTurn = turnImage.getScaledInstance(75, 150, Image.SCALE_SMOOTH);
							ImageIcon finalTurn = new ImageIcon(resizedTurn);
							window.getTurnLabel().setIcon(finalTurn);


							if(human.money > 0 && robot.money > 0) {

								window.getDirectionsLabel().setText("Would you like to bet or check?");

								while (!Window.isWasCheckClicked() && !Window.isWasBetClicked()) {
									if (Window.isWasRaiseClicked() || Window.isWasCallClicked() || Window.isWasFoldClicked()) {
										Window.setAllButtons();
									}
									window.getErrorLabel().setText("Please press the check button or enter a bet size and press the bet button to continue.");
								}
							}

							game(human, robot, window);
							window.getBetField().setText("");



							if (!hasHumanFolded && !Robot.getHasRobotFolded()) {

								while(!window.hasClicked()) {
									window.getErrorLabel().setText("Please press the continue button to continue.");
								}
								Window.setAllButtons();

								try {
									riverImage = ImageIO.read(new File(human.findCardImage(Player.getRiver())));

								} catch (IOException e) {
									e.printStackTrace();
								}
								Image resizedRiver = riverImage.getScaledInstance(75, 150, Image.SCALE_SMOOTH);
								ImageIcon finalRiver = new ImageIcon(resizedRiver);
								window.getRiverLabel().setIcon(finalRiver);


								if(human.money > 0 && robot.money > 0) {
									window.getDirectionsLabel().setText("Would you like to bet or check?");
									while (!Window.isWasCheckClicked() && !Window.isWasBetClicked()) {
										if (Window.isWasRaiseClicked() || Window.isWasCallClicked() || Window.isWasFoldClicked()) {
											Window.setAllButtons();
										}
										window.getErrorLabel().setText("Please press the check button or enter a bet size and press the bet button to continue.");
									}
								}

								game(human, robot, window);
								window.getBetField().setText("");



								if (!hasHumanFolded && !Robot.getHasRobotFolded()) {

									while(!window.hasClicked()) {
										window.getErrorLabel().setText("Please press the continue button to continue.");
									}
									Window.setAllButtons();

									try {
										robotCard1Image = ImageIO.read(new File(robot.findCardImage(robot.getCard1())));
										robotCard2Image = ImageIO.read(new File(robot.findCardImage(robot.getCard2())));

									} catch (IOException e) {
										e.printStackTrace();
									}
									Image resizedRobotCard1 = robotCard1Image.getScaledInstance(75, 150, Image.SCALE_SMOOTH);
									Image resizedRobotCard2 = robotCard2Image.getScaledInstance(75, 150, Image.SCALE_SMOOTH);
									ImageIcon finalRobotCard1 = new ImageIcon(resizedRobotCard1);
									ImageIcon finalRobotCard2 = new ImageIcon(resizedRobotCard2);
									window.getRobot1Label().setIcon(finalRobotCard1);
									window.getRobot2Label().setIcon(finalRobotCard2);


									if (human.checkRoyalFlush() && robot.checkRoyalFlush()) {
										window.getDirectionsLabel().setText(human.name + " and " + robot.name + " tie with a royal flush!");
										winningMessage = human.name + " and " + robot.name + " tie with a royal flush!";
										human.addMoney((Player.getPotSize() / 2));
										human.addMoney((Player.getPotSize() / 2));
										hasWon = true;
									} else if (human.checkRoyalFlush() && !robot.checkRoyalFlush()) {
										window.getDirectionsLabel().setText(human.name + " wins with a royal flush!");
										human.addMoney(Player.getPotSize());
										winningMessage = human.name + " wins with a royal flush!";
										hasWon = true;
									} else if (!human.checkRoyalFlush() && robot.checkRoyalFlush()) {
										window.getDirectionsLabel().setText(robot.name + " wins with a royal flush!");
										robot.addMoney(Player.getPotSize());
										winningMessage = robot.name + " wins with a royal flush!";
										hasWon = true;
									}


									if (!hasWon && human.checkStraightFlush() == robot.checkStraightFlush() && human.checkStraightFlush() > 0 && robot.checkStraightFlush() > 0) {
										window.getDirectionsLabel().setText(human.name + " and " + robot.name + " tie with a straight flush!");
										human.addMoney((Player.getPotSize() / 2));
										human.addMoney((Player.getPotSize() / 2));
										System.out.println("Success in straight flush");
										hasWon = true;
										winningMessage = human.name + " and " + robot.name + " tie with a straight flush!";
									} else if (!hasWon && human.checkStraightFlush() > robot.checkStraightFlush()) {
										window.getDirectionsLabel().setText(human.name + " wins with a straight flush!");
										human.addMoney(Player.getPotSize());
										hasWon = true;
										winningMessage = human.name + " wins with a straight flush!";
									} else if (!hasWon && human.checkStraightFlush() < robot.checkStraightFlush()) {
										window.getDirectionsLabel().setText(robot.name + " wins with a straight flush!");
										robot.addMoney(Player.getPotSize());
										hasWon = true;
										winningMessage = robot.name + " wins with a straight flush!";
									}


									if (!evaluate(human, robot, human.evaluate4Kind(), robot.evaluate4Kind(), "four of a kind").isEmpty()) {
										window.getDirectionsLabel().setText(evaluate(human, robot, human.evaluate4Kind(), robot.evaluate4Kind(), "four of a kind"));
									} else if (!evaluate(human, robot, human.checkFullHouse(), robot.checkFullHouse(), "full house").isEmpty()) {
										window.getDirectionsLabel().setText(evaluate(human, robot, human.checkFullHouse(), robot.checkFullHouse(), "a full house"));
									} else if (!evaluate(human, robot, human.evaluateFlush(), robot.evaluateFlush(), "flush").isEmpty()) {
										window.getDirectionsLabel().setText(evaluate(human, robot, human.evaluateFlush(), robot.evaluateFlush(), "a flush"));
									} else if (!evaluate(human, robot, human.evaluateStraight(), robot.evaluateStraight(), "straight").isEmpty()) {
										window.getDirectionsLabel().setText(evaluate(human, robot, human.evaluateStraight(), robot.evaluateStraight(), "a straight"));
									} else if (!evaluate(human, robot, human.evaluateTrips(), robot.evaluateTrips(), "three of a kind").isEmpty()) {
										window.getDirectionsLabel().setText(evaluate(human, robot, human.evaluateTrips(), robot.evaluateTrips(), "three of a kind"));
									} else if (!evaluatePairs(human, robot, human.evaluateTwoPair(), robot.evaluateTwoPair(), "two pair").isEmpty()) {
										window.getDirectionsLabel().setText(evaluatePairs(human, robot, human.evaluateTwoPair(), robot.evaluateTwoPair(), "two pair"));
									} else if (!evaluatePairs(human, robot, human.evaluateOnePair(), robot.evaluateOnePair(), "one pair").isEmpty()) {
										window.getDirectionsLabel().setText(evaluatePairs(human, robot, human.evaluateOnePair(), robot.evaluateOnePair(), "one pair"));
									} else {
										window.getDirectionsLabel().setText(evaluate(human, robot, human.evaluateHighCard(), robot.evaluateHighCard(), "high card"));
									}

									while(!window.hasClicked()) {
										window.getErrorLabel().setText("Please press the continue button to continue.");

									}
									Window.setAllButtons();
								} else {
									if (hasHumanFolded) {
										robot.addMoney(Player.getPotSize());

									} else {
										human.addMoney(Player.getPotSize());
									}
									foldProcedure(window, human, robot);
								}
							} else {
								if (hasHumanFolded) {
									robot.addMoney(Player.getPotSize());
								} else {
									human.addMoney(Player.getPotSize());
								}
								foldProcedure(window, human, robot);
							}
						} else {
							if (hasHumanFolded) {
								robot.addMoney(Player.getPotSize());
							} else {
								human.addMoney(Player.getPotSize());
							}
							foldProcedure(window, human, robot);
						}
					} else {
						if (hasHumanFolded) {
							robot.addMoney(Player.getPotSize());
						} else {
							human.addMoney(Player.getPotSize());
						}
						foldProcedure(window, human, robot);
					}





			} else if (human.money >= 100 && robot.money < 100) {
				window.getDirectionsLabel().setText("Robot does not have a large enough stack to ante. Human wins!");
				while(!window.hasClicked()) {
					window.getErrorLabel().setText("Please press the continue button to continue.");
				}
				Window.setAllButtons();
				window.getContinueButton().setText("Play again");
				while(!window.hasClicked()) {
					window.getDirectionsLabel().setText("Would you like to play again?");
				}
				Window.setAllButtons();
				human.money = 10000;
				robot.money = 10000;
			} else if (human.money < 100 && robot.money >= 100) {
				window.getDirectionsLabel().setText("Human does not have a large enough stack to ante. Robot wins!");
				while(!window.hasClicked()) {
					window.getErrorLabel().setText("Please press the continue button to continue.");
				}
				Window.setAllButtons();
				window.getContinueButton().setText("Play again");
				while(!window.hasClicked()) {
					window.getDirectionsLabel().setText("Would you like to play again?");
				}
				Window.setAllButtons();
				human.money = 10000;
				robot.money = 10000;
			} else {
				window.getDirectionsLabel().setText("Neither player has a large enough stack size to ante. There is a tie!");
				while(!window.hasClicked()) {
					window.getErrorLabel().setText("Please press the continue button to continue.");
				}
				Window.setAllButtons();
				window.getContinueButton().setText("Play again");
				while(!window.hasClicked()) {
					window.getDirectionsLabel().setText("Would you like to play again?");
				}
				Window.setAllButtons();
				human.money = 10000;
				robot.money = 10000;
			}

			if(human.money > 0 && robot.money == 0) {
				window.getDirectionsLabel().setText("Robot is out of money. Human wins!");
				while(!window.hasClicked()) {
					window.getErrorLabel().setText("Please press the continue button to continue.");
				}
				Window.setAllButtons();
				window.getContinueButton().setText("Play again");
				while(!window.hasClicked()) {
					window.getDirectionsLabel().setText("Would you like to play again?");
				}
				Window.setAllButtons();
				human.money = 10000;
				robot.money = 10000;
			} else if(human.money == 0 && robot.money > 0) {
				window.getDirectionsLabel().setText("Human is out of money. Robot wins!");
				while(!window.hasClicked()) {
					window.getErrorLabel().setText("Please press the continue button to continue.");
				}
				Window.setAllButtons();
				window.getContinueButton().setText("Play again");
				while(!window.hasClicked()) {
					window.getDirectionsLabel().setText("Would you like to play again?");
				}
				Window.setAllButtons();
				human.money = 10000;
				robot.money = 10000;
			}

		}
	}


	public static String evaluate(Player a, Player b, int[] aValues, int[] bValues, String handType) {
		for(int i = 0; i < aValues.length; i++) {
			if(!hasWon && i == aValues.length - 1 && aValues[i] == bValues[i] && aValues[i] != 0 && bValues[i] != 0) {
				a.addMoney((Player.getPotSize() / 4));
				b.addMoney((Player.getPotSize() / 4));
				return a.name + " and " + b.name + " tie with " + handType;
			} else if (!hasWon && aValues[i] > bValues[i]) {
				a.addMoney(Player.getPotSize() / 2);
				return a.name + " wins with " + handType;
			} else if (!hasWon && aValues[i] < bValues[i]) {
				b.addMoney(Player.getPotSize() / 2);
				return b.name + " wins with " + handType;
			}
		}
		return winningMessage;
	}

	public static String evaluatePairs(Player a, Player b, int[] aValues, int[] bValues, String handType) {
		if(handType.equals("two pair")) {
			if (!hasWon && aValues[0] == bValues[0] && aValues[1] == bValues[1] && aValues[2] == bValues[2] && aValues[0] != 0 && bValues[0] != 0) {
				a.addMoney((Player.getPotSize() / 4));
				b.addMoney((Player.getPotSize() / 4));
				return a.name + " and " + b.name + " tie with " + handType;
			}
				for (int i = 0; i < 3; i++) {
					if (aValues[i] > bValues[i]) {
						a.addMoney(Player.getPotSize() / 2);
						return a.name + " wins with " + handType;
					} else if (aValues[i] < bValues[i]) {
						b.addMoney(Player.getPotSize() / 2);
						return b.name + " wins with " + handType;
					}
					}
		} else if(!hasWon && handType.equals("one pair")) {
			if (aValues[0] == bValues[0] && aValues[1] == bValues[1] && aValues[2] == bValues[2] && aValues[3] == bValues[3] && aValues[0] != 0 && bValues[0] != 0) {
				a.addMoney(Player.getPotSize() / 4);
				b.addMoney(Player.getPotSize() / 4);
				return a.name + " and " + b.name + " tie with " + handType;
			}
			for (int i = 0; i < 4; i++) {
				if (aValues[i] > bValues[i]) {
					a.addMoney(Player.getPotSize() / 2);
					return a.name + " wins with " + handType;
				} else if (aValues[i] < bValues[i]) {
					b.addMoney(Player.getPotSize() / 2);
					return b.name + " wins with " + handType;
				}
			}
		}
		return winningMessage;
	}






/* From previous version which utilized the console as opposed to a GUI
	public static boolean isInteger(String input) {
    	try {
    		if(Integer.parseInt(input) == 1 || Integer.parseInt(input) == 2) {
				return true;
			} else {
    			return false;
			}
		} catch (NumberFormatException exception) {
    		return false;
		}
	}

	public static boolean isIntegerValidValue(String input, Robot robot) {
		try {
			if(Integer.parseInt(input) == 4 && robot.money > 0 || Integer.parseInt(input) == 5 || Integer.parseInt(input) == 6) {
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException exception) {
			return false;
		}
	}

 */

	public static boolean isIntegerValidBet(String input, Player player, Robot robot, Window window) {
		try {
			if(Integer.parseInt(input) == 0 && Window.isWasBetClicked()) {
				return true;
			}else if (Integer.parseInt(input) <= player.money && Integer.parseInt(input) > 0 && Integer.parseInt(input) % 10 == 0 && Integer.parseInt(input) <= robot.money && Window.isWasBetClicked()) {
					Player.setPotSize(Integer.parseInt(input));
					player.money -= Integer.parseInt(input);
					raiseValue = Integer.parseInt(input);
					return true;
			} else {
				Window.setWasBetClicked(false);
				return false;
				}

		} catch (NumberFormatException exception) {
			Window.setWasBetClicked(false);
			return false;
		}
	}

	public static boolean isIntegerValidRaise(String input, int bet, Player player, Robot robot, Window window) {
		try {
			if(Integer.parseInt(input) <= player.money && Integer.parseInt(input) >= bet * 2 && Integer.parseInt(input) % 10 == 0 && Integer.parseInt(input) <= robot.money && Window.isWasRaiseClicked()) {
				Player.setPotSize(Integer.parseInt(input) - raiseValue);
				player.money -= Integer.parseInt(input);
				player.money += raiseValue;
				raiseValue = Integer.parseInt(input);
				return true;
			} else {
				Window.setWasRaiseClicked(false);
				return false;
			}
		} catch (NumberFormatException exception) {
			Window.setWasRaiseClicked(false);
			return false;
		}
	}

	public static void isValidValue(Player player, Robot robot, Window window) {
	if(player.money > 0 && robot.money > 0) {
		if (Window.isWasCheckClicked()) {
			window.getDirectionsLabel().setText("You have checked.");
			humanBet = false;
			hasChecked = true;
			Window.setWasCheckClicked(false);
			while(!window.hasClicked()) {
				window.getErrorLabel().setText("Please press the continue button to continue.");
			}
			Window.setAllButtons();
		} else {
			isValidBetSize(player, robot, window);

		}
	} else {
		humanBet = false;
		hasChecked = true;
	}
	}

	public static void isValidBetSize(Player player, Robot robot, Window window) {
		boolean canProceed = true;
		String decision;
			decision = window.getBetField().getText();
		while (canProceed) {
			if(Window.isWasBetClicked()) {
				decision = window.getBetField().getText();
				if(isIntegerValidBet(decision, player, robot, window)) {
					canProceed = false;
				}
			}
			window.getDirectionsLabel().setText("Please enter a valid bet size.");
		}
		if (Integer.parseInt(decision) == 0) {
			System.out.println("You have checked.");
			humanBet = false;
			hasChecked = true;
			Window.setWasBetClicked(false);
		} else {
			window.getDirectionsLabel().setText("You have bet " + decision + ".");
			window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
			window.getHumanChipsLabel().setText("You have " + player.money + " chips remaining.");
			window.getBetField().setText("");
			Player.setHumanRecent(Integer.parseInt(decision));
			Window.setWasBetClicked(false);
			humanBet = true;
		}
		while(!window.hasClicked()) {
			window.getErrorLabel().setText("Please press the continue button to continue.");
		}
		Window.setAllButtons();
	}

	public static void isValidRaiseSize(Player player, Robot robot, Window window) {
		String decision;
		boolean canProceed = true;
		decision = window.getBetField().getText();
		while (canProceed) {
			if(Window.isWasRaiseClicked()) {
				decision = window.getBetField().getText();
				if(isIntegerValidRaise(decision, Robot.getRobotRecent(), player, robot, window)) {
					canProceed = false;
				}
			}
			window.getErrorLabel().setText("Please enter a valid raise size");
		}
		window.getDirectionsLabel().setText("You have raised to " + decision + ".");
		window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
		window.getHumanChipsLabel().setText("You have " + player.money + " chips remaining.");
		Player.setHumanRecent(Integer.parseInt(decision));
		window.getBetField().setText("");
		humanBet = true;
		Window.setWasRaiseClicked(false);
		while(!window.hasClicked()) {
			window.getErrorLabel().setText("Please press the continue button to continue.");
		}
		Window.setAllButtons();
	}

	public static void playerAction(Player player, Robot robot, Window window) {
			do {
				window.getErrorLabel().setText("What would you like to do?");
			} while (!Window.isWasCallClicked() && !Window.isWasFoldClicked() && !Window.isWasRaiseClicked());
			if (Window.isWasRaiseClicked()) {
				isValidRaiseSize(player, robot, window);
				humanBet = true;
			} else if (Window.isWasCallClicked()) {
				player.subtractMoney(Robot.getRobotRecent());
				player.addMoney(raiseValue);
				Player.setPotSize(Robot.getRobotRecent() - raiseValue);
				window.getDirectionsLabel().setText("You have called for " + (Robot.getRobotRecent() - raiseValue) + " .");
				window.getPotSizeLabel().setText("The pot is now " + Player.getPotSize());
				window.getHumanChipsLabel().setText("You have " + player.money + " chips remaining.");
				humanBet = false;
				raiseValue = 0;
				Robot.setRobotRaiseValue();
				Window.setWasCallClicked(false);
			} else {
				window.getDirectionsLabel().setText("You have folded. Robot wins!");
				hasHumanFolded = true;
				Robot.setRobotRaiseValue();
				raiseValue = 0;
				Window.setWasFoldClicked(false);
			}
	}

	public static void game(Player human, Robot robot, Window window) {
		int counter = 0;

			do {
				if (counter % 2 == 0) {
					if (Robot.getRobotBet()) {
						playerAction(human, robot, window);
						Robot.setRobotBet(false);
					} else {
						isValidValue(human, robot, window);
					}
				} else {
					if (humanBet) {
						robot.robotDecision(Player.getHumanRecent(), human, window);
					} else {
						robot.robotDecision(window);
					}
				}
				if (hasChecked && counter % 2 == 0 && human.money > 0) {
					robot.robotDecision(window);
					counter++;
				}
				hasChecked = false;
				counter++;
			} while (Robot.getRobotBet() || humanBet && !hasHumanFolded && !Robot.getHasRobotFolded());
		}


	public static void setHumanBet(boolean didBet) {
		humanBet = didBet;
	}

	public static void setRaiseValue() {
		raiseValue = 0;
	}

	public static void foldProcedure(Window window, Player human, Robot robot) {
		while(!window.hasClicked()) {
			window.getErrorLabel().setText("Please press the continue button to continue.");
		}
		window.setHasClicked(false);
		window.getHumanChipsLabel().setText("You have " + human.money + " remaining.");
		window.getRobotChipsLabel().setText("The robot has " + robot.money + " remaining.");
		window.getPotSizeLabel().setText("The pot is now 0.");
	}












	/*private static String compareFourKind(int aValue, int bValue) {
		if(aValue == bValue) {
			return a.name + " and " + b.name + " tie with four of a kind!";
		} else if(aValue < bValue) {
			return b.name + " wins with four of a kind!";
		} else {
			return a.name + " wins with four of a kind!";
		}
	}

	 */


	}






























