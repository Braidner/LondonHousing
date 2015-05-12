package org.braidner.londonhousing.response;

import java.util.Map;

/**
 * Created by smith / 13.05.2015.
 */
public class MapWardResponse {

    private Map<String, MapItResponse> response;

    public Map<String, MapItResponse> getResponse() {
        return response;
    }

    public void setResponse(Map<String, MapItResponse> response) {
        this.response = response;
    }
}
