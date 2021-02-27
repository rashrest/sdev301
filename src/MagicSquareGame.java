import java.util.Scanner;

/**
 * Driver program for the game
 * @author Raju Shrestha
 * @version 1.0
 */
public class MagicSquareGame {
    private static final short DRAW = 511;
    private static MagicSquare[] mainPlayer = {new MagicSquare(), new MagicSquare()};
    private static String[] players = new String[2];
    private static Scanner console = new Scanner(System.in);
    private static final short[] WINS_WHEN = {98, 273, 140, 266, 84, 161, 146, 56};

    /**
     * @param args command line args
     */
    public static void main(String[] args) {
        printMessage();
        playersName();
        runGame();
    }

    /**
     * prints a welcome message with rules
     */
    public static void printMessage() {
        String star = "************************";
        System.out.println("Welcome to the fame of Magic squares");
        System.out.println("Rules: \n2 players play the game. \nEach player takes turns picking a number from 1-9.");
        System.out.println("No number can be chosen twice.");
        System.out.println("The first player to have 3 numbers that sum to 15 wins! \n 2 7 6 \n 9 5 1 \n 4 3 8 \n" +
                "" + star);

    }

    /**
     * asks players name
     */
    public static void playersName() {
        for (int i = 0; i < players.length; i++) {
            System.out.println("Enter the name for player " + (i+1));
            players[i] = console.nextLine();
        }
    }

    /**
     * method to run the game
     */
    public static void runGame() {
        byte playerSelects;

        while (true) {
            for (int i = 0; i < players.length; i++) {
                playerSelects = playerSelections(i);

                while (mainPlayer[0].hasAlreadyChosen(playerSelects) || mainPlayer[1].hasAlreadyChosen(playerSelects)) {
                    System.out.println("A player has already chosen" + playerSelects);
                    playerSelects = playerSelections(i);
                }

                if (mainPlayer[i].choose(playerSelects)) {
                    System.out.println(mainPlayer[i].toString());
                }

                if (checkWinner(i) || checkDraw()) {
                    System.exit(0);
                }
            }

        }
    }


    /**
     * @param player is the player at index
     * @return which player selects what number
     */
    public static byte playerSelections(int player) {
        byte playerSelection;
        System.out.println(players[player] + ", please enter a number: ");
        playerSelection = console.nextByte();
        return playerSelection;

    }

    /**
     * @param player is the player at index
     * @return true if the game is won by player at index else returns false
     */
    public static boolean checkWinner(int player) {
        for (short winning_condition : WINS_WHEN) {
            if ((mainPlayer[player].getChoices() & winning_condition) == winning_condition) {
                System.out.println("Congratulations " + players[player] + ", you win!!.");
                return true;
            }
        }
        return false;
    }

    /**
     * @return true if game is won and false if its not draw
     */
    public static boolean checkDraw() {
        if ((mainPlayer[0].getChoices() | mainPlayer[1].getChoices()) == DRAW) {
            System.out.println("Draw! no one wins!");
            return true;
        }
        return false;
    }

}
