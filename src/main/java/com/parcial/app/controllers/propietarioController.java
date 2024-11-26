package com.parcial.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcial.app.models.entity.Propietario;
import com.parcial.app.repository.CitaRepository;
import com.parcial.app.repository.MascotaRepository;
import com.parcial.app.repository.PropietarioRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping
public class propietarioController {

    @Autowired
    private PropietarioRepository propietarioRepository;
    
    @Autowired
    private MascotaRepository mascotaRepository;
    
    @Autowired
    private CitaRepository citaRepository;

    // Mostrar la página con la lista de propietarios
    @GetMapping({ "/mostrarWebPropietario", "/webPropietario" })
    public String webPropietario(Model model) {
        model.addAttribute("webPropietario", propietarioRepository.findAll());
        return "webPropietario";
    }
    
    // Nueva ruta para mostrar los propietarios en una página diferente
    @GetMapping("/verPropietario")
    public String verPropietarios(Model model) {
        List<Propietario> propietarios = propietarioRepository.findAll();
        model.addAttribute("webPropietario", propietarioRepository.findAll());
        System.out.println("Propietarios: " + propietarios); // Depuración
        return "verPropietario"; // Este es el nombre de la vista (plantilla) a renderizar
    }
    
    // Mostrar el formulario para crear un nuevo propietario
    @GetMapping("/nuevoPropietario")
    public String nuevoPropietario(Model model) {
        model.addAttribute("propietario", new Propietario());
        return "nuevoPropietario"; // Esto busca la plantilla templates/nuevoPropietario.html
    }

    // Procesar el formulario y guardar el nuevo propietario
    @PostMapping("/guardarPropietario")
    public String guardarPropietario(@Valid @ModelAttribute Propietario propietario, BindingResult result, Model model) {
        System.out.println("Guardando propietario: " + propietario.getNombre()); // Depuración
        if (result.hasErrors()) {
            model.addAttribute("propietario", propietario);
            return "nuevoPropietario"; // Si hay errores, se devuelve al formulario
        }

        propietarioRepository.save(propietario); // Guardar en la base de datos
        return "redirect:/webPropietario"; // Redirigir a la lista de propietarios
    }
    
    //Metodo editar propietario
    @GetMapping("/editarPropietario/{id}")
    public String editarPropietario(@PathVariable Long id, Model model) {
        Propietario propietarioExistente = propietarioRepository.findById(id).orElse(null);
        if (propietarioExistente != null) {
            model.addAttribute("propietario", propietarioExistente);
            return "nuevoPropietario"; // Redirige a la vista para editar
        } else {
            model.addAttribute("error", "Propietario no encontrado.");
            return "redirect:/webPropietario"; // Redirige a la lista si no se encuentra
        }
    }

    //Metodo eliminar propietario
    @GetMapping("/eliminar/{id}")
    public String eliminarPropietario(@PathVariable Long id, Model model) {
        try {
            propietarioRepository.deleteById(id);
            model.addAttribute("mensaje", "Propietario eliminado correctamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al eliminar el propietario: " + e.getMessage());
        }
        return "redirect:/verPropietario";
    }

    
    @GetMapping("/verMascotitas")
    public String verMascotas (Model model) {
        model.addAttribute("mascotas", mascotaRepository.findAll());
        return "verMascotas";
    }

 // Mostrar la página con la lista de veterinarios
 		@GetMapping({"/verVeterinarioPropietario"})
 		public String verMascotasV (Model model) {
 			model.addAttribute("webVeterinario", propietarioRepository.findAll());
 			return "verVeterinarioPropietario";
 		}

 	// Mostrar todas las citas
 	    @GetMapping("/verCitas")
 	    public String verCitas(Model model) {
 	        model.addAttribute("citas", citaRepository.findAll());
 	        return "verCitas"; // Vista que muestra todas las citas
 	    }
}
