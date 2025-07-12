package com.inline.inline_task.Controller;

import com.inline.inline_task.DTO.LotDto;
import com.inline.inline_task.Service.LotService;
import jooqdata.tables.Lot;
import jooqdata.tables.records.LotRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lots")

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

}



