package pro.sky.teamwork.animalsheltertelegrambotv2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.teamwork.animalsheltertelegrambotv2.model.DailyReport;
import pro.sky.teamwork.animalsheltertelegrambotv2.service.DailyReportService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/carer/reports")
public class DailyReportController {

    private final DailyReportService dailyReportService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DailyReportController.class);

    public DailyReportController(DailyReportService dailyReportService) {
        this.dailyReportService = dailyReportService;
    }

    @Operation(summary = "Поис ежедневных отчётов по ID опекуна",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные ежедневные отчёты по ID опекуна",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(
                                                    implementation = DailyReport[].class))
                                    )
                            }
                    )
            })

    @GetMapping("/carer")
    public ResponseEntity<List<DailyReport>> findDailyReportByCarerId(
            @Parameter(description = "ID опекуна",
                    example = "1") @RequestParam(name = "Идентификатор опекуна") Long carerId) {

        var dailyReportByCarer = dailyReportService.findDailyReportByCarer(carerId);
        return ResponseEntity.ok(dailyReportByCarer);
    }

    @GetMapping("/carerdate")
    public ResponseEntity<List<DailyReport>> findDailyReportByCarerAndDate(
            @RequestParam Long carerId,
            @RequestParam LocalDate reportDate
    ) {
        LOGGER.info("Получение списка отчётов по опекуну и дате");
        var dailyReportByCarerAndDate =
                dailyReportService.findDailyReportByCarerAndDate(carerId, reportDate);
        return ResponseEntity.ok(dailyReportByCarerAndDate);
    }

    @GetMapping("/dog")
    public ResponseEntity<List<DailyReport>> findDailyReportsByDogId(
            @RequestParam(name = "Идентификатор собаки") Integer dogId
    ) {
        LOGGER.info("Получение списка отчётов по идентификатору собаки");
        var dailyReportsByDogId =
                dailyReportService.findDailyReportsByDogId(dogId);
        return ResponseEntity.ok(dailyReportsByDogId);
    }

    @GetMapping("/dogdate")
    public ResponseEntity<DailyReport> getDailyReportByDogIdOnDate(
            @Parameter(description = "Любое целое число, начиная с 0")
            @RequestParam(name = "Идентификатор собаки") Integer dogId,
            @Parameter(example = "1991-12-31",description = "Введите дату, на которую\n" +
                    "необходим отчёт")
            @RequestParam(name = "Дата отчёта") LocalDate date
    ) {
        LOGGER.info("Получение отчёта по идентификатору собаки и дате отчёта");
        var dailyReportByDogAndReportDate =
                dailyReportService.findDailyReportByDogAndReportDate(dogId, date);
        return ResponseEntity.ok(dailyReportByDogAndReportDate);
    }
}
