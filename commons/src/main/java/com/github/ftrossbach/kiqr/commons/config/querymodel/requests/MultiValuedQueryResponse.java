package com.github.ftrossbach.kiqr.commons.config.querymodel.requests;

import java.util.List;

/**
 * Created by ftr on 20/02/2017.
 */
public class MultiValuedQueryResponse extends AbstractQueryResponse{

    private List<byte[]> values;

    public MultiValuedQueryResponse() {
    }

    public MultiValuedQueryResponse(QueryStatus status, List<byte[]> values) {
        super(status);
        this.values = values;
    }

    public List<byte[]> getValues() {
        return values;
    }

    public void setValues(List<byte[]> values) {
        this.values = values;
    }
}
