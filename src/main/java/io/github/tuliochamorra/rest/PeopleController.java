package io.github.tuliochamorra.rest;
import io.github.tuliochamorra.model.entity.People;
import io.github.tuliochamorra.model.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/peoples")
@CrossOrigin("http://localhost:4200")
public class PeopleController {

    private PeopleRepository repository;

    @Autowired
    public PeopleController(PeopleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<People> getAll(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public People save(@RequestBody @Valid People people){
        return repository.save(people);
    }

    @GetMapping("{id}")
    public People findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.findById(id)
                .map(people -> {
                    repository.delete(people);
                    return Void.TYPE;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody People peopleAtualizado){
        repository.findById(id)
                .map(people -> {
                    peopleAtualizado.setId(people.getId());
                    return repository.save(peopleAtualizado);
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado"));
    }
}
