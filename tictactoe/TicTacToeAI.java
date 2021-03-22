package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeAI {
    private Status status;

    private char[][] cells;
    private char symbol;
    private int roundCount;

    public TicTacToeAI() {
        this.status = Status.GAME;
        this.cells = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        this.symbol = 'X';
        this.roundCount = 0;
    }

    public TicTacToeAI(char[][] cells, char symbol, int roundCount) {
        this.status = Status.GAME;
        this.cells = cells;
        this.symbol = symbol;
        this.roundCount = roundCount;
    }

    public void gameLoop(String[] player){
        Scanner scanner = new Scanner(System.in);
        System.out.println(toString());
        while (getStatus() == TicTacToeAI.Status.GAME){
            for (int i = 1; i <= 2; i++){
                switch (player[i]){
                    case "user":
                        boolean rightMove = false;
                        while (!rightMove){
                            System.out.print("Enter the coordinates: ");
                            try{
                                int x = Integer.parseInt(scanner.next());
                                int y = Integer.parseInt(scanner.next());
                                setField(--x, --y);
                                System.out.println(toString());
                            } catch (NumberFormatException e){
                                System.out.println("You should enter numbers!");
                                rightMove = false;
                                continue;
                            } catch (IllegalArgumentException e){
                                System.out.println("This cell is occupied! Choose another one!");
                                rightMove = false;
                                continue;
                            } catch (ArrayIndexOutOfBoundsException e){
                                System.out.println("Coordinates should be from 1 to 3!");
                                rightMove = false;
                                continue;
                            }
                            rightMove = true;
                        }
                        break;
                    case "easy":
                        System.out.println("Making move level \"easy\"");
                        aiMoveEasy();
                        System.out.println(toString());
                        break;
                    case "medium":
                        System.out.println("Making move level \"medium\"");
                        aiMoveMedium();
                        System.out.println(toString());
                        break;
                    case "hard":
                        System.out.println("Making move level \"hard\"");
                        aiMoveHard();
                        System.out.println(toString());
                        break;
                }
                if(getStatus() != TicTacToeAI.Status.GAME){
                    break;
                }
            }

        }

        System.out.println(getStatusMessage());
    }

    private void checkRow(int row) {
        if (cells[row][0] == cells[row][1] && cells[row][1] == cells[row][2]) {
            if (status == Status.GAME) {
                if (cells[row][0] == 'X') {
                    status = Status.X_WINS;
                } else if (cells[row][0] == 'O') {
                    status = Status.O_WINS;
                }
            }
        }
    }

    private int checkRow(int row, char sign) {
        if ((cells[row][0] == sign) && (cells[row][1] == sign) && (cells[row][2] == ' ')){
            return 2;
        } else if ((cells[row][1] == sign) && (cells[row][2] == sign) && (cells[row][0] == ' ')){
            return 0;
        } else if ((cells[row][0] == sign) && (cells[row][2] == sign) && (cells[row][1] == ' ')){
            return 1;
        }
        return 420;
    }

    private void checkColumn(int column) {
        if (cells[0][column] == cells[1][column] && cells[1][column] == cells[2][column]) {
            if (status == Status.GAME) {
                if (cells[0][column] == 'X') {
                    status = Status.X_WINS;
                } else if (cells[0][column] == 'O') {
                    status = Status.O_WINS;
                }
            }
        }
    }

    private int checkColumn(int column, char sign) {
        if ((cells[0][column] == sign) && (cells[1][column] == sign) && (cells[2][column] == ' ')){
            return 2;
        } else if ((cells[1][column] == sign) && (cells[2][column] == sign) && (cells[0][column] == ' ')){
            return 0;
        } else if ((cells[0][column] == sign) && (cells[2][column] == sign) && (cells[1][column] == ' ')){
            return 1;
        }
        return 420;
    }

    private void checkDiagonals() {
        if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2] || cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
            if (status == Status.GAME) {
                if (cells[1][1] == 'X') {
                    status = Status.X_WINS;
                } else if (cells[1][1] == 'O') {
                    status = Status.O_WINS;
                }
            }
        }
    }


    private Cell checkDiagonals(char sign) {
        if ((sign == cells[1][1]) && (cells[1][1] == cells[2][2]) && (cells[0][0] == ' ')){
            return new Cell(0, 0);
        } else if((sign == cells[0][0]) && (sign == cells[2][2]) && (cells[1][1] == ' ')){
            return new Cell(1, 1);
        } else if ((sign == cells[0][0]) && (sign == cells[1][1]) && (cells[2][2] == ' ')){
            return new Cell(2, 2);
        } else if((sign == cells[1][1]) && (sign == cells[2][0]) && (cells[0][2] == ' ')){
            return new Cell(0, 2);
        } else if ((sign == cells[0][2]) && (sign == cells[2][0]) && (cells[1][1] == ' ')){
            return new Cell(1, 1);
        } else if((sign == cells[0][2]) && (sign == cells[1][1]) && (cells[2][0] == ' ')){
            return new Cell(2, 0);
        }
        return null;
    }

    private boolean checkDiagonals(char[][] board) {
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] || board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[1][1] == 'X' || board[1][1] == 'O'){
                return true;
            }
        }
        return false;
    }

    private boolean checkColumn(char[][] board, int column) {
        if (board[0][column] == board[1][column] && board[1][column] == board[2][column]) {
            if (board[0][column] == 'X' || board[0][column] == 'O') {
                return true;
            }
        }
        return false;
    }

    private boolean checkRow(char[][] board, int row) {
        if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
            if (board[row][0] == 'X' || board[row][0] == 'O') {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if(cell == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    private char checkWinner(char[][] board) {
        if(checkDiagonals(board) || checkRow(board, 0) || checkRow(board, 1) || checkRow(board, 2) ||
                checkColumn(board, 0) || checkColumn(board, 1) || checkColumn(board, 2) ||
                checkIfFull(board)) {
            if(checkDiagonals(board)){
                return board[1][1];
            }
            for (int i = 0; i < 3; i++) {
                if (checkRow(board, i)) {
                    return board[i][i];
                }
                if (checkColumn(board, i)){
                    return board[i][i];
                }
            }
        }
        if (checkIfFull(board)){
            return ' ';
        }
        return 'n';
    }

    public void checkDraw() {
        if (status == Status.GAME && roundCount == 9) {
            status = Status.DRAW;
        }
    }

    public void setField(int x, int y) throws IllegalArgumentException, ArrayIndexOutOfBoundsException{
        if (x > 2 || x < 0 || y > 2 || y < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (cells[x][y] != ' ') {
            throw new IllegalArgumentException();
        }
        cells[x][y] = symbol;
        symbol = symbol == 'X' ? 'O' : 'X';
        roundCount++;

        checkRow(x);
        checkColumn(y);
        checkDiagonals();
        checkDraw();
    }

    public void aiMoveEasy(){
        Random random = new Random();
        boolean emptySpace = false;
        int x = 0;
        int y = 0;
        while (!emptySpace){
            x = random.nextInt(3);
            y = random.nextInt(3);
            if(cells[x][y] == ' '){
                emptySpace = true;
            }
        }
        setField(x, y);
    }

    public void aiMoveMedium(){
        boolean move = false;
        char currentSymbol = symbol;
        for (int i = 0; i < 2; i++){
            for (int ii = 0; ii < 3; ii++){
                if (checkRow(ii, currentSymbol) < 3){
                    setField(ii, checkRow(ii, currentSymbol));
                    move = true;
                    break;
                }
                if (checkColumn(ii, currentSymbol) < 3){
                    setField(checkColumn(ii, currentSymbol), ii);
                    move = true;
                    break;
                }
                if (checkDiagonals(currentSymbol) != null){
                    setField(checkDiagonals(currentSymbol).getX(), checkDiagonals(currentSymbol).getY());
                    move = true;
                    break;
                }
            }
            if(move){
                break;
            }
            currentSymbol = currentSymbol == 'X' ? 'O' : 'X';
        }
        if(!move){
            Random random = new Random();
            boolean emptySpace = false;
            int x = 0;
            int y = 0;
            while (!emptySpace){
                x = random.nextInt(3);
                y = random.nextInt(3);
                if(cells[x][y] == ' '){
                    emptySpace = true;
                }
            }
            setField(x, y);
        }
    }

    public void aiMoveHard(){
        Cell bestMove = new Cell();
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(cells[i][j] == ' '){
                    cells[i][j] = symbol;
                    int score = miniMax(cells, 0, false);
                    cells[i][j] = ' ';
                    if (score > bestScore){
                        bestScore = score;
                        bestMove.setX(i);
                        bestMove.setY(j);
                    }
                }
            }
        }
        setField(bestMove.getX(), bestMove.getY());
    }

    public int miniMax(char[][] board, int depth, boolean max){
        char otherSymbol = symbol == 'X' ? 'O' : 'X';
        if(checkWinner(board) == symbol){
            return 10;
        } else if (checkWinner(board) == otherSymbol){
            return -10;
        } else if (checkWinner(board) == ' '){
            return 0;
        }

        if(max) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' '){
                        board[i][j] = symbol;
                        bestScore = Math.max(bestScore, miniMax(board, depth + 1, false));
                        board[i][j] = ' ';
                    }
                }
            }
            return bestScore;
        } else{
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' '){
                        board[i][j] = otherSymbol;
                        bestScore = Math.min(bestScore, miniMax(board, depth + 1, true));
                        board[i][j] = ' ';
                    }
                }
            }
            return bestScore;
        }
    }

    public void aiMoveEasiest(){
        Random random = new Random();
        boolean win = true;
        while (win){
            boolean emptySpace = false;
            int x = 0;
            int y = 0;
            while (!emptySpace){
                x = random.nextInt(3);
                y = random.nextInt(3);
                if(cells[x][y] == ' ' ){
                    emptySpace = true;
                }
            }
            setField(x, y);
            checkRow(x);
            checkColumn(y);
            checkDiagonals();
            checkDraw();
            if(status != Status.GAME || status != Status.DRAW){
                cells[x][y] = ' ';
            } else{
                win = false;
            }
        }
    }

    public Status getStatus(){
        return status;
    }

    @Override
    public String toString() {
        return String.format("---------%n" +
                "| %c %c %c |%n" +
                "| %c %c %c |%n" +
                "| %c %c %c |%n" +
                "---------", cells[0][0], cells[0][1], cells[0][2], cells[1][0], cells[1][1], cells[1][2], cells[2][0], cells[2][1], cells[2][2]);
    }



    public enum Status {
        GAME,
        X_WINS,
        O_WINS,
        DRAW
    }

    public String getStatusMessage() {
        switch (status) {
            case GAME:
                return "Game not finished";
            case X_WINS:
                return "X wins";
            case O_WINS:
                return "O wins";
            case DRAW:
                return "Draw";
            default:
                return "Impossible";
        }
    }
}
