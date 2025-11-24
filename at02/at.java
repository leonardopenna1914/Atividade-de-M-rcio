import java.util.Scanner;

public class at {
    public static int buscarPessoaPorNome(Pessoa[] v, int qtd, String nome) { 
        for (int i = 0; i < qtd; i++) { 
            if (v[i].nome.equalsIgnoreCase(nome)) { 
                return i; 
            } 
        } 
        return -1; 
    } 
 
    // ======== Q1: cadastrarPessoa ======== 
    public static int cadastrarPessoa(Pessoa[] v, int qtd) { 
        Scanner sc = new Scanner(System.in); 
 
        if (qtd >= v.length) { 
            System.out.println("\n⚠ Não há espaço para cadastrar mais pessoas!"); 
            System.out.println("\n⚠ Não há espaço para cadastrar mais pessoas!"); 
            return qtd; 
        } 
 
        Pessoa p = new Pessoa(); 
 
        // Garante nome único 
        while (true) { 
            System.out.print("Digite o nome da pessoa: "); 
            p.nome = sc.nextLine(); 
 
            if (buscarPessoaPorNome(v, qtd, p.nome) == -1) { 
                break; 
            } else { 
                System.out.println("\n⚠ Nome já existente! Digite outro nome."); 
                System.out.println("\n⚠ Nome já existente! Digite outro nome."); 
            } 
        } 
 
        System.out.print("Digite a idade: "); 
        p.idade = sc.nextInt(); 
 
        System.out.print("Digite o peso (kg): "); 
        p.peso = sc.nextDouble(); 
 
        System.out.print("Digite a altura (m): "); 
        p.altura = sc.nextDouble(); 
 
        v[qtd] = p; 
         qtd++;
         System.out.println("\n✅ Pessoa cadastrada com sucesso!");
         return qtd;
    } 
 
    // ======== Função auxiliar: calcular IMC ======== 
    public static double calcularIMC(Pessoa p) { 
        return p.peso / (p.altura * p.altura); 
    } 
 
    // ======== Q2: imprimirPessoas ======== 
    public static void imprimirPessoas(Pessoa[] v, int qtd) { 
        if (qtd == 0) { 
            System.out.println("Nenhuma pessoa cadastrada."); 
            return; 
        } 
 
 
        System.out.println("\n=== LISTA DE PESSOAS CADASTRADAS ==="); 
        for (int i = 0; i < qtd; i++) { 
            double imc = calcularIMC(v[i]); 
            System.out.printf("Nome: %s | Idade: %d | Peso: %.2f kg | Altura: %.2f m | IMC: %.2f%n", v[i].nome, v[i].idade, v[i].peso, v[i].altura, imc); 
        } 
        System.out.println("====================================\n"); 
    } 
 
    // ======== Q3: maisVelhaIMCMagreza ======== 
    public static int maisVelhaIMCMagreza(Pessoa[] v, int qtd) { 
        int indice = -1; 
        int maiorIdade = -1; 
 
        for (int i = 0; i < qtd; i++) { 
            double imc = calcularIMC(v[i]); 
            if (imc < 18.5 && v[i].idade > maiorIdade) { 
                maiorIdade = v[i].idade; 
                indice = i; 
            } 
        } 
 
        return indice; 
    } 
 
    // ======== Q4: insertionSortPorNome ======== 
    public static void insertionSortPorNome(Pessoa[] v, int qtd) { 
        for (int i = 1; i < qtd; i++) { 
            Pessoa chave = v[i]; 
            int j = i - 1; 
 
            while (j >= 0 && v[j].nome.compareToIgnoreCase(chave.nome) > 0) { 
                v[j + 1] = v[j]; 
                j--; 
            } 
            v[j + 1] = chave; 
        } 
 
        System.out.println("\n✅ Vetor ordenado por nome com sucesso!"); 
    } 
 
    // ======== Q5: listar pessoas por faixa etária ======== 
    public static void listarPessoasPorFaixaEtaria(Pessoa[] v, int qtd, int idadeMin, int 
idadeMax) { 
        boolean encontrou = false;
        System.out.println("\nPessoas na faixa etária de " + idadeMin + " a " + idadeMax + " anos:");
        for (int i = 0; i < qtd; i++) {
            if (v[i].idade >= idadeMin && v[i].idade <= idadeMax) {
                System.out.println("Nome: " + v[i].nome + " | Idade: " + v[i].idade);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma pessoa encontrada nesta faixa etária.");
        }
    } 
 
    // ======== MAIN ======== 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        Pessoa[] vetor = new Pessoa[50]; // até 50 pessoas 
        int qtd = 0; 
        int opcao; 
 
        do { 
            System.out.println("======== MENU ========"); 
            System.out.println("1 - Cadastrar pessoa"); 
            System.out.println("2 - Imprimir pessoas"); 
            System.out.println("3 - Mostrar pessoa mais velha com IMC < 18.5"); 
            System.out.println("4 - Ordenar pessoas por nome"); 
            System.out.println("5 - Listar pessoas por faixa etária"); 
            System.out.println("0 - Sair"); 
            System.out.print("Escolha uma opção: "); 
            opcao = sc.nextInt(); 
            sc.nextLine(); // limpa o buffer 
 
            switch (opcao) { 
                case 1: 
                    qtd = cadastrarPessoa(vetor, qtd); 
                    break; 
 
                case 2: 
                    imprimirPessoas(vetor, qtd); 
                    break; 
 
                case 3: 
                    int indice = maisVelhaIMCMagreza(vetor, qtd); 
                    if (indice == -1) { 
                        System.out.println("Nenhuma pessoa com IMC de magreza encontrada."); 
                    } else { 
                        System.out.println("Pessoa mais velha com magreza: " + vetor[indice].nome 
+ 
                                " (" + vetor[indice].idade + " anos, IMC: " + String.format("%.2f", 
calcularIMC(vetor[indice])) + ")"); 
                    } 
                    break; 
 
                case 4: 
                    insertionSortPorNome(vetor, qtd); 
                    break; 
 
                case 5: 
                    System.out.print("Idade mínima: "); 
                    int min = sc.nextInt(); 
                    System.out.print("Idade máxima: "); 
                    int max = sc.nextInt(); 
                    listarPessoasPorFaixaEtaria(vetor, qtd, min, max); 
                    break; 
 
                case 0: 
                    System.out.println("Encerrando o programa..."); 
                    break; 
 
                default: 
                    System.out.println("Opção inválida!"); 
            } 
} while (opcao != 0); 
sc.close(); 
} 
} 