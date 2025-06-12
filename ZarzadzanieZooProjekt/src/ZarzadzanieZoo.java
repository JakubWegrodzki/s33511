public class ZarzadzanieZoo {
    public static void main(String[] args) {
        Zwierze lew = new Lew("Simba", 5, true);
        Zwierze orzel = new Orzel("Bielik", 3, 2.4);
        Zwierze waz = new Waz("Kobra", 2, true);

        Zwierze[] zoo = {lew, orzel, waz};

        for (Zwierze z : zoo) {
            z.wyswietlInformacje();
            System.out.println("Dźwięk: " + z.wydajDzwiek());
            System.out.println("Poruszanie: " + z.poruszajSie());
            System.out.println("Środowisko naturalne: " + z.srodowiskoNaturalne());
            System.out.println("-----");
        }

        ((Lew) lew).poluj();
        ((Orzel) orzel).szybuj();
        ((Waz) waz).syczy();
    }
}