/*
 * Program that generates as many NIFs as indicated in <int CANTIDAD> and stores
 * them at NIF.txt sorted by number.
*/
package nif;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Sergio
 */
public final class NIF implements Comparable <NIF>{

    private static final int AMOUNT = 10000;
    private final int NUM;
    private final char CHAR;
    private static final Random RND = new Random();
    private static final String NIF_CHARS = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final File FILE = new File ("NIF.txt");
    private static FileWriter FW;
    private static BufferedWriter BW;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        NIF[] NIF = new NIF[AMOUNT];
        int i;
        
        for (i=0; i < NIF.length; i++){
            NIF[i] = new NIF();
        }
        
        Arrays.sort(NIF);
        FW = new FileWriter (FILE);
        BW = new BufferedWriter(FW);
        
        for (i=0; i< NIF.length; i++){
            BW.write(NIF[i] + "  ");
            if (((i+1)%10==0) && (i!=0) && (i!=NIF.length-1)){
                BW.newLine();
            }
        }
        BW.close();
    }

    /**
     * Generates a random number with range = (0, 100.000.000)
     * and gets the control character for the generated number
     */
    public NIF() {
        NUM = RND.nextInt(10000000);
        CHAR = get_Char();
    }

    /**
     * Calculates the specific control char of a given NIF number.
     * @return 
     */
    public char get_Char() {
        return NIF_CHARS.charAt(NUM%23);
    }
    
    @Override
    public int compareTo(NIF t) {
        return this.NUM-t.NUM;
    }
    
    @Override
    public String toString(){
        String f = String.format("%08d", NUM);
        return f.concat(String.valueOf(CHAR));
    }
}
