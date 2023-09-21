package blackjack;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<int[]> sortedCards  = createCards(1,1);
        for (int[] card : sortedCards){
            System.out.println(Arrays.toString(card));
        }


    }

    public static ArrayList<int[]> createCards(int rank, int suit){

        ArrayList<int[]> cards = new ArrayList<>();

        for ( int i = suit ; i < 5; i++ )
        {
            for ( int j = rank; j < 14; j++ )
            {
                cards.add(new int[]{j,i});
            }
            rank = 1;
        }
        return cards;
    }
}