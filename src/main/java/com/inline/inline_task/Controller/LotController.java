package com.inline.inline_task.Controller;

import com.inline.inline_task.Service.LotService;
import jooqdata.tables.Lot;
import jooqdata.tables.records.LotRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lots")

public class LotController {

    private final LotService lotService;

    public LotController(LotService lotService) {
        this.lotService = lotService;
    }

    @GetMapping
    public List<LotRecord> getLots() {return lotService.findAll();}

    @GetMapping("/{id}")
    public LotRecord getLot(@PathVariable int id) {return lotService.findById(id);}

    @GetMapping("/{name}")
    public LotRecord getLotByName(@PathVariable String name) {return lotService.findByName(name);}

    @GetMapping("/{code}")
    public LotRecord getLotByCode(@PathVariable String code) {return lotService.findByCode(code);}

}
