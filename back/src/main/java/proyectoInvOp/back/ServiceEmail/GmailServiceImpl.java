package proyectoInvOp.back.ServiceEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.OrdenCompra;

import java.time.LocalDate;

@Service

public class GmailServiceImpl implements GmailService {

    @Value("${DIRECCION_GMAIL}")
    private String direccionGmail;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void mandarEmail(OrdenCompra ordenCompra) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        try {
            // Obtiene la información de la orden de compra
            Long nroOrden = ordenCompra.getId();
            String proveedor = ordenCompra.getProveedor().getNombre();
            String articulo = ordenCompra.getArticulo().getNombre();
            LocalDate fechaCreacion = ordenCompra.getFechaCreacion();
            float montoTotal = ordenCompra.getMontoTotal();
            int cantidad = ordenCompra.getCantidad();
            String estado = ordenCompra.getEstadoOrdenCompra().getNombre();

            // Construir el contenido del correo
            StringBuilder messageBody = new StringBuilder();
            messageBody.append("¡Orden de Compra Generada!\n\n");
            messageBody.append("Se ha generado automáticamente una orden de compra con los siguientes detalles:\n\n");
            messageBody.append("Fecha de Creación: ").append(fechaCreacion).append("\n");
            messageBody.append("Número de Orden: ").append(nroOrden).append("\n");
            messageBody.append("Proveedor: ").append(proveedor).append("\n");
            messageBody.append("Artículo: ").append(articulo).append("\n");
            messageBody.append("Cantidad: ").append(cantidad).append("\n");
            messageBody.append("Monto Total: $").append(montoTotal).append("\n");
            messageBody.append("Estado: ").append(estado).append("\n\n");
            messageBody.append("Para confirmar la Orden de Compra entre a la web y confirmela.\n\n");
            messageBody.append("¡Gracias!");

            // Información del email
            mailMessage.setFrom(direccionGmail);
            mailMessage.setTo(direccionGmail);
            mailMessage.setSubject("¡Orden de Compra Generada!");
            mailMessage.setText(messageBody.toString());
            mailSender.send(mailMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
