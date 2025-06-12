public class Waz extends Gad {
    private boolean czyJadowity;

    public Waz(String nazwa, int wiek, boolean czyJadowity) {
        super(nazwa, wiek);
        this.czyJadowity = czyJadowity;
    }

    public void syczy() {
        System.out.println(nazwa + " wydaje syk.");
    }

    @Override
    public String wydajDzwiek() {
        return "Sssssss...";
    }

    @Override
    public String poruszajSie() {
        return "Pełza po ziemi.";
    }

    @Override
    public String srodowiskoNaturalne() {
        return "Dżungle, pustynie, sawanny.";
    }
}