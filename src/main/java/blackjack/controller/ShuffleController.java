package blackjack.controller;

import java.util.Random;

public class ShuffleController {
    public static int[][] createDeckOfCards(int startRank, int startSuit){
        int NUM_SUITS = 4, NUM_RANKS = 13;
        int size = (13 - startRank + 1) + (4 - startSuit) * 13;
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

    public static int[][][] extractIthCard(int[][] cards, int index) {
        int[][] extractedCard = {{cards[index][0], cards[index][1]}};
        int[][] restOfCards = new int[cards.length - 1][2];
        int j = 0, i = 0;

        while ( i <= cards.length - 1) {
            if(i != index){
                restOfCards[j] = cards[i];
                j++;
            }
            i++;
        }
        return new int[][][] { extractedCard, restOfCards };
    }

    public static int[][][] drawACard(int[][] cards) {
        if(cards == null || cards.length == 0){
            throw new IllegalArgumentException("The cards array is empty or null.");
        }
        // int randomIndex = ThreadLocalRandom.current().nextInt(cards.length);;
        int randomIndex = (int)(Math.random() * (cards.length));
        return extractIthCard(cards, randomIndex);
    }

    public static int[][] shuffleDeckOfCards(int[][] cards) {
        int[][] shuffledCards = new int[cards.length][2];

        int i = 0;
        while (cards.length > 0) {
            int[][][] drawnCardAndRestOfCards = drawACard(cards);
            shuffledCards[i] = new int[]{drawnCardAndRestOfCards[0][0][0], drawnCardAndRestOfCards[0][0][1]};
            i++;

            int[][] updateCards =  new int[cards.length - 1][2];
            int updateIndex = 0, j = 0;

            while ( j < cards.length ) {
                if(!(cards[j][0] == drawnCardAndRestOfCards[0][0][0] && cards[j][1] == drawnCardAndRestOfCards[0][0][1])){
                    updateCards[updateIndex][0] = cards[j][0];
                    updateCards[updateIndex][1] = cards[j][1];
                    updateIndex++;
                }
                j++;
            }
            cards = updateCards;
        }
        return shuffledCards;
    }

    public static int[][] removeCard(int[][] cards, int[] cardToRemove){
        int[][] updateCards =  new int[cards.length - 1][2];
        int updateIndex = 0;

        for (int i = 0; i < cards.length; i++) {
            if(!(cards[i][0] == cardToRemove[0] && cards[i][1] == cardToRemove[1])){
                updateCards[updateIndex][0] = cards[i][0];
                updateCards[updateIndex][1] = cards[i][1];
                updateIndex++;
            }
        }
        return updateCards;
    }

    public static int[][][] splitDeckOfCards(int[][] cards){
        Random random = new Random();
        int randomIndex = random.nextInt(4)+26;
        int[][] p = new int[randomIndex][];
        int[][] r = new int[cards.length-randomIndex][];

        for (int i = 0; i < randomIndex; i++) {
            p[i] = cards[i];
        }

        for (int i = randomIndex; i < cards.length ; i++) {
            r[i-randomIndex] = cards[i];
        }
        int[][][] tt = new int[2][][];
        tt[0] = p;
        tt[1] = r;
        return tt;
    }

    public static int[][] discardCards(int[][] card1, int[][] card2 ){
        int size = card1.length + card2.length;
        int[][] newList = new int[size][2];

        for (int i = 0; i <card1.length; i++) {
            newList[i] = card1[i];
        }
        for (int i = 0; i < card2.length ; i++) {
            newList[i+ card1.length] = card2[i];
        }
        return newList;
    }
}
