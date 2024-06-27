package proyectoInvOp.back.ServiceEmail;

import proyectoInvOp.back.Entity.OrdenCompra;

public interface GmailService {

    public void mandarEmail(OrdenCompra ordenCompra) throws Exception;
}
