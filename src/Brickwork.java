import java.util.ArrayDeque;
import java.util.Scanner;

public class Brickwork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //insert input size of bricks and add them to array
        String[] areaOfSize = scanner.nextLine().split("\\s+");

        //check if input is valid
        if(!isWidthOrLengthValid(areaOfSize)){
            return;
        };

        //take width and length ot all bricks
        int width = Integer.parseInt(areaOfSize[0]);
        int length = Integer.parseInt(areaOfSize[1]);

        //create stack with brick numbers and fill the stack
        ArrayDeque<Integer> brickNumbers = new ArrayDeque<>();
        fillListOfBrickNumbers(brickNumbers, width, length);

        //initialise two matrix, first is the first layer and second is second layer
        int[][] firstLayer = new int[width][length];
        int[][] secondLayer = new int[width][length];

        //fill first layer
        if(addToFirstLayer(scanner, firstLayer, length)){
            return;
        }

        //fill second layer
        fillSecondLayer(brickNumbers, firstLayer, secondLayer);

        //print second layer with asterisk and dash
        printSecondLayer(secondLayer);
    }

    private static boolean isWidthOrLengthValid(String[] areaOfSize) {
        if(areaOfSize.length == 2) {
            try {
                int width = Integer.parseInt(areaOfSize[0]);
                int length = Integer.parseInt(areaOfSize[1]);
                if (width % 2 == 0 && length % 2 == 0 && width < 100 && length < 100 && width > 1 && length > 1) {
                    return true;
                }
            }catch (NumberFormatException e){
                System.out.println("-1");
                System.out.println("No solution exists.");
                return false;
            }
        }
        System.out.println("-1");
        System.out.println("No solution exists.");
        return false;
    }

    private static void fillSecondLayer(ArrayDeque<Integer> brickNumbers, int[][] firstLayer, int[][] secondLayer) {
        for (int i = 0; i < firstLayer.length - 1; i = i + 2) {
            for (int j = 0; j < firstLayer[i].length - 1; j++) {
                if (firstLayer[i][j] == firstLayer[i][j + 1]) {
                    secondLayer[i][j] = brickNumbers.peek();
                    secondLayer[i + 1][j] = brickNumbers.pop();
                } else {
                    secondLayer[i][j] = brickNumbers.peek();
                    secondLayer[i][j + 1] = brickNumbers.pop();
                    secondLayer[i + 1][j] = brickNumbers.peek();
                    secondLayer[i + 1][j + 1] = brickNumbers.pop();
                    j++;
                }
            }

            if (secondLayer[i][firstLayer[i].length - 1] == 0) {
                secondLayer[i][firstLayer[i].length - 1] = brickNumbers.peek();
                secondLayer[i + 1][firstLayer[i].length - 1] = brickNumbers.pop();
            }
        }
    }

    private static void fillListOfBrickNumbers(ArrayDeque<Integer> brickNumbers, int width, int length) {
        for (int i = width * length / 2; i >= 1; i--) {
            brickNumbers.push(i);
        }
    }

    private static void printSecondLayer(int[][] secondLayer) {
        for (int i = 0; i < secondLayer.length; ) {
            if(i == 0) {
                for (int j = 0; j < secondLayer[i + 1].length; j++) {
                    int currentNUmber = secondLayer[i + 1][j];
                    if (j == 0) {
                        System.out.print("   ");
                    }
                    if (currentNUmber <= 10) {
                        if (j > 0) {
                            if (secondLayer[i + 1][j] != secondLayer[i + 1][j - 1]) {
                                System.out.print("   ");
                            }
                        }
                        System.out.print("*-");
                    } else {
                        if (j > 0) {
                            if (secondLayer[i + 1][j] != secondLayer[i + 1][j - 1]) {
                                System.out.print("   ");
                            }
                        }
                        System.out.print("*- ");
                    }
                }
                System.out.println();
            }
            for (int j = 0; j < secondLayer[i].length; j++) {
                if(j == 0){
                    System.out.print("*- ");
                }
                if (j > 0) {
                    if (secondLayer[i][j] != secondLayer[i][j - 1]) {
                        System.out.print("*- ");
                    }
                }
                System.out.print(secondLayer[i][j] + " ");
                if(j == secondLayer[i].length - 1){
                    System.out.print("*-");
                }
            }
            System.out.println();
            i++;
            if(i - 2 == 0 && i == secondLayer.length){
                for (int j = 0; j < secondLayer[1].length; j++) {
                    int currentNUmber = secondLayer[1][j];
                    if (j == 0) {
                        System.out.print("   ");
                    }
                    if (currentNUmber < 10) {
                        if (j > 0) {
                            if (secondLayer[1][j] != secondLayer[1][j - 1]) {
                                System.out.print("   ");
                            }
                        }
                        System.out.print("*-");
                    } else {
                        if (j > 0) {
                            if (secondLayer[1][j] != secondLayer[1][j - 1]) {
                                System.out.print("   ");
                            }
                        }
                        System.out.print("*- ");
                    }
                }
            }else {
                if (i == secondLayer.length) {
                    for (int j = 0; j < secondLayer[i - 2].length; j++) {
                        int currentNUmber = secondLayer[i - 2][j];
                        if (j == 0) {
                            System.out.print("   ");
                        }
                        if (currentNUmber < 10) {
                            if (j > 0) {
                                if (secondLayer[i - 2][j] != secondLayer[i - 2][j - 1]) {
                                    System.out.print("   ");
                                }
                            }
                            if (secondLayer[i - 2][j] == secondLayer[i - 3][j]) {
                                System.out.print("  ");
                            } else {
                                System.out.print("*-");
                            }
                        } else {
                            if (j > 0) {
                                if (secondLayer[i - 2][j] != secondLayer[i - 2][j - 1]) {
                                    System.out.print("   ");
                                }
                            }
                            if (secondLayer[i - 2][j] == secondLayer[i - 3][j]) {
                                System.out.print("   ");
                            } else {
                                System.out.print("*- ");
                            }
                        }
                    }
                } else {
                    for (int j = 0; j < secondLayer[i].length; j++) {
                        int currentNUmber = secondLayer[i][j];
                        if (j == 0) {
                            System.out.print("   ");
                        }
                        if (currentNUmber < 10) {
                            if (j > 0) {
                                if (secondLayer[i][j] != secondLayer[i][j - 1]) {
                                    System.out.print("   ");
                                }
                            }
                            if (secondLayer[i][j] == secondLayer[i - 1][j]) {
                                System.out.print("  ");
                            } else {
                                System.out.print("*-");
                            }
                        } else {
                            if (j > 0) {
                                if (secondLayer[i][j] != secondLayer[i][j - 1]) {
                                    System.out.print("   ");
                                }
                            }
                            if (secondLayer[i][j] == secondLayer[i - 1][j]) {
                                System.out.print("   ");
                            } else {
                                System.out.print("*- ");
                            }
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    private static boolean addToFirstLayer(Scanner scanner, int[][] firstLayer, int length) {
        boolean wrongInput = false;
        for (int i = 0; i < firstLayer.length; i++) {

            String[] currentInputLine = scanner.nextLine().split("\\s+");
            if(currentInputLine.length != length){

                wrongInput = true;
                System.out.println("-1");
                System.out.println("Wrong input!");
                return wrongInput;
            }
            for (int j = 0; j < firstLayer[i].length; j++) {
                firstLayer[i][j] = Integer.parseInt(currentInputLine[j]);
            }
        }
        return wrongInput;
    }
}
