package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.domain.PhraseDetail;
import com.selflearning.englishcourses.service.PhraseDetailService;
import com.selflearning.englishcourses.service.dto.PhraseDetailDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PhraseDetailServiceImpl implements PhraseDetailService {

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PhraseDetail convertDtoToEntity(PhraseDetailDto phraseDetailDto) {
        return modelMapper.map(phraseDetailDto, PhraseDetail.class);
    }

    @Override
    public List<PhraseDetail> convertDtoToEntity(List<PhraseDetailDto> phraseDetailDtos) {
        return phraseDetailDtos.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
    }

    @Override
    public PhraseDetailDto convertEntityToDto(PhraseDetail phraseDetail) {
        return modelMapper.map(phraseDetail, PhraseDetailDto.class);
    }

    @Override
    public List<PhraseDetailDto> convertEntityToDto(List<PhraseDetail> phraseDetails) {
        return phraseDetails.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Page<PhraseDetailDto> convertEntityPageToDtoPage(Page<PhraseDetail> phraseDetailPage) {
        return new PageImpl<>(convertEntityToDto(phraseDetailPage.getContent()),
                phraseDetailPage.getPageable(),
                phraseDetailPage.getTotalElements());
    }
}
