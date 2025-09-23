import java.util.Scanner;

public class ConjuntosVetores {
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int[] conjuntoA = new int[30];
        int[] conjuntoB = new int[30];
        int tamA = 0, tamB = 0;
        int opcao;
        
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1:
                    tamA = inserirElemento(conjuntoA, tamA, "A");
                    break;
                case 2:
                    tamB = inserirElemento(conjuntoB, tamB, "B");
                    break;
                case 3:
                    imprimirConjuntos(conjuntoA, tamA, conjuntoB, tamB);
                    break;
                case 4:
                    imprimirUniao(conjuntoA, tamA, conjuntoB, tamB);
                    break;
                case 5:
                    imprimirIntersecao(conjuntoA, tamA, conjuntoB, tamB);
                    break;
                case 6:
                    imprimirDiferenca(conjuntoA, tamA, conjuntoB, tamB, "A - B");
                    break;
                case 7:
                    imprimirDiferenca(conjuntoB, tamB, conjuntoA, tamA, "B - A");
                    break;
                case 0:
                    System.out.println("Programa encerrado!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while(opcao != 0);
    }
    
    public static void exibirMenu() {
        System.out.println("\n=== MENU CONJUNTOS ===");
        System.out.println("1) Inserir 1 elemento no Conjunto A");
        System.out.println("2) Inserir 1 elemento no Conjunto B");
        System.out.println("3) Imprimir os Conjuntos A e B");
        System.out.println("4) Gerar e Imprimir a união de A e B");
        System.out.println("5) Gerar e Imprimir a interseção entre A e B");
        System.out.println("6) Gerar e Imprimir a diferença entre A e B");
        System.out.println("7) Gerar e Imprimir a diferença entre B e A");
        System.out.println("0) Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    public static int inserirElemento(int[] v, int tam, String nomeConjunto) {
        if(tam >= v.length) {
            System.out.println("Conjunto " + nomeConjunto + " está cheio!");
            return tam;
        }
        
        System.out.print("Digite o elemento a ser inserido no Conjunto " + nomeConjunto + ": ");
        int elemento = scanner.nextInt();
        
        if(buscaSequencial(v, tam, elemento) != -1) {
            System.out.println("Elemento já existe no conjunto!");
            return tam;
        }
        
        v[tam] = elemento;
        System.out.println("Elemento " + elemento + " inserido com sucesso!");
        return tam + 1;
    }
    
    public static int buscaSequencial(int[] v, int tam, int x) {
        for(int i = 0; i < tam; i++) {
            if(v[i] == x) {
                return i;
            }
        }
        return -1;
    }
    
    public static void imprimir(int[] v, int tam) {
        if(tam == 0) {
            System.out.print("{ }");
            return;
        }
        
        System.out.print("{ ");
        for(int i = 0; i < tam; i++) {
            System.out.print(v[i]);
            if(i < tam - 1) {
                System.out.print(", ");
            }
        }
        System.out.print(" }");
    }
    
    public static void imprimirConjuntos(int[] A, int tamA, int[] B, int tamB) {
        System.out.print("Conjunto A: ");
        imprimir(A, tamA);
        System.out.print("\nConjunto B: ");
        imprimir(B, tamB);
        System.out.println();
    }
    
    public static void imprimirUniao(int[] A, int tamA, int[] B, int tamB) {
        int[] uniao = new int[tamA + tamB];
        int tamUniao = 0;
        
        // Copia todos os elementos de A
        for(int i = 0; i < tamA; i++) {
            uniao[tamUniao++] = A[i];
        }
        
        // Adiciona elementos de B que não estão em A
        for(int i = 0; i < tamB; i++) {
            if(buscaSequencial(A, tamA, B[i]) == -1) {
                uniao[tamUniao++] = B[i];
            }
        }
        
        System.out.print("União A ∪ B: ");
        imprimir(uniao, tamUniao);
        System.out.println();
    }
    
    public static void imprimirIntersecao(int[] A, int tamA, int[] B, int tamB) {
        int[] intersecao = new int[Math.min(tamA, tamB)];
        int tamIntersecao = 0;
        
        for(int i = 0; i < tamA; i++) {
            if(buscaSequencial(B, tamB, A[i]) != -1) {
                intersecao[tamIntersecao++] = A[i];
            }
        }
        
        System.out.print("Interseção A ∩ B: ");
        imprimir(intersecao, tamIntersecao);
        System.out.println();
    }
    
    public static void imprimirDiferenca(int[] A, int tamA, int[] B, int tamB, String nome) {
        int[] diferenca = new int[tamA];
        int tamDiferenca = 0;
        
        for(int i = 0; i < tamA; i++) {
            if(buscaSequencial(B, tamB, A[i]) == -1) {
                diferenca[tamDiferenca++] = A[i];
            }
        }
        
        System.out.print("Diferença " + nome + ": ");
        imprimir(diferenca, tamDiferenca);
        System.out.println();
    }
}
