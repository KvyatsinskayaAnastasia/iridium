package com.iridium.library.service.power.impl;

import com.iridium.library.entity.power.MagicEO;
import com.iridium.library.mapper.power.MagicMapper;
import com.iridium.library.repository.power.MagicRepository;
import com.iridium.library.service.power.MagicService;
import com.iridium.openapi.model.Magic;
import com.iridium.openapi.model.MagicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Base service to work with magic model.
 * See the {@link com.iridium.library.service.power.MagicService}.
 * @author Kviatsinskaya Anastasia
 */
@Service
@RequiredArgsConstructor
public class BaseMagicService implements MagicService {

    /**
     * See the {@link com.iridium.library.repository.power.MagicRepository}.
     */
    private final MagicRepository magicRepository;
    /**
     * See the {@link com.iridium.library.mapper.power.MagicMapper}.
     */
    private final MagicMapper magicMapper;

    @Override
    public final UUID saveMagic(final Magic magic) {
        return magicRepository.save(magicMapper.toMagicEO(magic)).getId();
    }

    @Override
    public final List<MagicResponse> getAllMagic() {
        return magicMapper.toMagicResponseList(magicRepository.findAll());
    }

    @Override
    public final MagicResponse getMagicById(final UUID id) {
        final Optional<MagicEO> magicOptional = magicRepository.findById(id);
        return magicOptional.map(magicMapper::toMagicResponse).orElse(null);
    }
}
