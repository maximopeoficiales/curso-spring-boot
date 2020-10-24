package mx.com.gm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.gm.dao.IPersonaDao;
import mx.com.gm.domain.Persona;

@Service
public class PersonaServiceImpl implements IPersonaService {

     @Autowired
     private IPersonaDao personaDao;

     @Override
     @Transactional(readOnly = true)
     public List<Persona> listarPersonas() {
          return (List<Persona>) personaDao.findAll();
     }

     @Override
     @Transactional
     public void guardar(Persona persona) {
          personaDao.save(persona);
     }

     @Override
     @Transactional
     public void eliminar(Persona persona) {
          personaDao.delete(persona);
     }

     @Override
     @Transactional(readOnly = true)
     public Persona encontrarPersona(Persona persona) {
          // errors return null
          return personaDao.findById(persona.getIdPersona()).orElse(null);
     }

}
