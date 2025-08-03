import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class ProcessoSeletivo {
    public static void main(String[] args) {
        System.out.println("Processo Seletivo Iniciado\n");

        // Etapa 1: Definir os candidatos
        String[] candidatos = {"João", "Maria", "Pedro", "Ana", "Lucas", "Fernanda", "Carlos", "Beatriz", "Rafael", "Juliana"};

        // Etapa 2: Selecionar candidatos com base no salário pretendido
        System.out.println("--- Etapa 1: Seleção de Candidatos ---\n");
        List<String> candidatosSelecionados = selecaoCandidatos(candidatos);

        // Etapa 3: Imprimir a lista de selecionados
        System.out.println("--- Etapa 2: Impressão dos Candidatos Selecionados ---\n");
        imprimirSelecionados(candidatosSelecionados);

        // Etapa 4: Entrar em contato com os candidatos selecionados
        System.out.println("--- Etapa 3: Tentativa de Contato com os Candidatos ---\n");
        for (String candidato : candidatosSelecionados) {
            entrandoemContato(candidato);
        }

        System.out.println("\nProcesso Seletivo Finalizado");
    }

    static List<String> selecaoCandidatos(String[] todosOsCandidatos) {
        // Seleciona candidatos com base no salário pretendido
        // Define o salário base e inicializa a lista de candidatos selecionados
        double salarioBase = 2000.00;
        List<String> candidatosSelecionados = new ArrayList<>();
        int candidatosAtuais = 0;

        while (candidatosSelecionados.size() < 5 && candidatosAtuais < todosOsCandidatos.length) {
            String candidato = todosOsCandidatos[candidatosAtuais];
            double salarioPretendido = valorPretendido();

            System.out.printf("O candidato %s solicitou o valor de salário: R$ %.2f\n", candidato, salarioPretendido);

            if (salarioBase >= salarioPretendido) {
                System.out.printf("  -> O candidato %s foi selecionado para a vaga!\n\n", candidato);
                candidatosSelecionados.add(candidato);
            } else {
                System.out.printf("  -> O candidato %s não foi selecionado.\n\n", candidato);
            }
            candidatosAtuais++;
        }
        return candidatosSelecionados;
    }


    static void imprimirSelecionados(List<String> candidatosSelecionados) {
        // Imprime a lista de candidatos selecionados
        System.out.println("Lista de candidatos selecionados:");
        if (candidatosSelecionados.isEmpty()) {
            System.out.println("Nenhum candidato foi selecionado.");
        } else {
            for (int i = 0; i < candidatosSelecionados.size(); i++) {
                System.out.printf("  %d° - %s\n", (i + 1), candidatosSelecionados.get(i));
            }
        }
        System.out.println();
    }

    static void entrandoemContato(String candidato) {
        // Entra em contato com o candidato, tentando até 3 vezes
        int tentativasRealizadas = 1;
        boolean atendeu;
        do {
            atendeu = atender();
            if (!atendeu) {
                System.out.printf("  -> Tentativa %d com o candidato %s falhou.\n", tentativasRealizadas, candidato);
                tentativasRealizadas++;
            }
        } while (!atendeu && tentativasRealizadas <= 3);

        if (atendeu) {
            System.out.printf("  -> CONSEGUIMOS CONTATO com o candidato %s após %d tentativas.\n", candidato, tentativasRealizadas);
        } else {
            System.out.printf("  -> NÃO CONSEGUIMOS CONTATO com o candidato %s. Número máximo de tentativas (3) realizadas.\n", candidato);
        }
    }

    static boolean atender() {
        // Retorna true com 1/3 de chance
        return new Random().nextInt(3) == 1;
    }
     // Gera um valor de salário pretendido aleatório entre 1800 e 2200.
     // Retorna um valor double representando o salário pretendido.

    static double valorPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800.00, 2200.00);
    }
}
