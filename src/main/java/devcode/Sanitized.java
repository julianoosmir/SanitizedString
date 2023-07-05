package devcode;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringEscapeUtils;


public class Sanitized {
    public String sanitizeUrl(String urlString) {
        try {
            URI uri = new URI(urlString);
            String sanitizedScheme = sanitizeComponent(uri.getScheme());
            String sanitizedHost = sanitizeComponent(uri.getHost());
            String sanitizedPath = sanitizeComponent(uri.getPath());
            String sanitizedQuery = sanitizeComponent(uri.getQuery());
            String sanitizedFragment = sanitizeComponent(uri.getFragment());
            StringBuilder sanitizedUrlBuilder = new StringBuilder();
            sanitizedUrlBuilder.append(sanitizedScheme).append("://");
            sanitizedUrlBuilder.append(sanitizedHost);
            sanitizedUrlBuilder.append(sanitizedPath);
            if (sanitizedQuery != null) {
                sanitizedUrlBuilder.append("?").append(sanitizedQuery);
            }
            if (sanitizedFragment != null) {
                sanitizedUrlBuilder.append("#").append(sanitizedFragment);
            }
            
            return sanitizedUrlBuilder.toString();
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    private String sanitizeComponent(String component) {
        if (component == null) {
            return null;
        }
        String sanitizedComponent = component.replaceAll("[^\\x20-\\x7E]", "");

        return StringEscapeUtils.escapeJava(sanitizedComponent);

    }
}