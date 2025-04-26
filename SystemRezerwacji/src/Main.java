import java.util.ArrayList;

class Wydarzenie {
    private String nazwa;
    private String data;
    private String miejsce;
    private int maxLiczbaMiejsc;
    private int dostepneMiejsca;
    private double cena;

    public Wydarzenie(String nazwa, double cena) {
        this(nazwa, cena, "Nieokreślona data", "Nieokreślone miejsce");
    }

    public Wydarzenie(String nazwa, double cena, String data) {
        this(nazwa, cena, data, "Nieokreślone miejsce");
    }

    public Wydarzenie(String nazwa, double cena, String data, String miejsce) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.data = data;
        this.miejsce = miejsce;
        this.maxLiczbaMiejsc = 100;
        this.dostepneMiejsca = 100;
    }

    public String getNazwa() { return nazwa; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getMiejsce() { return miejsce; }
    public void setMiejsce(String miejsce) { this.miejsce = miejsce; }

    public int getMaxLiczbaMiejsc() { return maxLiczbaMiejsc; }
    public void setMaxLiczbaMiejsc(int maxLiczbaMiejsc) { this.maxLiczbaMiejsc = maxLiczbaMiejsc; }

    public int getDostepneMiejsca() { return dostepneMiejsca; }
    public void setDostepneMiejsca(int dostepneMiejsca) { this.dostepneMiejsca = dostepneMiejsca; }

    public double getCena() { return cena; }
    public void setCena(double cena) { this.cena = cena; }

    @Override
    public String toString() {
        return "Wydarzenie: " + nazwa + ", Data: " + data + ", Miejsce: " + miejsce + ", Cena: " + cena + " zł, Dostępne miejsca: " + dostepneMiejsca;
    }

    public boolean zarezerwujMiejsce() {
        if (dostepneMiejsca > 0) {
            dostepneMiejsca--;
            return true;
        }
        return false;
    }
}

class Klient {
    private String imie;
    private String nazwisko;
    private String email;
    private ArrayList<Wydarzenie> listaRezerwacji;

    public Klient(String imie, String nazwisko) {
        this(imie, nazwisko, "brak@brak.pl");
    }

    public Klient(String imie, String nazwisko, String email) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.listaRezerwacji = new ArrayList<>();
    }

    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }

    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public ArrayList<Wydarzenie> getListaRezerwacji() { return listaRezerwacji; }

    public void dodajRezerwacje(Wydarzenie wydarzenie) {
        listaRezerwacji.add(wydarzenie);
    }

    public void wyswietlRezerwacje() {
        if (listaRezerwacji.isEmpty()) {
            System.out.println("Brak rezerwacji.");
            return;
        }
        for (Wydarzenie w : listaRezerwacji) {
            System.out.println(w);
        }
    }

    public void anulujRezerwacje(Wydarzenie wydarzenie) {
        listaRezerwacji.remove(wydarzenie);
    }
}

class SystemRezerwacji {
    private ArrayList<Wydarzenie> wydarzenia;
    private ArrayList<Klient> klienci;

    public SystemRezerwacji() {
        this.wydarzenia = new ArrayList<>();
        this.klienci = new ArrayList<>();
    }

    public void dodajWydarzenie(Wydarzenie wydarzenie) {
        wydarzenia.add(wydarzenie);
    }

    public void dodajWydarzenie(String nazwa, double cena) {
        wydarzenia.add(new Wydarzenie(nazwa, cena));
    }

    public void dodajKlienta(Klient klient) {
        klienci.add(klient);
    }

    public void dodajKlienta(String imie, String nazwisko, String email) {
        klienci.add(new Klient(imie, nazwisko, email));
    }

    public Wydarzenie znajdzWydarzenie(String nazwa) {
        for (Wydarzenie w : wydarzenia) {
            if (w.getNazwa().equalsIgnoreCase(nazwa)) {
                return w;
            }
        }
        return null;
    }

    public Klient znajdzKlienta(String nazwisko) {
        for (Klient k : klienci) {
            if (k.getNazwisko().equalsIgnoreCase(nazwisko)) {
                return k;
            }
        }
        return null;
    }

    public boolean dokonajRezerwacji(Klient klient, Wydarzenie wydarzenie) {
        if (wydarzenie.zarezerwujMiejsce()) {
            klient.dodajRezerwacje(wydarzenie);
            return true;
        }
        return false;
    }

    public boolean zmienCeneWydarzenia(String nazwa, double nowaCena) {
        Wydarzenie wydarzenie = znajdzWydarzenie(nazwa);
        if (wydarzenie != null) {
            wydarzenie.setCena(nowaCena);
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        SystemRezerwacji system = new SystemRezerwacji();

        Wydarzenie koncert = new Wydarzenie("Koncert Symphony", 120.0);
        Wydarzenie teatr = new Wydarzenie("Hamlet", 85.0);
        system.dodajWydarzenie(koncert);
        system.dodajWydarzenie(teatr);

        Klient klient1 = new Klient("Jan", "Kowalski", "jan@example.com");
        Klient klient2 = new Klient("Anna", "Nowak", "anna@example.com");
        system.dodajKlienta(klient1);
        system.dodajKlienta(klient2);

        system.dokonajRezerwacji(klient1, koncert);
        system.dokonajRezerwacji(klient1, teatr);
        system.dokonajRezerwacji(klient2, koncert);

        System.out.println("Rezerwacje klienta " + klient1.getImie() + " " + klient1.getNazwisko() + ":");
        klient1.wyswietlRezerwacje();

        Wydarzenie koncertRef = system.znajdzWydarzenie("Koncert Symphony");
        System.out.println("\nZmiana ceny koncertu na 150.0 zł");
        koncertRef.setCena(150.0);

        System.out.println("\nRezerwacje klienta " + klient1.getImie() + " po zmianie ceny:");
        klient1.wyswietlRezerwacje();

        System.out.println("\nRezerwacje klienta " + klient2.getImie() + " po zmianie ceny:");
        klient2.wyswietlRezerwacje();

        System.out.println("\nKlient " + klient1.getImie() + " anuluje rezerwację na teatr:");
        klient1.anulujRezerwacje(teatr);
        klient1.wyswietlRezerwacje();

        System.out.println("\nDostępne miejsca na koncercie: " + koncert.getDostepneMiejsca() + " z " + koncert.getMaxLiczbaMiejsc());
    }
}
