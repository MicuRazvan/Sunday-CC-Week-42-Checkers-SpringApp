package Sunday_CC_Week_42_Checkers_SpringApp;

public class GameState {
    private String[][] board;
    private String turn;
    private String winner;

    public GameState(String[][] board, String turn, String winner) {
        this.board = board;
        this.turn = turn;
        this.winner = winner;
    }

    public String[][] getBoard() { return board; }
    public String getTurn() { return turn; }
    public String getWinner() { return winner; }
}