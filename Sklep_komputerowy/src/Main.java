import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SklepKomputerowy sklep = new SklepKomputerowy();


        Produkt laptop = new Produkt(1, "Laptop HP", "Laptop", 3500.00, 10);
        Produkt mysz = new Produkt(2, "Mysz Logitech", "Mysz", 150.00, 25);
        Produkt monitor = new Produkt(3, "Monitor Samsung", "Monitor", 800.00, 15);


        sklep.dodajProdukt(laptop);
        sklep.dodajProdukt(mysz);
        sklep.dodajProdukt(monitor);


        Klient klient1 = new Klient(1, "Jan", "Kowalski", "jan.kowalski@email.com", true);
        Klient klient2 = new Klient(2, "Anna", "Nowak", "anna.nowak@email.com", false);


        sklep.dodajKlienta(klient1);
        sklep.dodajKlienta(klient2);

        List<Produkt> produkty1 = new ArrayList<>();
        List<Integer> ilosci1 = new ArrayList<>();
        produkty1.add(laptop);
        produkty1.add(mysz);
        ilosci1.add(1);
        ilosci1.add(2);
        sklep.utworzZamowienie(klient1, produkty1, ilosci1, "2025-04-01");

        List<Produkt> produkty2 = new ArrayList<>();
        List<Integer> ilosci2 = new ArrayList<>();
        produkty2.add(monitor);
        ilosci2.add(1);
        sklep.utworzZamowienie(klient2, produkty2, ilosci2, "2025-04-02");

        sklep.wyswietlZamowieniaKlienta(1);
        sklep.wyswietlZamowieniaKlienta(2);
    }

    static class Produkt {
        private int id;
        private String nazwa;
        private String kategoria;
        private double cena;
        private int iloscWMagazynie;

        public Produkt(int id, String nazwa, String kategoria, double cena, int iloscWMagazynie) {
            this.id = id;
            this.nazwa = nazwa;
            this.kategoria = kategoria;
            this.cena = cena;
            this.iloscWMagazynie = iloscWMagazynie;
        }

        public void wyswietlInformacje() {
            System.out.println("ID: " + id + ", Nazwa: " + nazwa + ", Kategoria: " + kategoria + ", Cena: " + cena + ", Ilość: " + iloscWMagazynie);
        }

        public int getId() { return id; }
        public String getNazwa() { return nazwa; }
        public String getKategoria() { return kategoria; }
        public double getCena() { return cena; }
        public int getIloscWMagazynie() { return iloscWMagazynie; }
        public void setIloscWMagazynie(int ilosc) { this.iloscWMagazynie = ilosc; }
    }

    static class Klient {
        private int id;
        private String imie;
        private String nazwisko;
        private String email;
        private boolean czyStaly;

        public Klient(int id, String imie, String nazwisko, String email, boolean czyStaly) {
            this.id = id;
            this.imie = imie;
            this.nazwisko = nazwisko;
            this.email = email;
            this.czyStaly = czyStaly;
        }

        public void wyswietlInformacje() {
            System.out.println("ID: " + id + ", Imię: " + imie + ", Nazwisko: " + nazwisko + ", Email: " + email + ", Stały klient: " + czyStaly);
        }

        public boolean isCzyStaly() { return czyStaly; }
        public int getId() { return id; }
    }

    static class Zamowienie {
        private int id;
        private Klient klient;
        private List<Produkt> produkty;
        private List<Integer> ilosci;
        private String dataZamowienia;
        private String status;

        public Zamowienie(int id, Klient klient, List<Produkt> produkty, List<Integer> ilosci, String dataZamowienia, String status) {
            this.id = id;
            this.klient = klient;
            this.produkty = produkty;
            this.ilosci = ilosci;
            this.dataZamowienia = dataZamowienia;
            this.status = status;
        }

        public double obliczWartoscZamowienia() {
            double suma = 0;
            for (int i = 0; i < produkty.size(); i++) {
                suma += produkty.get(i).getCena() * ilosci.get(i);
            }
            if (klient.isCzyStaly()) {
                suma *= 0.9;
            }
            return suma;
        }

        public void wyswietlSzczegoly() {
            System.out.println("Zamówienie ID: " + id + ", Data: " + dataZamowienia + ", Status: " + status);
            for (int i = 0; i < produkty.size(); i++) {
                System.out.println(produkty.get(i).getNazwa() + " x" + ilosci.get(i));
            }
            System.out.println("Łączna wartość: " + obliczWartoscZamowienia());
        }

        public List<Produkt> getProdukty() {
            return produkty;
        }

        public List<Integer> getIlosci() {
            return ilosci;
        }
    }

    static class SklepKomputerowy {
        private List<Produkt> produkty = new ArrayList<>();
        private List<Klient> klienci = new ArrayList<>();
        private List<Zamowienie> zamowienia = new ArrayList<>();

        public void dodajProdukt(Produkt produkt) {
            produkty.add(produkt);
        }

        public void dodajKlienta(Klient klient) {
            klienci.add(klient);
        }

        public void utworzZamowienie(Klient klient, List<Produkt> produkty, List<Integer> ilosci, String data) {
            Zamowienie zamowienie = new Zamowienie(zamowienia.size() + 1, klient, produkty, ilosci, data, "Nowe");
            zamowienia.add(zamowienie);
            aktualizujStanMagazynowy(zamowienie);
        }

        public void aktualizujStanMagazynowy(Zamowienie zamowienie) {
            for (int i = 0; i < zamowienie.getProdukty().size(); i++) {
                Produkt produkt = zamowienie.getProdukty().get(i);
                int nowaIlosc = produkt.getIloscWMagazynie() - zamowienie.getIlosci().get(i);
                produkt.setIloscWMagazynie(nowaIlosc);
            }
        }

        public void zmienStatusZamowienia(int idZamowienia, String nowyStatus) {
            for (Zamowienie zamowienie : zamowienia) {
                if (zamowienie.id == idZamowienia) {
                    zamowienie.status = nowyStatus;
                }
            }
        }

        public void wyswietlProduktyWKategorii(String kategoria) {
            for (Produkt produkt : produkty) {
                if (produkt.getKategoria().equalsIgnoreCase(kategoria)) {
                    produkt.wyswietlInformacje();
                }
            }
        }

        public void wyswietlZamowieniaKlienta(int idKlienta) {
            for (Zamowienie zamowienie : zamowienia) {
                if (zamowienie.klient.getId() == idKlienta) {
                    zamowienie.wyswietlSzczegoly();
                }
            }
        }
    }
}