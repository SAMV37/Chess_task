import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] board = new int[8][8];
        String[] places = new String[5];
        String[] names = {"Black King", "White King", "White Queen", "White Bishop", "White Knight"};

        System.out.println("Please insert the places of your figures with the form A1, a1, 1a or 1A");

        for(int i=0;i<5;i++){
            System.out.print("Please insert the place of your " + names[i] + " here: ");
            places[i] = scan.nextLine();
            if(places[i].length() == 2 && Character.isDigit(places[i].charAt(0)) && Character.isDigit(places[i].charAt(0))){
                places[i] = new StringBuilder().append(places[i].charAt(1)).append(places[i].charAt(0)).toString();
            }
        }
        if(verify(places)){
            board = create_matrix(places);
        }else{
            System.out.println("You have inputted the places wrongly");
            System.exit(0);
        }

        if(calculate_steps(board)){
            System.out.println("It is mat");
        }else{
            System.out.println("It is not mat");
        }
    }
    public static boolean verify(String[] places){ // Function for verifying if the inputed device is ok or not
        int trues = 0;
        for(int i=0;i<places.length;i++){
            if(places[i].length() != 2){
                return false;
            }
            for(int j=i+1;j<places.length;j++){
                if(places[i].equals(places[j])){
                    return false;
                }
            }
            if(places[i].substring(0, 1).toLowerCase().matches("[a-h]") && Character.isDigit(places[i].charAt(1)) && Integer.parseInt(places[i].substring(1, 2)) >= 1 && Integer.parseInt(places[i].substring(1, 2)) <= 8){
                trues ++;
            }
        }
        if(trues == places.length){
            return true;
        }
        return false;
    }
    public static int[][] create_matrix(String[] places){ //Function for creating a 8x8 matrix and inserting figures in it.
        int[][] board = new int[8][8];
        for(int i=0;i<places.length;i++){
            if(places[i].substring(0, 1).toLowerCase().equals("a")){
                board[Integer.valueOf(places[i].substring(1, 2)) - 1][0] = i + 1;
            }else if(places[i].substring(0, 1).toLowerCase().equals("b")){
                board[Integer.valueOf(places[i].substring(1, 2)) - 1][1] = i + 1;
            }else if(places[i].substring(0, 1).toLowerCase().equals("c")){
                board[Integer.valueOf(places[i].substring(1, 2)) - 1][2] = i + 1;
            }else if(places[i].substring(0, 1).toLowerCase().equals("d")){
                board[Integer.valueOf(places[i].substring(1, 2)) - 1][3] = i + 1;
            }else if(places[i].substring(0, 1).toLowerCase().equals("e")){
                board[Integer.valueOf(places[i].substring(1, 2)) - 1][4] = i + 1;
            }else if(places[i].substring(0, 1).toLowerCase().equals("f")){
                board[Integer.valueOf(places[i].substring(1, 2)) - 1][5] = i + 1;
            }else if(places[i].substring(0, 1).toLowerCase().equals("g")){
                board[Integer.valueOf(places[i].substring(1, 2)) - 1][6] = i + 1;
            }else{
                board[Integer.valueOf(places[i].substring(1, 2)) - 1][7] = i + 1;
            }
        }
        return board;
    }
    public static boolean calculate_steps(int[][] board){ //Function for calculating every possible step of white figures
        int[][] new_board = new int[board.length][board.length];
        int king_i = 0, king_j = 0;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] == 1){
                    king_i = i;
                    king_j = j;
                }
                if(board[i][j] != 0 && board[i][j] != 1){
                    new_board[i][j] = 2;
                }
            }
        }

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] == 2){ //Calculating for white king
                    for(int k=i-1;k<=i+1;k++){
                        for(int h=j-1;h<=j+1;h++){
                            if(k >= 0 && k <= 7 && h >= 0 && h <= 7) {
                                new_board[k][h] = 1;
                            }
                        }
                    }
                }else if(board[i][j] == 3){ //Calculating for white queen
                    for(int k=i-1;k<=i+1;k++){
                        for(int h=j-1;h<=j+1;h++){
                            if(k >= 0 && k <= 7 && h >= 0 && h <= 7 && new_board[k][h] != 2) {
                                new_board[k][h] = 1;
                            }
                        }
                    }

                    int x = i,y = j + 1;

                    while(y < 8){
                        if(new_board[i][y] == 2){
                            new_board[i][y] = 1;
                            break;
                        }
                        new_board[i][y] = 1;
                        y++;
                    }
                    y = j - 1;
                    while(y>=0){
                        if(new_board[i][y] == 2){
                            new_board[i][y] = 1;
                            break;
                        }
                        new_board[i][y] = 1;
                        y--;
                    }
                    y = j;
                    x = i + 1;
                    while(x < 8){
                        if(new_board[x][j] == 2){
                            new_board[x][j] = 1;
                            break;
                        }
                        new_board[x][j] = 1;
                        x++;
                    }
                    x = i - 1;
                    while(x >= 0){
                        if(new_board[x][j] == 2){
                            new_board[x][j] = 1;
                            break;
                        }
                        new_board[x][j] = 1;
                        x--;
                    }
                    x = i + 1;
                    y = j + 1;


                    while(x < 8 && y < 8){
                        if(new_board[x][y] == 2){
                            new_board[x][y] = 1;
                            break;
                        }
                        new_board[x][y] = 1;
                        x++;
                        y++;
                    }
                    x = i-1; y = j-1;

                    while(x >= 0 && y >= 0){
                        if(new_board[x][y] == 2){
                            new_board[x][y] = 1;
                            break;
                        }
                        new_board[x][y] = 1;
                        x--;
                        y--;
                    }

                    x = i + 1;
                    y = j - 1;
                    while(x < 8 && y >= 0){
                        if(new_board[x][y] == 2){
                            new_board[x][y] = 1;
                            break;
                        }
                        new_board[x][y] = 1;
                        x++;
                        y--;
                    }

                    x = i - 1;
                    y = j + 1;
                    while(x >= 0 && y < 8){
                        if(new_board[x][y] == 2){
                            new_board[x][y] = 1;
                            break;
                        }
                        new_board[x][y] = 1;
                        x--;
                        y++;
                    }
                }else if(board[i][j] == 4){ //Calculating for white bishop
                    int x = i + 1;
                    int y = j + 1;


                    while(x < 8 && y < 8){
                        if(new_board[x][y] == 2){
                            new_board[x][y] = 1;
                            break;
                        }
                        new_board[x][y] = 1;
                        x++;
                        y++;
                    }
                    x = i-1; y = j-1;

                    while(x >= 0 && y >= 0){
                        if(new_board[x][y] == 2){
                            new_board[x][y] = 1;
                            break;
                        }
                        new_board[x][y] = 1;
                        x--;
                        y--;
                    }

                    x = i + 1;
                    y = j - 1;
                    while(x < 8 && y >= 0){
                        if(new_board[x][y] == 2){
                            new_board[x][y] = 1;
                            break;
                        }
                        new_board[x][y] = 1;
                        x++;
                        y--;
                    }

                    x = i - 1;
                    y = j + 1;
                    while(x >= 0 && y < 8){
                        if(new_board[x][y] == 2){
                            new_board[x][y] = 1;
                            break;
                        }
                        new_board[x][y] = 1;
                        x--;
                        y++;
                    }
                }else if(board[i][j] == 5){ //Calculating for white knight
                    if(i - 2 >= 0){
                        if(j - 1 > 0){
                            new_board[i - 2][j - 1] = 1;
                        }
                        if(j + 1 < 8){
                            new_board[i - 2][j + 1] = 1;
                        }
                    }

                    if(i + 2 < 8){
                        if(j - 1 > 0){
                            new_board[i + 2][j - 1] = 1;
                        }
                        if(j + 1 < 8){
                            new_board[i + 2][j + 1] = 1;
                        }
                    }

                    if(j + 2 < 8){
                        if(i - 1 > 0){
                            new_board[i - 1][j + 2] = 1;
                        }
                        if(i + 1 < 8){
                            new_board[i + 1][j + 2] = 1;
                        }
                    }

                    if(j - 2 >= 0){
                        if(i - 1 > 0){
                            new_board[i - 1][j - 2] = 1;
                        }
                        if(i + 1 < 8){
                            new_board[i + 1][j - 2] = 1;
                        }
                    }
                }
            }
        }
        return mat_or_not(new_board, king_i, king_j);
    }
    public static boolean mat_or_not(int[][] board, int a, int b){
        for(int i=a-1;i<=a+1;i++){
            for(int j=b-1;j<=b+1;j++)   {
                if(i >= 0 && i <= 7 && j >= 0 && j <= 7) {
                    if(board[i][j] == 0 || board[i][j] == 2){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
