/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.soluciones;

import ec.edu.ups.vista.VntVista;
import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author braya
 */
public class Controlador implements Runnable {
    private int id, res;
    private Random randomico = new Random();
    private JLabel filosofo;
    private JLabel tenedor_izq;
    private JLabel tenedor_der;
    private String proceso;
    private JTextArea texto;
    private JTextField vCome;
    private Thread thread;
    private int contador;

    public Controlador(int id, JLabel filosofo, JLabel tenedor_izq, JLabel tenedor_der, JTextField vCome,JTextArea texto) {
        this.id = id;
        this.filosofo = filosofo;
        this.tenedor_izq = tenedor_izq;
        this.tenedor_der = tenedor_der;
        this.texto = texto;
        this.vCome = vCome;
        thread = new Thread(this);
        thread.start();
        contador = 0;
    }

    public Controlador() {
    }
    
    @Override
    public void run() {
        for (int i = 0; i == contador; i++) {
            synchronized (this.tenedor_izq) {
                synchronized (this.tenedor_der) {
                    comiendo();
                }
            }
            pensando();
            contador++;
        }
    }

    public void comiendo() {
        tenedor_der.setText("Ocupado");
        tenedor_der.setForeground(Color.RED);

        tenedor_izq.setText("Ocupado");
        tenedor_izq.setForeground(Color.RED);

        filosofo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/vista/avatarp.png"))); // NOI18N
        filosofo.setText("Comiendo");
        filosofo.setBackground(Color.GREEN);

        res = Integer.valueOf(vCome.getText());
        res+=1;
        vCome.setText(String.valueOf(res));
        proceso = "Filosofo " + (id + 1) + " Comiendo\n";
        texto.append(proceso);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tenedor_der.setText("Libre");
        tenedor_der.setForeground(Color.BLACK);
        
        tenedor_izq.setText("Libre");
        tenedor_izq.setForeground(Color.BLACK);
        
        filosofo.setText("Pensando");
        filosofo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/vista/avatar.png"))); // NOI18N
        filosofo.setBackground(Color.BLACK);
        proceso = "Filosofo " + (id + 1) + " Dejo de comer, tenedor libre";
        texto.append(proceso);
    }

    public void pensando() {
        tenedor_der.setText("Libre");
        tenedor_der.setForeground(Color.BLACK);
        
        tenedor_izq.setText("Libre");
        tenedor_izq.setForeground(Color.BLACK);
        
        filosofo.setText("Pensando");
        filosofo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/vista/avatar.png"))); // NOI18N
        filosofo.setBackground(Color.BLACK);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Random getRandomico() {
        return randomico;
    }

    public void setRandomico(Random randomico) {
        this.randomico = randomico;
    }
}
