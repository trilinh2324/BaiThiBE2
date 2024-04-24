package com.baithi.controller;

import com.baithi.model.Personnel;
import com.baithi.service.IPersonnelSevice;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/personnel")
public class PersonnelController {
    @Autowired
    private IPersonnelSevice iPersonnelSevice;

    @GetMapping
    public ModelAndView listCustomer() {
        Iterable<Personnel> personnels = iPersonnelSevice.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("personnels", personnels);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("personnels", new Personnel());
        return modelAndView;
    }

    @PostMapping("create")
    public String create(@Validated @ModelAttribute("customer") Personnel personnel,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        iPersonnelSevice.save(personnel);
        redirectAttributes.addFlashAttribute("message", "Thêm thành công");
        return "redirect:/personnel";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable int id) {
        Optional<Personnel> personnels = iPersonnelSevice.findById(id);
        if (personnels.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("update");
            modelAndView.addObject("personnels", personnels.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@Validated @ModelAttribute("personnels") Personnel personnels,
                         BindingResult bindingResult,
                         RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        iPersonnelSevice.save(personnels);
        redirect.addFlashAttribute("message", "Sửa thành công");
        return "redirect:/personnel";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        iPersonnelSevice.remove(id);
        redirect.addFlashAttribute("message", "Xóa thành công");
        return "redirect:/personnel";
    }
    @GetMapping("/view/{id}")
    public ModelAndView viewForm(@PathVariable int id) {
        Optional<Personnel> personnels = iPersonnelSevice.findById(id);
        if (personnels.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("view");
            modelAndView.addObject("personnels", personnels.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }


}





