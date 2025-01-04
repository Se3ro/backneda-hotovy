package galamb.novyeshop.controller;

import galamb.novyeshop.dto.StatisticsDTO;
import galamb.novyeshop.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketStatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @Scheduled(fixedRate = 5000) // Aktualizace každých 5 sekund
    @SendTo("/topic/statistiky")
    public StatisticsDTO sendStatisticsUpdate() {
        return statisticsService.calculateStatistics();
    }
}
