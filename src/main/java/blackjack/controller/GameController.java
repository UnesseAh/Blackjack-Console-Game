package blackjack.controller;

import java.util.Arrays;

import static blackjack.controller.ShuffleController.*;
import static blackjack.view.GameView.*;

public class GameController {

    public static void startGame() {

        int[][][] splitDeckOfCards = createAndShuffleCards();
        int bank = 500;
//        System.out.println("Cards before split :");
//        System.out.println(Arrays.deepToString(splitDeckOfCards));


        int userBet = startBet(bank);

        int[][] restOfCards = splitDeckOfCards[1];
//        int[][] pyoch = splitDeckOfCards[0];
        int[][] pyoch = {{1, 1}, {10, 2}, {5, 1}, {5, 1}, {10, 1}, {2, 1}, {3, 1}, {6, 5}};
//        int[][] pyoch = {{6, 1}, {5, 2}, {5, 1}, {5, 1}, {10, 1}, {2, 1}, {3, 1}, {6, 5}};

        System.out.println(Arrays.deepToString(pyoch));
//        System.out.println(Arrays.deepToString(restOfCards));
//        System.out.println();

        int[][] playerHand = new int[2][2];
        int[][] dealerHand = new int[2][2];

        int i = 0;
        int playerIndex = 0;
        int dealerIndex = 0;

        while (i < 4) {
            int[][][] extractedAndRestOfCards = extractIthCard(pyoch, 0);

            pyoch = extractedAndRestOfCards[1];
            int[] extractedCard = extractedAndRestOfCards[0][0];

            if (i < 2) {
                playerHand[playerIndex] = extractedCard;
                playerIndex++;
            } else {
                dealerHand[dealerIndex] = extractedCard;
                dealerIndex++;
            }
            i++;
        }


        int playerScore = countHandScore(playerHand);
        int dealerScore = countHandScore(dealerHand);

        showHandAndScore(dealerHand, playerHand, dealerScore, playerScore);

        boolean blackJack = playerScore == 21;

        if (blackJack) {
            showBlackJackMessage();
            bank += userBet;
            System.out.println("Bank = " + bank + "$ | (+" + userBet + "$)");
            return;
        }


        int hitOrStandResult = hitOrStandOptions();

//        while (bank > 0) {
            Object[] dealerGameResult;
            Object[] playerGameResult;

            switch (hitOrStandResult) {
                case 1:
                    playerGameResult = playerGame(pyoch, playerScore);
                    playerScore = (int) playerGameResult[0];
                    pyoch = (int[][]) playerGameResult[1];

                    if (playerScore > 21) {
                        bank -= userBet;
                        showDealerHand(dealerHand, dealerScore, bank, userBet);

                        break;
                    }

                    showDealerHandAndScore(dealerHand);
                    dealerGameResult = dealerGame(pyoch, dealerScore);
                    dealerScore = (int) dealerGameResult[0];
                    pyoch = (int[][]) dealerGameResult[1];

                    if (dealerScore > 21) {
                        bank += userBet;
                        break;
                    }

                    bank = updateTheBank(playerScore, dealerScore, bank, userBet);
                    announceTheWinner(playerScore, dealerScore, userBet, bank);

                    System.out.println(Arrays.deepToString(pyoch));
                    break;
                case 2:
                    showDealerHandAndScore(dealerHand);

                    dealerGameResult = dealerGame(pyoch, dealerScore);
                    dealerScore = (int) dealerGameResult[0];
                    pyoch = (int[][]) dealerGameResult[1];

                    announceTheWinner(playerScore, dealerScore, userBet, bank);
                    bank = updateTheBank(playerScore, dealerScore, bank, userBet);

                    System.out.println(Arrays.deepToString(pyoch));
                    break;
            }
//            System.out.println("player Score : " + playerScore);
//            System.out.println("Dealer Score : " + dealerScore);
            userBet = startBet(bank);
//        }

    }

    public static void playFirstRound(){

    }


    public static int updateTheBank(int playerScore, int dealerScore, int bank, int userBet) {
        if (playerScore > dealerScore) {
            bank += userBet;
        } else if (playerScore < dealerScore) {
            bank -= userBet;
        }
        return bank;
    }


    public static Object[] playerGame(int[][] pyoch, int playerScore) {
        int hitOrStandResult = 1;

        while (hitOrStandResult == 1) {

            int[][][] extractedAndRestOfCards = extractIthCard(pyoch, 0);

            pyoch = extractedAndRestOfCards[1];
            int[] extractedCard = extractedAndRestOfCards[0][0];

            int cardScore = countCardScore(extractedCard[0]);

            if (extractedCard[0] == 1) {
                if (playerScore + countCardScore(extractedCard[0]) > 22) {
                    cardScore = 1;
                } else cardScore = countCardScore(extractedCard[0]);
            }
            playerScore += cardScore;

            if (playerScore < 22) {
                showPlayerDrawnCardAndScore(extractedCard, playerScore);
                hitOrStandResult = hitOrStandOptions();
            } else {
                showPlayerDrawnCardAndScore(extractedCard, playerScore);
                break;
            }
        }

        Object[] playerScoreAndNewPyoch = new Object[2];
        playerScoreAndNewPyoch[0] = playerScore;
        playerScoreAndNewPyoch[1] = pyoch;
        return playerScoreAndNewPyoch;
    }

    public static Object[] dealerGame(int[][] pyoch, int dealerScore) {

        while (dealerScore < 16) {
            int[][][] extractedAndRestOfCards = extractIthCard(pyoch, 0);

            pyoch = extractedAndRestOfCards[1];
            int[] extractedCard = extractedAndRestOfCards[0][0];

            dealerScore += extractedCard[0];

            if (dealerScore > 16) {
                System.out.println("--------------------------------------------------------");
                System.out.println("Dealer draws card : " + Arrays.toString(extractedCard) + " | Score : (" + dealerScore + ")" + ((dealerScore > 22) ? "\n(Dealer lost!)" : ""));
                break;
            } else {
                System.out.println("--------------------------------------------------------");
                System.out.println("Dealer draws card : " + Arrays.toString(extractedCard) + " | Score : (" + dealerScore + ")" + ((dealerScore > 22) ? "\n(Dealer lost!)" : ""));
            }
        }

        Object[] dealerScoreAndNewPyoch = new Object[2];
        dealerScoreAndNewPyoch[0] = dealerScore;
        dealerScoreAndNewPyoch[1] = pyoch;
        return dealerScoreAndNewPyoch;

    }

    public static int countCardScore(int extractedCard) {
        int cardScore = 0;

        if (extractedCard == 1) {
            cardScore = 11;
        } else if (extractedCard >= 2 && extractedCard <= 10) {
            cardScore = extractedCard;
        } else {
            cardScore = 10;
        }
        return cardScore;
    }


    public static int countHandScore(int[][] hand) {
        int j = 0;
        int handScore = 0;
        while (j < hand.length) {
            int cardScore = hand[j][0];
            switch (cardScore) {
                case 1:
                    cardScore = 11;
                    break;
                case 11:
                case 12:
                case 13:
                    cardScore = 10;
                    break;
            }
            handScore += cardScore;
            j++;
        }
        return handScore;
    }

}
