package com.lezord.app.mappers;

import ch.qos.logback.core.util.StringUtil;
import com.lezord.app.dto.request.ProfileUpdateRequestDTO;
import com.lezord.app.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {



    public void mergeUserInfo(User user, ProfileUpdateRequestDTO request) {
        if (StringUtils.isNotBlank(request.getFirstName()) && !user.getFirstName().equals(request.getFirstName())) user.setFirstName(request.getFirstName());
        if (StringUtils.isNotBlank(request.getLastName()) && !user.getLastName().equals(request.getLastName())) user.setLastName(request.getLastName());
        if (request.getDateOfBirth() != null && !request.getDateOfBirth().equals(user.getDateOfBirth())) user.setDateOfBirth(request.getDateOfBirth());
    }
}
