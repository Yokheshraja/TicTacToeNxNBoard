package com.game.tictactoenxnboard;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameLogic {
    //declarations
    private int[][] winPositionVertical;
    private int[][] winPositionHorizontal;
    private int[][] winPositionDiagonal;
    //could have been local but used as constants as suggested
    private final char USER = 'X';
    private final char BOT = 'O';
    private final String PLAYER1 = "Player X";
    private final String PLAYER2 = "Player O";
    //game sequence
    public void gameStart() throws InterruptedException {
        int dimension, playerPosition, botPosition;
        int boardDimension;
        char[][] gameBoard;
        UserInterface ui = new UserInterface();
        GameBoard gb = new GameBoard();
        dimension = ui.gameDimensions();
        boardDimension = gb.getDimension();
        //optional
        gameBoard = new char[boardDimension][boardDimension];
        gameBoard = gb.boardDimension(dimension);
        calWinPos(dimension);
        gb.createGameBoard(boardDimension);
        while(ui.getBotPositions().size() + ui.getPlayerPositions().size() < dimension * dimension){
            playerPosition = ui.playerInput();
            //could have replaced some more lines as separate functions but didn't as I didn't want more arguments
            gameBoard = gb.placeMove(playerPosition, USER);
            gb.printGameBoard(gameBoard);
            String printingStatement = checkWinner(ui.getPlayerPositions(), PLAYER1);
            stop(printingStatement, ui.getPlayerPositions(), ui.getBotPositions(), dimension);
            botPosition = ui.botInput();
            gameBoard = gb.placeMove(botPosition, BOT);
            TimeUnit.SECONDS.sleep(2);
            gb.printGameBoard(gameBoard);
            printingStatement = checkWinner(ui.getBotPositions(), PLAYER2);
            stop(printingStatement, ui.getPlayerPositions(), ui.getBotPositions(), dimension);
        }
    }
    //Game ending conditions
    public void stop(String printingStatement, ArrayList<Integer> playerPositions, ArrayList<Integer> botPositions,
                     int dimension){
        if (!printingStatement.equals(" ")){
            System.out.println(printingStatement);
            System.exit(0);
        }
        if(playerPositions.size() + botPositions.size() == dimension * dimension){
            System.out.println("Game ends in DRAW");
            System.exit(0);
        }
    }
    //Calculating winning positions
    public void calWinPos(int dimension){
        winPositionVertical = new int[dimension][dimension];
        winPositionHorizontal = new int[dimension][dimension];
        winPositionDiagonal = new int[2][dimension];
        int positionHor = 1;
        int positionDia = 1;
        boolean bDia = true;
        for(int i = 0; i < dimension; i++){
            int positionVer = i + 1;
            for(int j = 0; j < dimension; j++){
                winPositionHorizontal[i][j] = positionHor;
                positionHor++;
                winPositionVertical[i][j] = positionVer;
                positionVer = positionVer + dimension;
            }
        }
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < dimension; j++){
                if(i == 0) {
                    winPositionDiagonal[i][j] = positionDia;
                    positionDia = positionDia + dimension + 1;
                } else{
                    if(bDia) positionDia = dimension;
                    bDia = false;
                    winPositionDiagonal[i][j] = positionDia;
                    positionDia = positionDia + (dimension - 1);
                }
            }
        }
    }
    //checking winner with bot positions and players positions
    public String checkWinner(ArrayList<Integer> winnerPositions, String Player) {
        String printingStatement = " ";
        int winCountVer, winCountDia, winCountHor;
        for(int i = 0; i < winPositionVertical.length; i++){
            winCountHor = 0;
            winCountVer = 0;
            for(int j = 0; j < winPositionVertical[0].length; j++){
                if(winnerPositions.contains(winPositionVertical[i][j])){
                    winCountVer++;
                    if(winCountVer == winPositionVertical.length)
                        printingStatement = Player + " WON with vertical line";
                }
                if(winnerPositions.contains(winPositionHorizontal[i][j])){
                    winCountHor++;
                    if(winCountHor == winPositionHorizontal.length)
                        printingStatement = Player + " WON with horizontal line";
                }
            }
        }
        for(int i = 0; i < 2; i++){
            winCountDia = 0;
            for(int j = 0; j < winPositionDiagonal[0].length; j++){
                if(winnerPositions.contains(winPositionDiagonal[i][j])){
                    winCountDia++;
                    if(winCountDia == winPositionDiagonal[0].length)
                        printingStatement = Player + " WON with diagonal line";
                }
            }
        }
        return printingStatement;
    }
}
