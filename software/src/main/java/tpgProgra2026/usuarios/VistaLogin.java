package tpgProgra2026.usuarios;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VistaLogin extends JPanel {
    // Paleta
    private static final Color FONDO = new Color(238, 241, 246);
    private static final Color TARJETA = new Color(26, 32, 45);
    private static final Color CAMPO = new Color(44, 54, 74);
    private static final Color ACENTO = new Color(90, 140, 255);
    private static final Color ACENTO_HOVER = new Color(120, 163, 255);
    private static final Color SECUNDARIO = new Color(58, 74, 105);
    private static final Color SECUNDARIO_HOVER = new Color(76, 94, 128);
    private static final Color TEXTO = new Color(255, 255, 255);
    private static final Color TEXTO_SUAVE = new Color(150, 162, 186);
    private static final Color ERROR = new Color(255, 120, 120);
    private static final Color OK = new Color(110, 210, 150);

    private CampoTexto textNombre;
    private CampoContra textContra;
    private JLabel labelMensaje;
    private BotonRedondeado buttonIngreso;
    private BotonRedondeado buttonCancelar;
    private BotonRedondeado buttonRegistrar;

    public VistaLogin() {
        initComponents();
    }

    private void initComponents() {
        setBackground(FONDO);
        setPreferredSize(new Dimension(429, 499));
        setLayout(new GridBagLayout()); // centra la tarjeta

        PanelRedondeado tarjeta = new PanelRedondeado(TARJETA, 30);
        tarjeta.setLayout(new GridBagLayout());
        tarjeta.setPreferredSize(new Dimension(320, 420));

        JLabel titulo = new JLabel("Bienvenido", JLabel.CENTER);
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        titulo.setForeground(TEXTO);

        JLabel subtitulo = new JLabel("Ingresá o creá tu cuenta", JLabel.CENTER);
        subtitulo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        subtitulo.setForeground(TEXTO_SUAVE);

        textNombre = new CampoTexto("Nombre de usuario");
        textContra = new CampoContra("Contraseña");

        labelMensaje = new JLabel(" ", JLabel.CENTER);
        labelMensaje.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        labelMensaje.setForeground(ERROR);

        buttonIngreso = new BotonRedondeado("INGRESAR", ACENTO, ACENTO_HOVER);
        buttonCancelar = new BotonRedondeado("CANCELAR", SECUNDARIO, SECUNDARIO_HOVER);
        buttonRegistrar = new BotonRedondeado("REGISTRAR", SECUNDARIO, SECUNDARIO_HOVER);
        buttonIngreso.setActionCommand("INGRESAR");
        buttonCancelar.setActionCommand("CANCELAR");
        buttonRegistrar.setActionCommand("REGISTRAR");

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        c.gridy = 0;
        c.insets = new Insets(10, 30, 2, 30);
        tarjeta.add(titulo, c);

        c.gridy = 1;
        c.insets = new Insets(0, 30, 28, 30);
        tarjeta.add(subtitulo, c);

        c.gridy = 2;
        c.insets = new Insets(0, 30, 14, 30);
        tarjeta.add(textNombre, c);

        c.gridy = 3;
        c.insets = new Insets(0, 30, 6, 30);
        tarjeta.add(textContra, c);

        c.gridy = 4;
        c.insets = new Insets(0, 30, 10, 30);
        tarjeta.add(labelMensaje, c);

        c.gridy = 5;
        c.insets = new Insets(0, 30, 12, 30);
        tarjeta.add(buttonIngreso, c);

        c.gridy = 6;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(0, 30, 0, 6);
        tarjeta.add(buttonCancelar, c);

        c.gridx = 1;
        c.insets = new Insets(0, 6, 0, 30);
        tarjeta.add(buttonRegistrar, c);

        add(tarjeta, new GridBagConstraints());
    }

    // ---------- API pública (la usa el controlador) ----------

    public String getNombre() {
        return textNombre.getText().trim();
    }

    public String getContra() {
        return new String(textContra.getPassword());
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public void setActionListener(ActionListener a) {
        buttonIngreso.addActionListener(a);
        buttonCancelar.addActionListener(a);
        buttonRegistrar.addActionListener(a);
    }

    public void limpiarCampos() {
        textNombre.setText("");
        textContra.setText("");
        labelMensaje.setText(" ");
    }

    public void mostrarError(String mensaje) {
        labelMensaje.setForeground(ERROR);
        labelMensaje.setText(mensaje);
    }

    public void mostrarExito(String mensaje) {
        labelMensaje.setForeground(OK);
        labelMensaje.setText(mensaje);
    }

    // ---------- Componentes personalizados ----------

    private static void pintarCampo(Graphics g, JComponent comp) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(CAMPO);
        g2.fillRoundRect(0, 0, comp.getWidth(), comp.getHeight(), 16, 16);
        if (comp.hasFocus()) {
            g2.setColor(ACENTO);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRoundRect(1, 1, comp.getWidth() - 3, comp.getHeight() - 3, 16, 16);
        }
        g2.dispose();
    }

    private static void pintarPlaceholder(Graphics g, JComponent comp, String texto) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(TEXTO_SUAVE);
        g2.setFont(comp.getFont());
        FontMetrics fm = g2.getFontMetrics();
        int x = comp.getInsets().left;
        int y = (comp.getHeight() + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(texto, x, y);
        g2.dispose();
    }

    private static class CampoTexto extends JTextField {
        private final String placeholder;

        CampoTexto(String placeholder) {
            this.placeholder = placeholder;
            setOpaque(false);
            setForeground(TEXTO);
            setCaretColor(TEXTO);
            setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            setBorder(BorderFactory.createEmptyBorder(11, 16, 11, 16));
        }

        @Override
        protected void paintComponent(Graphics g) {
            pintarCampo(g, this);
            super.paintComponent(g);
            if (getText().isEmpty()) {
                pintarPlaceholder(g, this, placeholder);
            }
        }
    }

    private static class CampoContra extends JPasswordField {
        private final String placeholder;

        CampoContra(String placeholder) {
            this.placeholder = placeholder;
            setOpaque(false);
            setForeground(TEXTO);
            setCaretColor(TEXTO);
            setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            setBorder(BorderFactory.createEmptyBorder(11, 16, 11, 16));
        }

        @Override
        protected void paintComponent(Graphics g) {
            pintarCampo(g, this);
            super.paintComponent(g);
            if (getPassword().length == 0) {
                pintarPlaceholder(g, this, placeholder);
            }
        }
    }

    private static class BotonRedondeado extends JButton {
        private final Color normal;
        private final Color hover;

        BotonRedondeado(String texto, Color normal, Color hover) {
            super(texto);
            this.normal = normal;
            this.hover = hover;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(TEXTO);
            setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(BorderFactory.createEmptyBorder(11, 12, 11, 12));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isRollover() ? hover : normal);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class PanelRedondeado extends JPanel {
        private final Color color;
        private final int radio;

        PanelRedondeado(Color color, int radio) {
            this.color = color;
            this.radio = radio;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}