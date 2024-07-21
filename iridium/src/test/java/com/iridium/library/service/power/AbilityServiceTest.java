package com.iridium.library.service.power;

import com.iridium.library.entity.power.SkillEO;
import com.iridium.library.repository.power.AbilityRepository;
import com.iridium.library.repository.power.SkillRepository;
import com.iridium.openapi.model.AddAbilityRequest;
import org.instancio.Instancio;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbilityServiceTest {

    @Autowired
    AbilityService abilityService;
    @Autowired
    AbilityRepository abilityRepository;
    @Autowired
    SkillRepository skillRepository;

    @Test
    @DisplayName("test ability save, get and delete")
    public void testSuccessCreateGetDeleteAbility() {
        final var addAbilityRequest = Instancio.of(AddAbilityRequest.class).create();
        final var abilityId = abilityService.saveAbility(addAbilityRequest);
        final var abilityEO = abilityRepository.findByIdWithSkills(abilityId).orElse(null);
        assertNotNull(abilityEO);
        assertEquals(addAbilityRequest.getAbilityType().toString(), abilityEO.getAbilityType().toString());
        assertEquals(addAbilityRequest.getDescription(), abilityEO.getDescription());
        assertEquals(addAbilityRequest.getName(), abilityEO.getName());
        assertEquals(addAbilityRequest.getSkills().size(), abilityEO.getSkills().size());

        final var ability = abilityService.getAbilityById(abilityId);
        assertNotNull(ability);
        assertEquals(ability.getAbilityType().toString(), abilityEO.getAbilityType().toString());
        assertEquals(ability.getDescription(), abilityEO.getDescription());
        assertEquals(ability.getName(), abilityEO.getName());
        final var skills = ability.getSkills();
        final var skillEOs = abilityEO.getSkills();
        assertEquals(skills.size(), skillEOs.size());
        skills.forEach(skill -> {
            final SkillEO skillEO = skillEOs.stream().filter(sk -> sk.getId().equals(skill.getId())).findFirst().orElse(null);
            assertNotNull(skillEO);
            assertEquals(skill.getDescription(), skillEO.getDescription());
            assertEquals(skill.getMinLevel(), skillEO.getMinLevel());
        });

        abilityService.deleteAbility(abilityId);
        assertNull(abilityRepository.findById(abilityId).orElse(null));
        assertTrue(skillRepository.findByIdIn(abilityEO.getSkills().stream().map(SkillEO::getId).toList()).isEmpty());
    }
}
