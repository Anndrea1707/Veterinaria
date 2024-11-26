package com.parcial.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcial.app.models.entity.Cita;
import com.parcial.app.repository.CitaRepository;
import com.parcial.app.repository.MascotaRepository;
import com.parcial.app.repository.VeterinarioRepository;
import com.parcial.app.repository.PropietarioRepository;
import com.parcial.app.repository.TratamientoRepository;

@Controller
@RequestMapping("/citas")
public class citaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;
    
    @Autowired
    private TratamientoRepository tratamientoRepository;

    // Mostrar todas las citas
    @GetMapping("/verCitas")
    public String verCitas(Model model) {
        List<Cita> citas = citaRepository.findAll();
        model.addAttribute("listaCitas", citas);
        System.out.println("Citas: " + citas); // Depuración
        return "verCitas"; // Vista que muestra todas las citas
    }

    // Mostrar la página para registrar una nueva cita
    @GetMapping({"/nuevaCita", "listaCitas"})
    public String nuevaCita(Model model) {
        Cita cita = new Cita();
        model.addAttribute("cita", cita);
        model.addAttribute("mascotas", mascotaRepository.findAll());
        model.addAttribute("veterinarios", veterinarioRepository.findAll());
        model.addAttribute("propietarios", propietarioRepository.findAll());
        model.addAttribute("tratamientos", tratamientoRepository.findAll());
        return "nuevaCita"; // Vista para crear una nueva cita
    }

    // Guardar o actualizar una cita
    @PostMapping("/guardarCita")
    public String guardarCita(Cita cita, Model model) {
        if (cita.getId() != null && citaRepository.existsById(cita.getId())) {
            // Si el ID existe, actualizar la cita
            Cita citaExistente = citaRepository.findById(cita.getId()).get();
            citaExistente.setFecha(cita.getFecha());
            citaExistente.setHora(cita.getHora());
            citaExistente.setMotivo(cita.getMotivo());
            citaExistente.setPropietario(cita.getPropietario());
            citaExistente.setMascota(cita.getMascota());
            citaExistente.setVeterinario(cita.getVeterinario());
            citaExistente.setTratamiento(cita.getTratamiento());
            citaRepository.save(citaExistente);
        } else {
            // Si el ID no existe, crear una nueva cita
            citaRepository.save(cita);
        }
        model.addAttribute("mensaje", "Cita guardada correctamente.");
        return "redirect:/verCitas"; // Redirigir a la vista de las citas
    }

    // Eliminar una cita
    @GetMapping("/eliminarCita/{id}")
    public String eliminarCita(@PathVariable Long id, Model model) {
        try {
            citaRepository.deleteById(id);
            model.addAttribute("mensaje", "Cita eliminada correctamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al eliminar la cita: " + e.getMessage());
        }
        return "redirect:/verCitas"; // Redirigir a la vista de las citas
    }

    // Editar una cita
    @GetMapping("/editarCita/{id}")
    public String editarCita(@PathVariable("id") Long id, Model model) {
        Cita cita = citaRepository.findById(id).get();
        model.addAttribute("cita", cita);

        // Listar las mascotas, veterinarios y propietarios para el formulario
        model.addAttribute("mascotas", mascotaRepository.findAll());
        model.addAttribute("veterinarios", veterinarioRepository.findAll());
        model.addAttribute("propietarios", propietarioRepository.findAll());
        model.addAttribute("tratamientos", tratamientoRepository.findAll());

        return "nuevaCita"; // Vista para editar una cita
    }

    // Ver detalles de una cita específica
    @GetMapping("/verCitas/{id}")
    public String verCita(@PathVariable("id") Long id, Model model) {
        Cita cita = citaRepository.findById(id).get();
        model.addAttribute("cita", cita);
        return "verCitas"; // Vista para mostrar detalles de una cita
    }
}
