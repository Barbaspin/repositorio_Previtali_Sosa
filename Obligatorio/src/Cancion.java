import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

import java.util.Objects;

public class Cancion {

    private String id;
    private String nombre;
    private String artistas;
    private String rank;
    private String daily_movement;
    private String weekly_movement;
    private String pais;
    private String fecha;
    private String popularity;
    private String is_explicit;
    private String duration_ms;
    private String album_name;
    private String album_release_date;
    private String danceability;
    private String energy;
    private String key;
    private String loudness;
    private String mode;
    private String speechiness;
    private String acousticness;
    private String instrumentalness;
    private String liveness;
    private String valence;
    private String tempo;
    private String time_signature;


    public Cancion(MyLinkedListImpl<String> lista) {
        this.id = lista.get(0);
        this.nombre =lista.get(1);
        this.artistas = lista.get(2);
        this.rank = lista.get(3);
        this.daily_movement = lista.get(4);
        this.weekly_movement = lista.get(5);
        this.pais = lista.get(6);
        this.fecha = lista.get(7);
        this.popularity = lista.get(8);
        this.is_explicit = lista.get(9);
        this.duration_ms = lista.get(10);
        this.album_name = lista.get(11);
        this.album_release_date = lista.get(12);
        this.danceability = lista.get(13);
        this.energy = lista.get(14);
        this.key = lista.get(15);
        this.loudness = lista.get(16);
        this.mode = lista.get(17);
        this.speechiness = lista.get(18);
        this.acousticness = lista.get(19);
        this.instrumentalness = lista.get(20);
        this.liveness = lista.get(21);
        this.valence = lista.get(22);
        this.tempo = lista.get(23);
        this.time_signature = lista.get(24);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtistas() {
        return artistas;
    }

    public void setArtistas(String artistas) {
        this.artistas = artistas;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getDaily_movement() {
        return daily_movement;
    }

    public void setDaily_movement(String daily_movement) {
        this.daily_movement = daily_movement;
    }

    public String getWeekly_movement() {
        return weekly_movement;
    }

    public void setWeekly_movement(String weekly_movement) {
        this.weekly_movement = weekly_movement;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getIs_explicit() {
        return is_explicit;
    }

    public void setIs_explicit(String is_explicit) {
        this.is_explicit = is_explicit;
    }

    public String getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(String duration_ms) {
        this.duration_ms = duration_ms;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_release_date() {
        return album_release_date;
    }

    public void setAlbum_release_date(String album_release_date) {
        this.album_release_date = album_release_date;
    }

    public String getDanceability() {
        return danceability;
    }

    public void setDanceability(String danceability) {
        this.danceability = danceability;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLoudness() {
        return loudness;
    }

    public void setLoudness(String loudness) {
        this.loudness = loudness;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(String speechiness) {
        this.speechiness = speechiness;
    }

    public String getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(String acousticness) {
        this.acousticness = acousticness;
    }

    public String getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(String instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public String getLiveness() {
        return liveness;
    }

    public void setLiveness(String liveness) {
        this.liveness = liveness;
    }

    public String getValence() {
        return valence;
    }

    public void setValence(String valence) {
        this.valence = valence;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTime_signature() {
        return time_signature;
    }

    public void setTime_signature(String time_signature) {
        this.time_signature = time_signature;
    }
}
