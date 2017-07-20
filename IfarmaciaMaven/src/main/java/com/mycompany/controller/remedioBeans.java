/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.Cliente;
import com.mycompany.model.Farmacia;
import com.mycompany.model.Remedio;
import com.mycompany.model.SingletonEntityManagerFactory;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.inject.Named;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author LucasPC
 */
@Named(value = "remedioBeans")
@RequestScoped
public class remedioBeans {

    @EJB
    private Aplicacao aplicacao;
    private String nome_remedio;
    private String codigo;
    private String data_validade;
    private String faixa_etaria;
    private double preco;
    private int quantidade;
    private String tipo;
    private String filename;

    /**
     * Creates a new instance of remedioBeans
     */
    public remedioBeans() {

    }

    public String getNome_remedio() {
        return nome_remedio;
    }

    public void setNome_remedio(String nome_remedio) {
        this.nome_remedio = nome_remedio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getData_validade() {
        return data_validade;
    }

    public void setData_validade(String data_validade) {
        this.data_validade = data_validade;
    }

    public String getFaixa_etaria() {
        return faixa_etaria;
    }

    public void setFaixa_etaria(String faixa_etaria) {
        this.faixa_etaria = faixa_etaria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getComprimido() {
        return tipo;
    }

    public void setComprimido(String tipo) {
        this.tipo = tipo;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String inserirRemedio() {
        JSONParser parser = new JSONParser();
        List<Remedio> remedios = new ArrayList();
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        List<Cliente> clientes = new ArrayList();
        Farmacia farmacia = (Farmacia) SingletonSession.getInstance().getAttribute("farmacia");
        if (farmacia != null) {
            try {
                filename = filename.substring(8);
                Object obj = parser.parse(new FileReader(filename));
                JSONArray jsonArray = (JSONArray) obj;
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    nome_remedio = (String) jsonObject.get("nome_remedio");
                    codigo = (String) jsonObject.get("codigo");

                    data_validade = (String) jsonObject.get("data_validade");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar cal = Calendar.getInstance();
                    try {
                        cal.setTime(sdf.parse(data_validade));
                    } catch (ParseException pe) {

                    }
                    faixa_etaria = (String) jsonObject.get("faixa_etaria");

                    String precos = (String) jsonObject.get("preco");
                    this.preco = Double.parseDouble(precos);
                    String quantidades = (String) jsonObject.get("quantidade");
                    this.quantidade = Integer.parseInt(quantidades);
                    tipo = (String) jsonObject.get("tipo");
                    Remedio remedio = new Remedio(nome_remedio, preco, faixa_etaria, quantidade, tipo, cal, codigo);
                    remedios.add(remedio);
                }
                List<String> filenames = new ArrayList();
                filenames = aplicacao.listaFileName();

                int indice = filename.indexOf("main");

                if (filenames.isEmpty()) {
                    farmacia.setFilename(filename);
                } else {
                    farmacia.setFilename(filename + filenames.size());
                }
                farmacia.setFilename(filename);
                farmacia.setRemedios(remedios);
                List<Farmacia> farmacias = aplicacao.listaTodasFarmacias();
                if (farmacias.isEmpty()) {
                    farmacias = new ArrayList();
                    farmacias.add(farmacia);
                    cliente.setFarmacia(farmacias);
                    clientes.add(cliente);
                    farmacia.setClientes(clientes);

                } else {
                    farmacias.add(farmacia);
                    cliente.setFarmacia(farmacias);
                    clientes.add(cliente);
                    farmacia.setClientes(clientes);
                }

                aplicacao.AlterarCliente(cliente);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "sucessoRemedio";
    }

}
