import java.util.Scanner;
import java.io.PrintStream;
// import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

//System.out.println("");

public class MadLibs{
   
      //in order to create the output file first prompts user to decide 
      //whether they want to create a mad-lib, view their mad-lib or quit
      //if 'c' is selected then while loop is exited
   public static void main(String[] args) throws FileNotFoundException{
      Scanner sc = new Scanner(System.in);
      String action;
      
      intro();
      do {
      //prompt user to start, view, or quit
         System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
         action = sc.nextLine().toLowerCase(); 
         
         if (action.equals("c")){
            processFile(sc);
         }//end if action not c
      
         if (action.equals("v")) { 
            getResultFile(sc);
         }//end if action not v
      }
      while(!action.equals("q"));
      //endGame();
   }
   
   public static void intro() {  
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");
      System.out.println();
   }
   
   public static void endGame() {
      System.out.println();
      System.out.println("|---------------------|");
      System.out.println("|-!-Ending: GoodBye-!-|");
      System.out.println("|---------------------|");
   }
   
   public static Scanner getInputFile(Scanner sc)throws FileNotFoundException{
      //prompt user for file name
      System.out.print("Input file name: ");
      String name = sc.nextLine();
      
      File f = new File(name);
      while(!f.exists()){
         System.out.print("File not found. Try again: ");
         name = sc.nextLine();
         f = new File(name);
      }
      Scanner inputFile = new Scanner(f);
      return inputFile;
   }
   
   //asks for a file to read from for the mad-lib game
   //and creates file (named by user) to input the information
   public static PrintStream getOutputFile(Scanner sc)throws FileNotFoundException{ 
      //user is creating an output txt file 
      System.out.print("Output file name: ");
      String name = sc.nextLine();
      File f = new File(name);
         
         //create a new file with the name
      PrintStream outputFile = new PrintStream(f);
      return outputFile;
   }
   
   public static void getResultFile(Scanner sc)throws FileNotFoundException{
      //user is trying to view a files text context
      System.out.print("Input file name: ");
      String name = sc.nextLine();
      File f = new File(name);
      
      //if can't find ask till found
      while(!f.exists()){
         System.out.print("File not found. Try Again: ");
         name = sc.nextLine();
         f = new File(name);
      }
      
      //when found loop through and print line by line
      Scanner input = new Scanner(f);
      System.out.println("");
      while(input.hasNextLine()){
         String line = input.nextLine();
         //print each line from the file to the console
         System.out.println(line);
      }//has no more lines in text the file
      System.out.println("");
      input.close();
   }
   
   //look through the input file and copy text to the outputfile,
   //if text is "<" prompt ">" look to see if need a or an in front,
   //ask for a replacement article/prompt to paste to outputfile
   public static void processFile(Scanner sc)throws FileNotFoundException{
      Scanner input = getInputFile(sc);
      PrintStream outputFile = getOutputFile(sc);
      System.out.println("");
      //loop through each line
      while(input.hasNextLine()){
         String line = input.nextLine();
         Scanner lineScan = new Scanner(line);
         //loop through each word
         while(lineScan.hasNext()){
            String str = lineScan.next();
            //is it a prompt
            if(str.charAt(0)== '<' && str.charAt(str.length()-1) == '>'){
               String word = str.replace("<", "").replace(">", ": ").replace("-", " ");
               //check what the promt is
               if(str.charAt(1)== 'a' || str.charAt(1)== 'e' || str.charAt(1)== 'i' || str.charAt(1)== 'o' || str.charAt(1)== 'u'){
                  String article = "an ";
                  System.out.print("Please type " + article + word);
                  String newWord = sc.nextLine();
                  outputFile.print(newWord + " ");
               } 
               else {
                  String article = "a ";
                  System.out.print("Please type " + article + word);
                  String newWord = sc.nextLine();
                  outputFile.print(newWord + " ");
               }
            }//if not a prompt just add to the outputfile 
            else {
                  //add non-promt(regular word) to the output text file
               outputFile.print(str + " ");
            }
         }//no more words on line
         outputFile.println();
         lineScan.close();
      }//no more lines in inputfile--- (tarzan, simple, etc)
      System.out.println("Your mad-lib has been created!\n");
   }//done creating/prossesing your madlib
   
}//end intire class/programm