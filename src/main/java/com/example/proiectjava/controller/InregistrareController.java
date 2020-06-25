package com.example.proiectjava.controller;

import com.example.proiectjava.dao.InregistrareDAO;
import com.example.proiectjava.model.Inregistrare;
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
public class InregistrareController {

    @Autowired
    private InregistrareDAO dao;

    @RequestMapping("/inregistrari")
    public String viewHomePage(Model model) {
        List<Inregistrare> listInregistrare = dao.list();
        model.addAttribute("listInregistrare", listInregistrare);
        return "inregistrare/show";
    }

    @RequestMapping("/inregistrari/new")
    public String showNewForm(Model model) {
        Inregistrare inregistrare = new Inregistrare();
        model.addAttribute("inregistrare", inregistrare);

        return "inregistrare/new_form";
    }

    @RequestMapping(value = "/inregistrari/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("inregistrare") Inregistrare inregistrare) {
        dao.save(inregistrare);

        return "redirect:/inregistrari";
    }

    @RequestMapping("/inregistreri/edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("inregistrare/edit_form");
        Inregistrare inregistrare = dao.get(id);
        mav.addObject("inregistrare", inregistrare);

        return mav;
    }

    @RequestMapping(value = "/inregistrari/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("inregistrare") Inregistrare inregistrare) {
        dao.update(inregistrare);

        return "redirect:/inregistrari";
    }

    @RequestMapping("/inregistrari/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        dao.delete(id);
        return "redirect:/inregistrari";
    }

}
