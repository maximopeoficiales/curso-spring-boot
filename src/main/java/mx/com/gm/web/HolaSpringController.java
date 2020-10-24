package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.service.PersonaServiceImpl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class HolaSpringController {
    @Autowired
    private PersonaServiceImpl personaService;

    @GetMapping("/")
    public String inicio(Model model) {
        var personas = personaService.listarPersonas();
        log.info("ejecutando el controlador Spring MVC");
        model.addAttribute("personas", personas);
        return "index";
    }

    @GetMapping("/add")
    public String add(Persona persona) {
        return "add";
    }

    // se agrego validaciones
    @PostMapping("/save")
    public String save(@Valid Persona persona, Errors errors) {
        if (errors.hasErrors()) {
            return "add";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }

    /*
     * internamente hace una instancia como tambien obtiene el valor del parametro y
     * lo agrego a su propiedad
     */
    @GetMapping("/edit/{idPersona}")
    public String add(Persona persona, Model model) {
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "add";
    }

    @GetMapping("/delete/{idPersona}")
    public String delete(Persona persona, Model model) {
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
