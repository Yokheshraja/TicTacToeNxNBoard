package com.game.tictactoenxnboard;

public class GameBoard {
    //declarations
    private char[][] gameBoard;
    private int dimension;
    //getters
    public int getDimension() {
        return dimension;
    }
    //dimension to print board
    public char[][] boardDimension(int n){
        dimension = 5;
        for(int i = 3; i < n; i++) dimension = dimension + 2;
        gameBoard = new char[dimension][dimension];
        return gameBoard;
    }
    //passing boardDimensions is not mandatory
    //printing empty board and assignment of 2d array
    public void createGameBoard(int boardDimension){
        String boardCreated = "Board created! The game will start with player X";
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if((j % 2) == 0){
                    if((i % 2) == 0) {
                        gameBoard[i][j] = ' ';
                        System.out.print(' ');
                    } else{
                        gameBoard[i][j] = '-';
                        System.out.print('-');
                    }
                } else if((i % 2) == 0){
                    gameBoard[i][j] = '|';
                    System.out.print('|');
                } else{
                    gameBoard[i][j] = '+';
                    System.out.print('+');
                }
            }
            System.out.print('\n');
        }
        System.out.println(boardCreated);
    }
    //value of 2d array changed acc. to i/p
    public char[][] placeMove(int value, char symbol){
        int findSpace = 0;
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if((gameBoard[i][j] == ' ') || (gameBoard[i][j] == 'X') ||
                        gameBoard[i][j] == 'O'){
                    findSpace++;
                    if(findSpace == value){
                        gameBoard[i][j] = symbol;
                    }
                }
            }
        }
        return gameBoard;
    }
    //printing board
    public void printGameBoard(char[][] gameBoard){
        for(char[] row : gameBoard){
            for(char character : row){
                System.out.print(character);
            }
            System.out.println();
        }
    }
}
