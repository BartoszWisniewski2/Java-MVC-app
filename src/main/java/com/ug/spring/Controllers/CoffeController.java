package com.ug.spring.Controllers;


import com.ug.spring.domain.Coffe;
import com.ug.spring.service.Interfaces.CoffeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CoffeController {

    private CoffeService cs;

    @Autowired
    public CoffeController(CoffeService cs) {
        this.cs = cs;
    }

    @GetMapping("/coffe")
    public String home(Model model) {
        List<Coffe> coffesList = (List)cs.getCoffes();
        List<String> categoryList = coffesList.stream().map(x->x.getCategory()).distinct().collect(Collectors.toList());

        model.addAttribute("coffes", cs.getCoffes());
        model.addAttribute("coffesList", categoryList);
        return "coffe-all";
    }

    @GetMapping("/coffe/{category}")
    public String coffesByCategory(@PathVariable("category") String category, Model model) {
        List<Coffe> coffesList = (List)cs.getCoffes();
        List<String> categoryList = coffesList.stream().map(x->x.getCategory()).distinct().collect(Collectors.toList());
        List<Coffe> coffesListbyCategory = coffesList.stream().filter(x->x.getCategory().equals(category)).collect(Collectors.toList());

        model.addAttribute("coffes", coffesListbyCategory);
        model.addAttribute("coffesList", categoryList);
        return "coffe-all";
    }



    @GetMapping("/coffe/delete/{id}")
    public String deleteCoffe(@PathVariable("id") long id, Model model) {
        cs.delete(id);
        model.addAttribute("coffes", cs.getCoffes());
        return "coffe-all";
    }

    @GetMapping("/coffe/add")
    public String addCoffe(Model model) {
        model.addAttribute("coffe", new Coffe());
        return "coffe-form";
    }

    @GetMapping("/coffe/edit/{id}")
    public String edit(Model model,@PathVariable("id") long id) {
        List<Coffe> coffes = (ArrayList<Coffe>)cs.getCoffes();
        Coffe coffe = coffes.stream().filter(p->p.getCoffeId()==id).findAny().orElse(null);
        if(coffe==null)
            return "redirect:coffe";
        model.addAttribute("coffe",coffe);
        return "coffe-form";
    }
    @GetMapping("/coffe/wz/{id}")
    public String wz(Model model,@PathVariable("id") long id) {
        List<Coffe> coffes = (ArrayList<Coffe>)cs.getCoffes();
        Coffe coffe = coffes.stream().filter(p->p.getCoffeId()==id).findAny().orElse(null);
        if(coffe==null)
            return "redirect:coffe";
        coffe.setQuantity(coffe.getQuantity()-1);
        model.addAttribute("coffe",coffe);
        return "coffe-wz";
    }

    @PostMapping("/coffe/addd")
    public String addCoffe(Coffe coffe, Model model) {
        cs.add(coffe);
        model.addAttribute("coffes", cs.getCoffes());
        return "coffe-all";
    }



    @PostMapping("/coffe/save")
    public String save(@Valid @ModelAttribute("coffe") final Coffe coffe, Errors errors, Model model)
    {
        if(errors.hasErrors())
        {
            return "coffe-form";
        }
        Coffe coffeToSave = coffe;
        if(coffe.getCoffeId()==-1)
        {
            cs.add(coffeToSave);
        }
        else
        {
            cs.save(coffeToSave);
        }
        return "redirect:/coffe";
    }

    private static double toNumeric(String strNum) {
        double number=0;
        try {
            number = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
        }
        return number;
    }


}

