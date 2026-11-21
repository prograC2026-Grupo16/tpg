package tpgProgra2026.tablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import tpgProgra2026.TableroDestino.Posicion;

/** Dibuja la grilla del Tablero desde una foto del estado. Solo pinta: no conoce el modelo. */
class PanelTableroGrafico extends JPanel {
    private int celda = 18;   // lado de cada casilla: se recalcula en cada pintado según el tamaño disponible
    private EstadoSimulacion estado = null;

    PanelTableroGrafico() {
        setOpaque(false);
        setPreferredSize(new Dimension(300, 300));
        setMinimumSize(new Dimension(200, 200));
    }

    void mostrar(EstadoSimulacion e) {
        this.estado = e;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (estado == null || !estado.hayNave) {
            g2.setColor(EstiloTablero.TEXTO_SUAVE);
            g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
            g2.drawString("Creá una nave para ver el tablero", 40, getHeight() / 2);
            g2.dispose();
            return;
        }
        // la grilla ocupa todo el panel disponible (cualquier tamaño de ventana, tableros de 5x5 a 100x100)
        celda = Math.max(1, Math.min((getWidth() - 1) / estado.columnas, (getHeight() - 1) / estado.filas));
        g2.translate((getWidth() - celda * estado.columnas) / 2, (getHeight() - celda * estado.filas) / 2);
        for (int i = 0; i < estado.filas; i++) {          // x = fila, y = columna
            for (int j = 0; j < estado.columnas; j++) {
                int px = j * celda, py = i * celda;
                int m = celda >= 10 ? 3 : 1;   // margen interno de cada casilla
                g2.setColor(EstiloTablero.CAMPO);
                g2.fillRoundRect(px + 1, py + 1, celda - 2, celda - 2, 6, 6);
                String tipo = estado.elementos[i][j];
                if (tipo != null) {
                    g2.setColor(colorDe(tipo));
                    g2.fillOval(px + m, py + m, celda - 2 * m, celda - 2 * m);
                }
            }
        }
        marcar(g2, estado.base, EstiloTablero.OK, false);
        marcar(g2, estado.objetivo, EstiloTablero.DORADO, true);
        marcar(g2, estado.nave, EstiloTablero.ACENTO_HOVER, true);
        g2.dispose();
    }

    private void marcar(Graphics2D g2, Posicion p, Color c, boolean relleno) {
        if (p == null) {
            return;
        }
        int px = p.getY() * celda, py = p.getX() * celda;
        int m = celda >= 10 ? 3 : 1;
        g2.setColor(c);
        if (relleno) {
            g2.fillRoundRect(px + m, py + m, celda - 2 * m, celda - 2 * m, 8, 8);
        } else {
            g2.setStroke(new java.awt.BasicStroke(2f));
            g2.drawRoundRect(px + 1, py + 1, celda - 3, celda - 3, 6, 6);
        }
    }

    private static Color colorDe(String tipo) {
        String t = tipo.toLowerCase();
        if (t.contains("asteroide")) return new Color(170, 140, 110);
        if (t.contains("estaci")) return EstiloTablero.OK;
        if (t.contains("enemig")) return EstiloTablero.ENEMIGO;
        return EstiloTablero.TEXTO_SUAVE;
    }
}
