package com.imp.calculadora;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class CalculatorController {
    private double resultado;
    private boolean positivo = true;
    private StringBuilder nums = new StringBuilder();
    private StringBuilder operacion = new StringBuilder();
    private List<Double> numeros = new ArrayList<>();
    private List<String> signos = new ArrayList<>();

    @FXML
    public TextField panelOperaciones = new TextField();
    /*TODO: Mi proposito para resolver esque cada vez que el usuario ingrese una operacion a la variable global se le vayan
    *  aplicando esos cambios, es decir la operacion que se este realizando, cuando el usuario quiera saber el resultado se limpia
    * la pantalla y se muestra la variable "resultado"*/
    @FXML
    public JFXButton num1,num2,num3,num4,num5,num6,num7,num8,num9,num0;
    public void initialize() {
        panelOperaciones.setAlignment(Pos.CENTER_RIGHT);
        panelOperaciones.setEditable(false);
        panelOperaciones.setText("0");
    }
    public void cambiarSigno() {
        this.positivo = !positivo;
        if(positivo) {
            operacion.delete(0,1);
            nums.delete(0,1);
        } else {
            operacion.replace(0,0,"-");
            nums.replace(0,0,"-");
        }
        panelOperaciones.setText(String.valueOf(operacion));
        System.out.println(nums);
    }
    public void boton0() {
        capturarNumero("0");
    }
    public void boton1() {
        capturarNumero("1");
    }
    public void boton2() {
        capturarNumero("2");
    }
    public void boton3() {
        capturarNumero("3");
    }
    public void boton4() {
        capturarNumero("4");
    }
    public void boton5() {
        capturarNumero("5");
    }
    public void boton6() {
        capturarNumero("6");
    }
    public void boton7() {
        capturarNumero("7");
    }
    public void boton8() {
        capturarNumero("8");
    }
    public void boton9() {
        capturarNumero("9");
    }
    public void suma() {
        operacion.append("+");
        panelOperaciones.setText(String.valueOf(operacion));
        guardarValores("+");
    }
    public void resta() {
        operacion.append("-");
        panelOperaciones.setText(String.valueOf(operacion));
        guardarValores("-");
    }
    public void multiplicacion() {
        operacion.append("*");
        panelOperaciones.setText(String.valueOf(operacion));
        guardarValores("*");
    }
    public void division() {
        operacion.append("/");
        panelOperaciones.setText(String.valueOf(operacion));
        guardarValores("/");
    }
    private void capturarNumero(String num) {
        operacion.append(num);
        nums.append(num);
        panelOperaciones.setText(String.valueOf(operacion));
        System.out.println(nums);
    }
    public void limpiarTodo() {
        panelOperaciones.setText("");
        operacion.delete(0,operacion.length());
        nums.delete(0,nums.length());
        resultado = 0;
        numeros.clear();
        signos.clear();
        positivo = true;
    }
    private void guardarValores(String operacion) {
        try {
            double numActual = Double.parseDouble(String.valueOf(nums));
            System.out.println("Numero capturado: "+numActual);

            numeros.add(numActual);
            signos.add(operacion);
            nums.delete(0,nums.length());

        }catch (Exception e) {
            panelOperaciones.setText("ERROR DE SINTAXIS");
            System.out.println(e.getMessage());
        }
    }
    private void guardarValores() {
        try {
            double numActual = Double.parseDouble(String.valueOf(nums));
            System.out.println("Numero capturado: "+numActual);

            numeros.add(numActual);
            nums.delete(0,nums.length());

        }catch (Exception e) {
            panelOperaciones.setText("ERROR DE SINTAXIS");
            System.out.println(e.getMessage());
        }
    }

    public void borrarUltimoCaracter() {
        if(!nums.isEmpty() && !operacion.isEmpty()) {
            char ultimoCarac = operacion.charAt(operacion.length() - 1);
            if (Character.isDigit(ultimoCarac)) {
                // Si el último carácter es un dígito, eliminar de ambos StringBuilder
                nums.deleteCharAt(nums.length() - 1);
                operacion.deleteCharAt(operacion.length() - 1);
            } else {
                // Si el último carácter no es un dígito, solo eliminar de operacion
                signos.remove(signos.toArray().length-1);
                operacion.deleteCharAt(operacion.length() - 1);
            }
        }
        System.out.println("Num Actual: "+nums+"\nImprimir: "+operacion);
        panelOperaciones.setText(String.valueOf(operacion));
    }
    public void mostrarResultado() {
        guardarValores();
        try {
            aplicarOperacion();
        }catch (Exception e) {
            panelOperaciones.setText(e.getMessage());
        }
        System.out.println("Resultado: "+resultado);
        panelOperaciones.setText(String.valueOf(this.resultado));
    }
    private void aplicarOperacion()throws Exception {
        resultado = numeros.get(0);
        int i=1;

        try {
            while(i<numeros.toArray().length) {
                switch(signos.get(i-1)) {
                    case "+":
                        resultado += numeros.get(i);break;
                    case "-":
                        resultado -= numeros.get(i);break;
                    case "*":
                        resultado *= numeros.get(i);break;
                    case "/":
                        resultado /= numeros.get(i);break;
                }
                i++;
            }
        } catch (Exception e) {
            throw new Exception("ERROR DE SINTAXIS");
        }
    }
    public void puntoDecimal() {
        nums.append(".");
        operacion.append(".");
        panelOperaciones.setText(String.valueOf(operacion));
    }
}