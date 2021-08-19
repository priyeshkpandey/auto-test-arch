package com.api.client.model.request;

import com.gojek.api.APIRequestBody;
import com.gojek.api.MultipartI;
import com.gojek.api.RequestBodyType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
public class GenericAPIRequestBody implements APIRequestBody {
    private RequestBodyType type;
    private Object object;
    private List<MultipartI> multipartBodyList;
}
