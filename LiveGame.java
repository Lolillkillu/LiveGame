
import java.awt.*;
import java.util.*;
import java.util.HashSet;
import java.util.Random;
import java.lang.Thread;  
import java.io.*;  


public class LiveGame extends Thread  
{


    static HashSet<String> Position = new HashSet<String>();
    static HashSet<String> Position2 = new HashSet<String>();
    static HashSet<String> CheckPosition = new HashSet<String>();

    public static void main(String[] args) 
    {
        String sw;

        Scanner scn = new Scanner(System.in);
        System.out.print("podaj n: ");
        int n = scn.nextInt();
        System.out.print("podaj m: ");
        int m = scn.nextInt();
        double a;
        int b;
        n = n * 2 - 1;
        m = m * 2 - 1;

        String[][] gameBoard = new String[n][m];

        for (int i = 0; i < gameBoard.length; i++) 
        {
            for (int j = 0; j < gameBoard[i].length; j++) 
            {
                if (i % 2 == 0) 
                {
                    if (j % 2 == 0 || j == 0) 
                    {
                        gameBoard[i][j] = "   ";
                    } 
                    else 
                    {
                        gameBoard[i][j] = "|";
                    }
                } 
                else 
                {
                    gameBoard[i][j] = " -";
                }
            }
        }

        a = ((m / 2 + 1) * (n / 2 + 1)) * 0.3;
        b = (int) (Math.round(a));
        System.out.println("30% ~ " + b + "\n");

        // printGameBoard(gameBoard);
        randomGameBoard(gameBoard, b, n, m);
        printGameBoard(gameBoard);
        while(true)
        {
            System.out.println("====================");
            System.out.println("choose enter to contiue");
            System.out.println("choose e to exit");
            System.out.println("*or choose w to contiue");
            System.out.println("====================");


            sw = scn.nextLine();
            switch(sw)
            {
                case "":
                    Rules(gameBoard);
                    printGameBoard(gameBoard);
                    break;
                
                case "w":
                    while(true)
                    {
                        System.out.println("\n");
                        Rules(gameBoard);
                        printGameBoard(gameBoard);
                        try  
                        {  
                            Thread.sleep(150);  
                        }
                        catch(InterruptedException e)
                        {
                            System.out.println(e);
                        }
                        cls();
   
                        
                    }
            
                case "e":
                    System.exit(0);
                    break;

            }
            

        }

        

    }

    public static void printGameBoard(String[][] gameBoard) 
    {
        for (String[] row : gameBoard) 
        {
            for (String c : row) 
            {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("\n\n\n");
    }

    public static String[][] randomGameBoard(String[][] gameBoard, int b, int n, int m) {
        Random random = new Random();

        int x;
        int y;
        int h = 0;
        boolean exist = false;

        while (h < b) 
        {
            y = random.nextInt(n);
            x = random.nextInt(m);

            exist = Position.contains(x + " " + y);
            if (exist) 
            {
                System.out.println("hhh");
                continue;
            } else {
                if (x % 2 == 0) 
                {
                    if (y % 2 == 0 || y == 0) 
                    {
                        gameBoard[x][y] = " X ";
                        // System.out.println(x + "-" + y);
                        Position.add(x + " " + y);
                        h++;
                    }
                }
            }
        }
        return gameBoard;
    }

    public static String[][] Rules(String[][] gameBoard) 
    {
        Position2.clear();
        Position2.addAll(Position);
        Position.addAll(CheckPosition);
        CheckPosition.clear();

        for (int i = 0; i < gameBoard.length; i++) 
        {
            for (int j = 0; j < gameBoard[i].length; j++) 
            {
                if (i % 2 == 0) 
                {
                    if (j % 2 == 0 || j == 0) 
                    {
                                if (Check(i, j) == 3) 
                                {
                                    // System.out.println("Ok");
                                    // System.out.println(i + "-" + j);
                                    CheckPosition.add(i + " " + j);
                                    gameBoard[i][j] = " X ";
                                }
                                else if(Position.contains(i + " " + j) && Check(i, j) == 2)
                                { 
                                    // System.out.println("ok2");
                                    // System.out.println(i + "-" + j);
                                    CheckPosition.add(i + " " + j);
                                    gameBoard[i][j] = " X ";
                                }
                                else
                                { 
                                    // System.out.println("No");
                                    // System.out.println(i + "-" + j);
                                    Position2.remove(i + " " + j);
                                    gameBoard[i][j] = "   ";
                                }
                                               
                    }
                }
            }
        }
        Position.clear();
        Position.addAll(Position2);
        return gameBoard;
    }

    public static int Check(int i, int j) 
    {
        int licz = 0;

        if (Position.contains((i - 2) + " " + (j - 2))) 
        {
            licz++;
        }
        if (Position.contains((i - 2) + " " + (j))) 
        {
            licz++;
        }
        if (Position.contains((i - 2) + " " + (j + 2))) 
        {

            licz++;
        }
        if (Position.contains((i) + " " + (j - 2))) 
        {
            licz++;
        }
        if (Position.contains((i) + " " + (j + 2))) 
        {
            licz++;
        }
        if (Position.contains((i + 2) + " " + (j - 2))) 
        {
            licz++;
        }
        if (Position.contains((i + 2) + " " + (j))) 
        {
            licz++;
        }
        if (Position.contains((i + 2) + " " + (j + 2))) 
        {
            licz++;
        }

        return licz;

    }
    
    public static void cls() 
    {  

        System.out.print("\033[H\033[2J");  
        System.out.flush();  
     
     }


}
