package clienteNicoExpress.Threads;

import clienteNicoExpress.cliente.ManejadorCliente;
import serializable.ParametrosEncapsuladosParaClientes;

/**
 * Created by nicolas on 16/07/15.
 */
public class ThreadPedidorParametros extends  Thread
{
    private ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes;

    public void run()
    {
        parametrosEncapsuladosParaClientes = ManejadorCliente.pedirParametrosAlServer();
        ManejadorCliente.setPepc(parametrosEncapsuladosParaClientes);
    }

    /*GYS*/

    public ParametrosEncapsuladosParaClientes getParametrosEncapsuladosParaClientes() {
        return parametrosEncapsuladosParaClientes;
    }

    public void setParametrosEncapsuladosParaClientes(ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes) {
        this.parametrosEncapsuladosParaClientes = parametrosEncapsuladosParaClientes;
    }
}
