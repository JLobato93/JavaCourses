import java.util.Scanner;

public class MazeRunner {
    public static void main(String[] args) {
        Maze myMap = new Maze();
        int movesMade = 0;
        intro(myMap);
        while (!myMap.didIWin()) {
            userMove(myMap);
            movesMessages(++movesMade);
            if (movesMade == 100) {
                System.out.println("Sorry, but you didn't escape in time- you lose!");
                System.exit(0);
            }
        }
        System.out.println("You found the exit, congrats! You did it in " + movesMade + " moves.");
    }


    public static void intro(Maze myMap) {

        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position : ");
        myMap.printMap();
    }

    public static String userMove(Maze myMap) {
        Scanner input = new Scanner(System.in);
        System.out.print("Where would you like to move?  (R, L, U, D)");
        String move = input.next();
        while (!move.equalsIgnoreCase("R") && !move.equalsIgnoreCase("L") && !move.equalsIgnoreCase("U") && !move.equalsIgnoreCase("D")) {
            System.out.println("Please only enter (\"R\", \"U\", \"L\", \"D\")");
            move = input.next();
        }

        if (move.equalsIgnoreCase("R")) {
            System.out.println("You chose to move to the right.");
            if (myMap.canIMoveRight()) {
                myMap.moveRight();
                myMap.printMap();
            } else {
                cantMove(myMap, input, move);
            }

        } else if (move.equalsIgnoreCase("L")) {
            System.out.println("You chose to move to the left.");
            if (myMap.canIMoveLeft()) {
                myMap.moveLeft();
                myMap.printMap();
            } else {
                cantMove(myMap, input, move);
            }
        } else if (move.equalsIgnoreCase("U")) {
            System.out.println("You chose to move up.");
            if (myMap.canIMoveUp()) {
                myMap.moveUp();
                myMap.printMap();
            } else {
                cantMove(myMap, input, move);
            }

        } else {
            System.out.println("You chose to move down.");
            if (myMap.canIMoveDown()) {
                myMap.moveDown();
                myMap.printMap();
            } else {
                cantMove(myMap, input, move);
            }
        }
        return move;
    }

    public static void cantMove(Maze myMap, Scanner input, String move) {
        if (myMap.isThereAPit(move.toUpperCase())) {
            System.out.println("Watch out there is a pit ahead, jump over it?");
            String answer = input.next();
            String firstLetterOfAnswer = String.valueOf(answer.charAt(0));
            if (firstLetterOfAnswer.equalsIgnoreCase("y")) {
                navigatePit(myMap, move);
                myMap.printMap();
            } else {
                userMove(myMap);
            }
        } else {
            System.out.println("Sorry, you've hit a wall.");
        }
    }

    public static void movesMessages(int movesMade) {
        if (movesMade == 50) {
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        } else if (movesMade == 75) {
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        } else if (movesMade == 90) {
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        } else if (movesMade == 100) {
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
        }
    }

    public static void navigatePit(Maze myMap, String move) {
        myMap.jumpOverPit(move.toUpperCase());
    }

}

