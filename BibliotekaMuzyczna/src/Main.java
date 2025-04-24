import java.util.ArrayList;

class Playlista {
    private String nazwa;
    private ArrayList<String> utwory;

    public Playlista(String nazwa) {
        this.nazwa = nazwa;
        this.utwory = new ArrayList<>();
    }

    public String getNazwa() {
        return nazwa;
    }

    public ArrayList<String> getUtwory() {
        return utwory;
    }

    public void dodajUtwor(String utwor) {
        if (!utwory.contains(utwor)) {
            utwory.add(utwor);
        }
    }

    public void usunUtwor(String utwor) {
        utwory.remove(utwor);
    }

    public void wyswietl() {
        System.out.println("Playlista: " + nazwa);
        for (String utwor : utwory) {
            System.out.println("- " + utwor);
        }
    }
}

class BibliotekaMuzyczna {
    private ArrayList<String> utwory;
    private ArrayList<Playlista> playlisty;

    public BibliotekaMuzyczna() {
        utwory = new ArrayList<>();
        playlisty = new ArrayList<>();
    }


    public void dodajUtwor(String utwor) {
        if (!utwory.contains(utwor)) {
            utwory.add(utwor);
        }
    }

    public void usunUtwor(String utwor) {
        if (utwory.remove(utwor)) {
            for (Playlista playlista : playlisty) {
                playlista.usunUtwor(utwor);
            }
        }
    }

    public void wyswietlUtwory() {
        System.out.println("Lista utworów:");
        for (String utwor : utwory) {
            System.out.println("- " + utwor);
        }
    }

    public void wyszukajUtwory(String fraza) {
        System.out.println("Wyniki wyszukiwania dla \"" + fraza + "\":");
        for (String utwor : utwory) {
            if (utwor.toLowerCase().contains(fraza.toLowerCase())) {
                System.out.println("- " + utwor);
            }
        }
    }


    public void utworzPlayliste(String nazwa) {
        if (znajdzPlayliste(nazwa) == null) {
            playlisty.add(new Playlista(nazwa));
        }
    }

    private Playlista znajdzPlayliste(String nazwa) {
        for (Playlista playlista : playlisty) {
            if (playlista.getNazwa().equalsIgnoreCase(nazwa)) {
                return playlista;
            }
        }
        return null;
    }

    public void dodajUtworDoPlaylisty(String nazwaPlaylisty, String utwor) {
        Playlista playlista = znajdzPlayliste(nazwaPlaylisty);
        if (playlista != null && utwory.contains(utwor)) {
            playlista.dodajUtwor(utwor);
        }
    }

    public void wyswietlPlayliste(String nazwaPlaylisty) {
        Playlista playlista = znajdzPlayliste(nazwaPlaylisty);
        if (playlista != null) {
            playlista.wyswietl();
        } else {
            System.out.println("Nie znaleziono playlisty o nazwie: " + nazwaPlaylisty);
        }
    }

    public void wyswietlWszystkiePlaylisty() {
        System.out.println("Wszystkie playlisty:");
        for (Playlista playlista : playlisty) {
            playlista.wyswietl();
        }
    }


    public static void main(String[] args) {
        BibliotekaMuzyczna biblioteka = new BibliotekaMuzyczna();

        biblioteka.dodajUtwor("Imagine - John Lennon");
        biblioteka.dodajUtwor("Hey Jude - The Beatles");
        biblioteka.dodajUtwor("Bohemian Rhapsody - Queen");

        biblioteka.wyswietlUtwory();

        biblioteka.utworzPlayliste("Rock Classics");
        biblioteka.dodajUtworDoPlaylisty("Rock Classics", "Bohemian Rhapsody - Queen");
        biblioteka.dodajUtworDoPlaylisty("Rock Classics", "Hey Jude - The Beatles");

        biblioteka.utworzPlayliste("Peaceful Songs");
        biblioteka.dodajUtworDoPlaylisty("Peaceful Songs", "Imagine - John Lennon");

        biblioteka.wyswietlPlayliste("Rock Classics");
        biblioteka.wyswietlWszystkiePlaylisty();

        biblioteka.wyszukajUtwory("imagine");

        biblioteka.usunUtwor("Hey Jude - The Beatles");
        biblioteka.wyswietlWszystkiePlaylisty();
    }
}
