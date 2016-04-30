package otros;

import java.util.ArrayList;
import java.util.List;
import modelo.Producto;
import modelo.Promocion;

public class PromEstatica {
    public static List<Producto> LI = new ArrayList<>();
    public static String NOMBRE;
    public static String DESCUENTO;
    
    //Parte de la venta
    public static String FONO_CLIENTE;
    public static boolean ESTA_FONO = false;
    public static List<Producto> LIPRO = new ArrayList<>();
    public static List<Promocion> LIPP = new ArrayList<>();
}
