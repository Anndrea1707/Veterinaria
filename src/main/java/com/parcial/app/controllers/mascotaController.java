package com.parcial.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcial.app.models.entity.Mascota;
import com.parcial.app.models.entity.Propietario;
import com.parcial.app.repository.MascotaRepository;
import com.parcial.app.repository.PropietarioRepository;


@Controller
@RequestMapping
public class mascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;
    
    @Autowired
    private PropietarioRepository propietarioRepository;
    
    
    // Mostrar la página con la lista de propietarios
    @GetMapping({ "/mostrarWebPropietario", "/webPropietarios" })
    public String webPropietario(Model model) {
        model.addAttribute("webPropietario", mascotaRepository.findAll());
        return "webPropietario";
    }
    
    @GetMapping("/verMascotas")
    public String verMascotas (Model model) {
        List<Mascota> mascotas = mascotaRepository.findAll();
        model.addAttribute("listaMascotas", mascotas);
        System.out.println("Mascotas: " + mascotas); // Depuración
        return "verMascotas"; // Este es el nombre de la vista (plantilla) a renderizar
    }
    
    @GetMapping({"/nuevaMascota", "/listaMascotas"})
    public String mostrarFormulario (Model model) {
    	Mascota mascota = new Mascota();
    	model.addAttribute("mascota", mascota);
    	model.addAttribute("propietarios", propietarioRepository.findAll());
    	
    	return "nuevaMascota";
    }

    // Mostrar el formulario para crear una nueva mascota
    //@GetMapping("/nuevaMascota")
    //public String nuevoPropietario(Model model) {
    //  model.addAttribute("mascota", new Mascota());
    //  return "nuevaMascota"; // Esto busca la plantilla templates/nuevoPropietario.html
    //}
    
    @PostMapping("/guardarMascota")
    public String guardarMascota(Mascota mascota) {
        if (mascota.getId() != null && mascotaRepository.existsById(mascota.getId())) {
            // Si el ID existe, actualiza la mascota
            Mascota mascotaExistente = mascotaRepository.findById(mascota.getId()).get();
            mascotaExistente.setNombre(mascota.getNombre());
            mascotaExistente.setTipo(mascota.getTipo());
            mascotaExistente.setRaza(mascota.getRaza());
            mascotaExistente.setEdad(mascota.getEdad());
            mascotaExistente.setPropietario(mascota.getPropietario());
            mascotaRepository.save(mascotaExistente);
        } else {
            // Si el ID no existe, crea una nueva
            mascotaRepository.save(mascota);
        }
        return "redirect:/verMascotas";
    }
    // Procesar el formulario y guardar la nueva mascota
    //@PostMapping("/guardarMascota")
    //public String guardarMascota(@Valid @ModelAttribute Mascota mascota, BindingResult result, Model model) {
    //    System.out.println("Guardando mascota: " + mascota.getNombre()); // Depuración
    //    if (result.hasErrors()) {
    //        model.addAttribute("mascota", mascota);
    //        return "nuevaMascota"; // Si hay errores, se devuelve al formulario
    //    }

    //    mascotaRepository.save(mascota); // Guardar en la base de datos
    //    return "redirect:/webPropietario"; // Redirigir a la lista de propietarios
    //}
    
    //Metodo eliminar mascotas
    @GetMapping("/eliminarMascota/{id}")
    public String eliminarMascota(@PathVariable Long id, Model model) {
        try {
            mascotaRepository.deleteById(id);
            model.addAttribute("mensaje", "Mascota eliminada correctamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al eliminar la mascota: " + e.getMessage());
        }
        return "redirect:/verMascotas";
    }
    
    @GetMapping("/mascota/editar/{id}")
    public String modificarMascota(@PathVariable("id") Long id, Model model) {
        Mascota mascota = mascotaRepository.findById(id).get();
        model.addAttribute("mascota", mascota);
        
        List<Propietario> propietarios = propietarioRepository.findAll();
        model.addAttribute("propietarios", propietarios);
        
        return "nuevaMascota";
    }
    
}