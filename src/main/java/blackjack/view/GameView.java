package blackjack.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import static blackjack.controller.GameController.startGame;

public class GameView {
    private static Scanner scanner = new Scanner(System.in);

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

        if(playerChoice == 1) startBet();
        else if (playerChoice == 0) System.out.println("You exited the game successfully!");

    };
    public static void startBet(){
        System.out.println("Your bank : 1000$");
        System.out.println("How much do you want to bet?");
        int userBet = scanner.nextInt();

        while(userBet != 1 && userBet != 5 && userBet != 25 && userBet != 50 && userBet != 100 && userBet != 500){
            System.out.println("You can only bet 1$, 5$, 25$, 50$, 100$, or 500$");
            userBet = scanner.nextInt();
        }

        startGame(userBet);
    };
}
