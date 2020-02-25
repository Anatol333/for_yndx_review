package com.kozhukhar.carshop_online.web.filter;

import com.kozhukhar.carshop_online.util.gzip.GZipServletResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.net.HttpHeaders.*;

public class GZipFilter implements Filter {

    private static final String GZIP_NAME = "gzip";
    private static final String TEXT = "text";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (acceptsGZipEncoding(httpRequest) && isTypeText(httpRequest)) {
            httpResponse.addHeader(CONTENT_ENCODING, GZIP_NAME);
            GZipServletResponseWrapper gzipResponse =
                    new GZipServletResponseWrapper(httpResponse);
            chain.doFilter(request, gzipResponse);
            gzipResponse.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
        String acceptEncoding =
                httpRequest.getHeader(ACCEPT_ENCODING);

        return acceptEncoding != null && acceptEncoding.contains(GZIP_NAME);
    }

    private boolean isTypeText(HttpServletRequest request) {
        String accept = request.getHeader(ACCEPT);
        return accept != null && accept.contains(TEXT);
    }

}