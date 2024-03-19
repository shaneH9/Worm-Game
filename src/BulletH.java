import java.util.Scanner;
public class BulletH {
   
    public static void main(String[] args) {
      int diff = 0;
      Scanner s = new Scanner(System.in);
      System.out.println("Welcome to WORM. Would you like to know how to play worm, boss? Type Yes to know, and type No if you're already wormed up");
      String answer = s.nextLine();
        if(answer.equals("No")){
          System.out.println("Choose your difficulty level to start:");
                System.out.println("1: Easy");
                System.out.println("2: Medium");
                System.out.println("3: Hard");
                 diff = Integer.valueOf(s.nextLine()); 

              
        }
        else{
          System.out.println("OKAY!-----------------------------------------------------------------");
          System.out.println("WORM is a game of attrition that forces you to utilize brutal war tactics to punish your enemy into submission."); 
          System.out.println("Worms often break loose from their containment chambers, and when they do, we are tasked with depleting them using mind control chips.");
          System.out.println("These chips allow you to steer the worm away from tNohe scientists that it might be trying to attack. In order to control the situation, you must steer the worm away from these scientists from a remote device, and feed it depletion pellets.");
          System.out.println("These pellets are RED, and they will deplete the worm slightly. There are also PINK growth pellets that show up occassionally. If you feed this to the worm, it makes it a SUPER WORM. And that is never good");
          System.out.println("If you feel WORMED up, type ALL READY BOSS! below");
          String answer2 = s.nextLine();
          if(answer2.equals("ALL READY BOSS!")){
            System.out.println("Choose your difficulty level to start:");
            System.out.println("1: Easy");
            System.out.println("2: Medium");
            System.out.println("3: Hard");
             diff = Integer.valueOf(s.nextLine()); 

          }
        }
      
          
                
                    new GameFrame(diff); 
                }
              }
                
            
        
       
    

