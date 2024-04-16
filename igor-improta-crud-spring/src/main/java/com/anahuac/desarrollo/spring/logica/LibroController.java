package com.anahuac.desarrollo.spring.logica;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anahuac.desarrollo.spring.entidades.Libro;
import com.anahuac.desarrollo.spring.servicios.LibroCRUDService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class LibroController {
    @Autowired
    private LibroCRUDService libroService;

    @GetMapping("/index")
    public String showUsersList(Model modelo){
        modelo.addAttribute("libros", libroService.obtenerTodosLibros());
        return "index";
    }

    @RequestMapping(value="delete/{id}")
    public String delete(@PathVariable Integer id){
        libroService.borrarLibroId(id);
        return "redirect:/index";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("libro", new Libro());
        return "add-libro";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("libro") @Validated Libro libro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-libro";
        }

        int nextId = libroService.obtenerMaxId() + 1;
        libro.setId(nextId);

        libroService.insertarLibro(libro);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Integer id, Model model) {
        Libro libro = libroService.obtenerLibroId(id);
        model.addAttribute("libro", libro);
        return "update-libro";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("libro") @Validated Libro libro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-libro";
        }

        libro.setId(id);

        libroService.modificarLibro(libro);
        return "redirect:/index";
    }
}
