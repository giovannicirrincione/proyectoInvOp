package proyectoInvOp.back.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import proyectoInvOp.back.Entity.Base;
import proyectoInvOp.back.Repositories.BaseRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E,ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public List<E> findAll() throws Exception {
        try {
            List<E> entities = baseRepository.findAllActive();
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public E findById(Long id) throws Exception {
        try {
            Optional<E> entity = baseRepository.findActiveById(id);
            return entity.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E save(E entity) throws Exception {
        try {
            entity = baseRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (entityOptional.isPresent()) {
                E existingEntity = entityOptional.get();

                // Copiar los cambios de `entity` a `existingEntity`
                BeanUtils.copyProperties(entity, existingEntity, "id");

                return baseRepository.save(existingEntity);
            } else {
                throw new Exception("Entity not found with id: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }


    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            if (baseRepository.existsById(id)){
                baseRepository.bajaLogicaById(id);
                return true;
            } else{
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
