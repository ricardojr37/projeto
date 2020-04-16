import java.util.Scanner;

import perfis.*;
import perfis.exceptions.PEException;
import twitter.*;
import repositorio.exceptions.UNCException;

public class Main {
	//Primeira tela para aparecer ao abrir o Twitter
	public static void imprimirMenuInicial() {
		System.out.print("\n");
		System.out.println("*************************");
		System.out.println("Bem vindo ao JacobTweet");
		System.out.println("*************************");
		System.out.println(" 1. Criar perfil \n 2. Desativar perfil \n 3. Acessar perfil \n 4. Fechar");
		System.out.print("\n");
		System.out.print("Escolha uma opção: ");
	}
  
  public static void imprimirCabecalho(String usuario) {
		for (int i = 0; i < 5 + usuario.length(); i++) {
			System.out.print("=");
		}
		System.out.println("");
	}

	//Após usuário estar logado, esse menu será impresso
	public static void imprimirMenuUsuario(String usuario) {
		System.out.print("\n");
		imprimirCabecalho(usuario);
		System.out.println("Olá " + usuario + "!");
		imprimirCabecalho(usuario);
		System.out.println(" 1. Tweetar \n 2. Ver timeline \n 3. Ver seus tweets \n 4. Seguir \n 5. Número de seguidores \n 6. Mostrar seguidores \n 7. Listar seguidos \n 8. Sair \n 9. Fechar \n");
		System.out.print("Escolha uma opção: ");
	}
	

	public static void main(String[] args) {
		String usuario;
		MyTwitter twitter = new MyTwitter(); //criando twitter
		Scanner leitor = new Scanner(System.in); //scanner
		String entrada = "5";
		while(entrada != "4") { //while para ficar repetindo o menu até o usuário fechar
			imprimirMenuInicial(); //mostra o Menu Inicial
			entrada = leitor.nextLine(); //usuario seleciona a opção (se escolher 4, para o loop e fecha o programa)
			switch (entrada) { //a partir da escolha do usuário

			case "1": //Criar perfil
				String pessoaTipo = "0";
				boolean opcaoInvalida = false;
				while(pessoaTipo.charAt(0) != '1' && pessoaTipo.charAt(0) != '2') {
					if (opcaoInvalida) {
						System.out.println("ERRO - OPÇÃO INVÁLIDA!");
					} //Manipulação do Erro caso a pessoa escolha uma opção fora das mostradas abaixo 
					System.out.println("Escolha uma opção: \n 1. Pessoa Física \n 2. Pessoa Jurídica");
					pessoaTipo = leitor.nextLine();
					opcaoInvalida = true; //habilita a opção válida que será utilizada se a opção selecionada não existir
				}
				System.out.print("Usuário: ");
				usuario = leitor.nextLine();
				Perfil pessoa;
				if (pessoaTipo.charAt(0) == '1') { //pessoa física
					System.out.print("Informe o CPF: "); 
					long cpf = leitor.nextLong(); //coleta do CPF
					leitor.nextLine();
					pessoa = new PessoaFisica(usuario, cpf);
					
				} else { //pessoa juridica
					System.out.print("Informe o CNPJ: ");
					long cnpj = leitor.nextLong(); //coleta do CNPJ
					leitor.nextLine();
					pessoa = new PessoaJuridica(usuario, cnpj);
				}
				try { //AÇÃO PAR SER FEITA
					twitter.criarPerfil(pessoa);
				} catch (PEException pee) { //Usuário já existe
					System.out.println("ERRO! " + pee.getMessage());
				} 
				break;

			case "2": //Desativar perfil
				System.out.print("Usuário: ");
				usuario = leitor.nextLine();
				try { //AÇÃO PARA SER FEITA
					twitter.cancelarPerfil(usuario);
				} catch (Exception e) { //Mensagem de Erro
					System.out.println("ERRO! " + e.getMessage());
				}
				break;
			case "3":
          System.out.print("Usuário: ");//Acessar perfil
          usuario = leitor.nextLine();
          boolean sair = false;
          if(twitter.existe(usuario) == true){
            while (entrada != "7" && sair == false) { //segundo loop agora depois que o usuario é logado
              imprimirMenuUsuario(usuario);
              entrada = leitor.nextLine();
              switch (entrada) {
                case "1": //Fazer tweet
                  String mensagem;
                  System.out.println("Tweet:");
                  mensagem = leitor.nextLine();
                  try { //AÇÃO PARA SER FEITA
                    twitter.tweetar(usuario, mensagem);
                  } catch (Exception e) {
                    System.out.println("");
                    System.out.println("ERRO! " + e.getMessage());
                  } //Mensagem de Erro
                  break;
                case "2": //Ver Timeline
                  try {
                    for (Tweet tweet : twitter.timeline(usuario)) {
                      System.out.println(tweet.getMensagem());
                    }
                  } catch (Exception e) {
                    System.out.println("");
                    System.out.println("ERRO! " + e.getMessage());
                  }
                  break;
                case "3": //Carregar os tweets do usuario
                  try {
                    for (Tweet tweet : twitter.tweets(usuario)) {
                      System.out.println(tweet.getMensagem());
                    }
                  } catch(Exception e) {
                    System.out.println("ERRO! " + e.getMessage());
                  }
                  break;
                case "4": //Seguir
                  String usuarioSeguido;
                  System.out.println("Digite o nome do usuário a ser seguido:");
                  usuarioSeguido = leitor.nextLine();
                  try {
                    twitter.seguir(usuario, usuarioSeguido);
                  } catch (Exception e) {
                    System.out.println("ERRO! " + e.getMessage());
                  }			
                  break;
                case "5": //Numero de Seguidores
                  try {
                    System.out.println("Você tem " + twitter.numeroSeguidores(usuario) + " seguidor(es).");
                  } catch (Exception e) {
                    System.out.println("");
                    System.out.println("ERRO! " + e.getMessage());
                  }
                  break;
                case "6": //Mostrar seguidores
                  try {
                    for (Perfil seguidor : twitter.seguidores(usuario)) {
                      System.out.println(seguidor.getUsuario());
                    }
                  } catch (Exception e) {
                    System.out.println("ERRO! " + e.getMessage());
                  }
                  break;
                case "7": //Listar seguidos
                  try {
                  System.out.println("\nSeus seguidos são");
                  for (Perfil seguido : twitter.seguidos(usuario)) {
                    System.out.println(seguido.getUsuario());
                  }
                } catch (Exception e) {
                  System.out.println("\nERRO! " + e.getMessage());
                }
                break;
                case "8": //Sair
                  sair = true;
                  break;
                case "9": //Fechar
                  System.exit(0);
                  break;
                default: //Opção fora do padrão
                  System.out.println("ERRO - OPÇÃO INVÁLIDA!");
                  break;
              }
            }
          }
          else{
            System.out.println("Usuário não existe!");
          }
          break;
			case "4": //Fechar
				System.exit(0);
				break;
			default: //Fora das opções
				System.out.println("ERRO - OPÇÃO INVÁLIDA!");
				break;
			}
		}
		leitor.close();
	}
}