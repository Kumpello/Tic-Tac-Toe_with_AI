package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToeAI game = new TicTacToeAI();

        while (true){
            System.out.print("Input command: ");
            String[] commandLine = scanner.nextLine().split(" ");
            if (commandLine[0].equals("exit")){
                break;
            } else if(commandLine[0].equals("start") && commandLine.length == 3){
                for (int i = 1; i <= 2; i++){
                    if(!(commandLine[i].equals("user") || commandLine[i].equals("easy") || commandLine[i].equals("medium") || commandLine[i].equals("hard"))){
                        System.out.println("Bad parameters!");
                        break;
                    }
                }
                game.gameLoop(commandLine);
            } else{
                System.out.println("Bad parameters!");
            }
        }

    }

}
