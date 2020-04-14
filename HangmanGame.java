//Erik the Developer: Hangman Project
//
import java.util.*; //Used to import the Scanner class, for reading user input

public class HangmanGame //The class declaration
{
    
    private int triesLeft; //This keeps track of how many guesses the player has left
    private boolean doneGuessing; //This keeps a true/false "Are we done yet?" value

    private String word; //We'll use this to store the hidden word that the player is guessing
    private String displayWord; //We'll use this variable to store the partly hidden word that we show the player
    
    public HangmanGame(String newWord) //CONSTRUCTOR: Gets called when the class is created
    {
        this.start(newWord); //This line begins the game by calling start();
    }
    
    public void start(String newWord) //This method sets the game up and loops until the player wins or loses
    {
        triesLeft = 6; //Initialize triesLeft to 6
        doneGuessing = false; //The game just started.. we aren't done guessing yet.
        
        word = newWord.toLowerCase(); //We set the hidden word of the game to the word passed to this method
        
        displayWord = ""; //We need to set displayWord to something before we add the asterisks using the loop below
        
        for(int i = 0; i < word.length(); i++) //This loop sets displayWord to a string of asterisks so that the player cannot see the word.
        {
            displayWord += "*"; 
        }
        
        while(triesLeft > 0 && doneGuessing == false) //This loop executes until the player has finished the game
        {
            takeGuess();
        }
    }
    
    public void takeGuess() //This method contains all the logic that occurs during a turn in the game
    {
        Scanner scanner = new Scanner(System.in); //The Scanner class is provided by java.util.*
        String guess = "";
        
        while(guess.length() != 1) //This loop executes until the player has put in a valid(one-letter) guess
        {
            System.out.print("\n" + displayWord + "\n\nEnter a single letter to guess: "); //The "\n" is used to indicate a line break. Use these for cleaning up your console output.
            guess = scanner.nextLine().toLowerCase();
        }
        
        if(word.contains(guess)) //If the player's guess is in the word, we find all occurrences of that character and reveal it in the displayWord
        {
            ArrayList<Integer> indexes = new ArrayList<Integer>(); //We need this arrayList to keep track of every occurrence of the correctly guessed letter
            
            for(int i = 0; i < word.length(); i++) //This loop adds the occurrences of the character to the arrayList
            {
                if(word.charAt(i) == guess.charAt(0)) //guess.charAt(0) refers to the only character in the "guess" String
                {
                    indexes.add(i);
                }
            }
            
            for(int i : indexes) //Example of a for-each loop. This loop reveals the letters at each position in the "indexes" arrayList
            {
                displayWord = displayWord.substring(0, i) + word.charAt(i) + displayWord.substring(i + 1, displayWord.length());
            }
            
            System.out.println("Correct! Tries left: " + triesLeft);
        }
        else //If the player's guess is not in the word, we decrement the tries left and let the player know they messed up
        {
            triesLeft--;
            System.out.println("Wrong guess, try again. Tries left: " + this.triesLeft);
        }
        
        if(triesLeft <= 0)
        {
            lose();
        }
        else if(displayWord.equals(word)) //If the displayWord is equal to the hidden word, then the player has guessed the word
        {
            win();
        }
    }
    
    public void win() //Gets called when the player wins
    {
        doneGuessing = true; //This line was not written in the video, but should be there for clean code
        System.out.println("You won with " + triesLeft + " guesses to spare!");
    }
    
    public void lose() //Gets called when the player loses
    {
        doneGuessing = true; //This line was not written in the video, but should be there for clean code
        System.out.println("You lost! The word was " + word);
    }
}
