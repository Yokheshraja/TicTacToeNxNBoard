package com.game.tictactoenxnboard;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserInterface {
    //declarations
    private String typeInput;
    private int N;
    private final ArrayList<Integer> playerPositions = new ArrayList<>();
    private final ArrayList<Integer> botPositions = new ArrayList<>();

    //getters
    public ArrayList<Integer> getPlayerPositions() {
        return playerPositions;
    }

    public ArrayList<Integer> getBotPositions() {
        return botPositions;
    }
    //enumerations can also be used for typeInput
    //Board dimension input
    public int gameDimensions(){
        typeInput = "GameDimensions";
        N = getInput();
        return N;
    }
    //player input
    public int playerInput(){
        int playerPosition;
        typeInput = "PlayerInput";
        playerPosition = getInput();
        playerPositions.add(playerPosition);
        return playerPosition;
    }
    //bot input
    public int botInput(){
        int botPosition;
        typeInput = "BotInput";
        botPosition = getInput();
        botPositions.add(botPosition);
        return botPosition;
    }
    //method to get inputs
    public int getInput(){
        String dim = "Enter the value of N (3 or more):";
        String input = "Your Turn! Enter your placement";
        String bot = "Wait! it's bot turn!";
        boolean check = true;
        int value = 0;
        switch (typeInput) {
            case "GameDimensions":
                //input validation and inputs taken
                while (check) {
                    Scanner scan = new Scanner(System.in);
                    System.out.println(dim);
                    if (scan.hasNextInt()) {
                        value = scan.nextInt();
                        if (value > 2) {
                            check = false;
                        }
                    } else
                        continue;
                }
                break;
            case "PlayerInput":
                while (check) {
                    Scanner scan = new Scanner(System.in);
                    System.out.println(input + "(" + "1-" + N * N + "):");
                    if (scan.hasNextInt()) {
                        value = scan.nextInt();
                        if ((value <= N * N) && (value > 0) && !playerPositions.contains(value)
                                && !botPositions.contains(value)) {
                            check = false;
                        }
                    } else
                        continue;
                }
                break;
            case "BotInput":
                System.out.println(bot);
                while (check) {
                    value = new Random().nextInt(N * N) + 1;
                    if (!botPositions.contains(value) && !playerPositions.contains(value)) {
                        check = false;
                    }
                }
                break;
            default:
                break;
        }
        return value;
    }
}
