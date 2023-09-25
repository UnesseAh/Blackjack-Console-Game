package blackjack.model;

public class Card {
    private int suit;
    private int rank;

    public Card(int suit, int rank){
        this.suit = suit;
        this.rank = rank;
    }
    public int getSuit(){
        return this.suit;
    }
    public int getRank(){
        return this.rank;
    }
    public void setSuit(int suit){
        this.suit = suit;
    }
    public void setRank(int rank){
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }
}
