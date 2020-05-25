package com.ug.spring.Controllers.Api;

import com.ug.spring.domain.Coffe;
import com.ug.spring.service.Interfaces.CoffeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ComponentScan()
public class RestCoffeController {

    private HttpHeaders headers= new HttpHeaders();

    @Autowired
    RestCoffeController(CoffeService coffeService)
    {
        _coffeService = coffeService;
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    }

    private CoffeService _coffeService;

    @GetMapping("api/coffe")
    public ResponseEntity<Iterable<Coffe>> list()
    {
        ArrayList<Coffe> coffes = (ArrayList<Coffe>) _coffeService.getCoffes();
        return ResponseEntity.ok().headers(headers).body(coffes);
    }

    @GetMapping("api/coffe/{id}")
    public ResponseEntity<Coffe> getCoffe(@PathVariable long id)
    {
        ArrayList<Coffe> coffes =  (ArrayList<Coffe>) _coffeService.getCoffes();

        Coffe rtn = coffes.stream().filter(p->p.getCoffeId()==id).findAny().orElse(null);
        if(rtn!=null) {
            return new ResponseEntity<>(rtn, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("api/coffe/wz/{id}")
    public ResponseEntity<Coffe> getCoffeWz(@PathVariable long id)
    {
        ArrayList<Coffe> coffes =  (ArrayList<Coffe>) _coffeService.getCoffes();

        Coffe rtn = coffes.stream().filter(p->p.getCoffeId()==id).findAny().orElse(null);

        Coffe rtn2 = new Coffe(rtn.getCoffeId(), rtn.getName(), rtn.getPrice(), 1, rtn.getCategory(), rtn.getShelfNumber());
        if(rtn!=null) {
            for (Coffe coffe: coffes) {
                if (coffe.getCoffeId()==id){
                    coffe.setQuantity(coffe.getQuantity()-1);
                }
            }
            return new ResponseEntity<>(rtn2, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("api/coffe/update/{id}/{newLocation}")
    public ResponseEntity updateLocation(@PathVariable long id, @PathVariable int newLocation)
    {
        ArrayList<Coffe> coffes =  (ArrayList<Coffe>) _coffeService.getCoffes();
        Coffe rtn = coffes.stream().filter(p->p.getCoffeId()==id).findAny().orElse(null);
        if(rtn!=null) {
            rtn.setShelfNumber(newLocation);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("api/coffe/get/{category}")
    public ResponseEntity<Iterable<Coffe>> getCoffe(@PathVariable String category)
    {
        ArrayList<Coffe> coffes =  (ArrayList<Coffe>) _coffeService.getCoffes();
        List<Coffe> coffesListbyCategory = coffes.stream().filter(x->x.getCategory().equals(category)).collect(Collectors.toList());

        return ResponseEntity.ok().headers(headers).body(coffesListbyCategory);

    }


    @PostMapping("api/coffe/new")
    public ResponseEntity addCoffe(@RequestBody @Valid Coffe coffe, Errors errors)
    {
        if(errors.hasErrors())
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        _coffeService.add(coffe);
        return  new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("api/coffe/update/{id}")
    public ResponseEntity updateCoffe(@RequestBody @Valid Coffe coffe, @PathVariable long id, Errors errors)
    {
        if(errors.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ArrayList<Coffe> coffes =  (ArrayList<Coffe>) _coffeService.getCoffes();
        Coffe coffeIsInDb =  coffes.stream().filter(p->p.getCoffeId()==id).findAny().orElse(null);
        if(coffeIsInDb!=null)
        {
            _coffeService.save(coffeIsInDb);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("api/coffe/delete/{id}")
    public ResponseEntity deleteCoffe(@PathVariable long id)
    {
        Coffe coffeInDb = _coffeService.delete(id);
        if(coffeInDb==null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(HttpStatus.OK);
    }

}
