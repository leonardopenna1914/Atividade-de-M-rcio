
import java.util.Scanner;

public class Conjuntos {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int[] A = new int[30];
        int[] B = new int[30];
        int tamA = 0;
        int tamB = 0;

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Inserir no conjunto A");
            System.out.println("2 - Inserir no conjunto B");
            System.out.println("3 - Mostrar A e B");
            System.out.println("4 - União (A U B)");
            System.out.println("5 - Interseção (A ∩ B)");
            System.out.println("6 - Diferença (A - B)");
            System.out.println("7 - Diferença (B - A)");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    tamA = inserirElemento(A, tamA, teclado);
                    break;
                case 2:
                    tamB = inserirElemento(B, tamB, teclado);
                    break;
                case 3:
                    System.out.print("Conjunto A: ");
                    imprimir(A, tamA);
                    System.out.print("Conjunto B: ");
                    imprimir(B, tamB);
                    break;
                case 4:
                    int[] uniao = uniao(A, tamA, B, tamB);
                    System.out.print("A U B = ");
                    imprimir(uniao, uniao.length);
                    break;
                case 5:
                    int[] inter = intersecao(A, tamA, B, tamB);
                    System.out.print("A ∩ B = ");
                    imprimir(inter, inter.length);
                    break;
                case 6:
                    int[] difAB = diferenca(A, tamA, B, tamB);
                    System.out.print("A - B = ");
                    imprimir(difAB, difAB.length);
                    break;
                case 7:
                    int[] difBA = diferenca(B, tamB, A, tamA);
                    System.out.print("B - A = ");
                    imprimir(difBA, difBA.length);
                    break;
                case 0:
                    System.out.println("Fechando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        teclado.close();
    }

    public static int inserirElemento(int[] v, int tam, Scanner sc) {
        if (tam >= v.length) {
            System.out.println("Conjunto cheio!");
            return tam;
        }
        System.out.print("Digite o número: ");
        int num = sc.nextInt();

        if (buscaSequencial(v, tam, num) != -1) {
            System.out.println("Esse número já existe!");
            return tam;
        }

        v[tam] = num;
        tam++;
        return tam;
    }

    public static int buscaSequencial(int[] v, int tam, int x) {
        for (int i = 0; i < tam; i++) {
            if (v[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static void imprimir(int[] v, int tam) {
        System.out.print("{ ");
        for (int i = 0; i < tam; i++) {
            System.out.print(v[i]);
            if (i < tam - 1) System.out.print(", ");
        }
        System.out.println(" }");
    }

    public static int[] uniao(int[] A, int tamA, int[] B, int tamB) {
        int[] temp = new int[tamA + tamB];
        int tamU = 0;

        for (int i = 0; i < tamA; i++) {
            temp[tamU++] = A[i];
        }
        for (int i = 0; i < tamB; i++) {
            if (buscaSequencial(temp, tamU, B[i]) == -1) {
                temp[tamU++] = B[i];
            }
        }

        int[] resultado = new int[tamU];
        for (int i = 0; i < tamU; i++) {
            resultado[i] = temp[i];
        }
        return resultado;
    }

    public static int[] intersecao(int[] A, int tamA, int[] B, int tamB) {
        int[] temp = new int[Math.min(tamA, tamB)];
        int tamI = 0;

        for (int i = 0; i < tamA; i++) {
            if (buscaSequencial(B, tamB, A[i]) != -1) {
                temp[tamI++] = A[i];
            }
        }

        int[] resultado = new int[tamI];
        for (int i = 0; i < tamI; i++) {
            resultado[i] = temp[i];
        }
        return resultado;
    }

    public static int[] diferenca(int[] A, int tamA, int[] B, int tamB) {
        int[] temp = new int[tamA];
        int tamD = 0;

        for (int i = 0; i < tamA; i++) {
            if (buscaSequencial(B, tamB, A[i]) == -1) {
                temp[tamD++] = A[i];
            }
        }

        int[] resultado = new int[tamD];
        for (int i = 0; i < tamD; i++) {
            resultado[i] = temp[i];
        }
        return resultado;
    }
}
