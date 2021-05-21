package com.selflearning.englishcourses.service.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FieldValidationErrorDetails {

    @JsonProperty("error_title")
    private String title;
    @JsonProperty("error_status")
    private int status;
    @JsonProperty("error_detail")
    private String detail;
    @JsonProperty("error_timestamp")
    private long timestamp;
    @JsonProperty("error_path")
    private String path;
    @JsonProperty("error_developer_message")
    private String developerMessage;
    private Map<String, List<FieldValidationError>> errors = new HashMap<>();

}
