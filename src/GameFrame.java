import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    GameFrame(int x){
        this.add(new SnakeLevel(x));
        this.setTitle("LETS DANCE!");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); 
        this.setVisible(true); 
        this.setLocationRelativeTo(null);
    }



}
