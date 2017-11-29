package com.hpe.itsma.bo.ats.agent;

import com.google.gson.Gson;
import com.hpe.itsma.bo.common.exception.ForbiddenException;
import com.hpe.itsma.bo.common.exception.NoPermissionException;
import com.hpe.itsma.bo.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.InvalidParameterException;
import java.text.MessageFormat;

/**
 * Created by wxiaodon on 8/24/2017.
 */
public abstract class BaseAgent {

    Logger logger = LoggerFactory.getLogger(BaseAgent.class);

    private static final String PATH_FORMAT = "{0}://{1}:{2}{3}{4}";

    public <T> T doGet(String url, ParameterizedTypeReference<T> pTypeReference) {
        return exchange(url, HttpMethod.GET, null, pTypeReference);
    }

    public <T> T doPost(String url, Object body, ParameterizedTypeReference<T> pTypeReference) {
        return exchange(url, HttpMethod.POST, body, pTypeReference);
    }

    public <T> T doPut(String url, Object body, ParameterizedTypeReference<T> pTypeReference) {
        return exchange(url, HttpMethod.PUT, body, pTypeReference);
    }

    public <T> T exchange(String url, HttpMethod method, Object body, ParameterizedTypeReference<T> pTypeReference) {
        Gson gson = new Gson();
        String json = gson.toJson(body);
        logger.info("rest call with payload: {}", json);
        HttpEntity<Object> httpEntity = new HttpEntity<>(json, getHeaders());
        logger.info("rest call with :{Url: {}://{}:{}{}{}}", getSchema(), getHost(), getPort(), getContext(), url);
        try {
            ResponseEntity<T> responseEntity = restTemplate().exchange(MessageFormat.format(PATH_FORMAT, getSchema(), getHost(), getPort(), getContext(), url), method, httpEntity, pTypeReference);
            if (isStatusCodeOk(responseEntity.getStatusCode())) {
                return responseEntity.getBody();
            }
            if (isUnAuthorized(responseEntity.getStatusCode())) {
                throw new NoPermissionException(responseEntity.getBody().toString());
            }
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            logger.error(e.getMessage(), e);
            if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new InvalidParameterException(e.getMessage());
            } else if (e.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
                throw new ForbiddenException(e.getMessage());
            } else if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new NoPermissionException(e.getMessage());
            } else {
                throw new ServiceException("General Error Occurs!" + e.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException("General Error Occurs!" + e.getMessage());
        }
    }

    boolean isStatusCodeOk(HttpStatus httpStatus) {
        return httpStatus.is2xxSuccessful();
    }

    boolean isUnAuthorized(HttpStatus httpStatus) {
        return httpStatus.equals(HttpStatus.FORBIDDEN) || httpStatus.equals(HttpStatus.UNAUTHORIZED);
    }

    protected abstract RestTemplate restTemplate();

    protected abstract String getSchema();

    protected abstract String getHost();

    protected abstract String getPort();

    protected abstract String getContext();

    protected abstract HttpHeaders getHeaders();

}

