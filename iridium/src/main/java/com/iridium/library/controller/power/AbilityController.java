package com.iridium.library.controller.power;

import com.iridium.library.service.power.impl.BaseAbilityService;
import com.iridium.openapi.api.AbilityApi;
import com.iridium.openapi.model.Ability;
import com.iridium.openapi.model.AbilityType;
import com.iridium.openapi.model.AddAbility;
import com.iridium.openapi.model.ShortAbilityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AbilityController implements AbilityApi {

    private final BaseAbilityService abilityService;

    @Override
    public final ResponseEntity<UUID> addAbility(final AddAbility ability) {
        return ResponseEntity.ok(abilityService.saveAbility(ability));
        //todo: check info about http statuses (created at first)
    }

    @Override
    public final ResponseEntity<List<ShortAbilityResponse>> getAllAbilities(final AbilityType abilityType) {
        if (null != abilityType) {
            return ResponseEntity.ok(abilityService.getAllAbilitiesByType(abilityType));
        }
        return ResponseEntity.ok(abilityService.getAllAbilities());
    }

    @Override
    public final ResponseEntity<Ability> getAbility(final UUID id) {
        return ResponseEntity.ok(abilityService.getAbilityById(id));
    }
}
