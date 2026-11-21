package tpgProgra2026.tablero;

// import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 * Paleta y componentes del tablero. Son los mismos colores, fuentes y formas redondeadas de VistaLogin
 * (que los tiene como clases privadas y por eso no se pueden reutilizar sin modificarla).
 */
final class EstiloTablero {
    static final Color FONDO = new Color(238, 241, 246);
    static final Color TARJETA = new Color(26, 32, 45);
    static final Color CAMPO = new Color(44, 54, 74);
    static final Color ACENTO = new Color(90, 140, 255);
    static final Color ACENTO_HOVER = new Color(120, 163, 255);
    static final Color SECUNDARIO = new Color(58, 74, 105);
    static final Color SECUNDARIO_HOVER = new Color(76, 94, 128);
    static final Color TEXTO = new Color(255, 255, 255);
    static final Color TEXTO_SUAVE = new Color(150, 162, 186);
    static final Color ERROR = new Color(255, 120, 120);
    static final Color OK = new Color(110, 210, 150);
    static final Color ENEMIGO = new Color(230, 40, 40);     // naves enemigas: rojo
    static final Color DORADO = new Color(255, 196, 40);     // objetivo de la misión: dorado

    private EstiloTablero() { }

    static JLabel titulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        l.setForeground(TEXTO);
        return l;
    }

    static JLabel etiqueta(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        l.setForeground(TEXTO_SUAVE);
        return l;
    }

    static JTextField campo(int columnas) {
        JTextField c = new JTextField(columnas);
        c.setBackground(CAMPO);
        c.setForeground(TEXTO);
        c.setCaretColor(TEXTO);
        c.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        c.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        return c;
    }

    static <T> JComboBox<T> combo(T[] items) {
        JComboBox<T> c = new JComboBox<T>(items);
        c.setBackground(CAMPO);
        c.setForeground(TEXTO);
        c.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        return c;
    }

    static JTextArea areaTexto() {
        JTextArea a = new JTextArea();
        a.setEditable(false);
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setBackground(CAMPO);
        a.setForeground(TEXTO);
        a.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        a.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        return a;
    }

    /** Botón redondeado con hover, igual al de VistaLogin. */
    static class BotonRedondeado extends JButton {
        private final Color normal, hover;
        private boolean encima = false;

        BotonRedondeado(String texto, Color normal, Color hover) {
            super(texto);
            this.normal = normal;
            this.hover = hover;
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(TEXTO);
            setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(150, 34));
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseEntered(java.awt.event.MouseEvent e) { encima = true; repaint(); }
                @Override public void mouseExited(java.awt.event.MouseEvent e) { encima = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color c = !isEnabled() ? SECUNDARIO.darker() : (encima ? hover : normal);
            g2.setColor(c);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            g2.setColor(isEnabled() ? getForeground() : TEXTO_SUAVE);
            g2.setFont(getFont());
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(getText(), (getWidth() - fm.stringWidth(getText())) / 2,
                    (getHeight() - fm.getHeight()) / 2 + fm.getAscent());
            g2.dispose();
        }
    }

    /** Tarjeta oscura de esquinas redondeadas, igual a la de VistaLogin. */
    static class PanelRedondeado extends JPanel {
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

    /** Barra de progreso fina y redondeada (combustible, energía, desgaste). */
    static class Barra extends JPanel {
        private final Color color;
        private int valor = 0;
        private final int maximo;

        Barra(Color color, int maximo) {
            this.color = color;
            this.maximo = maximo;
            setOpaque(false);
            setPreferredSize(new Dimension(150, 12));
        }

        void setValor(int v) {
            valor = Math.max(0, Math.min(maximo, v));
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(CAMPO);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, Math.max(0, getWidth() * valor / maximo), getHeight(), 12, 12);
            g2.dispose();
        }
    }
}
