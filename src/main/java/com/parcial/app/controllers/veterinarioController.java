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


import com.parcial.app.models.entity.Veterinario;
import com.parcial.app.repository.VeterinarioRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping
public class veterinarioController {

	@Autowired
	private VeterinarioRepository veterinarioRepository;

	// Mostrar la página con la lista de propietarios
	@GetMapping({ "/mostrarWebVeterinario", "/webVeterinario" })
	public String webVeterinario(Model model) {
		model.addAttribute("webVeterinario", veterinarioRepository.findAll());
		return "webVeterinario";
	}
	
	// Mostrar la página con la lista de propietarios
	@GetMapping({"/verPropietarioVeterinario"})
	public String verPropietario(Model model) {
		model.addAttribute("webVeterinario", veterinarioRepository.findAll());
		return "verPropietarioVeterinario";
	}

	@GetMapping("/verVeterinario") // Mantener esta ruta sin cambios en veterinarioController
	public String verVeterinario(Model model) {
	    List<Veterinario> veterinario = veterinarioRepository.findAll();
	    model.addAttribute("webVeterinario", veterinarioRepository.findAll());
	    System.out.println("Veterinario: " + veterinario); // Depuración
	    return "verVeterinario"; // Este es el nombre de la vista (plantilla) a renderizar
	}

	// Mostrar el formulario para crear un nuevo propietario
	@GetMapping("/nuevoVeterinario")
	public String nuevoVeterinario(Model model) {
		model.addAttribute("veterinario", new Veterinario());
		return "nuevoVeterinario"; // Esto busca la plantilla templates/nuevoPropietario.html
	}

	// Procesar el formulario y guardar el nuevo propietario
	@PostMapping("/guardarVeterinario")
	public String guardarVeterinario(@Valid @ModelAttribute Veterinario veterinario, BindingResult result,
			Model model) {
		System.out.println("Guardando veterinario: " + veterinario.getNombre()); // Depuración
		if (result.hasErrors()) {
			model.addAttribute("veterinario", veterinario);
			return "nuevoVeterinario"; // Si hay errores, se devuelve al formulario
		}

		veterinarioRepository.save(veterinario); // Guardar en la base de datos
		return "redirect:/webVeterinario"; // Redirigir a la lista de propietarios
	}

	// Metodo editar propietario
	@GetMapping("/editarVeterinario/{id}")
	public String editarVeterinario(@PathVariable Long id, Model model) {
		Veterinario veterinarioExistente = veterinarioRepository.findById(id).orElse(null);
		if (veterinarioExistente != null) {
			model.addAttribute("veterinario", veterinarioExistente);
			return "nuevoVeterinario"; // Redirige a la vista para editar
		} else {
			model.addAttribute("error", "Veterinario no encontrado.");
			return "redirect:/webVeterinario"; // Redirige a la lista si no se encuentra
		}

	}

	// Metodo eliminar veterinario
	@GetMapping("/veterinario/eliminar/{id}")
	public String eliminarVeterinario(@PathVariable Long id, Model model) {
		try {
			veterinarioRepository.deleteById(id);
			model.addAttribute("mensaje", "Veterinario eliminado correctamente.");
		} catch (Exception e) {
			model.addAttribute("error", "Ocurrió un error al eliminar el Veterinario: " + e.getMessage());
		}
		return "redirect:/verVeterinario";
	}
}
