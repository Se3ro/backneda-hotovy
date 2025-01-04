package galamb.novyeshop.controller;

import galamb.novyeshop.dto.StatisticsDTO;
import galamb.novyeshop.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistiky")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public StatisticsDTO getStatistics() {
        return statisticsService.calculateStatistics();
    }
}
