package proyectoInvOp.back.Services;

import jakarta.transaction.Transactional;
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
    public E findById(ID id) throws Exception {
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
            E cliente = entityOptional.get();
            cliente = baseRepository.save(entity);
            return cliente;
        } catch (Exception e){
            throw new Exception(e.getMessage());
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
