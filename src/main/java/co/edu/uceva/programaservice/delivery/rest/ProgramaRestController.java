package co.edu.uceva.programaservice.delivery.rest;

import co.edu.uceva.programaservice.domain.model.Programa;
import co.edu.uceva.programaservice.domain.services.IProgramaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.edu.uceva.programaservice.domain.exception.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/programa-service")
public class ProgramaRestController {

    private final IProgramaService programaservice;
    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String PROGRAMA = "programa";
    private static final String PROGRAMAS = "programas";

    public ProgramaRestController(IProgramaService facultadService) {
        this.programaservice = facultadService;
    }

    @GetMapping("/programas")
    public ResponseEntity<Map<String, Object>> getProgramas() {
        List<Programa> facultades = programaservice.findAll();
        if (facultades.isEmpty()) {
            throw new NoHayProgramasException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(PROGRAMAS, facultades);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/programa/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Programa> programas = programaservice.findAll(pageable);
        if (programas.isEmpty()) {
            throw new PaginaSinProgramaException(page);
        }
        return ResponseEntity.ok(programas);
    }


    @PostMapping("/programas")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Programa programa, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        Programa nuevaPrograma = programaservice.save(programa);
        response.put(MENSAJE, "El programa ha sido creada con éxito!");
        response.put(PROGRAMA, nuevaPrograma);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/programas")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Programa programa) {
        programaservice.findById(programa.getId())
                .orElseThrow(() -> new ProgramaNoEncontradoException(programa.getId()));
        programaservice.delete(programa);
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El programa ha sido eliminada con éxito!");
        response.put(PROGRAMA, null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/programas")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Programa programa, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        programaservice.findById(programa.getId())
                .orElseThrow(() -> new ProgramaNoEncontradoException(programa.getId()));
        Map<String, Object> response = new HashMap<>();
        Programa programaActualizado = programaservice.update(programa);
        response.put(MENSAJE, "La programa ha sido actualizada con éxito!");
        response.put(PROGRAMA, programaActualizado);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/programas/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Programa programa = programaservice.findById(id)
                .orElseThrow(() -> new ProgramaNoEncontradoException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "el programa ha sido encontrada con éxito!");
        response.put(PROGRAMA, programa);
        return ResponseEntity.ok(response);
    }




}
