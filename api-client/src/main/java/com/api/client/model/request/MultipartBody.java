package com.api.client.model.request;

import com.gojek.api.MultipartI;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Setter
@Getter
@Builder
public class MultipartBody implements MultipartI {
    private String controlName;
    private String contentBody;
    private String mimeType;
    private File file;
}
