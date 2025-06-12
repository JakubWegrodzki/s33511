public class Orzel extends Ptak {
    private double rozpietoscSkrzydel;

    public Orzel(String nazwa, int wiek, double rozpietoscSkrzydel) {
        super(nazwa, wiek);
        this.rozpietoscSkrzydel = rozpietoscSkrzydel;
    }

    public void szybuj() {
        System.out.println(nazwa + " szybując przeszukuje teren.");
    }

    @Override
    public String wydajDzwiek() {
        return "Kiii-kiii!";
    }

    @Override
    public String poruszajSie() {
        return "Lata wysoko w powietrzu.";
    }

    @Override
    public String srodowiskoNaturalne() {
        return "Góry i tereny otwarte.";
    }
}