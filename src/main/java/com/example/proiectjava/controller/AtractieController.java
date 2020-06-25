package com.example.proiectjava.controller;

import com.example.proiectjava.dao.AtractieDAO;
import com.example.proiectjava.model.Atractie;
import com.example.proiectjava.model.Recenzie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AtractieController {

    @Autowired
    private AtractieDAO dao;

    @RequestMapping("/atractii")
    public String viewHomePage(Model model) {
        List<Atractie> listAtractie = dao.list();
        model.addAttribute("listAtractie", listAtractie);
        return "atractie/show";
    }

    @RequestMapping("/atractii/new")
    public String showNewForm(Model model) {
        Atractie atractie = new Atractie();
        model.addAttribute("atractie", atractie);

        return "atractie/new_form";
    }

    @RequestMapping(value = "/atractii/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("atractie") Atractie atractie) {
        dao.save(atractie);

        return "redirect:/atractii";
    }

    @RequestMapping("/atractii/view_recenzii/{id_atractie}")
    public String showRecenzii(@PathVariable(name = "id_atractie") int id, Model model) {
        List<Recenzie> listaRecenzii = dao.getAllRecenzii(id);
        model.addAttribute("listaRecenzii", listaRecenzii);

        return "atractie/view_recenzii";
    }

    @RequestMapping("/atractii/edit/{id_atractie}")
    public ModelAndView showEditForm(@PathVariable(name = "id_atractie") int id) {
        ModelAndView mav = new ModelAndView("atractie/edit_form");
        Atractie atractie = dao.get(id);
        mav.addObject("atractie", atractie);

        return mav;
    }

    @RequestMapping(value = "/atractii/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("atractie") Atractie atractie) {
        dao.update(atractie);

        return "redirect:/atractii";
    }

    @RequestMapping("/atractii/delete/{id_atractie}")
    public String delete(@PathVariable(name = "id_atractie") int id) {
        dao.delete(id);
        return "redirect:/atractii";
    }

}
