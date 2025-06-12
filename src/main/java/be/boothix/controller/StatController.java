package be.boothix.controller;

import be.boothix.dto.ClientDTO;
import be.boothix.dto.StatDTO;
import be.boothix.service.StatService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders = "Location")
@RestController
@RequestMapping(value = "/api/stat")
public class StatController {

    private StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping
    public StatDTO getAll() {
        return this.statService.getAllStat();
    }

}
