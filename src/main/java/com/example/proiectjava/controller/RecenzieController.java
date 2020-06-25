package com.example.proiectjava.controller;

import com.example.proiectjava.dao.RecenzieDAO;
import com.example.proiectjava.model.Recenzie;
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
public class RecenzieController {

    @Autowired
    private RecenzieDAO dao;

    @RequestMapping("/recenzii")
    public String viewHomePage(Model model) {
        List<Recenzie> listRecenzie = dao.list();
        model.addAttribute("listRecenzie", listRecenzie);
        return "recenzie/show";
    }

    @RequestMapping("/recenzii/new")
    public String showNewForm(Model model) {
        Recenzie recenzie = new Recenzie();
        model.addAttribute("recenzie", recenzie);

        return "recenzie/new_form";
    }

    @RequestMapping(value = "/recenzii/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("recenzie") Recenzie recenzie) {
        dao.save(recenzie);

        return "redirect:/recenzii";
    }

    @RequestMapping("/recenzii/edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("recenzie/edit_form");
        Recenzie recenzie = dao.get(id);
        mav.addObject("recenzie", recenzie);

        return mav;
    }

    @RequestMapping(value = "/recenzii/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("recenzie") Recenzie recenzie) {
        dao.update(recenzie);

        return "redirect:/recenzii";
    }

    @RequestMapping("/recenzii/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        dao.delete(id);
        return "redirect:/recenzii";
    }

}
