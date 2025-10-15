package Interfaces;

import Entity.Direccion;
import java.util.List;

/**
 *
 * @author alfre
 */
public interface IDireccionDAO {

    // CRUD Basico
    boolean agregar(Direccion direccion);
    boolean editar(Direccion direccion);
    boolean eliminar(Long id);
    Direccion buscarPorId(Long id);
    List<Direccion> obtenerTodas();
}