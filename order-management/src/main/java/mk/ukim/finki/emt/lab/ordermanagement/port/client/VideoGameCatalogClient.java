package mk.ukim.finki.emt.lab.ordermanagement.port.client;

import mk.ukim.finki.emt.lab.ordermanagement.application.VideoGameCatalog;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.VideoGame;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.VideoGameId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
class VideoGameCatalogClient implements VideoGameCatalog {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoGameCatalogClient.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    VideoGameCatalogClient(@Value("${app.product-catalog.url}") String serverUrl,
                         @Value("${app.product-catalog.connect-timeout-ms}") int connectTimeout,
                         @Value("${app.product-catalog.read-timeout-ms}") int readTimeout) {
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        // Never ever do a remote call without a finite timeout!
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(serverUrl);
    }

    @Override
    public List<VideoGame> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/products").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<VideoGame>>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving products", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public VideoGame findById(VideoGameId id) {
        try {
            return restTemplate.exchange(uri().path("/api/products/"+id.getId()).build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<VideoGame>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving product by id", ex);
            return null;
        }
    }
}
