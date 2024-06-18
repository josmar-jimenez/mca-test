package com.mca.controller;

import com.mca.controller.dto.GameDto;
import com.mca.core.usercase.SagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class SagaController {

    private final SagaService sagaService;

    @GetMapping("/{gameId}/saga")
    @ResponseBody
    public List<GameDto> getGameSaga(@PathVariable("gameId")String gameId){
        return sagaService.getSagaByGameId(gameId);
    }
}
