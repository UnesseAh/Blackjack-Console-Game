package blackjack.view;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

import static blackjack.controller.GameController.*;
import static blackjack.view.GameView.lunchGame;

public class Main {
    public static void main(String[] args){
//        lunchGame();
        int[][] sortedCards = createDeckOfCards(1,1);
        int[][][] shuffledCards = shuffleDeckOfCards(sortedCards);
//        ArrayList<int[]> test = shuffle_deck_of_cards(sortedCards);

    }
}