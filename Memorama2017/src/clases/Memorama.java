/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author gerardo
 */
public class Memorama {

    private String[] imagenes = {"c1.jpg", "c2.jpg", "c3.jpg", "c4.jpg", "c5.jpg",
        "c6.jpg", "c7.jpg", "c8.jpg", "c9.jpg", "c10.jpg",
        "c11.jpg", "c12.jpg", "c13.jpg", "c14.jpg", "c15.jpg",
        "c16.jpg", "c17.jpg", "c18.jpg", "c19.jpg", "c20.jpg",
        "c21.jpg", "c22.jpg", "c23.jpg", "c24.jpg", "c25.jpg",
        "c26.jpg", "c27.jpg", "c28.jpg", "c29.jpg", "c30.jpg"};
    private String[] categorias = {"/famosos/", "/caricaturas/", "/equipos/"};
    private String[] tablero; // es el arreglo que se mostrara en la pagina principal
    private int catego;  // 0=Famosos, 1= Caricaturas,  2 = equipos de futbol;

    private String Jugador1; // es el nombre del jugador 1
    private String jugador2; // es el nombre del jugador 2
    private int paresJ1; // numero de pares del jugador 1
    private int paresJ2; // numero de pares del jugador 2
    private boolean turnoJ1;  // true = turno del jugador1, false= turno del jugador2
    private int tiro1; // primer tarjeta seleccionada
    private int tiro2; // segunda tarjeta seleccionada
    private boolean[] volteadas = new boolean[24]; // arreglo para saber como se encuentran las cartas
    private boolean banTiro1 = true; // true = primer tiro;  false = segundo tiro
    private boolean banJuego; // indica si ya se a iniciado el juego
    private int nSel = 0; // numero de tarjetas seleccionadas

    public Memorama() {
        tablero = new String[24];
        paresJ1 = 0;
        paresJ2 = 0;
        turnoJ1 = true;
    }

    public void setParesJ1(int paresJ1) {
        this.paresJ1 = paresJ1;
    }

    public void setParesJ2(int paresJ2) {
        this.paresJ2 = paresJ2;
    }
    

    public void setCatego(int catego) {
        this.catego = catego;
    }

    public String getCategoria() {
        return categorias[catego - 1];
    }
    

    public String getJugador1() {
        return Jugador1;
    }

    public void setBanJuego(boolean banJuego) {
        this.banJuego = banJuego;
    }

    public boolean isBanJuego() {
        return banJuego;
    }

    public String getJugador2() {
        return jugador2;
    }

    public int getCatego() {
        return catego;
    }

    public boolean isBanTiro1() {
        return banTiro1;
    }

    public void setJugador1(String Jugador1) {
        this.Jugador1 = Jugador1;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public boolean isTurnoJ1() {
        return turnoJ1;
    }

    public int getTiro1() {
        return tiro1;
    }

    public int getTiro2() {
        return tiro2;
    }

    public void setnSel(int nSel) {
        this.nSel = nSel;
    }

    public int getnSel() {
        return nSel;
    }

    /**
     * pone el tiro para el jugador segun el turno,
     * verificando si esa posicion es valida 
     * @param tiro
     * @return 
     */
    public boolean setTiro(int tiro) {

        if (!volteadas[tiro]) { // no ha sido volteada
//            
            if (banTiro1) {

                tiro1 = tiro;
                banTiro1 = false;

            } else {
                tiro2 = tiro;
                banTiro1 = true;

            }
            volteadas[tiro] = true;
            nSel++;         // < ---------
            return true;
        } else {
            return false;
        }
    }

    /**
     * pone las imagenes en el tablero, cada una con su respectivo 
     * par
     */
    public void seleccionarImagenes() {

        boolean[] seleccionadas = new boolean[30];
        Random r1 = new Random();
        int x = 0;
        int i = 0;
        while (i < 12) {

            x = r1.nextInt(imagenes.length);
            if (!seleccionadas[x]) { // evalua si la casilla a sido seleccionada

                tablero[i] = imagenes[x];
                tablero[i + 12] = imagenes[x];
                i++;
                seleccionadas[x] = true;
            }

        }

    }

    /**
     * verifica si se a logrado un par, aumentando la puntuacion
     * del jugador que lo logro, en caso contrario pone las imagenes como
     * estaban
     * @return 
     */
    public boolean verificar() {
        if (nSel!=0 && tablero[tiro1].equals(tablero[tiro2])) {

            if(turnoJ1){
             paresJ1++;
            }else {
            paresJ2++;
            }
          
            return true;

        } else {
            volteadas[tiro1] = false;
            volteadas[tiro2] = false;
            turnoJ1 = !turnoJ1;
            return false;
        }
    }

    public int getParesJ1() {
        return paresJ1;
    }

    public int getParesJ2() {
        return paresJ2;
    }

    /**
     * inicializa los valores para empezar el juego
     */
    public void inicializadorDatos() {
        for (int i = 0; i < 24; i++) {

            volteadas[i] = false;
        }
        banTiro1 = true;
        turnoJ1 = true;
        paresJ1=0;
        paresJ2=0;
    }

    public String getTablero(int pos) {
        return tablero[pos];

    }

    /**
     * mezcla las imagenes en todo el tablero
     */
    
    public void mezclar() {
        String tempo;
        Random r1 = new Random();
        int x;
        int y;
        for (int i = 1; i < 100; i++) {
            x = r1.nextInt(24);
            y = r1.nextInt(24);
            tempo = tablero[x];
            tablero[x] = tablero[y];
            tablero[y] = tempo;

        }

    }

}
