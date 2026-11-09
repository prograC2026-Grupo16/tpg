
package tpgProgra2026.usuarios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class VistaLogin
extends JPanel {
    private JButton buttonCancelar;
    private JButton buttonIngreso;
    private JLabel labelIngresar;
    private JPanel panelIngresar;
    private JPanel panelLogin;
    private JPanel panelPrincipal;
    private JPasswordField textContra;
    private JTextField textNombre;

    public VistaLogin() {
        this.initComponents();
    }

    private void initComponents() {
        this.panelPrincipal = new JPanel();
        this.panelLogin = new JPanel();
        this.panelIngresar = new JPanel();
        this.labelIngresar = new JLabel();
        this.textNombre = new JTextField();
        this.textContra = new JPasswordField();
        this.buttonIngreso = new JButton();
        this.buttonCancelar = new JButton();
        this.setBackground(new Color(255, 255, 255));
        this.panelPrincipal.setBackground(new Color(255, 255, 255));
        this.panelPrincipal.setPreferredSize(new Dimension(429, 499));
        this.panelLogin.setBackground(new Color(26, 32, 45));
        this.panelLogin.setPreferredSize(new Dimension(200, 200));
        this.panelIngresar.setBackground(new Color(58, 74, 105));
        this.labelIngresar.setForeground(new Color(255, 255, 255));
        this.labelIngresar.setHorizontalAlignment(0);
        this.labelIngresar.setText("INGRESAR");
        GroupLayout panelIngresarLayout = new GroupLayout(this.panelIngresar);
        this.panelIngresar.setLayout(panelIngresarLayout);
        panelIngresarLayout.setHorizontalGroup(panelIngresarLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelIngresarLayout.createSequentialGroup().addGap(89, 89, 89).addComponent(this.labelIngresar, -2, 100, -2).addContainerGap(-1, Short.MAX_VALUE)));
        panelIngresarLayout.setVerticalGroup(panelIngresarLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, panelIngresarLayout.createSequentialGroup().addContainerGap(25, Short.MAX_VALUE).addComponent(this.labelIngresar, -2, 36, -2).addGap(22, 22, 22)));
        this.textNombre.setBackground(new Color(62, 74, 99));
        this.textNombre.setForeground(new Color(255, 255, 255));
        this.textNombre.setText("Escribe tu nombre");
        this.textNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                textNombreFocusGained(evt);
            }
            @Override
            public void focusLost(FocusEvent evt) {
                textNombreFocusLost(evt);
            }
        });
        this.textNombre.addActionListener(this::textNombreActionPerformed);
        this.textContra.setBackground(new Color(62, 74, 99));
        this.textContra.setForeground(new Color(255, 255, 255));
        this.textContra.setText("password");
        this.textContra.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                textContraFocusGained(evt);
            }
            @Override
            public void focusLost(FocusEvent evt) {
                textContraFocusLost(evt);
            }
        });
        this.buttonIngreso.setBackground(new Color(90, 124, 144));
        this.buttonIngreso.setForeground(new Color(255, 255, 255));
        this.buttonIngreso.setText("INGRESAR");
        this.buttonIngreso.addActionListener(this::buttonIngresoActionPerformed);
        this.buttonCancelar.setBackground(new Color(90, 124, 144));
        this.buttonCancelar.setForeground(new Color(255, 255, 255));
        this.buttonCancelar.setText("CANCELAR");
        GroupLayout panelLoginLayout = new GroupLayout(this.panelLogin);
        this.panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.panelIngresar, GroupLayout.Alignment.TRAILING, -1, -1, Short.MAX_VALUE).addGroup(GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup().addContainerGap(40, Short.MAX_VALUE).addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(panelLoginLayout.createSequentialGroup().addComponent(this.buttonIngreso).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, Short.MAX_VALUE).addComponent(this.buttonCancelar)).addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.textContra, -2, 212, -2).addComponent(this.textNombre, -2, 212, -2))).addGap(29, 29, 29)));
        panelLoginLayout.setVerticalGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelLoginLayout.createSequentialGroup().addGap(17, 17, 17).addComponent(this.panelIngresar, -2, -1, -2).addGap(34, 34, 34).addComponent(this.textNombre, -2, 31, -2).addGap(18, 18, 18).addComponent(this.textContra, -2, 32, -2).addGap(37, 37, 37).addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.buttonIngreso).addComponent(this.buttonCancelar)).addContainerGap(122, Short.MAX_VALUE)));
        GroupLayout panelPrincipalLayout = new GroupLayout(this.panelPrincipal);
        this.panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup().addContainerGap(66, Short.MAX_VALUE).addComponent(this.panelLogin, -2, 281, -2).addGap(64, 64, 64)));
        panelPrincipalLayout.setVerticalGroup(panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup().addContainerGap(61, Short.MAX_VALUE).addComponent(this.panelLogin, -2, 397, -2).addGap(41, 41, 41)));
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(15, 15, 15).addComponent(this.panelPrincipal, -2, 411, -2).addContainerGap(16, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 13, Short.MAX_VALUE).addComponent(this.panelPrincipal, -2, -1, -2)));
    }

    private void buttonIngresoActionPerformed(ActionEvent evt) {
    }

    private void textNombreFocusGained(FocusEvent evt) {
        if (this.textNombre.getText().equals("Escribe tu nombre")) {
            this.textNombre.setText("");
        }
    }

    private void textNombreActionPerformed(ActionEvent evt) {
    }

    private void textContraFocusGained(FocusEvent evt) {
        String contra = new String(this.textContra.getPassword());
        if (contra.equals("password")) {
            this.textContra.setText("");
        }
    }

    private void textNombreFocusLost(FocusEvent evt) {
        if (this.textNombre.getText().equals("")) {
            this.textNombre.setText("Escribe tu nombre");
        }
    }

    private void textContraFocusLost(FocusEvent evt) {
        String contra = new String(this.textContra.getPassword());
        if (contra.equals("")) {
            this.textContra.setText("password");
        }
    }

    public String getNombre() {
        return this.textNombre.getText();
    }

    public String getContra() {
        return new String(this.textContra.getPassword());
    }

    public void setActionListener(ActionListener a) {
        this.buttonIngreso.addActionListener(a);
        this.buttonCancelar.addActionListener(a);
    }

    public JTextField getTextNombre() {
        return this.textNombre;
    }




}
