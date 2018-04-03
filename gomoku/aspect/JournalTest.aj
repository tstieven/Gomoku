package gomoku.aspect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import gomoku.core.model.Spot;
import gomoku.ui.Borad;


public aspect JournalTest {

    private String player1 = "Blue Player";
    private String player2 = "Red Player";
    private String filename = "journal.txt";
    private List<String> player1List = new ArrayList<>();
    private List<String> player2List = new ArrayList<>();

    private void print(String string){
        try{
            Files.write(Paths.get(filename), (string + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printList(List<String> list) {
        print("");
        for (String str : list) {
            print(str);
        }
        print("");
    }

    private void finalPrint(){
        print("Partie terminee \n");
        print("Coup de " + player1 );
        printList(player1List);
        print("Coup de " + player2 );
        printList(player2List);
    }

    after (Borad board): call(void drawGrid(..))&& target(board){
        try {
            System.out.println("new");
            Files.deleteIfExists(Paths.get(filename));
            Files.createFile(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    after(Spot spot): target(spot) && call(void setOccupant(..)){
        if (spot.getOccupant().getName() == player1){
            player1List.add(spot.toString());
        }else {
            player2List.add(spot.toString());
        }
        System.out.println(spot.getOccupant().getName() + " : " + spot);
        String string = spot.getOccupant().getName() + " : " + spot;
        print(string);
    }

    after (Borad board): call(void gameOver(..))&& target(board){
        finalPrint();
    }
}

