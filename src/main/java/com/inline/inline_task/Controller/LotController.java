package com.inline.inline_task.Controller;

import com.inline.inline_task.DTO.LotDto;
import com.inline.inline_task.Service.LotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lots")
@CrossOrigin(origins = "http://localhost:3000")

public class LotController {

    private final LotService lotService;

    public LotController(LotService lotService) {
        this.lotService = lotService;
    }

    @GetMapping
    public List<LotDto> getLots() {return lotService.findAll();}

    @GetMapping("/id/{id}")
    public List<LotDto> getLot(@PathVariable int id) {return lotService.findById(id);}

    @GetMapping("/name/{name}")
    public List<LotDto> getLotByName(@PathVariable String name) {return lotService.findByName(name);}

    @GetMapping("/code/{code}")
    public List<LotDto> getLotByCode(@PathVariable String code) {return lotService.findByCode(code);}

    @PostMapping
    public void createOrSaveLot(@RequestBody LotDto lot) {
        LotService.save(lot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLot(@RequestBody LotDto lot, @PathVariable String id) {
        try {
            System.out.println("Updating lot with ID: " + id);

            LotDto dto = lotService.update(id, lot);
            return ResponseEntity.ok(dto);

        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLot(@PathVariable int id) {
        boolean deleted = lotService.deleteById(String.valueOf(id));
        if (deleted) {
            return ResponseEntity.ok("Лот успешно удалён");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Лот с id " + id + " не найден");
        }
    }


}



