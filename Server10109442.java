import java.net.*;
import java.io.*;

public class Server10109442 {
    private static char[][] board = new char[3][3];
    public static void main(String argv[]) throws Exception {
        String move;
        ServerSocket welcomeSocket = new ServerSocket(4444);
        board[0][0] = ' ';
        board[0][1] = ' ';
        board[0][2] = ' ';
        board[1][0] = ' ';
        board[1][1] = ' ';
        board[1][2] = ' ';
        board[2][0] = ' ';
        board[2][1] = ' ';
        board[2][2] = ' ';        
        Draw(board);
        System.out.println("You are player 0, wait for player X to move.");
        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            move = inFromClient.readLine();
            outToClient.writeBytes(move + '\n');
            MoveX(board, move);
            System.out.println("Enter your move:");
            move = userIn.readLine();
            outToClient.writeBytes(move + '\n');
            MoveO(board, move);
        }
    }
    public static void Draw (char[][] board) {
        System.out.println(" "+board[0][0]+" | "+board[0][1]+" | "+board[0][2]);
        System.out.println("---|---|---");
        System.out.println(" "+board[1][0]+" | "+board[1][1]+" | "+board[1][2]);
        System.out.println("---|---|---");
        System.out.println(" "+board[2][0]+" | "+board[2][1]+" | "+board[2][2]);
    }
    public static boolean Check (char[][] board) {
        if ((board[0][0] == board[0][1]) && (board[0][1] == board[0][2]) && (board[0][0] != ' ')) return true;
        if ((board[1][0] == board[1][1]) && (board[1][1] == board[1][2]) && (board[1][0] != ' ')) return true;
        if ((board[2][0] == board[2][1]) && (board[2][1] == board[2][2]) && (board[2][0] != ' ')) return true;
        if ((board[0][0] == board[1][0]) && (board[1][0] == board[2][0]) && (board[0][0] != ' ')) return true;
        if ((board[0][1] == board[1][1]) && (board[1][1] == board[2][1]) && (board[0][1] != ' ')) return true;
        if ((board[0][2] == board[1][2]) && (board[1][2] == board[2][2]) && (board[0][2] != ' ')) return true;
        if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[0][0] != ' ')) return true;
        if ((board[2][0] == board[1][1]) && (board[1][1] == board[0][2]) && (board[2][0] != ' ')) return true;
        else return false;
    }
    public static void MoveX (char[][] board, String move) {
        char[] charMove = move.toCharArray();
        int x = Character.getNumericValue(charMove[0]);
        int y = Character.getNumericValue(charMove[2]);
        board[x][y] = 'X';
        Draw(board);
        if (Check(board)) {
            System.out.println("The winner is Player X!");
            System.exit(0);
        }
        if (Tie(board)) {
            System.out.println("It is a tie!");
            System.exit(0);
        }
    }
    public static void MoveO (char[][] board, String move) {
        char[] charMove = move.toCharArray();
        int x = Character.getNumericValue(charMove[0]);
        int y = Character.getNumericValue(charMove[2]);
        board[x][y] = 'O';
        Draw(board);
        if (Check(board)) {
            System.out.println("The winner is Player O!");
            System.exit(0);
        }
        if (Tie(board)) {
            System.out.println("It is a tie!");
            System.exit(0);
        }
    }
    public static boolean Tie (char[][] board) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == ' ') return false;
            }
        }
        return true;
    } 
 }
