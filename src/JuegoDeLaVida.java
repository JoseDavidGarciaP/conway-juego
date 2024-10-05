import java.util.Random;
import java.util.Scanner;

public class JuegoDeLaVida {
    public static void main(String[] args) {
        boolean[][] tablero = new boolean[5][5];
        boolean[][] nuevoTablero = new boolean[5][5];
        final int TOP_FIVE_NUMBER = 5;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int generation = 0;

        // Inicializar tablero aleatoriamente
        for (int i = 0; i < TOP_FIVE_NUMBER; i++) {
            for (int j = 0; j < TOP_FIVE_NUMBER; j++) {
                tablero[i][j] = random.nextBoolean();
            }
        }
        
        while (true) {
            System.out.println("Generación " + generation);
            
            // Mostrar tablero
            for (int i = 0; i < TOP_FIVE_NUMBER; i++) {
                for (int j = 0; j < TOP_FIVE_NUMBER; j++) {
                    System.out.print(tablero[i][j] ? "■ " : "□ ");
                }
                System.out.println();
            }

            // Calcular siguiente generación
            for (int i = 0; i < TOP_FIVE_NUMBER; i++) {
                for (int j = 0; j < TOP_FIVE_NUMBER; j++) {
                    int vecinosVivos = 0;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (di == 0 && dj == 0) continue;
                            int fi = (i + di + 5) % 5;
                            int fj = (j + dj + 5) % 5;
                            if (tablero[fi][fj]) vecinosVivos++;
                        }
                    }
                    if (tablero[i][j]) {
                        nuevoTablero[i][j] = vecinosVivos == 2 || vecinosVivos == 3;
                    } else {
                        nuevoTablero[i][j] = vecinosVivos == 3;
                    }
                }
            }

            // Actualizar tablero
            for (int i = 0; i < 5; i++) {
                System.arraycopy(nuevoTablero[i], 0, tablero[i], 0, 5);
            }

            generation++;

            System.out.print("Presione Enter para la siguiente generación o 'q' para salir: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
        }
        scanner.close();
    }
}
