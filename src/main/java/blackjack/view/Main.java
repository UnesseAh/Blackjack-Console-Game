package blackjack.view;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        lunchGame();
//        Random r = new Random();
        ArrayList<int[]> sortedCards  = createCards(1,1);

//        recursion(r.nextInt(26),sortedCards);
        ArrayList<int[]> test = shuffle_deck_of_cards(sortedCards);
//        for (int[] nn : sortedCards){
//            System.out.println(Arrays.toString(nn));
//        }
    }

    public static void lunchGame(){
        System.out.println("██████╗ ██╗      █████╗  ██████╗██╗  ██╗     ██╗ █████╗  ██████╗██╗  ██╗\n" +
                "██╔══██╗██║     ██╔══██╗██╔════╝██║ ██╔╝     ██║██╔══██╗██╔════╝██║ ██╔╝\n" +
                "██████╔╝██║     ███████║██║     █████╔╝      ██║███████║██║     █████╔╝ \n" +
                "██╔══██╗██║     ██╔══██║██║     ██╔═██╗ ██   ██║██╔══██║██║     ██╔═██╗ \n" +
                "██████╔╝███████╗██║  ██║╚██████╗██║  ██╗╚█████╔╝██║  ██║╚██████╗██║  ██╗\n" +
                "╚═════╝ ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝ ╚════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝\n" +
                "                                                                        ");
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║ 1. ║ Start Game                         ║");
        System.out.println("║ 0. ║ Exit game                          ║");
        System.out.println("╚═════════════════════════════════════════╝");

        int playerChoice = -1;
        do {
            try{
                System.out.println("Choose 1 or 0 :");
                playerChoice = scanner.nextInt();
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                break;
            }
        }while(playerChoice != 1 && playerChoice != 0);

        if(playerChoice == 1) startGame();
        else if (playerChoice == 0)  System.out.println("You exited the game successfully!");

    };

    public static void startGame(){
        System.out.println("You started the game successfully!");
    };

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

//    public static void recursion(int n,ArrayList<int[]> sortedCards){
//        if(n<=0){
//            return ;
//        }
//        recursion(n-1,sortedCards);
//        shuffle_deck_of_cards(sortedCards);
//    }

}