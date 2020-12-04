/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Equipos.Equipo;
import Equipos.EquipoController;
import Futbolista.Futbolista;
import Futbolista.FutbolistaController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jaime
 */
public class TestsUnitarios {
    FutbolistaController controladorFut = new FutbolistaController();
    EquipoController controladorEqui = new EquipoController();
    public TestsUnitarios() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void InsertarFutbolistaEnArrayDeFutbolistas (){
        int tamano_inicial;
        tamano_inicial = controladorFut.getFutbolistas().size();
        Futbolista fut = new Futbolista("123456A", "Jose Alfredo", "Cifuentes", "Soplamocos", 80);
        controladorFut.guardarFutbolistas(fut);
        assertEquals(tamano_inicial + 1, controladorFut.getFutbolistas().size());
    }
    @Test
    public void EliminarEquipoEnArrayDeEquipos(){
        int tamano_inicial;
        tamano_inicial = controladorEqui.getEquipos().size();
        Equipo equi = new Equipo("123456A", "Atletico Escupieras", "Escupidera", "05/11/2020", 80, "1234A");
        controladorEqui.guardarEquipos(equi);
        controladorEqui.getEquipos().remove(controladorEqui.getEquipos().size()-1);
        assertEquals(tamano_inicial, controladorEqui.getEquipos().size());
    }
    @Test
    public void ModificarNombreDeUnEquipoEnElArrayDeEquipos(){
        String nombre_inicial;
        Equipo equi = new Equipo("123456A", "Atletico Escupieras", "Escupidera", "05/11/2020", 80, "1234A");
        controladorEqui.guardarEquipos(equi);
        nombre_inicial = equi.getNombre_equipo();
        equi.setNombre_equipo("Er Chimeneas");
        assertNotSame(nombre_inicial, equi.getNombre_equipo());
    }
    @Test
    public void CompararTamañosDeLosArraysDeFutbolistaYDeEquipoCuandoAAmbosSeLeAñadeUnObjeto(){
        int tamano_array_equipos;
        int tamano_array_futbolistas;
        Equipo equi = new Equipo("123456A", "Atletico Escupieras", "Escupidera", "05/11/2020", 80, "1234A");
        controladorEqui.guardarEquipos(equi);
        tamano_array_equipos = controladorEqui.getEquipos().size();
        Futbolista fut = new Futbolista("123456A", "Octavio", "Goros", "El Pinar", 5);
        controladorFut.guardarFutbolistas(fut);
        tamano_array_futbolistas = controladorFut.getFutbolistas().size();
        assertEquals(tamano_array_futbolistas, tamano_array_equipos);
    }
}
