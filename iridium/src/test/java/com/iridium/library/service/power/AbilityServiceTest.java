package com.iridium.library.service.power;

import com.iridium.library.entity.power.AbilityEO;
import com.iridium.library.entity.power.SkillEO;
import com.iridium.library.repository.power.AbilityRepository;
import com.iridium.library.repository.power.SkillRepository;
import com.iridium.openapi.model.AddAbilityRequest;
import com.iridium.openapi.model.Skill;
import org.instancio.Instancio;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.instancio.Select.field;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbilityServiceTest {

    @Autowired
    private AbilityService abilityService;
    @Autowired
    private AbilityRepository abilityRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Test
    public void testSaveAbility() {
        final var addAbilityRequest = Instancio.of(AddAbilityRequest.class).create();
        final var abilityId = abilityService.saveAbility(addAbilityRequest);
        final var abilityEO = abilityRepository.findByIdWithSkills(abilityId).orElse(null);
        assertNotNull(abilityEO);
        assertEquals(addAbilityRequest.getAbilityType().toString(), abilityEO.getAbilityType().toString());
        assertEquals(addAbilityRequest.getDescription(), abilityEO.getDescription());
        assertEquals(addAbilityRequest.getName(), abilityEO.getName());
        assertEquals(addAbilityRequest.getSkills().size(), abilityEO.getSkills().size());
    }

    @Test
    public void testGetAbilityById() {
        final var abilityEO = Instancio.of(AbilityEO.class).ignore(field(AbilityEO::getId)).create();
        abilityEO.getSkills().forEach(skillEO -> skillEO.setAbility(abilityEO));
        final var savedAbility = abilityRepository.save(abilityEO);

        final var ability = abilityService.getAbilityById(savedAbility.getId());
        assertNotNull(ability);
        assertEquals(ability.getAbilityType().toString(), savedAbility.getAbilityType().toString());
        assertEquals(ability.getDescription(), savedAbility.getDescription());
        assertEquals(ability.getName(), savedAbility.getName());
        final var skills = ability.getSkills();
        final var skillEOs = savedAbility.getSkills();
        assertEquals(skills.size(), skillEOs.size());
        skills.forEach(skill -> {
            final SkillEO skillEO = skillEOs.stream().filter(sk -> sk.getId().equals(skill.getId())).findFirst().orElse(null);
            assertNotNull(skillEO);
            assertEquals(skill.getDescription(), skillEO.getDescription());
            assertEquals(skill.getMinLevel(), skillEO.getMinLevel());
        });
    }

    @Test
    public void testDeleteAbility() {
        final var abilityEO = Instancio.of(AbilityEO.class).ignore(field(AbilityEO::getId)).create();
        abilityEO.getSkills().forEach(skillEO -> skillEO.setAbility(abilityEO));
        final var savedAbility = abilityRepository.save(abilityEO);

        abilityService.deleteAbility(savedAbility.getId());
        assertNull(abilityRepository.findById(savedAbility.getId()).orElse(null));
        assertTrue(skillRepository.findByIdIn(savedAbility.getSkills().stream().map(SkillEO::getId).toList()).isEmpty());
    }
}
