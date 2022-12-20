package org.cloudlabs_api.cloudlabs_place_service.services;

import org.cloudlabs_api.cloudlabs_place_service.configs.LocaleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;

@Service
public class LanguageService {

    @Autowired
    private LocaleConfig localeConfig;

    public String getLocale(HttpServletRequest request) {
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = localeConfig.localeResolver();
        return acceptHeaderLocaleResolver.resolveLocale(request).getLanguage();
    }

    public String getCode(String languageCode) {
        return languageCode.substring(0,2);
    }
}
