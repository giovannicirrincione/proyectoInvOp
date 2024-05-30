package proyectoInvOp.back.PatronObservador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Repositories.ArticuloRepository;
import proyectoInvOp.back.Services.ArticuloServiceImpl;

import java.util.List;
import java.util.Optional;
@Component
public class ArticuloObserver implements Observer{
    @Autowired
    ArticuloServiceImpl articuloService;
    @Autowired
    ArticuloRepository articuloRepository;
    //Metodo del observador que actualiza el stock
    @Override
    public void updateVenta(Articulo articulo, int cantidadVendida) {

        Optional<Articulo> existingArticulo = articuloRepository.findActiveById(articulo.getId());


        if (existingArticulo.isPresent()) {
            Articulo articuloBD = existingArticulo.get();

            articuloBD.setStockActual(articuloBD.getStockActual() - cantidadVendida);

            try {
                articuloService.update(articuloBD.getId(), articuloBD);

            } catch (Exception e) {

                throw new RuntimeException(e);
            }
        }


    }
    @Override
    public void updateCompra(Articulo articulo, int cantidadAdquirida) {

        Optional<Articulo> existingArticulo = articuloRepository.findActiveById(articulo.getId());

        if (existingArticulo.isPresent()) {
            Articulo articuloBD = existingArticulo.get();

            articuloBD.setStockActual(articuloBD.getStockActual() + cantidadAdquirida);

            try {
                articuloService.update(articuloBD.getId(), articuloBD);

            } catch (Exception e) {

                throw new RuntimeException(e);
            }
        }


    }


}
