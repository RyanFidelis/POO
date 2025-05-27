/*
Ryan Santos Fidelis
Atividade 03
VowelCont
08/05/25
*/
public class Vowels {
    public static int getCountFor(String str) {
        int contador = 0;
        String strMinuscula = str.toLowerCase();
        for (int i = 0; i < strMinuscula.length(); i++) {
            char c = strMinuscula.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                contador++;
            }
        }
        return contador;
    }

    public static int getCountWhile(String str) {
        int contador = 0;
        int i = 0;
        String strMinuscula = str.toLowerCase();
        while (i < strMinuscula.length()) {
            char c = strMinuscula.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                contador++;
            }
            i++;
        }
        return contador;
    }

    public static int getCountDoWhile(String str) {
        int contador = 0;
        int i = 0;
        String strMinuscula = str.toLowerCase();
        if (strMinuscula.length() == 0) {
            return 0;
        }
        do {
            char c = strMinuscula.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                contador++;
            }
            i++;
        } while (i < strMinuscula.length());
        return contador;
    }

    // Novo método para compatibilidade com os testes
    public static int getCount(String str) {
        return getCountFor(str);
    }

    public static void main(String[] args) {
        String texto = "abracadabra";
        System.out.println("Número de vogais (for): " + getCountFor(texto));
        System.out.println("Número de vogais (while): " + getCountWhile(texto));
        System.out.println("Número de vogais (do-while): " + getCountDoWhile(texto));
    }
}
