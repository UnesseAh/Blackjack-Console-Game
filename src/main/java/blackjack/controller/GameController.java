package blackjack.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameController {

    public static void startGame(int userBet){
        ArrayList<int[]> sortedCards  = createCards(1,1);
        ArrayList<int[]> shuffledCard = shuffle_deck_of_cards(sortedCards);
//        for (int[] nn : sortedCards){
//            System.out.println(Arrays.toString(nn));
//        }
    }

    public static int[][] createDeckOfCards(int startRank, int startSuit){

        int num = startSuit;
        int size = (13 - startRank + 1) + (4 - startSuit) * 13;
        int NUM_SUITS = 4;
        int NUM_RANKS = 13;
        int[][] cards = new int[size][2];

        int cardIndex = 0;
        for(int suit = startSuit; suit <= NUM_SUITS; suit++){
            for(int rank = startRank; rank <= NUM_RANKS; rank++){
                cards[cardIndex][0] = rank;
                cards[cardIndex][1] = suit;
                cardIndex++;
            }
            startRank = 1;
        }
        return cards;
    }

    public static ArrayList<int[]> createCards(int rank, int suit){
        ArrayList<int[]> cards = new ArrayList<>();

        for ( int i = suit ; i < 5; i++ ){
            for ( int j = rank; j < 14; j++ ){
                cards.add(new int[]{j,i});
            }
            rank = 1;
        }
        return cards;
    }

    public static ArrayList<int[]> extract_ith_card(int randNum, ArrayList<int[]> arrayList) {
        int[] getElementAtIndex = arrayList.get(randNum);
        arrayList.remove(randNum);
        arrayList.add(0, getElementAtIndex);
        return arrayList;
    };

    public static ArrayList<int[]> draw_a_card(ArrayList<int[]> list) {
        Random rand = new Random();
        int randNum = rand.nextInt(51);

        ArrayList<int[]> drawCard = extract_ith_card(randNum, list);
        return drawCard;
    };

    public static ArrayList<int[]> shuffle_deck_of_cards(ArrayList<int[]> sortedArray) {
        ArrayList<int[]> suffledCard = draw_a_card(sortedArray);
        return suffledCard;
    };

}
