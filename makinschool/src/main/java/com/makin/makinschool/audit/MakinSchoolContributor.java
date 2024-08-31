package com.makin.makinschool.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MakinSchoolContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> makinMap = new HashMap<String, String>();
        makinMap.put("App Name", "MakinSchool");
        makinMap.put("App Version", "0.0.1");
        makinMap.put("App Description", "MakinSchool Web Application.");
        builder.withDetail("makinschool-info", makinMap);
    }
}
