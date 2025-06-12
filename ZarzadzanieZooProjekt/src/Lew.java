public class Lew extends Ssak {
    private boolean czyDrapieznik;

    public Lew(String nazwa, int wiek, boolean czyDrapieznik) {
        super(nazwa, wiek);
        this.czyDrapieznik = czyDrapieznik;
    }

    public void poluj() {
        System.out.println(nazwa + " poluje na zwierzynę.");
    }

    @Override
    public String wydajDzwiek() {
        return "Rooaaar!";
    }

    @Override
    public String poruszajSie() {
        return "Biega na czterech łapach.";
    }

    @Override
    public String srodowiskoNaturalne() {
        return "Sawanna i stepy Afryki.";
    }
}