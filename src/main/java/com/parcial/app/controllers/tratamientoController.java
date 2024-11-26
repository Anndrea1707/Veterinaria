package com.parcial.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.parcial.app.models.entity.Tratamiento;


import com.parcial.app.repository.MascotaRepository;

import com.parcial.app.repository.TratamientoRepository;
import com.parcial.app.repository.VeterinarioRepository;

@Controller
@RequestMapping
public class tratamientoController {

	@Autowired
	private TratamientoRepository tratamientoRepository;

	@Autowired
	private MascotaRepository mascotaRepository;

	@Autowired
	private VeterinarioRepository veterinarioRepository;

	// Mostrar la página con la lista de Mascotas
	@GetMapping("/verMascotasTratamiento")
	public String verMascotasTratamiento(Model model) {
		model.addAttribute("verMascotas", tratamientoRepository.findAll());
		return "verMascotas";
	}

	// Mostrar la página con la lista de veterinario
	@GetMapping("/verVeterinarioTratamiento")
	public String verVeterianario(Model model) {
		model.addAttribute("veterinarios", tratamientoRepository.findAll());
		return "verVeterinario";
	}

	@GetMapping("/verTratamiento")
	public String verTratamiento(Model model) {
		List<Tratamiento> tratamiento = tratamientoRepository.findAll();
		model.addAttribute("listaTratamiento", tratamiento);
		System.out.println("Tratamiento: " + tratamiento); // Depuración
		return "verTratamiento"; // Este es el nombre de la vista (plantilla) a renderizar
	}

	@GetMapping({ "/nuevoTratamiento", "/listaTratamiento" })
	public String mostrarFormulario(Model model) {
		Tratamiento tratamiento = new Tratamiento();
		model.addAttribute("tratamiento", tratamiento);
		model.addAttribute("mascotas", mascotaRepository.findAll());
		model.addAttribute("veterinarios", veterinarioRepository.findAll());

		return "nuevoTratamiento";
	}

	@PostMapping("/guardarTratamiento")
	public String guardarTratamiento(Tratamiento tratamiento, Model model) {
		if (tratamiento.getId() != null && tratamientoRepository.existsById(tratamiento.getId())) {
			// Si el ID existe, actualiza el tratamiento
			Tratamiento tratamientoExistente = tratamientoRepository.findById(tratamiento.getId()).get();
			tratamientoExistente.setDescripcion(tratamiento.getDescripcion());
			tratamientoExistente.setFecha_Fin(tratamiento.getFecha_Fin());
			tratamientoExistente.setFecha_Inicio(tratamiento.getFecha_Inicio());
			tratamientoRepository.save(tratamientoExistente);
		} else {
			// Si el ID no existe, crea una nueva
			tratamientoRepository.save(tratamiento);
		}
		model.addAttribute("mensaje", "Tratamiento guardado correctamente.");
		return "redirect:/verTratamiento";
	}

	@GetMapping("/eliminarTratamiento/{id}")
	public String eliminarTratamiento(@PathVariable Long id, Model model) {
		try {
			tratamientoRepository.deleteById(id);
			model.addAttribute("mensaje", "Tratamiento eliminado correctamente.");
		} catch (Exception e) {
			model.addAttribute("error", "Ocurrió un error al eliminar el Tratamiento: " + e.getMessage());
		}
		return "redirect:/verTratamiento";
	}

	@GetMapping("/tratamiento/editar/{id}")
	public String modificarTratamiento(@PathVariable("id") Long id, Model model) {
		Tratamiento tratamiento = tratamientoRepository.findById(id).get();
		model.addAttribute("tratamiento", tratamiento);

		List<Tratamiento> tratamientos = tratamientoRepository.findAll();
		model.addAttribute("tratamientos", tratamientos);

		return "nuevoTratamiento";
	}
	
	// Mostrar la página con la lista de mascotas
		@GetMapping({"/verMascotasVeterinario"})
		public String verMascotasV (Model model) {
			model.addAttribute("webVeterinario", veterinarioRepository.findAll());
			return "verMascotasVeterinario";
		}

}