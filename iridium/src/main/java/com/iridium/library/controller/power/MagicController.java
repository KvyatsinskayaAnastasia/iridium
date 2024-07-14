package com.iridium.library.controller.power;

import com.iridium.library.service.power.impl.BaseMagicService;
import com.iridium.openapi.api.MagicApi;
import com.iridium.openapi.model.Magic;
import com.iridium.openapi.model.MagicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MagicController implements MagicApi {

    /**
     * See {@link com.iridium.library.service.power.MagicService}.
     */
    private final BaseMagicService magicService;

    @Override
    public final ResponseEntity<UUID> addMagic(final Magic magic) {
        return ResponseEntity.status(HttpStatus.CREATED).body(magicService.saveMagic(magic));
    }

    @Override
    public final ResponseEntity<List<MagicResponse>> getAllMagic() {
        return ResponseEntity.ok(magicService.getAllMagic());
    }

    @Override
    public final ResponseEntity<MagicResponse> getMagic(final UUID id) {
        return ResponseEntity.ok(magicService.getMagicById(id));
    }
}
