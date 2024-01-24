/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

public class HistoricoPeso {
    private double peso;
    private String data;
    private String cpf;
    
    public HistoricoPeso(double peso, String data, String cpf){
        this.setPeso(peso); 
        this.setData(data); 
        this.setCpf(cpf);
    }
    
    public HistoricoPeso(){}
    
    public void setPeso(double peso){this.peso = peso;}
    public void setData(String data){this.data = data;}
    public void setCpf(String cpf){this.cpf = cpf;}
    
    public double getPeso(){return peso;}
    public String getData(){return data;}
    public String getCpf(){return cpf;}
    
    
    public static String calcIMC(double peso, double altura){
        double imc = peso/Math.pow(altura, 2);
        
        if (imc < 18.5){return imc + " magreza";}
        else if (imc <= 24.9){return imc + " normal";}
        else if (imc <= 29.9){return imc + " sobrepeso";}
        else if (imc <= 34.9){return imc + " obesidade I";}
        else if (imc <= 39.9){return imc + " obesidade II";}
        else {return imc + " obesidade III";}
        
        }
    
}
