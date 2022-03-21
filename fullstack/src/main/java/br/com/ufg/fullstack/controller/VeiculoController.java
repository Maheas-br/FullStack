package br.com.ufg.fullstack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufg.fullstack.entity.Veiculo;
import br.com.ufg.fullstack.repositoy.VeiculoRepository;

@RestController
public class VeiculoController {
	
    @Autowired
    private VeiculoRepository veiculoRepository;
    
    @RequestMapping(value = "/veiculo", method = RequestMethod.GET)
    public List<Veiculo> Get() {
        return veiculoRepository.findAll();
    }
    
    
    @RequestMapping(value = "/veiculo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Veiculo> GetById(@PathVariable(value = "id") long id)
    {
    	
        Optional<Veiculo> pessoa = veiculoRepository.findById(id);
        if(pessoa.isPresent())
            return new ResponseEntity<Veiculo>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    @RequestMapping(value = "/veiculo", method =  RequestMethod.POST)
    public Veiculo Post(@RequestBody Veiculo veiculo)
    {
        return veiculoRepository.save(veiculo);
    }    
    
    @RequestMapping(value = "/veiculo/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Veiculo> Put(@PathVariable(value = "id") long id, @RequestBody Veiculo newVeiculo)
    {
        Optional<Veiculo> oldVeiculo = veiculoRepository.findById(id);
        if(oldVeiculo.isPresent()){
            Veiculo veiculo = oldVeiculo.get();
            veiculo.setModelo(newVeiculo.getModelo());
            veiculo.setPlaca(newVeiculo.getPlaca());
            veiculoRepository.save(veiculo);
            return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
    @RequestMapping(value = "/veiculo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        if(veiculo.isPresent()){
            veiculoRepository.delete(veiculo.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
}
