package signcheck;

import java.util.Map;
import java.util.HashMap;

import signcheck.LetterInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.IOException;
import java.lang.NumberFormatException;

import java.util.Scanner;

public class SignChecker {
    
    public static void main (String[] args) {
        
        // Create HashMap to hold information regarding available sign pieces
        HashMap<String, LetterInfo> available_pieces
            = new HashMap<String, LetterInfo>();

        // Load information regarding available sign pieces and signboard from file.
        
        try {
            File file = new File("sign_info.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            line = bufferedReader.readLine();
            float cmPerLine = Float.parseFloat(line);

            line = bufferedReader.readLine();
            int numberOfLines = Integer.parseInt(line);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String[] splitLine = line.split("\\|");
                //for (String s : splitLine) System.out.print(s+"_");
                available_pieces.put(splitLine[0], new LetterInfo(Integer.parseInt(splitLine[1])));
            }
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("eh");
            e.printStackTrace();
            System.exit(1);
        }

         // Ask user if message is to be written twice (two sided sign board)
        System.out.print("Will this be string be written on both sides of the sign? (y/n):");
        Scanner input = new Scanner(System.in);
        String twoSides = input.nextLine();
        boolean twoSidedMessage = (twoSides.equalsIgnoreCase("y") ? true : false); 

        // Accept desired string from user
        System.out.println("Enter desired string to be written on sign. " 
                + "If each side of a two sided sign is unique, type both messages out here.");
        System.out.println("Press enter to start new line. Press enter on a blank line to submit full string. ");

        StringBuffer str = new StringBuffer();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.equals("")) break;
            str.append(line);
            str.append('\n');
        }
        
        // Count characters needing to be used

        HashMap<String, Integer> required_pieces
            = new HashMap<String, Integer>();
        int piecesToAdd = twoSidedMessage ? 2 : 1;
        for (int i = 0; i < str.length(); i++) {
            String s = str.substring(i, i+1).toUpperCase();
            if (required_pieces.get(s) == null) {
                required_pieces.put(s, Integer.valueOf(piecesToAdd));
            } else {
                required_pieces.put(s, Integer.valueOf(required_pieces.get(s).intValue() + piecesToAdd));
            }
        }



        // Check if there are enough pieces to write the desired string
        boolean enoughPieces = true;
        for (Map.Entry<String, Integer> entry : required_pieces.entrySet()) {
            if (entry.getKey().equals("\n") || entry.getKey().equals(" ")) continue;
            int extraPieces = available_pieces.getOrDefault(
                    entry.getKey(), new LetterInfo(0, 1)).getCount()- entry.getValue().intValue();
            if (extraPieces < 0) {
                enoughPieces = false;
                System.out.println("Not enough \'" + entry.getKey() + "\" pieces. Need " + (-1 * extraPieces) + " more.");
            }
        }
        
        boolean enoughSpace = true;
        // Check if there is space to write desired string
        // pass for now.

        // 
        
        if (enoughPieces && enoughSpace) {
            System.out.println("You have enough pieces and space to write out your message.");
        } else if (enoughPieces && !enoughSpace) {
            System.out.println("You do not have enough space to write out your message.");
        } else if (!enoughPieces && enoughSpace) {
            System.out.println("You do not have enough pieces to write out your message.");
        } else {
            System.out.println("You do not have enough pieces OR space to write out your message.");
        }
    }

}
