import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

//create a game panel for every character 
//call different panels for different levels 
public class SnakeLevel extends JPanel implements ActionListener{
    //the locations of people are stored in arrays (x val indicie 0, y val indicie 1) in the arraylist people. 
    //we need to make it so apples dont spawn on people 
    //we need to make it so fake apples dissapear and are replaced after 10 seconds (maybe search the grid, check if any apples are pink, if so delete then new apple)
    //we need to make it so the game ends if you hit people
    static final int SCREEN_WIDTH = 600; 
    static final int SCREEN_HEIGHT = 600; 
    static final int UNIT_SIZE = 15;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static int DELAY = 50;   
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    final int yguyLocation = 0; 
    int enemyXlocation;
    int enemyYlocation; 
    char direction = 'R';
    boolean running = false; 
    Timer timer; 
    Random random; 
    int applesEaten; 
    int countdown = 10;
    int bodyParts = 30; 
    int appleX; 
    int appleY;
    int extrAppleX;
    int extrAppleY;
    ArrayList<int[]> people = new ArrayList<int[]>(); 
    int personX;
    int personY; 
    int difficultyLevel; 
    boolean isFake = false; 
    boolean sick = false; 
    

    SnakeLevel(int diff){
        random = new Random(); 
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter()); 
        this.difficultyLevel = diff;
        openBattle(); 
        



    }

    public void openBattle(){
        System.out.print("LETS DANCE!");
        getPeopleLocations(difficultyLevel); // delete if program not working
        newApple(); 
        running = true; 
        timer = new Timer(DELAY, this);
        timer.start(); 

    }

    public void getPeopleLocations(int difficultyLevel){
        int count = 0; 
        int pToAdd = 0;
        if(difficultyLevel == 1){
            pToAdd = 10;
        }
        else if(difficultyLevel == 2){
            pToAdd = 20;
        }
        else{
            pToAdd = 35; 
        }
        while(count<pToAdd){

            personX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
            personY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
            while(personY < 2 || personX <10 ){
                personX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
            personY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
            }
            int[] keeper = new int[2]; 
            keeper[0] = personX; 
            keeper[1] = personY; 
            people.add(keeper);
            count++; 
        }

        ArrayList<int[]> icelandicComparer = new ArrayList<int[]>(); 

        for(int x = people.size()-1; x>=0; x--){
            if(!icelandicComparer.contains(people.get(x))){
                icelandicComparer.add(people.get(x));
            }
        }
        people = icelandicComparer;
                
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g); 
        draw(g); 
    }

    public void newApple(){
      
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        int randomNum = random.nextInt(10);
        for(int x=0; x<people.size(); x++){
            if(appleX == people.get(x)[0] && appleY == people.get(x)[1]){
                appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
                appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
            }
        }
        if((randomNum == 2 || randomNum == 4)){
            isFake = true; 
            extraApple();
            
        }
        else{
            isFake = false; 
           

        }
    
    }

    public void extraApple(){
        if(isFake){
            extrAppleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
            extrAppleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
            for(int x=0; x<people.size(); x++){
                if(extrAppleX == people.get(x)[0] && extrAppleY == people.get(x)[1]){
                    extrAppleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
                    extrAppleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
                }
            }
            
        }
    }

   
    


    public void checkGoodApple(){
        if(x[0] == appleX && y[0] == appleY){
            if(!isFake){
                bodyParts--; 
                applesEaten++;
                newApple(); 
            }
            else{
                bodyParts = bodyParts+10; 
                applesEaten++; 
                newApple(); 
                sick = true; 
            }
           
        }
        else if(x[0] == extrAppleX && y[0] == extrAppleY){
                bodyParts--; 
                applesEaten++;
                newApple(); 
        }
    }

 


    public void move(){
        for(int b=bodyParts; b>0; b--){
            x[b] = x[b-1];
            y[b] = y[b-1];
        }

            switch(direction){
                case 'U':
                y[0] = y[0] - UNIT_SIZE; 
                break; 
                case 'D':
                y[0] = y[0] + UNIT_SIZE; 
                break; 
                case 'L':
                x[0] = x[0] - UNIT_SIZE; 
                break; 
                case 'R':
                x[0] = x[0] + UNIT_SIZE; 
                break;
            }

        
    }

    public void checkCollision(){
        for(int t = bodyParts; t>0; t--){
            if(x[0] == x[t] && y[0] == y[t]){
                running = false;
            }
            if(x[0] < 0){
                running = false; 
            }
            if(x[0] > SCREEN_WIDTH){
                running = false; 
            }
            if(y[0] < 0){
                running = false; 
            }
            if(y[0] > SCREEN_HEIGHT){
                running = false; 
            }
            if(!running){
                timer.stop(); 
            }
            for(int b=0; b<people.size(); b++){
                if(y[0] == people.get(b)[1] && x[0] == people.get(b)[0]){
                   running = false;
                }
            }
            
        }
    }

   public boolean checkWin(){
    if(bodyParts == 1){
        running = false;
        return true;
    }
    return false;
   }

    public void lose(Graphics g){
        g.setColor(Color.pink);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("You Lost Worm!", (SCREEN_WIDTH - metrics.stringWidth("You Lost Worm!"))/2, SCREEN_HEIGHT/2);
    }

    public void win(Graphics g){
        g.setColor(Color.pink);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Victory Royale!", (SCREEN_WIDTH - metrics.stringWidth("Victory Royale!"))/2, SCREEN_HEIGHT/2);
    }

    public void draw(Graphics g){
        if(running){
        if(isFake){
            
            g.setColor(new Color(245,27,85));
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); 
            g.setColor(Color.red);
            g.fillOval(extrAppleX,extrAppleY,UNIT_SIZE,UNIT_SIZE);
                
        }
        else{
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); 
        }

        
        

        for(int v=0; v<people.size(); v++){
            int xLoc = people.get(v)[0];
            int yLoc = people.get(v)[1];
            g.setColor(Color.white);
            g.fillRect(xLoc,yLoc, UNIT_SIZE,UNIT_SIZE);
        }

        

        
    if(!sick){

    
        for(int z=0; z<bodyParts; z++){
            if(z%2 == 0){
                g.setColor(Color.pink);
                g.fillRect(x[z], y[z], UNIT_SIZE, UNIT_SIZE);
            }
            else{
                g.setColor(Color.red);
                g.fillRect(x[z],y[z], UNIT_SIZE, UNIT_SIZE);

            }
        }
    }
    else{

    for(int z=0; z<bodyParts; z++){
        if(z%2 == 0){
            g.setColor(new Color(0,51,0));
            g.fillRect(x[z], y[z], UNIT_SIZE, UNIT_SIZE);
        }

    }

    
    
}

        }
        else if(checkWin()){
            win(g);
        }
        else{
            lose(g); 
        }

        
    }

    public void actionPerformed(ActionEvent e){
        if(running){
            move();
            checkGoodApple(); 
            checkCollision(); 
            checkWin();
            
        }
        repaint();
    }

    public class myKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                if(direction != 'R'){
                    direction = 'L';

                }
                break; 
                case KeyEvent.VK_RIGHT:
                if(direction != 'L'){
                    direction = 'R';
                }
                break; 
                case KeyEvent.VK_UP:
                if(direction != 'D'){
                    direction = 'U';
                }
                break;
                case KeyEvent.VK_DOWN:
                if(direction != 'U'){
                    direction = 'D';
                } 
            }
    
            }
        }
    }




