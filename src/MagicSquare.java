/**
 * A magic square is a 3x3 grid of numbers between 1-9, where each row, column and diagonal sums to 15. We will use
 * this structure to help us implement the numbers game.
 * @author Raju Shrestha
 * @version 1.0
 *
 */

public class MagicSquare {
    private short choices=0;
    private byte [] square;

    /**
     * @param square  Holds a byte array of numbers indicating the magic square grid layout.
     * */
    public MagicSquare(byte[] square){
        this.square=square;



    }

    /**
     * Default constructor
     */
    public MagicSquare(){
        square= new byte[]{
                2, 7, 6,
                9, 5, 1,
                4, 3, 8
        };
        choices= (short) 0b000000000;


    }

    /**
     * @param selection expects a selection between 1-9 (inclusive) and throws an IllegalArgumentException
     * @return If the selection has already been chosen, then the method returns false, otherwise it will set the bit
     * at the index selection - 1 to "on" and return true
     */
    public boolean choose(byte selection){

        short mask= (short) (1 << selection-1);

        if(selection<1 || selection>9){

          throw new IllegalArgumentException(selection + " is not between 1 and 9.");
        }
      else
            choices = (short) (choices | mask);
      return true;
    }


    /**
     * @param selection expects a selection between 1-9 (inclusive) and throws an IllegalArgumentException
     * @return true if the bit at index selection - 1 is set to the "on" position, or false if the bit is in the
     * "off" position.
     */
    public boolean hasAlreadyChosen(byte selection){

        short mask =(short) (1 << selection-1);
        if((choices & mask) !=0){
            return true;
        }
        else
        return false;

    }


    /**
     * @return Getter for the choices field. This number can then be examined to determine if the player has won the
     * match or not.
     */
    public short getChoices() {

        return choices;
    }

    /**
     * @return Getter for the square field. This field helps determine the magic square layout
     */
    public byte[] getSquare() {
        return square;
    }

    /**
     *
     * @return a string representation of the magic square with 3x3 grid of numbers in the predetermined order
     */
    @Override
    public String toString() {
        String printNums="";
        for (byte b : square) {
            if (hasAlreadyChosen(b)) {
                printNums =  printNums + b + " " ;
            }
            else
            printNums = printNums + "_ ";

            if(b==0b00000110 || b==0b00000001){
                printNums=printNums+System.lineSeparator();
            }
        }


        return printNums;
    }

}

