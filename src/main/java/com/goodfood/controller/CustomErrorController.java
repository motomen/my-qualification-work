package com.goodfood.controller;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

/**
 * Created by Yaroslav on 08.04.2015.
 */
@Controller
@RequestMapping("/error")
class CustomErrorController {

    final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String customError(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model,
                              @RequestParam(value = "message",required = false) String message) {
        // retrieve some useful information from the request
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        // String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        message = (null == message)? MessageFormat.format("Ошибка {0} для {1} \" message {2} \" ",
                statusCode, requestUri, getExceptionMessage(throwable, statusCode))
                : message;

        model.addAttribute("errorMessage", message);
        logger.error(message);
        return "/error";
    }

    private String getExceptionMessage(Throwable throwable, Integer statusCode) {
        if (throwable != null) {
            return ExceptionUtils.getRootCauseMessage(throwable).toString();
        }
        return  getMessage(statusCode);
    }


    private String getMessage(int statusCode){
        switch (statusCode){
            case 400:
                return "Поганий запит";
            case 401:
                return "Відсутня Авторизація";
            case 402:
                return "потрібна сплата";
            case 403:
                return "заборонено";
            case 404:
                return "не знайдено";
            case 405:
                return "метод не підтримується";
            case 406:
                return "неприпустимо";
            case 407:
                return "проксі-сервер перевірки автентичності потрібно";
            case 408:
                return "Вичерпано час очікування";
            case 409:
                return "конфлікт";
            case 410:
                return "вилучений";
            case 411:
                return "необхідну довжину";
            case 412:
                return "умова є хибною";
            case 413:
                return "завеликий розмір запиту ";
            case 414:
                return "Запитаний ідентифікатор URI задовгий";
            case 415:
                return "непідтримуваного типу даних";
            case 416:
                return "Запитаний діапазон недоступний";
            case 417:
                return "бажане є неприйнятною";
            case 418:
                return "я - чайник";
            case 422:
                return "Non-aggregatable екземпляр";
            case 423:
                return "заблокований";
            case 424:
                return "відсутні залежності";
            case 425:
                return "неврегульовані набір";
            case 426:
                return "Необхідно оновити";
            case 428:
                return "необхідними передумовами";
            case 429:
                return "занадто багато запитів";
            case 431:
                return "поля заголовка запиту є занадто великими";
            case 434:
                return "Запитану адресу недоступний";
            case 449:
                return "Повторіть з";
            case 451:
                return "недоступний через правові питання";
            case 456:
                return "невиправлені помилка";
            case 499:
                return "невиправлені помилка";
            case 500:
                return "Внутрішня помилка сервера";
            case 501:
                return "не реалізовано";
            case 502:
                return "поганий, помилковий шлюз";
            case 503:
                return "Служба недоступна";
            case 504:
                return "шлюз не відповідає";
            case 505:
                return "HTTP-версії не підтримується";
            case 506:
                return "варіант також веде переговори про";
            case 507:
                return "переповнення зберігання";
            case 508:
                return "обнаружена петля";
            case 509:
                return "пропускна здатність вичерпана канал ширини";
            case 510:
                return "не розгорнутий";
            case 511:
                return "потребує автентифікації в мережі";
            default: return "";
        }
    }
}
