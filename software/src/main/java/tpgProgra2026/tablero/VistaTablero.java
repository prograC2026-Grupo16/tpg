package tpgProgra2026.tablero;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import tpgProgra2026.TableroDestino.Observador;
import tpgProgra2026.tablero.EstiloTablero.Barra;
import tpgProgra2026.tablero.EstiloTablero.BotonRedondeado;
import tpgProgra2026.tablero.EstiloTablero.PanelRedondeado;

/**
 * Vista principal de la simulación. Mismo estilo que VistaLogin: JPanel con fondo claro y tarjetas oscuras
 * redondeadas, setActionListener(...) para el controlador y mostrarError()/mostrarExito() para el feedback.
 *
 * Es Observador del ModeloTablero y del ModeloUniverso: cuando cambian, el modelo llama a actualizar()
 * y la vista se redibuja sola desde la última foto (EstadoSimulacion).
 */
public class VistaTablero extends JPanel implements Observador {

    public static final String CREAR_NAVE = "CREAR_NAVE";
    public static final String AGREGAR_TRIPULANTE = "AGREGAR_TRIPULANTE";
    public static final String INICIAR_MISION = "INICIAR_MISION";
    public static final String AGREGAR_NAVE_UNIVERSO = "AGREGAR_NAVE_UNIVERSO";
    public static final String AGREGAR_GENERADORA = "AGREGAR_GENERADORA";

    private final ModeloTablero modelo;
    private final ModeloUniverso universo;

    private JTextField textNombreNave;
    private JComboBox<String> comboTipoNave, comboCargo, comboOrigen, comboMision;
    private JTextField textAntiguedad, textFilas, textColumnas;
    private BotonRedondeado buttonCrear, buttonTripulante, buttonMision, buttonNaveUniv, buttonGeneradora;
    private JLabel labelMensaje, labelNave, labelMotor, labelGemas, labelUsuario, labelPlataforma;
    private Barra barraCombustible, barraEnergia, barraDesgaste;
    private PanelTableroGrafico tableroGrafico;
    private boolean avisoDestruccionMostrado = false;
    private DefaultTableModel tablaTripulantes;
    private JTextArea areaBitacora, areaInformes, areaVisor;

    private boolean refrescoPendiente = false;

    public VistaTablero(ModeloTablero modelo, ModeloUniverso universo, String usuario) {
        this.modelo = modelo;
        this.universo = universo;
        initComponents();
        labelUsuario.setText("Sesión: " + usuario);
        modelo.agregarObservador(this);
        universo.agregarObservador(this);
        redibujar();
    }

    private void initComponents() {
        setBackground(EstiloTablero.FONDO);
        setPreferredSize(new Dimension(1000, 660));
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Encabezado y pie siempre visibles; el resto va en pestañas con barra de desplazamiento propia,
        // así se ve toda la información aunque la ventana sea chica.
        add(armarEncabezado(), BorderLayout.NORTH);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        pestanas.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        pestanas.setBackground(EstiloTablero.FONDO);
        pestanas.addTab("Nave y tripulación", conScroll(armarPestanaNave()));
        pestanas.addTab("Misión y tablero", conScroll(armarPestanaMision()));
        pestanas.addTab("Visor Recóndito", conScroll(armarPestanaVisor()));
        add(pestanas, BorderLayout.CENTER);

        add(armarPie(), BorderLayout.SOUTH);
    }

    /** Cada pestaña se envuelve en un JScrollPane: si no entra, aparecen las barras en vez de cortarse. */
    private JScrollPane conScroll(JPanel contenido) {
        contenido.setOpaque(false);
        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(EstiloTablero.FONDO);
        fondo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        fondo.add(contenido, BorderLayout.CENTER);
        JScrollPane sp = new JScrollPane(fondo);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.getViewport().setBackground(EstiloTablero.FONDO);
        sp.getVerticalScrollBar().setUnitIncrement(16);
        sp.getHorizontalScrollBar().setUnitIncrement(16);
        return sp;
    }

    private JPanel tarjeta() {
        PanelRedondeado t = new PanelRedondeado(EstiloTablero.TARJETA, 30);
        t.setLayout(new GridBagLayout());
        t.setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));
        return t;
    }

    private GridBagConstraints fila(int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = y;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(3, 0, 3, 0);
        return c;
    }

    // ---------- encabezado y pie (siempre visibles) ----------
    private JPanel armarEncabezado() {
        PanelRedondeado t = new PanelRedondeado(EstiloTablero.TARJETA, 30);
        t.setLayout(new BorderLayout(16, 0));
        t.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        labelNave = EstiloTablero.titulo("Sin nave");
        labelMotor = EstiloTablero.etiqueta("Motor warp: -");
        labelUsuario = EstiloTablero.etiqueta("Sesión: -");
        t.add(labelNave, BorderLayout.WEST);
        t.add(labelMotor, BorderLayout.CENTER);
        t.add(labelUsuario, BorderLayout.EAST);
        return t;
    }

    private JPanel armarPie() {
        PanelRedondeado t = new PanelRedondeado(EstiloTablero.TARJETA, 30);
        t.setLayout(new BorderLayout());
        t.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        labelMensaje = new JLabel(" ", JLabel.CENTER);
        labelMensaje.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        labelMensaje.setForeground(EstiloTablero.ERROR);
        t.add(labelMensaje, BorderLayout.CENTER);
        return t;
    }

    // ---------- pestaña 1: creación de la nave y tripulantes ----------
    private JPanel armarPestanaNave() {
        JPanel pagina = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;
        g.weighty = 1;
        g.insets = new Insets(0, 0, 0, 10);
        g.gridx = 0;
        g.weightx = 0.4;
        pagina.add(armarTarjetaCreacion(), g);
        g.gridx = 1;
        g.weightx = 0.6;
        g.insets = new Insets(0, 0, 0, 0);
        pagina.add(armarTarjetaTripulantes(), g);
        return pagina;
    }

    private JPanel armarTarjetaCreacion() {
        JPanel t = tarjeta();
        int y = 0;
        t.add(EstiloTablero.titulo("Creación de nave"), fila(y++));
        textNombreNave = EstiloTablero.campo(12);
        comboTipoNave = EstiloTablero.combo(ModeloTablero.TIPOS_NAVE);
        t.add(EstiloTablero.etiqueta("Nombre"), fila(y++));
        t.add(textNombreNave, fila(y++));
        t.add(EstiloTablero.etiqueta("Tipo"), fila(y++));
        t.add(comboTipoNave, fila(y++));
        textFilas = EstiloTablero.campo(5);
        textColumnas = EstiloTablero.campo(5);
        textFilas.setText("20");
        textColumnas.setText("20");
        t.add(EstiloTablero.etiqueta("Tamaño del tablero: filas (5-100)"), fila(y++));
        t.add(textFilas, fila(y++));
        t.add(EstiloTablero.etiqueta("Tamaño del tablero: columnas (5-100)"), fila(y++));
        t.add(textColumnas, fila(y++));
        t.add(EstiloTablero.etiqueta("El 20% de las casillas se llena con asteroides, estaciones y naves enemigas."), fila(y++));
        buttonCrear = new BotonRedondeado("Crear nave", EstiloTablero.ACENTO, EstiloTablero.ACENTO_HOVER);
        buttonCrear.setActionCommand(CREAR_NAVE);
        t.add(buttonCrear, fila(y++));
        GridBagConstraints relleno = fila(y);
        relleno.weighty = 1;
        t.add(new JPanel() {{ setOpaque(false); }}, relleno);
        return t;
    }

    private JPanel armarTarjetaTripulantes() {
        JPanel t = tarjeta();
        int y = 0;
        t.add(EstiloTablero.titulo("Tripulantes"), fila(y++));
        t.add(EstiloTablero.etiqueta("Para despegar hace falta un Capitán (y como máximo uno)."), fila(y++));
        comboCargo = EstiloTablero.combo(ModeloTablero.CARGOS);
        comboOrigen = EstiloTablero.combo(ModeloTablero.ORIGENES);
        textAntiguedad = EstiloTablero.campo(5);
        textAntiguedad.setText("1");
        t.add(EstiloTablero.etiqueta("Cargo"), fila(y++));
        t.add(comboCargo, fila(y++));
        t.add(EstiloTablero.etiqueta("Origen"), fila(y++));
        t.add(comboOrigen, fila(y++));
        t.add(EstiloTablero.etiqueta("Antigüedad (años)"), fila(y++));
        t.add(textAntiguedad, fila(y++));
        buttonTripulante = new BotonRedondeado("Agregar tripulante", EstiloTablero.SECUNDARIO, EstiloTablero.SECUNDARIO_HOVER);
        buttonTripulante.setActionCommand(AGREGAR_TRIPULANTE);
        t.add(buttonTripulante, fila(y++));

        tablaTripulantes = new DefaultTableModel(new Object[]{"Id", "Cargo", "Origen", "Años", "Sueldo"}, 0) {
            private static final long serialVersionUID = 1L;
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(tablaTripulantes);
        tabla.setBackground(EstiloTablero.CAMPO);
        tabla.setForeground(EstiloTablero.TEXTO);
        tabla.setGridColor(EstiloTablero.TARJETA);
        tabla.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        tabla.getTableHeader().setBackground(EstiloTablero.SECUNDARIO);
        tabla.getTableHeader().setForeground(EstiloTablero.TEXTO);
        JScrollPane sp = new JScrollPane(tabla);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.getViewport().setBackground(EstiloTablero.CAMPO);
        sp.setPreferredSize(new Dimension(300, 180));
        t.add(sp, grande(y++));
        return t;
    }

    // ---------- pestaña 2: control de la misión, recursos y tablero ----------
    private JPanel armarTarjetaMision() {
        JPanel t = tarjeta();
        int y = 0;
        t.add(EstiloTablero.titulo("Control de misión"), fila(y++));
        comboMision = EstiloTablero.combo(ModeloTablero.MISIONES);
        buttonMision = new BotonRedondeado("Despegar", EstiloTablero.ACENTO, EstiloTablero.ACENTO_HOVER);
        buttonMision.setActionCommand(INICIAR_MISION);
        JPanel control = new JPanel(new java.awt.GridLayout(1, 2, 10, 0));
        control.setOpaque(false);
        control.add(comboMision);
        control.add(buttonMision);
        t.add(control, fila(y++));

        barraCombustible = new Barra(EstiloTablero.ACENTO, 100);
        barraEnergia = new Barra(EstiloTablero.OK, 100);
        barraDesgaste = new Barra(EstiloTablero.ERROR, 100);
        JPanel barras = new JPanel(new java.awt.GridLayout(2, 3, 12, 2));
        barras.setOpaque(false);
        barras.add(EstiloTablero.etiqueta("Combustible"));
        barras.add(EstiloTablero.etiqueta("Energía"));
        barras.add(EstiloTablero.etiqueta("Desgaste"));
        barras.add(barraCombustible);
        barras.add(barraEnergia);
        barras.add(barraDesgaste);
        t.add(barras, fila(y++));
        labelGemas = EstiloTablero.etiqueta("Gemas: 0");
        t.add(labelGemas, fila(y++));

        tableroGrafico = new PanelTableroGrafico();
        t.add(tableroGrafico, grande(y++));
        t.add(EstiloTablero.etiqueta("<html>Azul: nave · Verde: base/estación · Dorado: destino<br>"
                + "Rojo: nave enemiga · Marrón: asteroide</html>"), fila(y++));
        return t;
    }

    // ---------- pestaña 2: misión y tablero, con bitácora e informes al costado ----------
    private JPanel armarPestanaMision() {
        JPanel pagina = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;
        g.weighty = 1;
        g.gridx = 0;
        g.weightx = 0.6;
        g.insets = new Insets(0, 0, 0, 10);
        pagina.add(armarTarjetaMision(), g);
        g.gridx = 1;
        g.weightx = 0.4;
        g.insets = new Insets(0, 0, 0, 0);
        pagina.add(armarColumnaRegistros(), g);
        return pagina;
    }

    /** Bitácora arriba e informes de misión abajo, apilados al costado del tablero. */
    private JPanel armarColumnaRegistros() {
        JPanel columna = new JPanel(new java.awt.GridLayout(2, 1, 0, 10));
        columna.setOpaque(false);
        JPanel a = tarjeta();
        a.add(EstiloTablero.titulo("Bitácora"), fila(0));
        areaBitacora = EstiloTablero.areaTexto();
        a.add(scroll(areaBitacora, 200), grande(1));
        JPanel b = tarjeta();
        b.add(EstiloTablero.titulo("Informes de misión"), fila(0));
        areaInformes = EstiloTablero.areaTexto();
        b.add(scroll(areaInformes, 200), grande(1));
        columna.add(a);
        columna.add(b);
        return columna;
    }

    // ---------- pestaña 3: visor recóndito ----------
    private JPanel armarPestanaVisor() {
        JPanel t = tarjeta();
        int y = 0;
        t.add(EstiloTablero.titulo("Visor Recóndito"), fila(y++));
        labelPlataforma = EstiloTablero.etiqueta("Baterías: 0/" + universo.getCapacidad());
        t.add(labelPlataforma, fila(y++));
        buttonNaveUniv = new BotonRedondeado("+ Nave", EstiloTablero.SECUNDARIO, EstiloTablero.SECUNDARIO_HOVER);
        buttonNaveUniv.setActionCommand(AGREGAR_NAVE_UNIVERSO);
        buttonGeneradora = new BotonRedondeado("+ Generadora", EstiloTablero.SECUNDARIO, EstiloTablero.SECUNDARIO_HOVER);
        buttonGeneradora.setActionCommand(AGREGAR_GENERADORA);
        JPanel botones = new JPanel(new java.awt.GridLayout(1, 2, 10, 0));
        botones.setOpaque(false);
        botones.add(buttonNaveUniv);
        botones.add(buttonGeneradora);
        t.add(botones, fila(y++));
        areaVisor = EstiloTablero.areaTexto();
        t.add(scroll(areaVisor, 300), grande(y++));
        return t;
    }

    private JScrollPane scroll(JTextArea a, int alto) {
        JScrollPane sp = new JScrollPane(a);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.setPreferredSize(new Dimension(300, alto));
        return sp;
    }

    private GridBagConstraints grande(int y) {
        GridBagConstraints c = fila(y);
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        return c;
    }

    // ---------- API de la vista (mismo estilo que VistaLogin) ----------

    public void setActionListener(ActionListener l) {
        buttonCrear.addActionListener(l);
        buttonTripulante.addActionListener(l);
        buttonMision.addActionListener(l);
        buttonNaveUniv.addActionListener(l);
        buttonGeneradora.addActionListener(l);
    }

    public String getNombreNave() { return textNombreNave.getText(); }
    public String getTipoNave() { return (String) comboTipoNave.getSelectedItem(); }
    public String getCargo() { return (String) comboCargo.getSelectedItem(); }
    public String getOrigen() { return (String) comboOrigen.getSelectedItem(); }
    public String getFilasTexto() { return textFilas.getText(); }
    public String getColumnasTexto() { return textColumnas.getText(); }
    public String getAntiguedadTexto() { return textAntiguedad.getText(); }
    public String getMision() { return (String) comboMision.getSelectedItem(); }

    public void limpiarCampos() {
        textNombreNave.setText("");
    }

    public void mostrarError(String mensaje) {
        labelMensaje.setForeground(EstiloTablero.ERROR);
        labelMensaje.setText(envolver(mensaje));
    }

    public void mostrarExito(String mensaje) {
        labelMensaje.setForeground(EstiloTablero.OK);
        labelMensaje.setText(envolver(mensaje));
    }

    /** Parte los mensajes largos en varias líneas para que entren en la tarjeta. */
    private static String envolver(String mensaje) {
        return "<html><body style='width:480px; text-align:center'>" + mensaje + "</body></html>";
    }

    // ---------- Observador ----------

    /**
     * Lo llaman los modelos, a veces desde el hilo de la misión o de los hilos del universo.
     * Se pasa al hilo de Swing y se agrupan los avisos: si llegan varios seguidos se dibuja una sola vez.
     */
    @Override
    public void actualizar() {
        synchronized (this) {
            if (refrescoPendiente) {
                return;
            }
            refrescoPendiente = true;
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                synchronized (VistaTablero.this) {
                    refrescoPendiente = false;
                }
                redibujar();
            }
        });
    }

    private void redibujar() {
        EstadoSimulacion e = modelo.getEstado();
        labelNave.setText(e.hayNave ? e.nombreNave + " (" + e.tipoNave + ")" + (e.viva ? "" : "  — DESTRUIDA")
                : "Sin nave");
        avisarSiSeDestruyo(e);
        labelMotor.setText("Motor warp: " + (e.hayNave ? e.estadoMotor : "-")
                + (e.misionEnCurso ? "   ·   misión en curso" : ""));
        barraCombustible.setValor(e.combustible);
        barraEnergia.setValor(e.energia);
        barraDesgaste.setValor(e.desgaste);
        labelGemas.setText("Gemas: " + e.gemas + "   ·   Combustible " + e.combustible + "  Energía " + e.energia
                + "  Desgaste " + e.desgaste);
        tableroGrafico.mostrar(e);

        tablaTripulantes.setRowCount(0);
        for (EstadoSimulacion.FilaTripulante f : e.tripulantes) {
            tablaTripulantes.addRow(new Object[]{f.id, f.cargo, f.origen.isEmpty() ? "-" : f.origen, f.antiguedad,
                    (long) f.sueldo});
        }
        buttonMision.setEnabled(e.puedeIniciarMision());
        buttonCrear.setEnabled(!e.misionEnCurso);
        buttonTripulante.setEnabled(e.hayNave && !e.misionEnCurso);

        poner(areaBitacora, e.bitacora);
        poner(areaInformes, e.informes);
        poner(areaVisor, universo.getEventos());
        labelPlataforma.setText("Baterías: " + universo.getBaterias() + "/" + universo.getCapacidad()
                + "   ·   naves " + universo.getCantidadNaves() + "   ·   generadoras " + universo.getCantidadGeneradoras());
    }

    /** Avisa una sola vez (por nave) que la nave fue destruida; la causa queda en la bitácora. */
    private void avisarSiSeDestruyo(EstadoSimulacion e) {
        if (e.hayNave && !e.viva) {
            if (!avisoDestruccionMostrado) {
                avisoDestruccionMostrado = true;
                mostrarError("¡La nave fue destruida! Mirá la bitácora para ver la causa.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(VistaTablero.this,
                                "La nave fue destruida.\nEn la bitácora está la causa. Creá una nave nueva para seguir.",
                                "Nave destruida", JOptionPane.WARNING_MESSAGE);
                    }
                });
            }
        } else {
            avisoDestruccionMostrado = false;
        }
    }

    private void poner(JTextArea area, java.util.List<String> lineas) {
        StringBuilder sb = new StringBuilder();
        for (String l : lineas) {
            sb.append(l).append('\n');
        }
        area.setText(sb.toString());
        area.setCaretPosition(area.getDocument().getLength());
    }
}
