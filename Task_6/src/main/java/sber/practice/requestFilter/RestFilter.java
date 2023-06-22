package sber.practice.requestFilter;


import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@Component
public class RestFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(RestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("===== Начало обработки запроса =====");
        long time = System.currentTimeMillis();

        logger.info("Информация о запросе:");
        logger.info(String.format("ID запроса: %s", servletRequest.getRequestId()));
        logger.info(String.format("Контент: %s (%s bytes)", servletRequest.getContentType(),
                (servletRequest.getContentType() != null) ? servletRequest.getContentLengthLong() : "0"));
        logger.info(String.format("Кодировка: %s", servletRequest.getCharacterEncoding()));
        logger.info(String.format("Locale: %s", servletRequest.getLocale()));
        logger.info(String.format("Адрес: %s", servletRequest.getLocalAddr()));
        logger.info(String.format("Порт: %s", servletRequest.getLocalPort()));
        logger.info(String.format("Протокол: %s", servletRequest.getProtocol()));
        logger.info(String.format("Имя сервера: %s", servletRequest.getServerName()));
        logger.info(String.format("Используется защищённый канал: %s", (servletRequest.isSecure()) ? "да" : "нет"));

        Enumeration<String> params = servletRequest.getParameterNames();
        Map<String, String[]> paramsMap = servletRequest.getParameterMap();
        if (!params.hasMoreElements()) {
            logger.info("У запроса отсутствуют параметры");
        } else {
            logger.info("Параметры запроса: ");
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                logger.info(String.format("   '%s' = %s", param, Arrays.stream(paramsMap.get(param)).toList()));
            }
        }

        Enumeration<String> attributeNames =  servletRequest.getAttributeNames();
        if (!attributeNames.hasMoreElements()) {
            logger.info("У запроса отсутствуют атрибуты");
        } else {
            logger.info("Атрибуты запроса: ");
            while (attributeNames.hasMoreElements()) {
                String attribute = attributeNames.nextElement();
                logger.info(String.format("   %s = %s", attribute, servletRequest.getAttribute(attribute)));
            }
        }

        logger.info("Информация об ответе:");
        logger.info(String.format("Контент: %s", servletResponse.getContentType()));
        logger.info(String.format("Кодировка: %s", servletResponse.getCharacterEncoding()));
        logger.info(String.format("Locale: %s", servletResponse.getLocale()));

        filterChain.doFilter(servletRequest, servletResponse);
        logger.info(String.format("Запрос обработан. Примерное время выполнения: %d ms", System.currentTimeMillis() - time));
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
