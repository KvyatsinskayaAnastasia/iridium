package com.iridium.library.controller.character;

import com.iridium.library.service.character.impl.BaseRaceService;
import com.iridium.openapi.api.RaceApi;
import com.iridium.openapi.model.RaceRequest;
import com.iridium.openapi.model.RaceResponse;
import com.iridium.openapi.model.ShortRaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RaceController implements RaceApi {

    private final BaseRaceService raceService;

    @Override
    public final ResponseEntity<UUID> addRace(final RaceRequest race) {
        return ResponseEntity.status(HttpStatus.CREATED).body(raceService.saveRace(race));
    }

    @Override
    public final ResponseEntity<List<ShortRaceResponse>> getAllRaces() {
        return ResponseEntity.ok(raceService.getAllRaces());
    }

    @Override
    public final ResponseEntity<RaceResponse> getRace(final UUID id) {
        return ResponseEntity.ok(raceService.getRaceById(id, RaceResponse.class));
    }

}
