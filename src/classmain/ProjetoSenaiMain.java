package classmain;

import creatingclass.Task;
import creatingclass.Usuario;
import entities.enums.OrderStatus;


import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class ProjetoSenaiMain {

    public static Scanner sc = new Scanner (System.in); // Static para usar na homepage (Uma função statica)
    public static Usuario usuarioLogado = null; //Static para usar no código completo;


    public static void main(String[] args) throws InterruptedException  { // Declarar que um método pode lançar exceções de um determinado tipo.

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date dataAgora = new Date();

        Locale.setDefault(Locale.US); //Define o código padrão do idioma
        ArrayList <Usuario> usuariosCadastrados = new ArrayList<>(); // Lista dos usuários cadastrados nessa parte do login
        boolean funcionar = true; // Para funcionar o while

        while (funcionar) {
            System.out.println("[1] Fazer cadastro ☺");
            System.out.println("[2] Fazer login ☺");
            System.out.println("[3] Sair \uD83D\uDE41"); // Apenas o código do emoji
            System.out.println("Horário de entrada: "+sdf.format(dataAgora));

            System.out.print("\nDigite a opção desejada: ");
            byte opçãoInicial = sc.nextByte();
            sc.nextLine(); //Limpar buffer de entrada (Depois de usar int,byte ou short precisa limpar esse buffer)

            switch (opçãoInicial) {
                case 1:{
                    System.out.println("\n=========================");
                    System.out.println("\t\tCADASTRO");
                    System.out.println("=========================");
                    System.out.print("\nDigite o seu e-mail: ");
                    String e_mailCadastro = sc.nextLine();
                    System.out.print("Digite a sua senha: ");
                    String senhaCadastro = sc.nextLine();

                    Usuario u = new Usuario (e_mailCadastro, senhaCadastro); // Classe do usuário logado
                    usuariosCadastrados.add(u); // Colocado na lista

                    ArrayList<Task> listToDo = new ArrayList<>();
                    u.setTaskList(listToDo);// Colocando outra lista no taskList


                    TimeUnit.SECONDS.sleep(2); // Delay de 2 segundos
                    System.out.println("\nCadastro realizado com sucesso!!\n");
                    break;

                }
                case 2:{
                    System.out.println("\n=========================");
                    System.out.println("\t\t LOGIN");
                    System.out.println("=========================");


                    System.out.print("\nDigite o seu e-mail: ");
                    String e_mailLogin = sc.nextLine();
                    System.out.print("Digite a sua senha: ");
                    String senhaLogin = sc.nextLine();

                    boolean sucessoLogin = false; // inicialmente está falso, se tudo der certo será verdadeiro

                    for (Usuario x : usuariosCadastrados){ //Um for each, para percorrer a lista inteira com essa váriavel temporária
                        String compararEmail = x.getEmail(); // O "compararEmail" vai pegar toda a informação da lista
                        String compararSenha = x.getSenha(); // O "compararSenha" vai pegar toda a informação da lista

                        boolean emailConfere = e_mailLogin.equals(compararEmail);// Agora vai ser comparado tudo e o "emailConfere será a comprovação em boolean"
                        boolean senhaConfere = senhaLogin.equals(compararSenha); // Agora vai ser comparado tudo e o "senhaConfere será a comprovação em boolean"

                        if (emailConfere && senhaConfere){
                            usuarioLogado = (x); // Se estiver tudo certo a variável statica vai pegar o que estiver na várivel temporária x do for each
                            sucessoLogin = true; // Se deu tudo certo, a váriavel login é verdadeira
                            break;
                        }
                    }
                    if (!sucessoLogin){ //Se deu errado, aparece essa mensagem
                        System.out.println("\nEmail ou senha incorretos!\nTente novamente\n");
                    }
                    else{
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("\nLogin feito com sucesso! \n");
                        homepage(); // Função stática da página de tárefas
                    }
                    break;
                }
                case 3:{
                    funcionar = false; //Opção "Sair" fazendo a váriavel boolean fácil para finalizar o while
                    break;
                }
                default:{
                    System.out.println("\nNumero digitado incorreto, digite o número novamente!\n"); // se não for nenhum desses números...
                }
            }
        }
    }

    private static void homepage()throws InterruptedException {
        boolean funcionar = true; // mesma pegada do boolean do início

        do {
            System.out.println("===================================");
            System.out.println("\t\t\tTO DO LIST                    "); // \t é tipo tab
            System.out.println("===================================");
            System.out.println("[1] Mostrar tarefas ☺");
            System.out.println("[2] Mostrar tarefas finalizadas ☺");
            System.out.println("[3] Mostrar não tarefas finalizadas ☺");
            System.out.println("[4] Adicionar tarefas ☺");
            System.out.println("[5] Finalizar tarefas ☺");
            System.out.println("[6] Excluir tarefas ☺");
            System.out.println("[7] Área de cadastro\uD83D\uDE41");
            System.out.println("[8] Fechar programa\uD83D\uDE41 \n");
            System.out.print("Digite a opção desejada: ");
            byte opcaoHomepage = sc.nextByte();
            sc.nextLine(); //Limpar buffer
            System.out.println("");// apenas para pular linha

            switch (opcaoHomepage) {
                case 1:{

                    System.out.println("===================================");
                    System.out.println("\t\t\tLISTA DE TAREFAS             ");
                    System.out.println("===================================");

                    ArrayList <Task> taskList = usuarioLogado.getTaskList(); // tasklist pegando as informações do usuárioLogado

                    if (taskList.isEmpty()){ //Se não ter nenhuma tarefa nessa lista...
                        System.out.println("\nLista de tarefas vazia!\n");
                    }
                    else {
                        for (int i = 0; i < taskList.size(); i++) { // .size é o tamanho da lista
                            Task task = taskList.get(i); //task pegando as informações de toda a lista

                            System.out.println("\nTarefa = " + (i+1)); // +1 para o usuário entender melhor
                            System.out.println("Titulo = " + task.getTitulo());
                            System.out.println("Finalizada = " + task.isFinished() + "\n"); // variável bollean se estiver finalizada ou não

                        }
                    }

                    break;
                }
                case 2:{

                    System.out.println("===================================");
                    System.out.println("\tLISTA DE TAREFAS  FINALIZADAS    ");
                    System.out.println("===================================");

                    ArrayList <Task> taskList = usuarioLogado.getTaskList(); // pegando as informações do usuáriologado
                    ArrayList <Task> taskFinished = new ArrayList<>();

                    for (Task x : taskList) {
                        if (x.isFinished()){
                            taskFinished.add(x);
                        }
                    }

                    if(taskFinished.isEmpty()){
                        System.out.println("\nLista de tarefas finalizadas vazia!\n");
                    }

                    for (int i = 0; i < taskFinished.size(); i++) {
                        Task task = taskFinished.get(i);

                        System.out.println("\nTarefa = " + (i+1));
                        System.out.println("Titulo = " + task.getTitulo());
                        System.out.println("Status = " + OrderStatus.FINALIZADA + "\n"); // ENUM
                    }
                    break;
                }
                case 3:{
                    System.out.println("===================================");
                    System.out.println("\tLISTA DE TAREFAS NÃO FINALIZADAS ");
                    System.out.println("===================================");

                    ArrayList <Task> taskList = usuarioLogado.getTaskList();
                    ArrayList <Task> notIsFinished = new ArrayList<>();

                    for (Task x: taskList) {
                        if (!x.isFinished()) { // a variável finalizado for !(não) ou seja, se não estiver finalizado, será acrescentada na outra variável nãofinalizada
                            notIsFinished.add(x);
                        }
                    }
                        if(notIsFinished.isEmpty()){
                            System.out.println("\nLista de tarefas não finalizadas vazia!\n");
                        }

                    for (int i = 0; i < notIsFinished.size(); i++){ //percorrer toda a lista de nãofinalizada
                        Task task = notIsFinished.get(i);

                        System.out.println("\nTarefa = " + (i+1));
                        System.out.println("Titulo = " + task.getTitulo());
                        System.out.println("Status = " + OrderStatus.NÃO_FINALIZADA + "\n"); // ENUM

                    }
                    break;
                }
                case 4:{
                    System.out.println("===================================");
                    System.out.println("\t\t\tADICIONAR TAREFAS            ");
                    System.out.println("===================================");

                    System.out.print("\nDigite a tarefa que deseja adicionar: ");
                    String tarefa = sc.nextLine();

                    Task taskList = new Task();
                    taskList.setTitulo(tarefa); //Mudando o título para o que foi escrito na variável
                    taskList.setFinished(false);//Inicialmente a tarefa não está finalizada, então é falsa

                    usuarioLogado.getTaskList().add(taskList); // adicionando tudo que está salvo na variável do usuariologado

                    System.out.println("\nTarefa adicionada com sucesso!!\n");

                    break;
                }
                case 5:{
                    System.out.println("===================================");
                    System.out.println("\t\t\tFINALIZAR TAREFAS            ");
                    System.out.println("===================================");

                    ArrayList <Task> taskList = usuarioLogado.getTaskList();
                    ArrayList <Task> taskListFinish = new ArrayList<>();

                    for (Task x: taskList) {
                        if (!x.isFinished()) {
                            taskListFinish.add(x);//Tudo que não estiver finalizado irá aparecer nessa váriavel para ser usado no próximo for
                        }
                    }

                    if(taskListFinish.isEmpty()){ //Se essa lista estiver vazia
                        System.out.println("\nNão existe tarefas para finalizar neste momento!\n");
                        break;
                    }
                    else{
                        for (int i = 0; i<taskListFinish.size(); i++ ){ //Usando a váriavel
                            Task x = taskListFinish.get(i); // Variável temporária para mostrar titulo

                            System.out.println("\nPosição —> {" + (i+1) + "} titulo —> " + x.getTitulo());
                        }
                        System.out.print("Digite a tarefa que deseja finalizar: ");
                        int tarefaFinalizada = sc.nextByte();
                        sc.nextLine();

                        taskListFinish.get(tarefaFinalizada-1).setFinished(true); // Como eu coloquei +1 para o usuário entender melhor, se ele escolher 1 então vai ser a posição 0

                        System.out.println("\nTarefa finalizada!!\n");
                        break;
                    }
                }
                case 6:{
                    System.out.println("===================================");
                    System.out.println("\t\t\tEXCLUIR TAREFAS              ");
                    System.out.println("===================================");

                    ArrayList <Task> taskList = usuarioLogado.getTaskList();

                    for (int i = 0; i<taskList.size(); i++ ){
                        Task x = taskList.get(i);

                        System.out.println("\nPosição —> {" + (i+1) + "} titulo —> " + x.getTitulo());
                    }
                    System.out.print("Digite a tarefa que deseja excluir: ");
                    int deleteTask = sc.nextByte();
                    sc.nextLine();

                    taskList.remove(deleteTask-1); // mesma lógica do finalizar tarefas
                    System.out.println("\nTarefa excluída com sucesso!!\n");
                    break;
                }
                case 7:{
                    System.out.println("Direcionando para a área de cadastro");
                    funcionar = false;
                    break;
                }
                case 8:{
                    System.exit(0); //Tipo o return 0 em c
                    break;
                }
                default:{ // Se não for nenhum desses numeros, faça isso
                    System.out.println("\nNumero digitado incorreto, você será direcionado(a) para a página de cadastro!\n");
                    TimeUnit.MILLISECONDS.sleep(1700);
                    funcionar = false; // esse false só irá funcionar nesse do-while, não do while da parte de login, pois está em escopos diferentes.
                }
            }
        }while (funcionar);

    }
}