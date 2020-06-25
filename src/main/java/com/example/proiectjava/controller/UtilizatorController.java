package com.example.proiectjava.controller;

import com.example.proiectjava.dao.UtilizatorDAO;
import com.example.proiectjava.model.Utilizator;
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
public class UtilizatorController {

    @Autowired
    private UtilizatorDAO dao;

    @RequestMapping("/utilizatori")
    public String viewHomePage(Model model) {
        List<Utilizator> listUtilizator = dao.list();
        model.addAttribute("listUtilizator", listUtilizator);
        return "utilizator/show";
    }

    @RequestMapping("/utilizatori/new")
    public String showNewForm(Model model) {
        Utilizator utilizator = new Utilizator();
        model.addAttribute("utilizator", utilizator);

        return "utilizator/new_form";
    }

    @RequestMapping(value = "/utilizatori/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("utilizator") Utilizator utilizator) {
        dao.save(utilizator);

        return "redirect:/utilizatori";
    }

    @RequestMapping("/utilizatori/edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("utilizator/edit_form");
        Utilizator utilizator = dao.get(id);
        mav.addObject("utilizator", utilizator);

        return mav;
    }

    @RequestMapping(value = "/utilizatori/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("utilizator") Utilizator utilizator) {
        dao.update(utilizator);

        return "redirect:/utilizatori";
    }

    @RequestMapping("/utilizatori/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        dao.delete(id);
        return "redirect:/utilizatori";
    }

}