package com.selflearning.englishcourses.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VocabularyDto extends BaseDto {

    private UUID id;
    private WordDto word;
    private WordClassDto wordClass;
    private String description;
    private String meaning;
    private String imagePath;

}
