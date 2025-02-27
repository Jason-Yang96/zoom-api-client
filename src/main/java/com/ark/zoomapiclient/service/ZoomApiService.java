package com.ark.zoomapiclient.service;

import com.ark.zoomapiclient.authentication.ZoomAuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ZoomApiService {
    @Autowired
    private ZoomAuthenticationHelper zoomAuthenticationHelper;

    @Autowired
    RestTemplate restTemplate;

    @Value("${zoom.oauth2.api-url}")
    private String zoomApiUrl;
    private static String BEARER_AUTHORIZATION = "Bearer %s";

    private static final String ZOOM_USER_BASE_URL_REPORT = "%s/v2/report";


    public ResponseEntity<String> getAllParticipants() {
        ResponseEntity<String> response = null;
        try {
            HttpHeaders authHeader = createBearerAuthHeader(zoomAuthenticationHelper.getAuthenticationToken());
            HttpEntity<String> entity = new HttpEntity<String>(authHeader);
            response = restTemplate.exchange(getReportParticipantListUrl(), HttpMethod.GET, entity, String.class);
            return response;
        } catch (Exception e) {
            //sout is used for demo purposes you could use @Slf4j
            System.out.println(String.format("%s 때문에 참가 인원을 불러올 수가 없습니다. 응답 코드: %d", e.getMessage(), response.getStatusCode()));
            e.printStackTrace();
        }
        return response;
    }

    public String getReportParticipantListUrl() {
        StringBuilder sb = new StringBuilder(
                String.format(ZOOM_USER_BASE_URL_REPORT, zoomApiUrl));
        sb.append("/meetings/6378707237/participants");
        return sb.toString();
    }

    public static HttpHeaders createBearerAuthHeader(String token) {
        HttpHeaders headers = createHTTPHeader();
        String authToken = String.format(BEARER_AUTHORIZATION, token);
        headers.set(HttpHeaders.AUTHORIZATION, authToken);
        return headers;
    }

    private static HttpHeaders createHTTPHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


}
