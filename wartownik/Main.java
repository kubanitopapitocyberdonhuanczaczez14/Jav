import java.util.Random;
import java.util.Scanner;

public class Main{
    static int[] wypelnijTablice(int n){
        Random rand = new Random();
        int[] tab = new int[n + 1];
        for(int i = 0; i < n; i++){
            tab[i] = rand.nextInt(100) + 1;
        }
        return tab;
    }
    static int szukajZWartownikiem(int[] tab, int szukana){
        int n = tab.length - 1;
        tab[n] = szukana;
        int i = 0;
        while(tab[i] != szukana){
            i++;
        }
        if(i == n){
            return -1;
        }
        return i;
    }
    public static void main(String[] args){
        int n = 50;
        int[] tab = wypelnijTablice(n);

        Scanner scanner= new Scanner(System.in);
        System.out.print("Podaj szukana liczbe: ");
        int szukana = scanner.nextInt();

        int wynik = szukajZWartownikiem(tab, szukana);

        for(int i = 0; i < n; i++){
            System.out.print(tab[i]);
            if(i < n-1){
                System.out.print(", ");
            }
        }
        System.out.println();

        if(wynik == -1){
            System.out.println("Nie znaleziono liczby w tablicy");
        }else{
            System.out.println("Znaleziono na indeksie: " + wynik);
        }
    }
}
