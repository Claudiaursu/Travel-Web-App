package com.example.proiectjava.controller;

import com.example.proiectjava.dao.VacantaDAO;
import com.example.proiectjava.model.Atractie;
import com.example.proiectjava.model.Vacanta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VacantaController {

    @Autowired
    private VacantaDAO dao;

    @RequestMapping("/vacante")
    public String viewHomePage(Model model) {
        List<Vacanta> listVacanta = dao.list();
        model.addAttribute("listVacanta", listVacanta);
        return "vacanta/show";
    }

    @RequestMapping("/vacante/new")
    public String showNewForm(Model model) {
        Vacanta vacanta = new Vacanta();
        model.addAttribute("vacanta", vacanta);
        return "vacanta/new_form";
    }

    @RequestMapping("/vacante/view_attractions/{id}")
    public String showAttractions(@PathVariable(name = "id") int id, Model model){
        List<Atractie> listaAtractii = dao.getAllAttractions(id);
        model.addAttribute("listaAtractii",listaAtractii);

        return "vacanta/view_attractions";
    }

    @RequestMapping(value = "/vacante/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("vacanta") Vacanta vacanta) {
        dao.save(vacanta);

        return "redirect:/vacante";
    }

    @RequestMapping("/vacante/edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("vacanta/edit_form");
        Vacanta vacanta = dao.get(id);
        mav.addObject("vacanta", vacanta);

        return mav;
    }

    @RequestMapping(value = "/vacante/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("vacanta") Vacanta vacanta) {
        dao.update(vacanta);

        return "redirect:/vacante";
    }

    @RequestMapping("/vacante/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        dao.delete(id);
        return "redirect:/vacante";
    }

}

