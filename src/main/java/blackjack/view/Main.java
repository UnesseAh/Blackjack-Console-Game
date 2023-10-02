package blackjack.view;

import java.util.*;

import static blackjack.controller.ShuffleController.*;

public class Main {
    public static void main(String[] args){
//        lunchGame();
        int[][] sortedCards = createDeckOfCards(10,4);
        int[][] shuffledCards = shuffleDeckOfCards(sortedCards);
        int[][] dd = {{1,1},{1,2},{1,3}};
        int[][][] tt = drawACard(dd);
        System.out.println(Arrays.deepToString(tt));
//        int[][][] tt = piocher_n_cartes(sortedCards);
//        System.out.println("\nHado llewlin:\n");
//        System.out.println(Arrays.deepToString(tt[0]));
//        System.out.println("\nHado llekhrin:\n");
//        System.out.println(Arrays.deepToString(tt[1]));
//        int[][] num1 = {{1,1},{1,2}, {1,3}};
//        int[][] num2 = {{2,1},{2,2}, {2,3}};
//
//        int[][] tt = discard_cards(num1, num2);
//        System.out.println(Arrays.deepToString(tt));

    }
}