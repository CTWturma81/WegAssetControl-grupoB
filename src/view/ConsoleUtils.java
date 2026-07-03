package util;

public class ConsoleUtils {

    // Códigos ANSI
    public static final String RESET = "\u001B[0m";
    public static final String AZUL = "\u001B[34m";
    public static final String AZUL_BRILHANTE = "\u001B[94m";
    public static final String BRANCO = "\u001B[97m";
    public static final String NEGRITO = "\u001B[1m";

    public static void clear(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }

    public static void exibirBanner(){
        String[] linhas = {
                "██╗    ██╗███████╗ ██████        █████╗ ███████╗███████╗███████╗████████╗",
                "██║    ██║██╔════╝██╔════╝      ██╔══██╗██╔════╝██╔════╝██╔════╝╚══██╔══╝",
                "██║ █╗ ██║█████╗  ██║  ███╗     ███████║███████╗███████╗█████╗     ██║   ",
                "██║███╗██║██╔══╝  ██║   ██║     ██╔══██║╚════██║╚════██║██╔══╝     ██║   ",
                "╚███╔███╔╝███████╗╚██████╔╝     ██║  ██║███████║███████║███████╗   ██║   ",
                " ╚══╝╚══╝ ╚══════╝ ╚═════╝      ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝   ╚═╝   "
        };

        for(String linha : linhas){
            System.out.println(colorirLinha(linha));
        }

        System.out.println(BRANCO + "                    Sistema de Gestão Industrial" + RESET);
        System.out.println();
    }

    private static String colorirLinha(String linha){
        StringBuilder resultado = new StringBuilder();
        boolean corAtualAzul = false;

        for(char c : linha.toCharArray()){
            boolean ehBloco = (c == '█');

            if(ehBloco != corAtualAzul){
                resultado.append(ehBloco ? AZUL : BRANCO);
                corAtualAzul = ehBloco;
            }

            resultado.append(c);
        }

        resultado.append(RESET);
        return resultado.toString();
    }

    public static void telaPadrao(){
        clear();
        exibirBanner();
    }
}