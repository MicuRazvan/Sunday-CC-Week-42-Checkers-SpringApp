package Sunday_CC_Week_42_Checkers_SpringApp;

import org.springframework.stereotype.Service;

@Service
public class GameService {
    private String[][] board = new String[8][8];
    private String turn = "red";

    public GameService() {
        resetGame();
    }

    public void resetGame() {
        this.turn = "red";
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 != 0) {
                    if (r < 3) {
                        board[r][c] = "blue";
                    } else if (r > 4) {
                        board[r][c] = "red";
                    } else {
                        board[r][c] = null;
                    }
                } else {
                    board[r][c] = null;
                }
            }
        }
    }

    public boolean makeMove(int fromR, int fromC, int toR, int toC) {
        String piece = board[fromR][fromC];

        if (piece == null || !piece.equals(turn)) return false;
        if (board[toR][toC] != null) return false;
        if ((toR + toC) % 2 == 0) return false;

        int rowDiff = toR - fromR;
        int colDiff = Math.abs(toC - fromC);

        int allowedDirection = piece.equals("red") ? -1 : 1;

        if (rowDiff == allowedDirection && colDiff == 1) {
            board[toR][toC] = piece;
            board[fromR][fromC] = null;
            switchTurn();
            return true;
        }

        if (rowDiff == (allowedDirection * 2) && colDiff == 2) {
            int midR = fromR + allowedDirection;
            int midC = fromC + (toC - fromC) / 2;
            String opponentPiece = piece.equals("red") ? "blue" : "red";

            if (opponentPiece.equals(board[midR][midC])) {
                board[toR][toC] = piece;
                board[fromR][fromC] = null;
                board[midR][midC] = null;
                switchTurn();
                return true;
            }
        }

        return false;
    }

    private void switchTurn() {
        turn = turn.equals("red") ? "blue" : "red";
    }

    public String checkWinner() {
        int redCount = 0;
        int blueCount = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ("red".equals(board[r][c])) redCount++;
                if ("blue".equals(board[r][c])) blueCount++;
            }
        }
        if (redCount == 0) return "blue";
        if (blueCount == 0) return "red";
        return null;
    }

    public String[][] getBoard() { return board; }
    public String getTurn() { return turn; }
}