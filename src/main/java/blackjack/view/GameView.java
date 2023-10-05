package blackjack.view;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static blackjack.controller.GameController.startGame;
import static blackjack.controller.ShuffleController.*;

public class GameView {
    private static Scanner scanner = new Scanner(System.in);

    public static void lunchGame(){
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║              BLACKJACK CONSOLE GAME                 ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║ 1. ║ Start Game                                     ║");
        System.out.println("║ 0. ║ Exit game                                      ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");

        int playerChoice = -1;
        do {
            try{
                System.out.println("Choice :");
                playerChoice = scanner.nextInt();
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                break;
            }
        }while(playerChoice != 1 && playerChoice != 0);

        if(playerChoice == 1) {
            startGame();
        }
        else System.out.println("You exited the game successfully!");
    };

    public static int[][][] createAndShuffleCards(){


        int[][] sortedCards = createDeckOfCards(1,1);
        int[][] shuffledCards = shuffleDeckOfCards(sortedCards);
        int[][][] splitDeckOfCards = splitDeckOfCards(shuffledCards);

        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║ Creating cards...                                   ║");
        System.out.println("║ Shuffling cards...                                  ║");
        System.out.println("║ Splitting cards...                                  ║");
        System.out.println("║ Your bank : 1000$                                   ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");

        return splitDeckOfCards;
    }
    public static int startBet(int bank){
        System.out.printf(" How much do you want to bet? you have : %d$\n", bank);
        int userBet = scanner.nextInt();

        while(userBet != 1 && userBet != 5 && userBet != 25 && userBet != 50 && userBet != 100 && userBet != 500){
            System.out.println("You can only bet 1$, 5$, 25$, 50$, 100$, or 500$");
            userBet = scanner.nextInt();
        }
        System.out.println("You bet : " + userBet + "$");
        return userBet;
    };

    public static int hitOrStandOptions(){
        System.out.println("1-Hit | 2-Stand");
        int playerChoice = scanner.nextInt();
        return playerChoice;
    }

    public static void showHandAndScore(int[][] dealerHand, int[][] playerHand, int dealerScore, int playerScore){
        System.out.println("--------------------------------------------------------");
        System.out.println("Dealer hand : [?,?] " +  Arrays.toString(dealerHand[1]));
        System.out.println("Dealer score : ?" );
        System.out.println();
        System.out.println("Player hand : " + Arrays.toString(playerHand[0]) + " " + Arrays.toString(playerHand[1]));
        System.out.println("Player score : " + playerScore);
        System.out.println("--------------------------------------------------------");

    }
    public static void showPlayerDrawnCardAndScore(int[] extractedCard, int playerScore){
        System.out.println("--------------------------------------------------------");
        System.out.println("Player drawn card : " + Arrays.toString(extractedCard) + " | Score : ("+playerScore+")");
        System.out.println("--------------------------------------------------------");

    }
    public static void announceTheWinner(int playerScore, int dealerScore, int userBet, int bank) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Player Score : " + playerScore + " | Dealer score : " + dealerScore);
        if( playerScore > dealerScore && playerScore < 22 ){
            System.out.println("--------------------------------------------------------");
            System.out.println("Player won");
            System.out.println("Bank = " + (bank + userBet) +"$ | (+" + userBet + "$)");
            System.out.println("--------------------------------------------------------");
        }else if (dealerScore > playerScore && dealerScore < 22 ){
            System.out.println("--------------------------------------------------------");
            System.out.println("Dealer won");
            System.out.println("Bank = " + (bank - userBet) + "$ | (-" + userBet + "$)");
            System.out.println("--------------------------------------------------------");
        }else {
            System.out.println("--------------------------------------------------------");
            System.out.println("Push");
            System.out.println("Bank = " + bank+ "$");
            System.out.println("--------------------------------------------------------");
        }
    }

    public static void showDealerHandAndScore(int[][] dealerHand) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Dealer playing now : ");
        System.out.println("Dealer Hand : " + Arrays.toString(dealerHand[0]) + " " + Arrays.toString(dealerHand[0]));
    }

    public static void showMessageOfBank(int bank, int updatedBank) {
        if(updatedBank > bank){
            System.out.println("Congrats you won");
        }else if (updatedBank < bank ){
            System.out.println("Sorry you lost");
        }else {
            System.out.println("you didn't lose or win");
        }
    }

    public static void showBlackJackMessage() {
            System.out.println("+++++++++++++++++++++++++++++++++");
            System.out.println("♠ Congrats you won a BlackJack ♠");
            System.out.println("+++++++++++++++++++++++++++++++++");
    }

    public static void showDealerHand(int[][] dealerHand, int dealerScore, int bank, int userBet){
        System.out.println("Reveal dealer hand : " +  Arrays.toString(dealerHand[0]) + " " +  Arrays.toString(dealerHand[1]) + " | Dealer score : " + dealerScore);
        System.out.println("The Dealer won");
        System.out.println("Bank = " + bank + "$ | (-" + userBet + "$)");


    }
}
