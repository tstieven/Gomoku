package gomoku.aspect;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import gomoku.core.model.Spot;

public aspect JournalTest {

    after(Spot spot): target(spot) && call(void setOccupant(..)){
        System.out.println(spot.getOccupant() + " : " + spot);
        String string = spot.getOccupant() + " : " + spot;
        try{
            FileOutputStream fos = new FileOutputStream("journal.txt", true);
            PrintStream printStream= new PrintStream(fos);
            printStream.println(string);
            printStream.close();
        }catch (FileNotFoundException e) {
            System.out.println("Error : FileNotFoundException");
        }
    }
}

