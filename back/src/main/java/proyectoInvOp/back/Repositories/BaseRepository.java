package proyectoInvOp.back.Repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import proyectoInvOp.back.Entity.Base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E extends Base, ID extends Serializable> extends JpaRepository<E,ID> {
    //Baja logica para todas la entidades
    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.fechaBaja = CURRENT_DATE WHERE e.id = :id")
    void bajaLogicaById(ID id);
    //Solo trae los que no esten dados de baja
    @Transactional
    @Query("SELECT e FROM #{#entityName} e WHERE e.fechaBaja IS NULL")
    List<E> findAllActive();

    //Solo trae los que no esten dados de baja
    @Transactional
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.fechaBaja IS NULL")
    Optional<E> findActiveById(ID id);



}