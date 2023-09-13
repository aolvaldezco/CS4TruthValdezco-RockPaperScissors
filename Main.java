import java.util.Random;
import java.util.Scanner;

class Main {
  private static Random random = new Random();
  private static Scanner sc = new Scanner(System.in);

  public static int playerTurn(int x, int y){ 
    //output player turn instructions
    System.out.println("\n\nYour Turn (Round "+x+" of "+y+")\n"+
                       "1. Rock\n"+
                       "2. Paper\n"+
                       "3. Scissors\n");
    int playerObj = sc.nextInt();
    return playerObj; //return player input
  }

  public static int botTurn(){
    //generate and return random # btwn 0 and 3
    int botPlay = random.nextInt(3)+1;
    return botPlay;
  }
  public static String turnConvert(int intTurn){
    //convert player input into rockpaperscissors
    String stringCon = "";
    switch(intTurn){
      case 1:
        stringCon = "Rock";
        break;
      case 2:
        stringCon = "Paper";
        break;
      case 3:
        stringCon = "Scissors";
        break;
      }
    return stringCon;
  }
  
  public static int gameRound(int totalRounds){
    
    //output start screen
    System.out.println("\n\n\nWelcome! Would You Like"+ 
                       " To Play Rock Paper Scissors?");
    System.out.println("1. Play against AI");
    System.out.println("2. Change Number of Rounds per Game (Currently:" 
                       + totalRounds +")");
    System.out.println("3. Exit Game");
    
    int input1 = sc.nextInt(); //listen for input
    int playAgain = 1; //initialize default method return value
    
    switch(input1){ //switch case for menu input
      case 1:
        
        //initialize round wins
        int botWins = 0;
        int playerWins = 0;
        
        //start game
        for(int roundCount = 0; 
            roundCount <= (totalRounds-1); 
            roundCount++){
          //listen for players' turn
          int pTurn = playerTurn((roundCount+1),totalRounds);
          int bTurn = botTurn();

          //convert game turns into strings
          String pTurnString = turnConvert(pTurn);
          String bTurnString = turnConvert(bTurn);
          
         
          while((pTurn>3)||(pTurn<1)){ //prevent invalid inputs
            System.out.println("Invalid Turn!"
                              +"Please Replay the Round!");
            pTurn = playerTurn((roundCount+1),totalRounds);
            bTurn = botTurn();
            pTurnString = turnConvert(pTurn);
            bTurnString = turnConvert(bTurn);
          }
          
          while(pTurn == bTurn){ //prevent ties
            System.out.println("It was a Tie! You"+
                              " both chose "+pTurnString+"!");
            System.out.println("Please Replay the Round.");
            
            pTurn = playerTurn((roundCount+1),totalRounds);
            bTurn = botTurn();
            pTurnString = turnConvert(pTurn);
            bTurnString = turnConvert(bTurn);
          }

          //logic operations
          switch(pTurnString){
            case "Rock":
              
            //compares ai plays to rock
            if(bTurnString.equals("Paper")){
              System.out.println("AI used Paper against Rock! +1 Point to AI");
              botWins++;
            }
            else if(bTurnString.equals("Scissors")){
              System.out.println("AI used Scissors against Rock! +1 Point to Player");
              playerWins++;
            }
            else System.out.println("Error!!");
            break;
              
            //compares ai plays to paper
            case "Paper":
            if(bTurnString.equals("Scissors")){
              System.out.println("AI used Scissors against Paper! +1 Point to AI");
              botWins++;
            }
            else if(bTurnString.equals("Rock")){
              System.out.println("AI used Rock against Paper! +1 Point to Player");
              playerWins++;
            }
            else System.out.println("Error!!");
            break;
              
            //compares ai plays to scissors
            case "Scissors":
            if(bTurnString.equals("Rock")){
              System.out.println("AI used Rock against Scissors! +1 Point to AI");
              botWins++;
            }
            else if(bTurnString.equals("Paper")){
              System.out.println("AI used Paper against Scissors! +1 Point to Player");
              playerWins++;
            }
            else System.out.println("Error!!");
            break;
            default:System.out.println("Error!!");
          }
        }
        if (playerWins > botWins){ //output win game result
          System.out.println("Congratulations! You have Won the Game!");
        }
        else if (botWins > playerWins){ //output loss result
          System.out.println("The AI has won the Game!");
        }
        //ask for player input
        System.out.println("Would You like to Play Again?\n"+
                          "1. Play Again\n"+
                          "2. Exit Program\n");
        playAgain = sc.nextInt();
        if(playAgain==1){ 
          playAgain=0;      //change return value to 0
          return playAgain; //to return to menu
        }
        else if(playAgain==2){
          System.exit(0);
        }
        while((playAgain!=2)&&(playAgain!=1)){
          playAgain = sc.nextInt(); //prvent invalid input
        }
        return playAgain;

      case 2: //change default # of rounds
        System.out.println("How many Rounds do you want"+
                          " to play per Game?");
        playAgain = sc.nextInt();
        
        return playAgain;
      case 3: //exit program
        System.exit(0);
    } 
    return playAgain;
  }
  

  public static void main(String[] args) {
    int returnVal = gameRound(3);
    while(returnVal>0){ //call method again after changing
                        //default # of rounds
      returnVal = gameRound(returnVal);
    }
    while(returnVal==0){ //winning game, play again
      returnVal = gameRound(3);
      while(returnVal>0){  //call method again after 
                           //winning
      returnVal = gameRound(returnVal);
    }
    }
  }
}