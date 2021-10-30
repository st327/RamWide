import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class test extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    
    //Declare variables
    public static final int WIDTH = 1100; //Dimensions of Jeopardy game board
    public static final int HEIGHT = 1000;
    private JPanel board; //Name of board(panel)
    private JPanel info;
    private JButton [] myJeopardy; //Array for buttons
    private JButton reset;
    private JLabel player1Wins; //Number of wins per player
    private JLabel player2Wins;
    private static String player1; //Variable to store player 1 name
    private static String player2; //Variable to store player 2 name
    private String answer;
    private static int p1Score; //Variable to keep track of player 1 score
    private static int p2Score; //Variable to keep track to player 2 score
    private int score;
    private int turn;
    

    public test(Color theColor){
        super("My First Jeopardy"); //Name that appears at the top
        setSize(WIDTH, HEIGHT); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5,5));
        
        board = new JPanel(new GridLayout(7, 6,15,5)); // Grid layout width = 6, height = 7
        getContentPane().setBackground(Color.BLACK);
        
        myJeopardy = new JButton[42];// Array of 42 buttons
        for (int i = 0; i < myJeopardy.length; i++){ //Loop to construct each button
            myJeopardy[i] = new JButton(""+i);// construct new button
            categoryPics(i); //Call method to display pictures on each button
            
            //Make only question buttons clickable
            if(i >5){
            myJeopardy[i].addActionListener(this); // To make it clickable
            }//End if
            
            myJeopardy[i].setSize(10,10);
            categories(i); //Call method to set button background colors
            myJeopardy[i].setForeground(Color.BLACK);
            board.add(myJeopardy[i]); 
        }// End for
                
        add(board, BorderLayout.CENTER);
        info = new JPanel(new FlowLayout());// layout of another panel at bottom of game
        reset = new JButton("RESET"); //Reset button
        reset.addActionListener(this); //Make reset button clickable
        player1Wins = new JLabel(player1 + "'s Score: ");
        player2Wins = new JLabel(player2 + "'s Score: ");
        info.add(player1Wins); //Add buttons to board
        info.add(reset);
        info.add(player2Wins);
        add(info, BorderLayout.SOUTH);
    }//End Jeopardy
    /*************************************************************************/

    public static void main(String[] args) {
        
        int input = 0; //Get user input
                
        do{ //Menu to display rules, enter player names, play game and exit menu
            input = Integer.parseInt(JOptionPane.showInputDialog("1. View the rules of the game \n"
                    + "2. Enter Player One and Player Two names \n"
                    + "3. Play the game! \n"
                    + "4. Exit Menu"));
            
            if (input == 1){ //Rules of the game
                JOptionPane.showMessageDialog(null, "1. This is a two player game. \n"
                        + "2. Must pick a category and a point value to play \n"
                        + "3. Click on the chosen box for the question. \n"
                        + "4. Correct responses must satisfy the demands of both the clue and the category. \n"
                        + "5. Responses must be spelled correctly \n"
                        + "6. Click the “Reset” button to start all over.\n"
                        + "7. Player with highest dollar amount at end of game wins", "Rules of Jeopardy",
                        JOptionPane.INFORMATION_MESSAGE);
                
            }//End if
                else if (input == 2){ //Enter player 1 and player 2 names
                    player1 = JOptionPane.showInputDialog("Enter Player One's name: ");
                    player2 = JOptionPane.showInputDialog("Enter Player Two's name: ");
                }//End else
                    else if (input == 3){
                        while (player1 == null || player2 == null){ //While loop validation if no entry for names
                            JOptionPane.showMessageDialog(null, "You didn't enter a name for Player One or Player Two. \n"
                                    + "Please enter names for Player One or Player Two: ");
                            player1 = JOptionPane.showInputDialog("Enter Player One's name: ");
                            player2 = JOptionPane.showInputDialog("Enter Player Two's name: ");
                        }//End while
                        
                        //Start Game
                        test w = new test (Color.RED);
                        w.setVisible(true);
                    }//End else if
        }
        while (input > 0 && input < 4);
        
    }//End main
    
    public void actionPerformed(ActionEvent e) {
        System.out.println(e); //What prints out in console
        String actionCommand = e.getActionCommand();
        System.out.println("this is the item : " + actionCommand); //Prints out index location of button in array
        
        if ( actionCommand.equals("RESET")){
            resetBoard(); //Call to reset game
        }//End if
        else{
            int pos = Integer.parseInt(actionCommand);// convert string into int
            System.out.println(pos);
            if (turn % 2 == 0){
                questions(pos, score); //Call method to display questions for each button
                p1Score += score; //Running total score
                player1Wins.setText(player1 + "'s Score: $" + p1Score); //Display scores and player names
                turn++;
            }//if
            else if (turn % 2 == 1){
                questions(pos, score);
                p2Score += score;
                player2Wins.setText(player2 + "'s Score: $" + p2Score); //Display scores and player names
                turn++;
            }//End else if
        }//End else
        winner(); //Call to display winner
    }//End actionPerformed
    
    public void winner(){
        //If every button is disabled, the game ends and displays the winner's name
        if (myJeopardy[6].isEnabled() == false && myJeopardy[7].isEnabled() == false && myJeopardy[8].isEnabled() == false &&
            myJeopardy[9].isEnabled() == false && myJeopardy[10].isEnabled() == false && myJeopardy[11].isEnabled() == false &&
            myJeopardy[12].isEnabled() == false && myJeopardy[13].isEnabled() == false && myJeopardy[14].isEnabled() == false &&
            myJeopardy[15].isEnabled() == false && myJeopardy[16].isEnabled() == false && myJeopardy[17].isEnabled() == false &&
            myJeopardy[18].isEnabled() == false && myJeopardy[19].isEnabled() == false && myJeopardy[20].isEnabled() == false &&
            myJeopardy[21].isEnabled() == false && myJeopardy[22].isEnabled() == false && myJeopardy[23].isEnabled() == false &&
            myJeopardy[24].isEnabled() == false && myJeopardy[30].isEnabled() == false && myJeopardy[36].isEnabled() == false &&
            myJeopardy[25].isEnabled() == false && myJeopardy[31].isEnabled() == false && myJeopardy[37].isEnabled() == false &&
            myJeopardy[26].isEnabled() == false && myJeopardy[32].isEnabled() == false && myJeopardy[38].isEnabled() == false &&
            myJeopardy[27].isEnabled() == false && myJeopardy[33].isEnabled() == false && myJeopardy[39].isEnabled() == false &&
            myJeopardy[28].isEnabled() == false && myJeopardy[34].isEnabled() == false && myJeopardy[40].isEnabled() == false &&
            myJeopardy[29].isEnabled() == false && myJeopardy[35].isEnabled() == false && myJeopardy[41].isEnabled() == false ){
                if (p1Score > p2Score || p1Score >= 6300){
                    JOptionPane.showMessageDialog(null, player1 + " is the WINNER!!!","Winner",JOptionPane.INFORMATION_MESSAGE);
                    disableButtons(); //Call to disable buttons
                }
                else if (p1Score < p2Score || p2Score >= 6300){
                    JOptionPane.showMessageDialog(null, player2 + " is the WINNER!!!","Winner",JOptionPane.INFORMATION_MESSAGE);
                    disableButtons(); //Call to disable buttons
                }
        }
    }//End winner
    
    /************************************************************************/
    public int questions(int pos, int score){
    
        switch (pos){
        //Nationwide trivia
        case 6: myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What city and state are Nationwide headquarters located? </html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Nationwide for $100 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("Columbus, OH") || answer.equals("columbus, oh")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Columbus, OH");
                    score = moneyWrong(pos);
                }
            break;
            /************/
        case 12:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What was Nationwide first known as?<br> "
                        + "(Full name please)");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Nationwide for $200 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("Farm Bureau Mutual Automobile Insurance Company")|| answer.equals("farm bureau mutual automobile")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Farm Bureau Mutual Automobile Insurance Company");
                    score = moneyWrong(pos);
                }
                break;
            /****************/
        case 18: myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What is the Nationwide jingle?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Nationwide for $300 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("nationwide is on your side")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Nationwide is on your side");
                    score = moneyWrong(pos);
                }
            break;
        case 24:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What are ARGs? </html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Nationwide for $400 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("associate resource groups")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Associate Resource Groups");
                    score = moneyWrong(pos);
                }
            break;
            /************/
        case 30:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>Who is the current CEO?");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Nationwide for $500 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("kirt walker")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Kirt Walker");
                    score = moneyWrong(pos);
                }
            break;
            /***************/
        case 36:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>How many associates does Nationwide employ across the US?");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Nationwide for $600 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("26,000")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 26,000");
                    score = moneyWrong(pos);
                }
            break;
            /******************************************************************************************************/
        // GUESS IT 
        case 7:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>The famous app that has the blue bird logo? </html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("GUESS IT $100 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("twitter")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: twitter");
                    score = moneyWrong(pos);
                }
            break;
            /*************/
        case 13:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>WWW stands for? </html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("GUESS IT $200 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("World Wide Web")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: World Wide Web");
                    score = moneyWrong(pos);
                }
            break;
            /************/
        case 19:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>The sweetest and scariest day of the year?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("GUESS IT $300 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("Halloween") || answer.equals("halloween") || answer.equals("HALLOWEEN")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Halloween");
                    score = moneyWrong(pos);
                }
            break;
            /************/
        case 25:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What has many keys but can't open a single lock?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("GUESS IT for $400 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("Piano") || answer.equals("piano")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: piano");
                    score = moneyWrong(pos);
                }
            break;
            /************/
        case 31:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What has to be broke before you use it?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("GUESS IT for $500 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("an egg")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: an egg");
                    score = moneyWrong(pos);
                }
            break;
            /************/
        case 37:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>Wears red and white and rides a slay</html>");
                        
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("GUESS IT for $600 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("Santa") || answer.equals("SANTA")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Santa");
                    score = moneyWrong(pos);
                }
            break;
            /******************************************************************************************************/
            //Marvel: Category 1
            case 8:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>In \"Avengers: End Game,\" how many infinity stones are there?</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Marvel for $100 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("six")|| answer.equals("6")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: six");
                        score = moneyWrong(pos);
                    }
                break;
                /*************/
            case 14:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>In \"The Avengers,\" Which movie kicked off the Marvel Universe?</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Avengers for $200 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("iron man")|| answer.equals("Iron Man")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: iron man");
                        score = moneyWrong(pos);
                    }
                break;
                /************/
            case 20:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>In \"Avengers: Age of Ultron,\" Wanda and her brother Pietro are from where?</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Avengers for $300 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("sokovia") || answer.equals("Sokovia")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Sokovia");
                        score = moneyWrong(pos);
                    }
                break;
                /************/
            case 26:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html> In \"Wanda Vison,\" what are the names of Wanda's Twins?</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Marvel for $400 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("Billy and Tommy") || answer.equals("billy and tommy")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Billy and Tommy");
                        score = moneyWrong(pos);
                    }
                break;
                /************/
            case 32:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>Who does Thanos sacrifice for the Soul Stone?</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Marvel for $500 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("gamora")|| answer.equals("Gamora")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Gamora");
                        score = moneyWrong(pos);
                    }
                break;
                /************/
            case 38:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>What type of doctor is Doctor Strange?<br>"
                            + "a. Neurosugeon<br>"
                            + "b. Orthopedic<br>"
                            + "c. Psychologist</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Di for $600\n What is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("a")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: A");
                        score = moneyWrong(pos);
                    }
                break;
        
        /******************************************************************************************************/
        //Companies
        case 9:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>Finger lickin good.</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Companies for $100 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("kfc")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: kfc");
                    score = moneyWrong(pos);
                }
            break;
            /*************/
        case 15:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>America runs on ___</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Companies for $200 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("donuts")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: donuts");
                    score = moneyWrong(pos);
                }
            break;
            /************/
        case 21:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>Save Money. Live Better.</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Companies for $300 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("walmart")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: walmart");
                    score = moneyWrong(pos);
                }
            break;
            /************/
        case 27:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,10));
                myJeopardy[pos].setText("<html>What brand is known for its famous three stripes?<br>"
                        + "a. Adidas<br>"
                        + "b. Nike</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Companies for $400 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("a")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: A");
                    score = moneyWrong(pos);
                }
            break;
            /************/
        case 33:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What is the logo called known as the Jumpman Logo?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Companies $500 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("jordans")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Jordans");
                    score = moneyWrong(pos);
                }
            break;
            /************/
        case 39:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What logo has two dots on one half and one dot on the other half?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);
                
                answer = JOptionPane.showInputDialog("Companies for $600 \nWhat is: ");
                answer = answer.toLowerCase();
                
                if (answer.equals("dominos")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Dominos");
                    score = moneyWrong(pos);
                }
            break;
            

            /******************************************************************************************************/
            //Covid-19
            case 10:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>What percentage of U.S Citzens are vaccinated?(Numbers Only)</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Covid-19 $100 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("57")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: 57");
                        score = moneyWrong(pos);
                    }
                break;
                /*************/
            case 16:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>How many people died from covid in the united States?</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Covid-19 for $200 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("754,000")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 754,000");
                        score = moneyWrong(pos);
                    }
                break;
                /************/
            case 22:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>During this pandemic, "
                            + "what is the ideal range of distance you should keep from one another?(number in feet)</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Covid-19 for $300 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("6")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: 6");
                        score = moneyWrong(pos);
                    }
                break;
                /************/
            case 28:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>What age group contracted the virus the most? A.60-79 B. 18-24</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Covid-19 $400 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("b")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: B");
                        score = moneyWrong(pos);
                    }
                break;
                /************/
            case 34:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>What is the main symptom of Covid-19?</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Covid-19 $500 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("fever")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: fever");
                        score = moneyWrong(pos);
                    }
                break;
                /************/
            case 40:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                    myJeopardy[pos].setText("<html>Did 1/3 of all small businesses close due to the pandemic? a. True b. False</html>");
                    myJeopardy[pos].setEnabled(false);
                    myJeopardy[pos].setBackground(Color.BLACK);
                    myJeopardy[pos].setIcon(null);
                    
                    answer = JOptionPane.showInputDialog("Covid-19 $600 \nWhat is: ");
                    answer = answer.toLowerCase();
                    
                    if (answer.equals("a")){
                        JOptionPane.showMessageDialog(null, "CORRECT!");
                        score = money(pos);
                        System.out.println(score);
                    }else{
                        JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: a");
                        score = moneyWrong(pos);
                    }
                break;
                
                /******************************************************************************************************/
                //Major cities & state capital
                case 11:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                        myJeopardy[pos].setText("<html>What city is known for its casinos and world-class entertainment?</html>");
                        myJeopardy[pos].setEnabled(false);
                        myJeopardy[pos].setBackground(Color.BLACK);
                        myJeopardy[pos].setIcon(null);
                        
                        answer = JOptionPane.showInputDialog("Cities for $100 \nWhat is: ");
                        answer = answer.toLowerCase();
                        
                        if (answer.equals("Las Vegas") || answer.equals("las vegas")){
                            JOptionPane.showMessageDialog(null, "CORRECT!");
                            score = money(pos);
                            System.out.println(score);
                        }else{
                            JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Las Vegas");
                            score = moneyWrong(pos);
                        }
                    break;
                    /*************/
                case 17:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                        myJeopardy[pos].setText("<html>What US city is known as “the city that never sleeps”?</html>");
                        myJeopardy[pos].setEnabled(false);
                        myJeopardy[pos].setBackground(Color.BLACK);
                        myJeopardy[pos].setIcon(null);
                        
                        answer = JOptionPane.showInputDialog("Cities for $200 \nWhat is: ");
                        answer = answer.toLowerCase();
                        
                        if (answer.equals("nyc") || answer.equals("New York City")){
                            JOptionPane.showMessageDialog(null, "CORRECT!");
                            score = money(pos);
                            System.out.println(score);
                        }else{
                            JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: New York City");
                            score = moneyWrong(pos);
                        }
                    break;
                    /************/
                case 23:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                        myJeopardy[pos].setText("<html>What city is known for its creole cuisine, Mardi Gras, and the French Quarter?</html>");
                        myJeopardy[pos].setEnabled(false);
                        myJeopardy[pos].setBackground(Color.BLACK);
                        myJeopardy[pos].setIcon(null);
                        
                        answer = JOptionPane.showInputDialog("Cities for $300 \nWhat is: ");
                        answer = answer.toLowerCase();
                        
                        if (answer.equals("new orleans")){
                            JOptionPane.showMessageDialog(null, "CORRECT!");
                            score = money(pos);
                            System.out.println(score);
                        }else{
                            JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: New Orleans");
                            score = moneyWrong(pos);
                        }
                    break;
                    /************/
                case 29:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                        myJeopardy[pos].setText("<html>What city is know for its coffee culture, the Seahawks, and the Space Needle?</html>");
                        myJeopardy[pos].setEnabled(false);
                        myJeopardy[pos].setBackground(Color.BLACK);
                        myJeopardy[pos].setIcon(null);
                        
                        answer = JOptionPane.showInputDialog("Cities for $400 \nWhat is: ");
                        answer = answer.toLowerCase();
                        
                        if (answer.equals("seattle")){
                            JOptionPane.showMessageDialog(null, "CORRECT!");
                            score = money(pos);
                            System.out.println(score);
                        }else{
                            JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Seattle");
                            score = moneyWrong(pos);
                        }
                    break;
                    /************/
                case 35:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                        myJeopardy[pos].setText("<html>What city is part of “The Triangle” and also the capital of North Carolina?</html>");
                        myJeopardy[pos].setEnabled(false);
                        myJeopardy[pos].setBackground(Color.BLACK);
                        myJeopardy[pos].setIcon(null);
                        
                        answer = JOptionPane.showInputDialog("Cities for $500 \nWhat is: ");
                        answer = answer.toLowerCase();
                        
                        if (answer.equals("raleigh") || answer.equals("Raleigh")){
                            JOptionPane.showMessageDialog(null, "CORRECT!");
                            score = money(pos);
                            System.out.println(score);
                        }else{
                            JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Raleigh");
                            score = moneyWrong(pos);
                        }
                    break;
                    /************/
                case 41:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                        myJeopardy[pos].setText("<html>What city is known for a UFO conspiracy?</html>");
                        myJeopardy[pos].setEnabled(false);
                        myJeopardy[pos].setBackground(Color.BLACK);
                        myJeopardy[pos].setIcon(null);
                        
                        answer = JOptionPane.showInputDialog("Cities for $600 \nWhat is: ");
                        answer = answer.toLowerCase();
                        
                        if (answer.equals("roswell")){
                            JOptionPane.showMessageDialog(null, "CORRECT!");
                            score = money(pos);
                            System.out.println(score);
                        }else{
                            JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Roswell");
                            score = moneyWrong(pos);
                        }
                    break;
        }//End switch
        return score;
    }
    /************************************************************************/
    public int money(int pos){
        //Method to set what each button is worth score wise
        if (pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 10 || pos == 11){
            score = 100;
        }
        else if (pos == 12 || pos == 13 || pos == 14 || pos == 15 || pos == 16 || pos == 17){
            score = 200;
        }
        else if (pos == 18 || pos == 19 || pos == 20 || pos == 21 || pos == 22 || pos == 23){
            score = 300;
        }
        else if (pos == 24 || pos == 25 || pos == 26 || pos == 27 || pos == 28 || pos == 29){
            score = 400;
        }
        else if (pos == 30 || pos == 31 || pos == 32 || pos == 33 || pos == 34 || pos == 35){
            score = 500;
        }
        else if (pos == 36 || pos == 37 || pos == 38 || pos == 39 || pos == 40 || pos == 41){
            score = 600;
        }
        return score;
    }//End money
    
    /************************************************************************/
    public int moneyWrong(int pos){
        //Method to display a score of ) if answer is incorrect
        if (pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 10 || pos == 11 || 
            pos == 12 || pos == 13 || pos == 14 || pos == 15 || pos == 16 || pos == 17 ||
            pos == 18 || pos == 19 || pos == 20 || pos == 21 || pos == 22 || pos == 23 ||
            pos == 24 || pos == 25 || pos == 26 || pos == 27 || pos == 28 || pos == 29 ||
            pos == 30 || pos == 31 || pos == 32 || pos == 33 || pos == 34 || pos == 35 ||
            pos == 36 || pos == 37 || pos == 38 || pos == 39 || pos == 40 || pos == 41){
            score = 0;
        }//End if
        
        return score;
    }//End money
    
    /************************************************************************/
    public void categories(int i){
        if(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5){
            myJeopardy[i].setBackground(Color.WHITE);
        }else
            if(i == 8 || i == 14 || i == 20 || i == 26 || i == 32 || i == 38){
                myJeopardy[i].setBackground(Color.WHITE);
            }//End if
    
    }//End categories
    /************************************************************************/
    public void categoryPics(int i){
        switch (i){
        case 0: 
            ImageIcon im = new ImageIcon(new ImageIcon("nationwide.png").getImage().getScaledInstance(
                    180,140, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(im);
        break;
        case 1: 
            ImageIcon ima = new ImageIcon(new ImageIcon("guessIt.jpg").getImage().getScaledInstance(
                    180,100, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(ima);
        break;
        case 2: 
            ImageIcon imag = new ImageIcon(new ImageIcon("marvel.png").getImage().getScaledInstance(
                    180,100, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(imag);
        break;
        case 3: 
            ImageIcon image = new ImageIcon(new ImageIcon("companies.jpeg").getImage().getScaledInstance(
                    160,90, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image);
        break;
        case 4: 
            ImageIcon image1 = new ImageIcon(new ImageIcon("covid.jpg").getImage().getScaledInstance(
                    170,100, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image1);
        break;
        case 5: 
            ImageIcon image2 = new ImageIcon(new ImageIcon("cities.jpeg").getImage().getScaledInstance(
                    160,90, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image2);
        break;
        /**********************/
        
        //Nationwide category
        case 6: 
            ImageIcon image3 = new ImageIcon(new ImageIcon("nationwide1.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image3);
        break;
        case 12: 
            ImageIcon image4 = new ImageIcon(new ImageIcon("nationwide2.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image4);
        break;
        case 18: 
            ImageIcon image5 = new ImageIcon(new ImageIcon("nationwide3.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image5);
        break;
        case 24: 
            ImageIcon image6 = new ImageIcon(new ImageIcon("nationwide4.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image6);
        break;
        case 30: 
            ImageIcon image7 = new ImageIcon(new ImageIcon("nationwide5.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image7);
        break;
        case 36: 
            ImageIcon image8 = new ImageIcon(new ImageIcon("nationwide6.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image8);
        break;
        
/************************/
        
        //Guess It category
        case 7: 
            ImageIcon image9 = new ImageIcon(new ImageIcon("thumbnail_Image.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image9);
        break;
        case 13: 
            ImageIcon image10 = new ImageIcon(new ImageIcon("thumbnail_Image-2.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image10);
        break;
        case 19: 
            ImageIcon image11 = new ImageIcon(new ImageIcon("thumbnail_Image-3.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image11);
        break;
        case 25: 
            ImageIcon image12 = new ImageIcon(new ImageIcon("thumbnail_Image-4.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image12);
        break;
        case 31: 
            ImageIcon image13 = new ImageIcon(new ImageIcon("thumbnail_Image-5.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image13);
        break;
        case 37: 
            ImageIcon image14 = new ImageIcon(new ImageIcon("thumbnail_Image-6.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image14);
        break;
        
/**********************/
        
        //Marvel category
        case 8: 
            ImageIcon image15 = new ImageIcon(new ImageIcon("marvel100.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image15);
        break;
        case 14: 
            ImageIcon image16 = new ImageIcon(new ImageIcon("marvel200.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image16);
        break;
        case 20: 
            ImageIcon image17 = new ImageIcon(new ImageIcon("marvel300.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image17);
        break;
        case 26: 
            ImageIcon image18 = new ImageIcon(new ImageIcon("marvel400.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image18);
        break;
        case 32: 
            ImageIcon image19 = new ImageIcon(new ImageIcon("marvel500.png").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image19);
        break;
        case 38: 
            ImageIcon image20 = new ImageIcon(new ImageIcon("marvel600.png").getImage().getScaledInstance(
                    180,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image20);
        break;
        
/************************/
        
        //Companies
        case 9: 
            ImageIcon image21 = new ImageIcon(new ImageIcon("companies1.jpeg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image21);
        break;
        case 15: 
            ImageIcon image22 = new ImageIcon(new ImageIcon("companies2.jpeg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image22);
        break;
        case 21: 
            ImageIcon image23 = new ImageIcon(new ImageIcon("companies3.jpeg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image23);
        break;
        case 27: 
            ImageIcon image24 = new ImageIcon(new ImageIcon("companies4.jpeg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image24);
        break;
        case 33: 
            ImageIcon image25 = new ImageIcon(new ImageIcon("companies5.jpeg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image25);
        break;
        case 39: 
            ImageIcon image26 = new ImageIcon(new ImageIcon("companies6.jpeg").getImage().getScaledInstance(
                    180,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image26);
        break;
        
/***************************/
        
        //covid-19 category
        case 10: 
            ImageIcon image27 = new ImageIcon(new ImageIcon("covid1.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image27);
        break;
        case 16: 
            ImageIcon image28 = new ImageIcon(new ImageIcon("covid2.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image28);
        break;
        case 22: 
            ImageIcon image29 = new ImageIcon(new ImageIcon("covid3.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image29);
        break;
        case 28: 
            ImageIcon image30 = new ImageIcon(new ImageIcon("covid4.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image30);
        break;
        case 34: 
            ImageIcon image31 = new ImageIcon(new ImageIcon("covid5.jpg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image31);
        break;
        case 40: 
            ImageIcon image32 = new ImageIcon(new ImageIcon("covid6.jpg").getImage().getScaledInstance(
                    180,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image32);
        break;
        
/***************************/
        
        //Cities category
        case 11: 
            ImageIcon image33 = new ImageIcon(new ImageIcon("cities1.jpeg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image33);
        break;
        case 17: 
            ImageIcon image34 = new ImageIcon(new ImageIcon("cities2.jpeg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image34);
        break;
        case 23: 
            ImageIcon image35 = new ImageIcon(new ImageIcon("cities3.jpeg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image35);
        break;
        case 29: 
            ImageIcon image36 = new ImageIcon(new ImageIcon("cities4.jpeg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image36);
        break;
        case 35: 
            ImageIcon image37 = new ImageIcon(new ImageIcon("cities5.jpeg").getImage().getScaledInstance(
                    190,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image37);
        break;
        case 41: 
            ImageIcon image38 = new ImageIcon(new ImageIcon("cities6.jpeg").getImage().getScaledInstance(
                    180,130, Image.SCALE_DEFAULT));
            myJeopardy[i].setIcon(image38);
        break;
        }//End switch
    }//End categoryPics
    
/*******************************************************************/
    public void resetBoard(){
        for (int i = 0; i < myJeopardy.length; i++){
            myJeopardy[i].setEnabled(true);
            myJeopardy[i].setText("" + i);
            categories(i);
            categoryPics(i);
            myJeopardy[i].setForeground(Color.BLACK);
            p1Score = 0;
            p2Score = 0;
            player1Wins.setText(player1 + "'s Score: " + p1Score);
            player2Wins.setText(player2 + "'s Score: " + p2Score);
        }
    }//End resetBoard
/*******************************************************************/
    public void disableButtons(){
        for (int i = 0; i < myJeopardy.length; i++){
            myJeopardy[i].setEnabled(false);
            myJeopardy[i].setText("" + i);
            categories(i);
            categoryPics(i);
            myJeopardy[i].setForeground(Color.BLACK);
        }//End for
    }//End disableBoard    
}//End Jeopardy
