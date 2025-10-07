package Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author alfre
 */
@Entity
public class VideoJuego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private int puntaje;
    private String desarrolladora;
    
    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL)
    private Set<Logro> logros;
    
    @ManyToMany
    @JoinTable(name = "videjuego_jugador",
            joinColumns = @JoinColumn(name = "videojuego_id"),
            inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    private Set<Jugador> jugadores;

    public VideoJuego() {
    }

    public VideoJuego(Long id, String nombre, int puntaje, String desarrolladora, Set<Logro> logros, Set<Jugador> jugadores) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.desarrolladora = desarrolladora;
        this.logros = logros;
        this.jugadores = jugadores;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public Set<Logro> getLogros() {
        return logros;
    }

    public void setLogros(Set<Logro> logros) {
        this.logros = logros;
    }
    
    
}
