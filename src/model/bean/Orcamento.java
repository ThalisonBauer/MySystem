package model.bean;
public class Orcamento {
    private int idOrcamento;
    private String TituloOrcamento;
    private int idCliente;
    private String dateOrcamento;
    private float totalOrcamento;
    private String nomeCliente;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    

    public float getTotalOrcamento() {
        return totalOrcamento;
    }

    public void setTotalOrcamento(float totalOrcamento) {
        this.totalOrcamento = totalOrcamento;
    }
    

    public String getDateOrcamento() {
        return dateOrcamento;
    }

    public void setDateOrcamento(String dateOrcamento) {
        this.dateOrcamento = dateOrcamento;
    }

    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public String getTituloOrcamento() {
        return TituloOrcamento;
    }

    public void setTituloOrcamento(String tituloOrcamento) {
        this.TituloOrcamento = tituloOrcamento;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setDateOrcamento(java.util.Date dataAtual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
